package com.akqa.andrew.dropwizardtest.core;

/**
 * Created by andrew.bao on 2/19/14.
 */
public final class City {
    private final int id;
    private final String name;

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof City)) {
            return false;
        }

        return (this.id == ((City) obj).getId());
    }
}
