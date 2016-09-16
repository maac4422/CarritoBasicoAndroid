package com.miguel.pruebaintergrupo.presenter;

import android.content.Context;

import com.miguel.pruebaintergrupo.db.ProductsConstructor;
import com.miguel.pruebaintergrupo.view.IProductDetailView;

/**
 * Created by miguel on 9/15/16.
 */
public class ProductDetailPresenter implements IProductDetailPresenter {

    private IProductDetailView iProductDetailView ;
    private Context context;
    private ProductsConstructor productsConstructor;

    public ProductDetailPresenter(IProductDetailView iProductDetailView){
        this.iProductDetailView = iProductDetailView;
    }

    @Override
    public void addProduct(int id,int quantity,int price,Context context) {
        this.context = context;
        productsConstructor = new ProductsConstructor(context);
        Boolean res = productsConstructor.addProductShoppingCart(id,quantity,price);
        iProductDetailView.addProductResult(res);
    }

}
