package com.naskogeorgiev.simpleshoppinglist.models;

import com.orm.SugarRecord;

import java.util.List;


public class ShoppingList extends SugarRecord {
    private String title;
    private List<ShoppingItem> items;

    public ShoppingList() {

    }

    public ShoppingList(String title, List<ShoppingItem> items) {
        this.setTitle(title);
        this.setItems(items);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ShoppingItem> getItems() {
        return ShoppingItem.find(ShoppingItem.class, "listId = ?", String.valueOf(getId()));

    }

    public void setItems(List<ShoppingItem> items) {
        this.items = items;
    }
}
