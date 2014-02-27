package com.akqa.andrew.dropwizardtest.views;

import com.akqa.andrew.dropwizardtest.core.City;
import com.akqa.andrew.dropwizardtest.core.Store;
import com.yammer.dropwizard.views.View;

import java.util.List;

/**
 * Created by andrew.bao on 2/19/14.
 */
public class StoreListView extends View {

    private final String templateName;
    private final List<Store> stores;
    private final List<City> cities;

    public StoreListView(String templateName, List<Store> stores, List<City> cities) {
        // super("storelist.ftl");
        super(templateName);
        this.templateName = templateName;
        this.stores = stores;
        this.cities = cities;

    }

    public List<Store> getStores() {
        return this.stores;
    }

    public List<City> getCities() {
        return this.cities;
    }
}
