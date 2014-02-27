package com.akqa.andrew.dropwizardtest.dao.mysql;

import com.akqa.andrew.dropwizardtest.core.City;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by andrew.bao on 2/25/14.
 */
public class CityMapper implements ResultSetMapper<City> {
    public City map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new City(r.getInt("id"), r.getString("name"));
    }
}