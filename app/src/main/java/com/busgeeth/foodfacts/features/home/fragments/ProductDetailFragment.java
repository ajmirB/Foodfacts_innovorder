package com.busgeeth.foodfacts.features.home.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.busgeeth.foodfacts.R;
import com.busgeeth.foodfacts.features.commons.BaseFragment;
import com.busgeeth.foodfacts.features.home.presenters.ProductDetailPresenter;


public class ProductDetailFragment extends BaseFragment implements ProductDetailPresenter.View {

    ProductDetailPresenter mProductDetailPresenter;

    ImageView mImageView;

    TextView mTitleTextView;

    TextView mEnergyTextView;

    TextView mIngredientsTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_foodfact_detail, container, false);

        mImageView = (ImageView) view.findViewById(R.id.product_detail_image_view);
        mTitleTextView = (TextView) view.findViewById(R.id.product_detail_title_text_view);
        mEnergyTextView = (TextView) view.findViewById(R.id.product_detail_energy_text_view);
        mIngredientsTextView = (TextView) view.findViewById(R.id.product_detail_ingredients_text_view);

        mProductDetailPresenter = new ProductDetailPresenter(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProductDetailPresenter.onViewCreated();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mProductDetailPresenter.onViewDestroyed();
    }

    public void productToDisplay(long barcodeNumber) {
        mProductDetailPresenter.productToDisplay(barcodeNumber);
    }

    // region ProductDetailPresenter.View

    @Override
    public void showContent(@NonNull ProductDetailPresenter.Data data) {
        //TODO : load picture

        mTitleTextView.setText(data.title);
        mEnergyTextView.setText(data.energyInKCal);
        mIngredientsTextView.setText(data.ingredients);
    }

    // endregion
}
