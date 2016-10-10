package com.naskogeorgiev.simpleshoppinglist.adapters;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.naskogeorgiev.simpleshoppinglist.R;
import com.naskogeorgiev.simpleshoppinglist.interfaces.IListAdapterCallback;
import com.naskogeorgiev.simpleshoppinglist.interfaces.IRecycleViewSelectedElement;
import com.naskogeorgiev.simpleshoppinglist.models.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> mData;
    private static IRecycleViewSelectedElement mListener;
    private static IListAdapterCallback mLongPressCallback;

    public ProductAdapter(List<Product> data, IRecycleViewSelectedElement listener) {
        this.mData = data;
        mListener = listener;
        mLongPressCallback = (IListAdapterCallback)listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.tvProductTitle.setText(mData.get(position).getTitle());
        holder.tvProductQuantity.setText(String.valueOf(mData.get(position).getQuantity()));
        holder.chbProductFound.setChecked(mData.get(position).isFound());

        if(mData.get(position).isFound()) {
            holder.tvProductTitle.setPaintFlags(holder.tvProductTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvProductQuantity.setPaintFlags(holder.tvProductQuantity.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.tvProductTitle.setPaintFlags(0);
            holder.tvProductQuantity.setPaintFlags(0);
        }

        holder.setItemPosition(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_product_name)
        TextView tvProductTitle;

        @BindView(R.id.tv_product_quantity)
        TextView tvProductQuantity;

        @BindView(R.id.chb_product_found)
        CheckBox chbProductFound;

        private int position;

        ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            tvProductTitle.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.d("LONG PRESS","SUCCESS");
                    mLongPressCallback.onLongClicked();
                    return true;
                }
            });
            tvProductQuantity.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.d("LONG PRESS","SUCCESS");
                    mLongPressCallback.onLongClicked();
                    return true;
                }
            });

            chbProductFound.setOnClickListener(new View.OnClickListener() {
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
