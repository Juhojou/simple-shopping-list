package com.naskogeorgiev.simpleshoppinglist;

import android.app.Application;

import com.naskogeorgiev.simpleshoppinglist.models.ShoppingItem;
import com.naskogeorgiev.simpleshoppinglist.models.ShoppingList;
import com.orm.SugarApp;
import com.orm.SugarContext;
import com.orm.SugarDb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ShoppingApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SugarDb sugarDb = new SugarDb(getApplicationContext());
        new File(sugarDb.getDB().getPath()).delete();

        SugarContext.init(getApplicationContext());

        ShoppingList.findById(ShoppingList.class, (long) 1);
        ShoppingItem.findById(ShoppingItem.class, (long) 1);

        initDatabase();
    }

    private void initDatabase() {
        ShoppingList shoppingList = new ShoppingList("Android List 1", new ArrayList<ShoppingItem>());
        List<ShoppingItem> items = new ArrayList<>();
        ShoppingItem item = new ShoppingItem("Milk", 1, false, shoppingList);
        item.save();
        items.add(item);

        shoppingList.setItems(items);
        shoppingList.save();
    }
}
