package com.naskogeorgiev.simpleshoppinglist.activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

import com.naskogeorgiev.simpleshoppinglist.R;
import com.naskogeorgiev.simpleshoppinglist.adapters.ShoppingListAdapter;
import com.naskogeorgiev.simpleshoppinglist.fragments.CreateShoppingListDialogFragment;
import com.naskogeorgiev.simpleshoppinglist.interfaces.IDialogListener;
import com.naskogeorgiev.simpleshoppinglist.interfaces.IRecycleViewSelectedElement;
import com.naskogeorgiev.simpleshoppinglist.interfaces.ShoppingListAPI;
import com.naskogeorgiev.simpleshoppinglist.models.ShoppingList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements IRecycleViewSelectedElement, IDialogListener {

    private final String TAG = getClass().getSimpleName();

    ShoppingListAPI api;
    List<ShoppingList> shoppingLists;
    ShoppingListAdapter mAdapter;

    @BindView(R.id.btn_create_list)
    Button mBtnCreateList;

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        api = ShoppingListAPI.retrofit.create(ShoppingListAPI.class);

        Call<List<ShoppingList>> call = api.getShoppingLists();
        call.enqueue(new Callback<List<ShoppingList>>() {
            @Override
            public void onResponse(Call<List<ShoppingList>> call, Response<List<ShoppingList>> response) {
                shoppingLists = response.body();
                initializeRecyclerView(shoppingLists);
            }

            @Override
            public void onFailure(Call<List<ShoppingList>> call, Throwable t) {
                Log.e(TAG, "Failed to get shopping lists!");
            }
        });
    }

    private void initializeRecyclerView(List<ShoppingList> data) {
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_shopping_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ShoppingListAdapter(data, MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemSelected(int position) {
        int listIndex = shoppingLists.get(position).getId();
        Intent i = new Intent(MainActivity.this, ListActivity.class);
        i.putExtra("Index", listIndex);
        startActivity(i);
    }

    @OnClick(R.id.btn_create_list)
    void openCreateListDialog() {
        CreateShoppingListDialogFragment dialog = new CreateShoppingListDialogFragment();
        dialog.show(getFragmentManager(), "CreateShoppingListDialogFragment");
    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        EditText etTitle = (EditText) dialog.getDialog().findViewById(R.id.et_shopping_list_title);
        String title = etTitle.getText().toString();
        if (title.length() < 1) {
            etTitle.setError(getString(R.string.title_required));
        } else {

            ShoppingList shoppingList = new ShoppingList(title, false);
            Call<ShoppingList> call = api.createShoppingList(shoppingList);
            call.enqueue(new Callback<ShoppingList>() {
                @Override
                public void onResponse(Call<ShoppingList> call, Response<ShoppingList> response) {
                    shoppingLists.add(response.body());
                    mAdapter.notifyItemInserted(shoppingLists.size());
                }

                @Override
                public void onFailure(Call<ShoppingList> call, Throwable t) {
                    Log.e(TAG, "Failed to create new shopping list!");
                }
            });
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
