package com.miguel.pruebaintergrupo.view;


import android.view.View;

import com.miguel.pruebaintergrupo.adapter.ShoppingCartAdapter;
import com.miguel.pruebaintergrupo.pojo.ShoppingCart;

import java.util.ArrayList;

/**
 * Created by miguel on 9/16/16.
 */
public interface IShoppingCartView {
    void generateVerticalLinearLayout();

    ShoppingCartAdapter createAdapter(ArrayList<ShoppingCart> shoppingCarts);

    void initializerAdapterRV(ShoppingCartAdapter adapter);

    void purchase(View view);

    void purchaseResult(Boolean rest);

    void sumShoppingCart(int sum);
}
