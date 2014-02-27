package com.akqa.andrew.dropwizardtest.services;

import com.akqa.andrew.dropwizardtest.core.City;
import com.akqa.andrew.dropwizardtest.dao.CityDAO;
import com.google.inject.Inject;

import java.util.List;

/**
 * Created by andrew.bao on 2/24/14.
 */
public class CityService {
    private final CityDAO cityDAO;

    @Inject
    public CityService(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    public List<City> getAllCities() {
        return cityDAO.getAllCities();
    }

    public City GetCityByID(int cityId) {
        City city = null;
        List<City> cities = getAllCities();
        int index = cities.indexOf(new City(cityId, ""));

        if (index >= 0) {
            city = cities.get(index);
        }

        return city;
    }
}
