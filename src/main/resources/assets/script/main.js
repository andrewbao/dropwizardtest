function testAlert(){
    alert('test');
}

function addStore(){
    $.ajax({
      url: "test.html",
      context: document.body
    }).done(function() {
      $( this ).addClass( "done" );
    });
}