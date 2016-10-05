package com.naskogeorgiev.simpleshoppinglist.activities;

import android.app.DialogFragment;
import android.os.Bundle;
import android.preference.EditTextPreference;
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

import com.naskogeorgiev.simpleshoppinglist.CreateItemDialogFragment;
import com.naskogeorgiev.simpleshoppinglist.IRecycleViewSelectedElement;
import com.naskogeorgiev.simpleshoppinglist.R;
import com.naskogeorgiev.simpleshoppinglist.ShoppingListAdapter;
import com.naskogeorgiev.simpleshoppinglist.SimpleDividerItemDecoration;
import com.naskogeorgiev.simpleshoppinglist.models.ShoppingItem;
import com.naskogeorgiev.simpleshoppinglist.models.ShoppingList;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements IRecycleViewSelectedElement, CreateItemDialogFragment.DialogListener{

    private RecyclerView mRecyclerView;
    private ShoppingListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ShoppingItem> data;

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
                CreateItemDialogFragment dialog = new CreateItemDialogFragment();
                dialog.show(getFragmentManager(), "CreateItemDialog");
            }
        });

        data = new ArrayList<>();
        generateData();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_shopping_list);
        mLayoutManager = new LinearLayoutManager(ListActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ShoppingListAdapter(data, ListActivity.this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(ListActivity.this));
    }

    private void generateData() {
//        data.add(0, new ShoppingItem("Milk", 3, false));
//        data.add(1, new ShoppingItem("Coffee", 3, true));
//        data.add(2, new ShoppingItem("Cola", 3, true));
//        data.add(3, new ShoppingItem("Onions", 3, false));
//        data.add(4, new ShoppingItem("Meat", 3, false));
//        data.add(5, new ShoppingItem("Glass", 3, true));
//        data.add(6, new ShoppingItem("Tendjera", 14, true));
//        data.add(7, new ShoppingItem("Tigan", 33, true));
//        data.add(8, new ShoppingItem("Ice Cream", 23, true));
//        data.add(9, new ShoppingItem("Моркови", 13, true));
//        data.add(10, new ShoppingItem("Milk", 3, false));
//        data.add(11, new ShoppingItem("Coffee", 3, true));
//        data.add(12, new ShoppingItem("Cola", 3, true));
//        data.add(13, new ShoppingItem("Onions", 3, false));
//        data.add(14, new ShoppingItem("Meat", 3, false));
//        data.add(15, new ShoppingItem("Glass", 3, true));
//        data.add(16, new ShoppingItem("Tendjera", 14, true));
//        data.add(17, new ShoppingItem("Tigan", 33, true));
//        data.add(18, new ShoppingItem("Ice Cream", 23, true));
//        data.add(19, new ShoppingItem("Моркови", 13, true));
//        data.add(20, new ShoppingItem("Milk", 3, false));
//        data.add(21, new ShoppingItem("Coffee", 3, true));
//        data.add(22, new ShoppingItem("Cola", 3, true));
//        data.add(23, new ShoppingItem("Onions", 3, false));
//        data.add(24, new ShoppingItem("Meat", 3, false));
//        data.add(25, new ShoppingItem("Glass", 3, true));
//        data.add(26, new ShoppingItem("Tendjera", 14, true));
//        data.add(27, new ShoppingItem("Tigan", 33, true));
//        data.add(28, new ShoppingItem("Ice Cream", 23, true));
//        data.add(29, new ShoppingItem("Моркови", 13, true));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckboxSelected(int position) {
        if(data.size() >= position) {
            data.get(position).setFound(!data.get(position).isFound());
            mAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        EditText view = (EditText)dialog.getDialog().findViewById(R.id.et_product_name);
        String name = view.getText().toString();
        view = (EditText)dialog.getDialog().findViewById(R.id.et_product_amount);
        int amount = Integer.parseInt(view.getText().toString());

        int index = data.size();
        data.add(index, new ShoppingItem(name, amount, false, ShoppingList.findById(ShoppingList.class, (long) 1)));
        mAdapter.notifyItemInserted(index);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
    }
}
