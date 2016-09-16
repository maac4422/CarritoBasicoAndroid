package com.miguel.pruebaintergrupo.db;

import android.content.ContentValues;
import android.content.Context;

import com.miguel.pruebaintergrupo.pojo.ShoppingCart;

import java.util.ArrayList;

/**
 * Created by miguel on 9/16/16.
 */
public class ShoppingCartConstructor {

    private  Context context;
    private DataBase db;

    public ShoppingCartConstructor(Context context) {
        this.context = context;
        db = new DataBase(context);
    }

    public ArrayList<ShoppingCart> getShoppingCart(){
        return db.getShoppingCartValid();
    }

    public Boolean purchare(){
        Boolean res = false;

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.TABLE_SHOPPING_CART_STATE,DataBaseConstants.TABLE_SHOPPING_CART_STATE_INVALID);
        res = db.purchare(contentValues);

        return res;
    }

    public int sumShoppingCart(){
        return db.sumShoppingCart();
    }
}
