package com.busgeeth.foodfacts.features.home.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
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
        View view =  inflater.inflate(R.layout.fragment_product_detail, container, false);

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
        Glide.with(this)
                .asBitmap()
                .load(data.imageUrl)
                .into(new BitmapImageViewTarget(mImageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        mImageView.setImageDrawable(circularBitmapDrawable);
                    }
                });

        mTitleTextView.setText(data.title);
        mEnergyTextView.setText(data.energyInKCal);
        mIngredientsTextView.setText(data.ingredients);
    }

    // endregion
}
