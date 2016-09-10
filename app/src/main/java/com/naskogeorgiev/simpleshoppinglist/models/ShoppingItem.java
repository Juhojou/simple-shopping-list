package com.naskogeorgiev.simpleshoppinglist.models;

/**
 * Created by nasko.georgiev on 10.9.2016 г..
 */

public class ShoppingItem {
    private String mName;

    public ShoppingItem(String mName, float mAmount, boolean mFound) {
        this.mName = mName;
        this.mAmount = mAmount;
        this.mFound = mFound;
    }

    private float mAmount;
    private boolean mFound;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public float getAmount() {
        return mAmount;
    }

    public void setAmount(float mAmount) {
        this.mAmount = mAmount;
    }

    public boolean isFound() {
        return mFound;
    }

    public void setFound(boolean mFound) {
        this.mFound = mFound;
    }
}
