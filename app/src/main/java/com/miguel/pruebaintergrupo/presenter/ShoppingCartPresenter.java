package com.miguel.pruebaintergrupo.presenter;

import android.content.Context;

import com.miguel.pruebaintergrupo.db.ShoppingCartConstructor;
import com.miguel.pruebaintergrupo.pojo.ShoppingCart;
import com.miguel.pruebaintergrupo.view.IShoppingCartView;

import java.util.ArrayList;

/**
 * Created by miguel on 9/16/16.
 */
public class ShoppingCartPresenter implements IShoppingCartPresenter {

    private Context context;
    private IShoppingCartView iShoppingCartView;
    private ShoppingCartConstructor shoppingCartConstructor;
    private ArrayList<ShoppingCart> shoppingCarts;

    public ShoppingCartPresenter(IShoppingCartView iShoppingCartView, Context context) {
        this.context=context;
        this.iShoppingCartView = iShoppingCartView;
        getShoppingCartDB();
        sumShoppingCart();
    }


    @Override
    public void getShoppingCartDB() {
        shoppingCartConstructor = new ShoppingCartConstructor(context);
        shoppingCarts = shoppingCartConstructor.getShoppingCart();
        showShoppingCartRV();
    }

    @Override
    public void showShoppingCartRV() {
        iShoppingCartView.initializerAdapterRV(iShoppingCartView.createAdapter(shoppingCarts));
        iShoppingCartView.generateVerticalLinearLayout();
    }

    @Override
    public void purchare() {
        shoppingCartConstructor = new ShoppingCartConstructor(context);
        Boolean rest = shoppingCartConstructor.purchare();
        iShoppingCartView.purchaseResult(rest);
    }

    @Override
    public void sumShoppingCart() {
        shoppingCartConstructor = new ShoppingCartConstructor(context);
        int sum = shoppingCartConstructor.sumShoppingCart();
        iShoppingCartView.sumShoppingCart(sum);
    }
}
