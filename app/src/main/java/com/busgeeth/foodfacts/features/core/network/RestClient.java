package com.busgeeth.foodfacts.features.core.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    public static FoodFactService getFoodFactService() {
        Retrofit retrofit = getRestClient();
        return retrofit.create(FoodFactService.class);
    }

    private static Retrofit getRestClient() {
        return new Retrofit.Builder()
                .baseUrl("https://world.openfoodfacts.org/api/v0/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
