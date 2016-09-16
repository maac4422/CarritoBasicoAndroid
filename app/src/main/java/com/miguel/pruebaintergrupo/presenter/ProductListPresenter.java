package com.miguel.pruebaintergrupo.presenter;

import android.content.Context;

import com.miguel.pruebaintergrupo.db.ProductsConstructor;
import com.miguel.pruebaintergrupo.pojo.Product;
import com.miguel.pruebaintergrupo.view.IProductListView;

import java.util.ArrayList;

/**
 * Created by miguel on 9/15/16.
 */
public class ProductListPresenter implements IProductListPresenter {

    private IProductListView iProductListView;
    private ArrayList<Product> products;
    private ProductsConstructor productsConstructor;
    private Context context;

    public ProductListPresenter(IProductListView iProductListView,Context context){
        this.iProductListView = iProductListView;
        this.context = context;
        getProductDB();
    }

    @Override
    public void getProductDB() {
        productsConstructor = new ProductsConstructor(context);
        products = productsConstructor.getProducts();
        showProductRV();
    }

    @Override
    public void showProductRV() {
        iProductListView.initializerAdapterRV(iProductListView.createAdapter(products));
        iProductListView.generateVerticalLinearLayout();
    }
}
