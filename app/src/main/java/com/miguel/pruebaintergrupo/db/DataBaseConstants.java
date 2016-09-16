package com.miguel.pruebaintergrupo.db;

/**
 * Created by miguel on 9/14/16.
 */
public final class DataBaseConstants {

    public static final String DATABASE_NAME = "carrito";
    public static final int DATABASE_VERSION = 1;

    //Tabla de Usuario

    public static final String TABLE_USERS = "users";
    public static final String TABLE_USERS_ID = "id";
    public static final String TABLE_USERS_USERNAME = "username";
    public static final String TABLE_USERS_PASSWORD = "password";


    //Tabla de products
    public static final String TABLE_PRODUCTS = "products";
    public static final String TABLE_PRODUCTS_ID = "id";
    public static final String TABLE_PRODUCTS_NAME = "name";
    public static final String TABLE_PRODUCTS_PRICE = "price";



    //Tabla de carrito
    public static final String TABLE_SHOPPING_CART = "shopping_carts";
    public static final String TABLE_SHOPPING_CART_ID = "id";
    public static final String TABLE_SHOPPING_CART_PRODUCT_ID = "product_id";
    public static final String TABLE_SHOPPING_CART_TOTAL_PRICE = "total_price";
    public static final String TABLE_SHOPPING_CART_QUANTITY = "quantity";
    public static final String TABLE_SHOPPING_CART_STATE = "state";

    public static final String TABLE_SHOPPING_CART_STATE_VALID = "valid";
    public static final String TABLE_SHOPPING_CART_STATE_INVALID = "invalid";


}
