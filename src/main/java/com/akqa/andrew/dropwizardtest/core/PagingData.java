package com.akqa.andrew.dropwizardtest.core;

import java.util.ArrayList;

/**
 * Created by andrew.bao on 2/24/14.
 */
public final class PagingData<T> {
    private int totalAmount;
    private ArrayList<T> pageData;

    public PagingData(int totalAmount, ArrayList<T> pageData) {
        this.totalAmount = totalAmount;
        this.pageData = pageData;
    }

    public int getTotalAmount() {
        return this.totalAmount;
    }

    public ArrayList<T> getPageData() {
        return this.pageData;
    }
}
