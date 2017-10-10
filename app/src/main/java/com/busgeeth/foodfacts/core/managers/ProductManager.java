package com.busgeeth.foodfacts.core.managers;

import android.util.Log;

import com.busgeeth.foodfacts.App;
import com.busgeeth.foodfacts.SharedApplication;
import com.busgeeth.foodfacts.core.model.db.DbProduct;
import com.busgeeth.foodfacts.core.model.db.DbProductDao;
import com.busgeeth.foodfacts.core.model.entities.Product;
import com.busgeeth.foodfacts.core.model.entities.ProductStatus;
import com.busgeeth.foodfacts.core.model.errors.ProductNotFound;
import com.busgeeth.foodfacts.core.model.errors.UnknownError;
import com.busgeeth.foodfacts.core.network.FoodFactService;
import com.busgeeth.foodfacts.core.network.RestClient;
import com.busgeeth.foodfacts.core.stores.ProductStore;
import com.busgeeth.foodfacts.helpers.ProductHelper;

import io.reactivex.Single;

public class ProductManager {

    private static final String TAG = ProductManager.class.getSimpleName();

    FoodFactService mFoodFactService;

    ProductStore mProductStore;

    public ProductManager() {
        mFoodFactService = RestClient.getFoodFactService();
        mProductStore = new ProductStore();
    }

    /**
     * Get the product from the store and if it's not found from the store, then try to find it
     * in the server
     * @param barcodeNumber barcode to identify the product
     * @return
     */
    public Single<Product> getProduct(long barcodeNumber) {
        return mProductStore.getProduct(barcodeNumber)
                .switchIfEmpty(getProductFromServer(barcodeNumber).toMaybe())
                .toSingle();
    }

    /**
     * Launch a request to the server to get the product from its barcode
     * @param barcodeNumber barcode to identify the product
     * @return the product or an error
     */
    public Single<Product> getProductFromServer(long barcodeNumber) {
        Log.d(TAG, "getProductFromServer");
        return Single.fromObservable(mFoodFactService.getProduct(barcodeNumber))
                .flatMap(productResponse -> {
                    if (productResponse.getProduct() != null) {
                        return Single.just(productResponse.getProduct());
                    } else if (productResponse.getStatus() == ProductStatus.NOT_FOUND) {
                        return Single.error(new ProductNotFound());
                    } else {
                        return Single.error(new UnknownError());
                    }
                })
                .doOnSuccess(product -> {
                    App app = SharedApplication.getInstance().getApplication();
                    DbProductDao dbProductDao = app.getDaoSession().getDbProductDao();
                    DbProduct dbProduct = ProductHelper.entitytoDbProduct(product);
                    dbProductDao.insertOrReplaceInTx(dbProduct);
                });
    }
}
