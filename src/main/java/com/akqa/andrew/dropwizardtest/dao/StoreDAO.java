package com.akqa.andrew.dropwizardtest.dao;

import com.akqa.andrew.dropwizardtest.core.Store;

import java.util.List;

/**
 * Created by andrew.bao on 2/19/14.
 */
public interface StoreDAO {
    List<Store> getAllStores();

    void add(Store store) throws Exception;

    void update(Store store) throws Exception;

    void delete(Store store) throws Exception;
}
