package com.busgeeth.foodfacts;

import com.google.gson.Gson;

public class SharedApplication {

    private static SharedApplication mSharedApplication = null;
    private App mApp;
    private Gson mSimpleGson;

    private SharedApplication(App app) {
        mApp = app;
        mSimpleGson = new Gson();
    }

    public static void initInstance(App app) {
        if (mSharedApplication == null) {
            mSharedApplication = new SharedApplication(app);
        }
    }

    public static SharedApplication getInstance() {
        if (mSharedApplication == null) {
            throw new RuntimeException("Shared application context must be initialized " +
                    "with method 'initInstance' before being used");
        }
        return mSharedApplication;
    }

    public App getApplication() {
        return mApp;
    }

    public Gson getGson() {
        return mSimpleGson;
    }
}
