package com.naskogeorgiev.simpleshoppinglist.activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.naskogeorgiev.simpleshoppinglist.fragments.CreateProductDialogFragment;
import com.naskogeorgiev.simpleshoppinglist.interfaces.IRecycleViewSelectedElement;
import com.naskogeorgiev.simpleshoppinglist.R;
import com.naskogeorgiev.simpleshoppinglist.adapters.ProductAdapter;
import com.naskogeorgiev.simpleshoppinglist.SimpleDividerItemDecoration;
import com.naskogeorgiev.simpleshoppinglist.interfaces.ShoppingListAPI;
import com.naskogeorgiev.simpleshoppinglist.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity implements IRecycleViewSelectedElement, CreateProductDialogFragment.DialogListener {

    private RecyclerView mRecyclerView;
    private ProductAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Product> products;

    int listId;
    ShoppingListAPI api;

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            Log.e(TAG, "Unable to set up navigation!");
            Log.e(TAG, e.getMessage());
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateProductDialogFragment dialog = new CreateProductDialogFragment();
                dialog.show(getFragmentManager(), "CreateItemDialog");
            }
        });

        Intent i = getIntent();
        if (i.hasExtra("Index")) {
            listId = i.getIntExtra("Index", -1);
        }


        api = ShoppingListAPI.retrofit.create(ShoppingListAPI.class);
        Call<List<Product>> call = api.getProducts(listId);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                mRecyclerView = (RecyclerView) findViewById(R.id.recycler_product);
                mLayoutManager = new LinearLayoutManager(ListActivity.this);
                mRecyclerView.setLayoutManager(mLayoutManager);

                mAdapter = new ProductAdapter(products, ListActivity.this);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(ListActivity.this));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(final int position) {
        if (products.size() >= position) {
            Product product = products.get(position);
            product.setFound(!product.isFound());

            final Call<Product> call = api.updateProduct(product.getId(), product);
            call.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    if(response.body() != null)
                        mAdapter.notifyItemChanged(position);
                    else {
                        Toast.makeText(getApplicationContext(), "Response code: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {

                }
            });

        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        EditText view = (EditText) dialog.getDialog().findViewById(R.id.et_product_title);
        String name = view.getText().toString();
        if (name.length() == 0 || name.equals(" ") || name.equals("\n")) {
            Toast.makeText(ListActivity.this, "Please give a product!", Toast.LENGTH_SHORT).show();
        } else {
            view = (EditText) dialog.getDialog().findViewById(R.id.et_product_quantity);
            float quantity = Float.parseFloat(view.getText().toString());

            final Product product = new Product(name, quantity, false, listId);
            Call<Product> call = api.createProduct(product);
            call.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    products.add(product);
                    mAdapter.notifyItemInserted(products.size());
                }


                @Override
                public void onFailure(Call<Product> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
    }
}
