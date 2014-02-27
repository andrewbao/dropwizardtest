package com.akqa.andrew.dropwizardtest.resources;

import com.akqa.andrew.dropwizardtest.core.*;
import com.akqa.andrew.dropwizardtest.services.CityService;
import com.akqa.andrew.dropwizardtest.services.StoreService;
import com.google.inject.Inject;
import com.sun.jersey.spi.resource.Singleton;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew.bao on 2/19/14.
 */
@Path("/storeapi")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
public class StoreAPIResource {

    private final StoreService storeService;
    private final CityService cityService;
    private static final int PAGE_SIZE = 10;

    @Inject
    public StoreAPIResource(final StoreService storeService, final CityService cityService) {
        this.storeService = storeService;
        this.cityService = cityService;
    }

    @POST
    @Path("/add")
    public Result add(@FormParam("id") int id, @FormParam("name") String name, @FormParam("cityId") int cityId) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The param 'name' cannot be empty.");
        }

        Result result;
        try {
            City city = cityService.GetCityByID(cityId);

            if (city == null) {
                throw new Exception(String.format("City % doesn't exist.", cityId));
            }

            storeService.add(new Store(id, name, city));
            result = new Result("", ResultCode.Succeed.toString());
        } catch (Exception ex) {
            result = new Result(ex.getMessage(), ResultCode.Failed.toString());
        }
        return result;
    }

    @POST
    @Path("/update")
    public Result update(@FormParam("id") Integer id, @FormParam("name") String name, @FormParam("city") Integer cityId) {
        Result result;
        try {
            City city = cityService.GetCityByID(cityId);
            if (city == null) {
                result = new Result(String.format("The city % doesn't exists.", cityId), ResultCode.Failed.toString());
            } else {
                storeService.update(new Store(id, name, city));
                result = new Result("", ResultCode.Succeed.toString());
            }
        } catch (Exception ex) {
            result = new Result(ex.getMessage(), ResultCode.Failed.toString());
        }
        return result;
    }

    @POST
    @Path("/delete")
    public Result delete(@FormParam("id") int id) {
        Result result;
        try {
            storeService.delete(new Store(id, "", null /*TODO need to put city here */));
            result = new Result("", ResultCode.Succeed.toString());
        } catch (Exception ex) {
            result = new Result(ex.getMessage(), ResultCode.Failed.toString());
        }
        return result;
    }

    @POST
    @Path("/search")
    public PagingData<Store> search(@FormParam("name") String name) {

        ArrayList<Store> matchedStores = new ArrayList<Store>(PAGE_SIZE);
        PagingData<Store> pagingStores = new PagingData<Store>(0, matchedStores);

        if (!StringUtils.isBlank(name)) {

            List<Store> stores = storeService.getAllStores();

            for (int i = 0; i < stores.size(); i++) {
                Store store = stores.get(i);
                if (store.getName().contains(name)) {
                    matchedStores.add(store);
                }
            }

            pagingStores = new PagingData<Store>(stores.size(), matchedStores);
        }

        return pagingStores;
    }
}
