package com.busgeeth.foodfacts.features.commons;

public class BasePresenter<T extends BasePresenter.View> {

    protected T mView;

    public BasePresenter(T view) {
        mView = view;
    }

    public void onViewCreated() {
    }

    public void onViewDestroyed() {
    }

    public interface View {
    }
}
