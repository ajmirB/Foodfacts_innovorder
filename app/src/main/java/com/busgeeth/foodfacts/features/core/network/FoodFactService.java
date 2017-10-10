package com.busgeeth.foodfacts.features.core.network;

import com.busgeeth.foodfacts.features.core.model.entities.ProductResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FoodFactService {
        @GET("product/{barcodeNumber}.json")
        Observable<ProductResponse> getProduct(@Path("barcodeNumber") long barcodeNumber);
}
