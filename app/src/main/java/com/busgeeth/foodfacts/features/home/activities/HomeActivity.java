package com.busgeeth.foodfacts.features.home.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.busgeeth.foodfacts.R;
import com.busgeeth.foodfacts.features.commons.BaseActivity;


public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
