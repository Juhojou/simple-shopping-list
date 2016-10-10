package com.naskogeorgiev.simpleshoppinglist.interfaces;

import com.naskogeorgiev.simpleshoppinglist.models.Product;
import com.naskogeorgiev.simpleshoppinglist.models.ShoppingList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ShoppingListAPI {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://sslist-api.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("/shopping_lists")
    Call<List<ShoppingList>> getShoppingLists();

    @POST("/shopping_lists")
    Call<ShoppingList> createShoppingList(@Body ShoppingList shoppingList);

    @GET("/shopping_lists/{id}/products")
    Call<List<Product>> getProducts(@Path("id") int id);

    @POST("/products")
    Call<Product> createProduct(@Body Product product);

    @PUT("/products/{id}")
    Call<Product> updateProduct(@Path("id") int id, @Body Product product);
}
