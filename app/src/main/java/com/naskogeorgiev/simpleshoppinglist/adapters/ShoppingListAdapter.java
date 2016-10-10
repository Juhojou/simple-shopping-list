package com.naskogeorgiev.simpleshoppinglist.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.naskogeorgiev.simpleshoppinglist.R;
import com.naskogeorgiev.simpleshoppinglist.interfaces.IRecycleViewSelectedElement;
import com.naskogeorgiev.simpleshoppinglist.models.ShoppingList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder> {

    private List<ShoppingList> mData;
    private static IRecycleViewSelectedElement mListener;

    public ShoppingListAdapter(List<ShoppingList> data, IRecycleViewSelectedElement listener) {
        mData = data;
        mListener = listener;
    }

    @Override
    public ShoppingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_shopping_list, parent, false);
        return new ShoppingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShoppingListViewHolder holder, int position) {
        holder.tvShoppingListTitle.setText(mData.get(position).getTitle());
        holder.chbComplete.setChecked(mData.get(position).isCompleted());
        holder.setItemPosition(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ShoppingListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_shopping_list_title)
        TextView tvShoppingListTitle;

        @BindView(R.id.chb_shopping_list_completed)
        CheckBox chbComplete;

        @BindView(R.id.card_view_shopping_list)
        CardView cardView;

        int position;

        ShoppingListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            chbComplete.setEnabled(false);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemSelected(position);
                }
            });
        }

        void setItemPosition(int position) {
            this.position = position;
        }
    }
}
