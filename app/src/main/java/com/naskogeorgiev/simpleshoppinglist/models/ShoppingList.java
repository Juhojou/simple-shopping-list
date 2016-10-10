package com.naskogeorgiev.simpleshoppinglist.models;


public class ShoppingList {
    private int id;
    private String title;
    private boolean completed;

    public ShoppingList(String title, boolean completed) {
        this.setTitle(title);
        this.setCompleted(completed);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getId() {
        return id;
    }
}
