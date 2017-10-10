package com.busgeeth.foodfacts.features.home.presenters;

import com.busgeeth.foodfacts.features.commons.BasePresenter;

public class ProductDetailPresenter extends BasePresenter<ProductDetailPresenter.View> {

    public ProductDetailPresenter(View view) {
        super(view);
    }

    public interface View extends BasePresenter.View {
    }
}
