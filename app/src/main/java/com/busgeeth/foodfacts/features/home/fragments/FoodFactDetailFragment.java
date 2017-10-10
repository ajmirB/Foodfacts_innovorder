package com.busgeeth.foodfacts.features.home.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.busgeeth.foodfacts.R;
import com.busgeeth.foodfacts.features.commons.BaseFragment;


public class FoodFactDetailFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_foodfact_detail, container, false);
        return view;
    }
}
