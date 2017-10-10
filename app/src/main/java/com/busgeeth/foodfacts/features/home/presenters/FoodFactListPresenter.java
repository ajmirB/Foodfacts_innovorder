package com.busgeeth.foodfacts.features.home.presenters;

import com.busgeeth.foodfacts.features.commons.BasePresenter;

public class FoodFactListPresenter extends BasePresenter<FoodFactListPresenter.View> {

    public FoodFactListPresenter(View view) {
        super(view);
    }

    public interface View extends BasePresenter.View {
    }
}
