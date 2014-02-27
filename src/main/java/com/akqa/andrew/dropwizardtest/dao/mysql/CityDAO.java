package com.akqa.andrew.dropwizardtest.dao.mysql;

import com.akqa.andrew.dropwizardtest.core.City;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by andrew.bao on 2/24/14.
 */
public abstract class CityDAO implements com.akqa.andrew.dropwizardtest.dao.CityDAO {

    @RegisterMapper(CityMapper.class)
    @SqlQuery("select id, name from City")
    public abstract List<City> getAllCities();
}
