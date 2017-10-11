package com.busgeeth.foodfacts.features.home.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.busgeeth.foodfacts.R;
import com.busgeeth.foodfacts.features.commons.BaseFragment;
import com.busgeeth.foodfacts.features.home.adapters.ProductListAdapter;
import com.busgeeth.foodfacts.features.home.presenters.ProductListPresenter;

import java.util.List;


public class ProductListFragment extends BaseFragment implements ProductListPresenter.View {

    private ProductListPresenter mProductListPresenter;

    private Listener mProductListListener;

    private RecyclerView mRecyclerView;

    private List<ProductListPresenter.ProductItemData> mProductItemDataList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Listener) {
            mProductListListener = (Listener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_foodfact_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.product_list_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mProductListPresenter = new ProductListPresenter(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProductListPresenter.onViewCreated();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mProductListPresenter.onViewDestroyed();
    }

    public void onNewProductInStore(long barcode) {
        mProductListPresenter.onNewProductInStore(barcode);
    }

    // region ProductListPresenter.View

    @Override
    public void showContent(@NonNull ProductListPresenter.Data data) {
        mProductItemDataList = data.productItemsData;
        ProductListAdapter productListAdapter = new ProductListAdapter(mProductItemDataList);
        mRecyclerView.setAdapter(productListAdapter);
    }

    @Override
    public void addProductInList(@NonNull ProductListPresenter.ProductItemData productItemData) {
        mProductItemDataList.add(0, productItemData);
        mRecyclerView.getAdapter().notifyItemInserted(0);
    }

    @Override
    public void onProductClicked(long barcodeNumber) {
        if (mProductListListener != null) {
            mProductListListener.onProductClicked(barcodeNumber);
        }
    }

    // endregion

    public interface Listener {
        void onProductClicked(Long barcodeNumber);
    }
}
