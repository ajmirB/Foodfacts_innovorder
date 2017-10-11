package com.busgeeth.foodfacts.features.home.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.busgeeth.foodfacts.R;
import com.busgeeth.foodfacts.core.model.entities.Product;
import com.busgeeth.foodfacts.features.commons.BaseActivity;
import com.busgeeth.foodfacts.features.home.fragments.ProductDetailFragment;
import com.busgeeth.foodfacts.features.home.fragments.ProductListFragment;
import com.busgeeth.foodfacts.features.home.presenters.HomePresenter;


public class HomeActivity extends BaseActivity implements HomePresenter.View, ProductListFragment.Listener {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private HomePresenter mPresenter;

    private ProductListFragment mProductListFragment;

    private ProductDetailFragment mProductDetailFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mProductListFragment = (ProductListFragment) getSupportFragmentManager().findFragmentById(R.id.home_food_fact_list_fragment);
        mProductDetailFragment = (ProductDetailFragment) getSupportFragmentManager().findFragmentById(R.id.home_food_fact_detail_fragment);

        mPresenter = new HomePresenter(this);

        final TextInputEditText searchInputEditText = (TextInputEditText) findViewById(R.id.home_search_text_input_edit_text);
        searchInputEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mPresenter.searchBarcordeClicked(searchInputEditText.getText().toString());
                return true;
            }
            return false;
        });
    }

    // region HomePresenter.View

    @Override
    public void alertError(int messageResId) {
        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void addProductInList(@NonNull Product product) {
        mProductListFragment.onNewProductInStore(product.getBarcode());
    }

    @Override
    public void showProductDetail(@NonNull Product product) {
        mProductDetailFragment.productToDisplay(product.getBarcode());
    }

    // endregion

    // region ProductListFragment.Listener

    @Override
    public void onProductClicked(Long barcodeNumber) {
        Log.d(TAG, "onProductClicked: " + barcodeNumber);
        mProductDetailFragment.productToDisplay(barcodeNumber);
    }

    // endregion
}
