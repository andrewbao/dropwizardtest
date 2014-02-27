package com.akqa.andrew.dropwizardtest.dao.hibernate;

import com.akqa.andrew.dropwizardtest.core.City;
import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by andrew.bao on 2/26/14.
 */
public class CityDAO extends AbstractDAO<City> implements com.akqa.andrew.dropwizardtest.dao.CityDAO {
    public CityDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<City> getAllCities() {
        return list(namedQuery("com.akqa.andrew.dropwizardtest.core.City.getAllStores"));
    }
}
