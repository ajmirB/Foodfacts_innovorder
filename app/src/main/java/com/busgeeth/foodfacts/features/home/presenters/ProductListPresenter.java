package com.busgeeth.foodfacts.features.home.presenters;

import android.support.annotation.NonNull;

import com.busgeeth.foodfacts.core.managers.ProductManager;
import com.busgeeth.foodfacts.core.model.entities.Product;
import com.busgeeth.foodfacts.features.commons.BasePresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ProductListPresenter extends BasePresenter<ProductListPresenter.View> {

    public ProductListPresenter(View view) {
        super(view);
    }



    @Override
    public void onViewCreated() {
        super.onViewCreated();
        ProductManager productManager = new ProductManager();
        productManager.getAllProduct()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        productList -> showContent(productList),
                        Throwable::printStackTrace);
    }

    private void showContent(List<Product> products) {
        Data data = new Data();
        data.productItemsData = Observable.fromIterable(products)
                .map(product -> {
                    ProductItemData productItemData = new ProductItemData();
                    productItemData.productName = product.getName();
                    productItemData.barcode = product.getBarcode();
                    productItemData.onClickListener = v -> mView.onProductClicked(product.getBarcode());
                    return productItemData;
                })
                .toList()
                .blockingGet();
        mView.showContent(data);
    }

    // region Presenter protocol

    public interface View extends BasePresenter.View {
        void showContent(@NonNull Data data);
        void onProductClicked(long barcodeNumber);
    }

    public class Data {
        public List<ProductItemData> productItemsData;
    }

    public class ProductItemData {
        public String productName;
        public Long barcode;
        public android.view.View.OnClickListener onClickListener;
    }

    // endregion
}
