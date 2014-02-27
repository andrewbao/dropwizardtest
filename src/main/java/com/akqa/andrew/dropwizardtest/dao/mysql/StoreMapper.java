package com.akqa.andrew.dropwizardtest.dao.mysql;

import com.akqa.andrew.dropwizardtest.core.City;
import com.akqa.andrew.dropwizardtest.core.Store;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by andrew.bao on 2/25/14.
 */
public class StoreMapper implements ResultSetMapper<Store> {
    public Store map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Store(r.getInt("storeId"), r.getString("storeName"), new City(r.getInt("cityId"), r.getString("cityName")));
    }
}
