package com.akqa.andrew.dropwizardtest.core;

/**
 * Created by andrew.bao on 2/21/14.
 */
public final class Result {
    private final String msg;
    private final String code;

    public Result(String message, String resultCode) {
        msg = message;
        this.code = resultCode;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getCode() {
        return this.code;
    }
}

