package com.busgeeth.foodfacts.features.home.presenters;

import android.support.annotation.NonNull;

import com.busgeeth.foodfacts.R;
import com.busgeeth.foodfacts.core.managers.ProductManager;
import com.busgeeth.foodfacts.core.model.entities.Product;
import com.busgeeth.foodfacts.core.model.errors.ProductNotFound;
import com.busgeeth.foodfacts.features.commons.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<HomePresenter.View> {

    ProductManager mProductManager;

    public HomePresenter(View view) {
        super(view);
        mProductManager = new ProductManager();

        mProductManager.onInsertInStoreObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        product -> {
                            mView.addProductInList(product);
                            mView.showProductDetail(product);
                        },
                        Throwable::printStackTrace
                );
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
                            product -> {},
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
        void addProductInList(@NonNull Product product);
        void showProductDetail(@NonNull Product product);
    }
}
