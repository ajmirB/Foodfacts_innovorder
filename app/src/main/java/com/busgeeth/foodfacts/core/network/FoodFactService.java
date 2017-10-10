package com.busgeeth.foodfacts.core.network;

import com.busgeeth.foodfacts.core.model.entities.ProductResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FoodFactService {
        @GET("product/{barcodeNumber}.json")
        Observable<ProductResponse> getProduct(@Path("barcodeNumber") long barcodeNumber);
}
