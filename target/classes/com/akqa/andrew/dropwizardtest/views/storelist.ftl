<#-- @ftlvariable name="" type="com.akqa.andrew.dropwizardtest.views.StoreListView" -->
    <html>
    <head>
        <link rel="stylesheet" type="text/css" href="/style/main.css"/>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
    </head>
    <body>
    <header class="headroom">

    </header>
    <strong><h1>Welcome 2 Dropwizard Store</h1></strong>
    <input id="txtSearch" type="text" class="input-large search-query">
    <button id="btnSearch" type="submit" class="btn-sm btn-primary">Search</button>
    <table border="1" class="table table-striped table-bordered table-hover">
        <tr>
            <td>ID</td>
            <td>Name</td>
            <td>City</td>
            <td>Action</td>
        </tr>
        <#list getStores() as store>
            <tr class="storerow">
                <td>${store.getId()}</td>
                <td>${store.getName()}</td>
                <td class="actionCell">
                    <select class="selStoreCity">
                        <#list getCities() as city>
                            <#if city.getId() == store.getCity().getId() >
                                <option value="${city.getId()}" selected>${city.getName()}</option>
                                <#else>
                                    <option value="${city.getId()}">${city.getName()}</option>
                            </#if>
                        </#list>
                    </select>
                </td>
                <td>
                    <div><a href="#" class="btnUpdate btn-sm btn-success">Update</a> <a href="#" class="btnDelete btn-sm btn-danger">Delete</a></div>
                </td>
            </tr>
        </#list>
        <tr id="lastRow">
            <td><input type="text" class="txtId"/></td>
            <td><input type="text" class="txtName"/></td>
            <td>
                <select class="selCity">
                    <#list getCities() as city>
                        <option value="${city.getId()}">${city.getName()}</option>
                    </#list>
                </select>
            </td>
            <td><a id="btnAdd" href="#" class="btn-sm btn-success">Add</a></td>
        </tr>
        <tr id="hiddenRow" style="display:none;">
            <td></td>
            <td></td>
            <td class="actionCell">
                <select class="selCity">
                    <#list getCities() as city>
                        <option value="${city.getId()}">${city.getName()}</option>
                    </#list>
                </select>
            </td>
            <td>
                <div><a href="#" class="btnUpdate btn-sm btn-success">Update</a> <a href="#" class="btnDelete btn-sm btn-danger">Delete</a></div>
            </td>
        </tr>
    </table>
    </body>
    <script src="/script/main.js"></script>
    <script src="/script/headroom.min.js"></script>
    <script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/knockout/3.0.0/knockout-min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <script>
    $(document).ready(function() {

        $("#btnAdd").on("click", function(event){
            var row = $(this).parents("tr:first");
            var id = row.find(".txtId").val();
            var name = row.find(".txtName").val();
            var cityId = row.find(".selCity").val();

            // get service to add store
            $.post( "/storeapi/add", { id: id, name: name, cityId: cityId })
              .done(function( data ) {
                if(data.code == "Succeed"){
                    addNewStore(id, name, cityId);
                }
                else{
                    alert("Add new store failed.");
                }
              });

            row.find(".txtId").val("");
            row.find(".txtName").val("");
            row.find(".selCity").val("");
        });

        $("#btnSearch").on("click", function(event){
            search();
        });

        $(".actionCell").on("click", ".btnDelete", function(event){
            var row = $(this).parents("tr:first");
            var id = row.children("td:first").text();
            $.post( "/storeapi/delete", { id: id })
              .done(function( data ) {
                if(data.code == "Succeed"){
                    row.remove();
                }
                else{
                    alert("Delete store failed.");
                }
              });
        });

        function addNewStore(id, name, cityId){
            var newRow = $("#hiddenRow").clone();
            newRow.removeAttr("style");
            newRow.removeAttr("id");
            newRow.addClass("storerow");

            newRow.find("td").eq(0).text(id);
            newRow.find("td").eq(1).text(name);
            newRow.find(".selStoreCity").val(cityId);

            newRow.insertBefore($("#lastRow"));
        }

        function refreshStores(stores){
            $(".storerow").remove();

            $.each(stores, function(i, val){
                addNewStore(val.id, val.name, val.city.id);
            });
        }

        function search(){
            var searchName = $("#txtSearch").val();

            // get service to add store
            $.post( "/storeapi/search", { name: searchName })
              .done(function( data ) {
                refreshStores(data.pageData);
              });
        }

    });


    </script>
    </html>