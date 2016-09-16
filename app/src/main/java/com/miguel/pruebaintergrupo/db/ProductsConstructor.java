package com.miguel.pruebaintergrupo.db;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.miguel.pruebaintergrupo.pojo.Product;

import java.util.ArrayList;

/**
 * Created by miguel on 9/14/16.
 */
public class ProductsConstructor {

    private Context context;
    private DataBase db;

    public ProductsConstructor(Context context) {
        this.context = context;
        db = new DataBase(context);
    }

    public ArrayList<Product> getProducts() {



        if (!db.validateExistProducts()){
            insertProducts(db);
        }

        return db.getAllProducts();
    }

    public void insertProducts(DataBase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.TABLE_PRODUCTS_NAME, "Computador");
        contentValues.put(DataBaseConstants.TABLE_PRODUCTS_PRICE, 1403245);

        db.insertProduct(contentValues);

        contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.TABLE_PRODUCTS_NAME, "Celular");
        contentValues.put(DataBaseConstants.TABLE_PRODUCTS_PRICE, 560324);


        db.insertProduct(contentValues);

        contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.TABLE_PRODUCTS_NAME, "Televisor 4K");
        contentValues.put(DataBaseConstants.TABLE_PRODUCTS_PRICE, 3200456);

        db.insertProduct(contentValues);

        contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.TABLE_PRODUCTS_NAME, "Batidora");
        contentValues.put(DataBaseConstants.TABLE_PRODUCTS_PRICE, 60000);

        db.insertProduct(contentValues);

        contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.TABLE_PRODUCTS_NAME, "DVD");
        contentValues.put(DataBaseConstants.TABLE_PRODUCTS_PRICE, 120000);

        db.insertProduct(contentValues);
    }

    public Boolean addProductShoppingCart(int id,int quantity,int price){
        Boolean res = false;

        res = db.validateProductExist(id);
        try {
            if (res){
                ContentValues contentValues = new ContentValues();
                contentValues.put(DataBaseConstants.TABLE_SHOPPING_CART_PRODUCT_ID,id);
                contentValues.put(DataBaseConstants.TABLE_SHOPPING_CART_QUANTITY,quantity);
                contentValues.put(DataBaseConstants.TABLE_SHOPPING_CART_TOTAL_PRICE,price * quantity);
                res = db.addProductShoppingCart(contentValues);

                return res;
            }else{
                return res;
            }
        }catch (Exception e){
            return false;
        }

    }
}
