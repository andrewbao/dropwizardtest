package com.akqa.andrew.dropwizardtest.dao.mysql;

import com.akqa.andrew.dropwizardtest.core.Store;
import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

import java.lang.annotation.*;

/**
 * Created by andrew.bao on 2/25/14.
 */
@BindingAnnotation(BindStoreCity.StoreBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface BindStoreCity {

    public static class StoreBinderFactory implements BinderFactory {
        public Binder build(Annotation annotation) {
            return new Binder<BindStoreCity, Store>() {
                public void bind(SQLStatement q, BindStoreCity bind, Store arg) {
                    q.bind("cityId", arg.getCity().getId());
                }
            };
        }
    }
}