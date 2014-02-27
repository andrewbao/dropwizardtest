package com.akqa.andrew.dropwizardtest.dao.mysql;

import com.akqa.andrew.dropwizardtest.core.Store;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.GetHandle;

import java.util.List;

/**
 * Created by andrew.bao on 2/24/14.
 */
public abstract class StoreDAO implements com.akqa.andrew.dropwizardtest.dao.StoreDAO, GetHandle {

    @RegisterMapper(StoreMapper.class)
    @SqlQuery("select s.id as storeId, s.name as storeName, s.cityId, c.name as cityName FROM Store as s inner join City as c on s.cityId = c.id")
    public abstract List<Store> getAllStores();

    // @SqlUpdate("insert into Store(id, name, cityId) values(:id, :name, :cityId)")
    //public abstract void add(@BindBean Store store) throws Exception;

    public void update(@BindBean Store store) throws Exception {
        Handle h;
        h = getHandle();
        try {

            h.createStatement("update store set name=:name, cityId=:cityId where id=:id")
                    .bind("id", store.getId())
                    .bind("name", store.getName())
                    .bind("cityId", store.getCity().getId())
                    .execute();
        } finally {
            h.close();
        }
    }

    @SqlUpdate("delete from store where id = :id")
    public abstract void delete(@BindBean Store store) throws Exception;

    public void add(@BindBean Store store) throws Exception {
        Handle h = getHandle();
        try {

            h.createStatement("insert into Store(id, name, cityId) values(:id, :name, :cityId)")
                    .bind("id", store.getId())
                    .bind("name", store.getName())
                    .bind("cityId", store.getCity().getId())
                    .execute();
        } finally {
            h.close();
        }
    }
}
