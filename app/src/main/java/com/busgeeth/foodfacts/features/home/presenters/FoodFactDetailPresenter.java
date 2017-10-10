package com.busgeeth.foodfacts.features.home.presenters;

import com.busgeeth.foodfacts.features.commons.BasePresenter;

public class FoodFactDetailPresenter extends BasePresenter<FoodFactDetailPresenter.View> {

    public FoodFactDetailPresenter(View view) {
        super(view);
    }

    public interface View extends BasePresenter.View {
    }
}
