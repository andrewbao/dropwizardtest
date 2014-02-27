package com.akqa.andrew.dropwizardtest.exceptions;

/**
 * Created by andrew.bao on 2/24/14.
 */
public class InvalidCityException extends Exception {

    private final int id;

    public InvalidCityException(int id) {
        super(String.format("The city, id %, doesn't exist.", id));
        this.id = id;
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
