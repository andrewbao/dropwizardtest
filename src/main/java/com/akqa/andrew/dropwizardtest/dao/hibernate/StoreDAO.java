package com.akqa.andrew.dropwizardtest.dao.hibernate;

import com.akqa.andrew.dropwizardtest.core.Store;
import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by andrew.bao on 2/26/14.
 */
public class StoreDAO extends AbstractDAO<Store> implements com.akqa.andrew.dropwizardtest.dao.StoreDAO {
    public StoreDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Store> getAllStores() {
        return list(namedQuery("com.akqa.andrew.dropwizardtest.core.Store.getAllStores"));
    }

    @Override
    public void add(Store store) throws Exception {
        persist(store);
    }

    @Override
    public void update(Store store) throws Exception {
        update(store);
    }

    @Override
    public void delete(Store store) throws Exception {
        delete(store);
    }
}
