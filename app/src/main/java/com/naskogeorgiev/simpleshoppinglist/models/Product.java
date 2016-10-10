package com.naskogeorgiev.simpleshoppinglist.models;

public class Product {
    private int id;
    private String title;
    private float quantity;
    private boolean found;
    private int shopping_list_id;

    public Product(String title, float quantity, boolean found, int listId) {
        this.setTitle(title);
        this.setQuantity(quantity);
        this.setFound(found);
        this.setListId(listId);
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
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public int getListId() {
        return shopping_list_id;
    }

    public void setListId(int listId) {
        this.shopping_list_id = listId;
    }

    public int getId() {return id;}
}
