package com.akqa.andrew.dropwizardtest.dao.fake;

import com.akqa.andrew.dropwizardtest.core.City;
import com.akqa.andrew.dropwizardtest.core.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew.bao on 2/19/14.
 */
public class StoreDAO implements com.akqa.andrew.dropwizardtest.dao.StoreDAO {

    private final static List<Store> stores = new ArrayList<Store>(10);

    public List<Store> getAllStores() {
        if (stores.size() <= 0) {
            stores.add(new Store(1, "andrew", new City(1, "Shanghai")));
        }

        return stores;
    }

    public void add(Store store) throws Exception {
        stores.add(store.getId(), store);
    }

    public void update(Store store) throws Exception {
        stores.remove(store);
        stores.add(store);
    }

    public void delete(Store store) {
        Integer storeId = new Integer(store.getId());

        stores.remove(storeId);
    }
}
