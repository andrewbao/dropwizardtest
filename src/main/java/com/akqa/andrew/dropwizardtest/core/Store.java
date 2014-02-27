package com.akqa.andrew.dropwizardtest.core;

import javax.persistence.*;

/**
 * Created by andrew.bao on 2/19/14.
 */

public final class Store {
    private final int id;

    private final String name;

    private final City city;

    public Store(int id, String name, City city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public City getCity() {
        return city;
    }

    @Override
    public String toString() {
        return String.format("Store[id:%, name:%]", id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Store)) {
            return false;
        }

        return (this.id == ((Store) obj).getId());
    }

}
