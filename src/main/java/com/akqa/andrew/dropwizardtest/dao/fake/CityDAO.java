package com.akqa.andrew.dropwizardtest.dao.fake;

import com.akqa.andrew.dropwizardtest.core.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew.bao on 2/19/14.
 */
public class CityDAO implements com.akqa.andrew.dropwizardtest.dao.CityDAO {

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<City>(10);

        cities.add(new City(1, "Shanghai"));
        cities.add(new City(2, "Beijing"));
        cities.add(new City(3, "Guangzhou"));
        cities.add(new City(4, "Shenzheng"));

        return cities;
    }
}
