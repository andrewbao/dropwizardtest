package com.akqa.andrew.dropwizardtest.services;

import com.akqa.andrew.dropwizardtest.core.Store;
import com.akqa.andrew.dropwizardtest.dao.CityDAO;
import com.akqa.andrew.dropwizardtest.dao.StoreDAO;
import com.google.inject.Inject;

import java.util.List;

/**
 * Created by andrew.bao on 2/24/14.
 */
public class StoreService {
    private final CityDAO cityDAO;
    private final StoreDAO storeDAO;

    @Inject
    public StoreService(CityDAO cityDAO, StoreDAO storeDAO) {
        this.cityDAO = cityDAO;
        this.storeDAO = storeDAO;
    }

    public List<Store> getAllStores() {
        return storeDAO.getAllStores();
    }

    public void add(Store store) throws Exception {
        if (getStoreByID(store.getId()) != null) {
            throw new Exception(String.format("Store with same id % already exists.", store.getId()));
        }

        this.storeDAO.add(store);
    }

    public void update(Store store) throws Exception {
        storeDAO.update(store);
    }

    public void delete(Store store) throws Exception {
        storeDAO.delete(store);
    }

    public Store getStoreByID(int storeId) {
        Store store = null;
        List<Store> cities = getAllStores();
        int index = cities.indexOf(new Store(storeId, "", null));

        if (index >= 0) {
            store = cities.get(index);
        }

        return store;
    }
}
