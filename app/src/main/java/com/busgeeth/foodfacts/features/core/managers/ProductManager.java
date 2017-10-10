package com.busgeeth.foodfacts.features.core.managers;

import com.busgeeth.foodfacts.features.core.model.entities.Product;
import com.busgeeth.foodfacts.features.core.model.entities.ProductStatus;
import com.busgeeth.foodfacts.features.core.model.errors.ProductNotFound;
import com.busgeeth.foodfacts.features.core.model.errors.UnknownError;
import com.busgeeth.foodfacts.features.core.network.FoodFactService;
import com.busgeeth.foodfacts.features.core.network.RestClient;

import io.reactivex.Single;

public class ProductManager {

    FoodFactService mFoodFactService;

    public ProductManager() {
        mFoodFactService = RestClient.getFoodFactService();
    }

    public Single<Product> getProduct(long barcodeNumber) {
        return Single.fromObservable(mFoodFactService.getProduct(barcodeNumber))
                .flatMap(productResponse -> {
                    if (productResponse.getProduct() != null) {
                        return Single.just(productResponse.getProduct());
                    } else if (productResponse.getStatus() == ProductStatus.NOT_FOUND) {
                        return Single.error(new ProductNotFound());
                    } else {
                        return Single.error(new UnknownError());
                    }
                });
    }
}