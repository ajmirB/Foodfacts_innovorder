package com.busgeeth.foodfacts.features.commons;

public class BasePresenter<T extends BasePresenter.View> {

    public void onViewCreated() {
    }

    public void onViewDestroyed() {
    }

    public interface View {
    }
}
