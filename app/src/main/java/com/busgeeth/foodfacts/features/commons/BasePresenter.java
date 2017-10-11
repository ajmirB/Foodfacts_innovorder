package com.busgeeth.foodfacts.features.commons;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<T extends BasePresenter.View> {

    protected T mView;

    protected CompositeDisposable mCompositeDisposable;

    public BasePresenter(T view) {
        mView = view;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void onViewCreated() {
    }

    public void onViewDestroyed() {
        mCompositeDisposable.dispose();
    }

    public interface View {
    }
}
