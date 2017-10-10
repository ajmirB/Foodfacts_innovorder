package com.busgeeth.foodfacts.core.managers;

import android.util.Log;

import com.busgeeth.foodfacts.core.model.entities.Product;
import com.busgeeth.foodfacts.core.model.entities.ProductStatus;
import com.busgeeth.foodfacts.core.model.errors.ProductNotFound;
import com.busgeeth.foodfacts.core.model.errors.UnknownError;
import com.busgeeth.foodfacts.core.network.FoodFactService;
import com.busgeeth.foodfacts.core.network.RestClient;
import com.busgeeth.foodfacts.core.stores.ProductStore;

import java.util.List;

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
     * Get all the product stored
     * @return the list of product stored, an empty list if there is nothing stored
     */
    public Single<List<Product>> getAllProduct() {
        return mProductStore.getAllProduct();
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
    private Single<Product> getProductFromServer(long barcodeNumber) {
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
                .flatMap(product -> mProductStore.saveProduct(product).andThen(Single.just(product)));
    }
}
