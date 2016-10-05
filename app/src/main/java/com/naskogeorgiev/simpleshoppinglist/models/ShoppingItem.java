package com.naskogeorgiev.simpleshoppinglist.models;

import com.orm.SugarRecord;

public class ShoppingItem extends SugarRecord {
    private String title;
    private float quantity;
    private boolean isFound;
    private ShoppingList list;

    public ShoppingItem()
    {

    }

    public ShoppingItem(String title, float quantity, boolean isFound, ShoppingList list) {
        this.setTitle(title);
        this.setQuantity(quantity);
        this.setFound(isFound);
        this.setList(list);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public boolean isFound() {
        return isFound;
    }

    public void setFound(boolean found) {
        isFound = found;
    }

    public ShoppingList getList() {
        return list;
    }

    public void setList(ShoppingList list) {
        this.list = list;
    }
}
