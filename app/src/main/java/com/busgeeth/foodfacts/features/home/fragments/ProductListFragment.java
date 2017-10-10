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


public class ProductListFragment extends BaseFragment implements ProductListPresenter.View {

    ProductListPresenter mProductListPresenter;

    Listener mProductListListener;

    RecyclerView mRecyclerView;

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

    // region ProductListPresenter.View

    @Override
    public void showContent(@NonNull ProductListPresenter.Data data) {
        ProductListAdapter productListAdapter = new ProductListAdapter(data.productItemsData);
        mRecyclerView.setAdapter(productListAdapter);
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
