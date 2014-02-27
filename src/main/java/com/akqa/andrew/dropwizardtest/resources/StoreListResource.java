package com.akqa.andrew.dropwizardtest.resources;

import com.akqa.andrew.dropwizardtest.services.CityService;
import com.akqa.andrew.dropwizardtest.services.StoreService;
import com.akqa.andrew.dropwizardtest.views.StoreListView;
import com.google.inject.Inject;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by andrew.bao on 2/19/14.
 */
@Path("/stores")
@Produces(MediaType.TEXT_HTML)
public class StoreListResource {

    private final StoreService storeService;
    private final CityService cityService;

    @Inject
    public StoreListResource(final StoreService storeService, final CityService cityService) {
        this.storeService = storeService;
        this.cityService = cityService;
    }

    @GET
    @Timed
    @UnitOfWork
    public StoreListView stores() {
        return new StoreListView("storelist.ftl",
                storeService.getAllStores(),
                cityService.getAllCities()
        );
    }

}
