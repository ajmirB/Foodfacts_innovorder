package com.busgeeth.foodfacts.features.home.presenters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.busgeeth.foodfacts.R;
import com.busgeeth.foodfacts.SharedApplication;
import com.busgeeth.foodfacts.core.managers.ProductManager;
import com.busgeeth.foodfacts.core.model.entities.Ingredient;
import com.busgeeth.foodfacts.core.model.entities.Product;
import com.busgeeth.foodfacts.features.commons.BasePresenter;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductDetailPresenter extends BasePresenter<ProductDetailPresenter.View> {

    private ProductManager mProductManager;

    public ProductDetailPresenter(View view) {
        super(view);
        mProductManager = new ProductManager();
    }

    public void productToDisplay(long barcodeNumber) {
        mProductManager.getProduct(barcodeNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::showContent,
                        Throwable::printStackTrace
                );
    }

    private void showContent(Product product) {
        Context context = SharedApplication.getInstance().getApplication();

        Data data = new Data();
        data.imageUrl = product.getImageUrl();
        data.title = product.getName();
        data.energyInKCal = context.getString(R.string.product_detail_energy, product.getNutriments().getEnergy());

        List<String> ingredientsInString = Observable.fromIterable(product.getIngredientList())
                .map(Ingredient::getText)
                .toList().blockingGet();
        data.ingredients = StringUtils.join(ingredientsInString, ", ");

        mView.showContent(data);
    }

    public interface View extends BasePresenter.View {
        void showContent(@NonNull Data data);
    }

    public class Data {
        public String imageUrl;
        public String title;
        public String energyInKCal;
        public String ingredients;
    }
}
