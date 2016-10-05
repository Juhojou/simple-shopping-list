package com.naskogeorgiev.simpleshoppinglist;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.naskogeorgiev.simpleshoppinglist.models.ShoppingItem;

import java.util.ArrayList;

/**
 * Created by nasko.georgiev on 10.9.2016 г..
 */

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingViewHolder> {

    private ArrayList<ShoppingItem> mData;
    static IRecycleViewSelectedElement mListener;

    public ShoppingListAdapter(ArrayList<ShoppingItem> data, IRecycleViewSelectedElement listener) {
        this.mData = data;
        this.mListener = listener;
    }

    @Override
    public ShoppingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_shopping_item, parent, false);
        return new ShoppingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShoppingViewHolder holder, int position) {
        holder.tvName.setText(mData.get(position).getTitle());
        holder.tvAmount.setText(String.valueOf(mData.get(position).getQuantity()));
        holder.chbFound.setChecked(mData.get(position).isFound());

        if(mData.get(position).isFound()) {
            holder.tvName.setPaintFlags(holder.tvName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvAmount.setPaintFlags(holder.tvAmount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.tvName.setPaintFlags(0);
            holder.tvAmount.setPaintFlags(0);
        }

        holder.setItemPosition(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ShoppingViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvAmount;
        private CheckBox chbFound;
        private int position;

        public ShoppingViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvAmount = (TextView) itemView.findViewById(R.id.tv_amount);
            chbFound = (CheckBox) itemView.findViewById(R.id.chb_found);

            chbFound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onCheckboxSelected(position);
                }
            });
        }

        public void setItemPosition(int position) {
            this.position = position;
        }
    }
}
