package com.akqa.andrew.dropwizardtest.health;

import com.yammer.metrics.core.HealthCheck;

/**
 * Created by andrew.bao on 2/19/14.
 */
public class DAOHealthCheck extends HealthCheck {
    private String name;

    public DAOHealthCheck(String name) {
        super(name);
        this.name = name;
    }


    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
