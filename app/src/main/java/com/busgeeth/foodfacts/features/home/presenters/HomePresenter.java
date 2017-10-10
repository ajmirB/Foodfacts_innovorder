package com.busgeeth.foodfacts.features.home.presenters;

import android.util.Log;

import com.busgeeth.foodfacts.R;
import com.busgeeth.foodfacts.features.commons.BasePresenter;
import com.busgeeth.foodfacts.features.core.managers.ProductManager;
import com.busgeeth.foodfacts.features.core.model.errors.ProductNotFound;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<HomePresenter.View> {

    ProductManager mProductManager;

    public HomePresenter(View view) {
        super(view);
        mProductManager = new ProductManager();
    }

    public void searchBarcordeClicked(String barcodeNumberString) {
        if (barcodeNumberString == null || barcodeNumberString.isEmpty()) {
            mView.alertError(R.string.home_search_no_barcode_error);
        } else {
            Long barcodeNumber = Long.valueOf(barcodeNumberString);
            mProductManager.getProduct(barcodeNumber)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            product -> {
                                Log.d("test", "onEditorAction: on next");
                            },
                            throwable -> {
                                if (throwable instanceof ProductNotFound) {
                                    mView.alertError(R.string.home_product_not_found_error);
                                } else {
                                    mView.alertError(R.string.error_unknown);
                                }
                            }
                    );
        }
    }

    public interface View extends BasePresenter.View {
        void alertError(int messageResId);
    }
}
