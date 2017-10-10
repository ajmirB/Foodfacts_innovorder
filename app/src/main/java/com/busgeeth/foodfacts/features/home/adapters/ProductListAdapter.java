package com.busgeeth.foodfacts.features.home.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busgeeth.foodfacts.R;
import com.busgeeth.foodfacts.features.home.presenters.ProductListPresenter;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private List<ProductListPresenter.ProductItemData> mProductItemDataList;

    public ProductListAdapter(@NonNull List<ProductListPresenter.ProductItemData> productItemDataList) {
        mProductItemDataList = productItemDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductListPresenter.ProductItemData productItemData = mProductItemDataList.get(position);
        holder.titleTextView.setText(productItemData.productName);
        holder.barcodeTextView.setText(String.format("%s", productItemData.barcode));
        holder.itemView.setOnClickListener(productItemData.onClickListener);
    }

    @Override
    public int getItemCount() {
        return mProductItemDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;

        TextView barcodeTextView;

        ViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.product_item_title_text_view);
            barcodeTextView = (TextView) itemView.findViewById(R.id.product_item_barcode_text_view);
        }
    }
}
