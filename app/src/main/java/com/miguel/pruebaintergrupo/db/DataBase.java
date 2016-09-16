package com.miguel.pruebaintergrupo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.miguel.pruebaintergrupo.pojo.Product;
import com.miguel.pruebaintergrupo.pojo.ShoppingCart;

import java.util.ArrayList;

/**
 * Created by miguel on 9/14/16.
 */
public class DataBase extends SQLiteOpenHelper{

    private Context context;

    public DataBase(Context context){
        super(context,DataBaseConstants.DATABASE_NAME,null,DataBaseConstants.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateUsers = "CREATE TABLE " + DataBaseConstants.TABLE_USERS + "("+
                                DataBaseConstants.TABLE_USERS_ID        +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                DataBaseConstants.TABLE_USERS_USERNAME  +" TEXT NOT NULL, " +
                                DataBaseConstants.TABLE_USERS_PASSWORD  +" TEXT NOT NULL "
                                +")";


        String queryCreateProducts = "CREATE TABLE " + DataBaseConstants.TABLE_PRODUCTS + "("+
                                DataBaseConstants.TABLE_PRODUCTS_ID     + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                DataBaseConstants.TABLE_PRODUCTS_NAME   + " TEXT NOT NULL, " +
                                DataBaseConstants.TABLE_PRODUCTS_PRICE  + " INTEGER NOT NULL "
                                +")";

        String queryCreateShoppingCart = "CREATE TABLE " + DataBaseConstants.TABLE_SHOPPING_CART + "("+
                DataBaseConstants.TABLE_SHOPPING_CART_ID            + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DataBaseConstants.TABLE_SHOPPING_CART_QUANTITY      + " INTEGER NOT NULL ," +
                DataBaseConstants.TABLE_SHOPPING_CART_STATE         + " TEXT NOT NULL DEFAULT " + DataBaseConstants.TABLE_SHOPPING_CART_STATE_VALID + ", " +
                DataBaseConstants.TABLE_SHOPPING_CART_TOTAL_PRICE   + " INTEGER NOT NULL, " +
                DataBaseConstants.TABLE_SHOPPING_CART_PRODUCT_ID    + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + DataBaseConstants.TABLE_SHOPPING_CART_PRODUCT_ID + ") " +
                " REFERENCES " + DataBaseConstants.TABLE_PRODUCTS + "(" + DataBaseConstants.TABLE_SHOPPING_CART_PRODUCT_ID +")"
                +")";

        db.execSQL(queryCreateUsers);
        db.execSQL(queryCreateProducts);
        db.execSQL(queryCreateShoppingCart);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST " + DataBaseConstants.TABLE_SHOPPING_CART);
        db.execSQL("DROP TABLE IF EXIST " + DataBaseConstants.TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXIST " + DataBaseConstants.TABLE_USERS);
        onCreate(db);
    }



    //Metodos de usuario
    public void insertUsers(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DataBaseConstants.TABLE_USERS,null,contentValues);
        db.close();
    }

    public Boolean ValidateLogin(String username,String password){

        int users = 0;
        String query = "SELECT COUNT(*) FROM " +DataBaseConstants.TABLE_USERS +
                        " WHERE "+DataBaseConstants.TABLE_USERS_USERNAME + " = '" + username + "' AND " +
                        DataBaseConstants.TABLE_USERS_PASSWORD + " = '" + password + "' ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query,null);

        if (registros.moveToNext()){
            users= registros.getInt(0);
        }

        db.close();
        if (users != 0){
            return true;
        }else{
            return false;
        }


    }

    public Boolean ValidateExistUser(){

        int users = 0;
        String query = "SELECT COUNT(*) FROM " + DataBaseConstants.TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor registros = db.rawQuery(query,null);

        if (registros.moveToNext()){
            users= registros.getInt(0);
        }

        db.close();
        if (users != 0){
            return true;
        }else{
            return false;
        }

    }



    //Metodos de productos

    public ArrayList<Product> getAllProducts(){
        ArrayList<Product> products = new ArrayList<>();

        String query = "SELECT * FROM " + DataBaseConstants.TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor registros = db.rawQuery(query,null);

        while (registros.moveToNext()){
            Product actuallyProduct =new Product();
            actuallyProduct.setId(registros.getInt(0));
            actuallyProduct.setName(registros.getString(1));
            actuallyProduct.setPrice(registros.getInt(2));

            products.add(actuallyProduct);
        }

        db.close();
        return products;
    }

    public Boolean validateExistProducts(){
        int products = 0;
        String query = "SELECT COUNT(*) FROM " + DataBaseConstants.TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor registros = db.rawQuery(query,null);

        if (registros.moveToNext()){
            products= registros.getInt(0);
        }

        db.close();
        if (products != 0){
            return true;
        }else{
            return false;
        }
    }

    public void insertProduct(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DataBaseConstants.TABLE_PRODUCTS,null,contentValues);
        db.close();
    }

    public boolean validateProductExist(int id){
        int product = 0;
        String query = "SELECT COUNT(*) FROM " + DataBaseConstants.TABLE_PRODUCTS +
                        " WHERE " + DataBaseConstants.TABLE_PRODUCTS_ID +
                        " = " + id;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor registros = db.rawQuery(query,null);

        if (registros.moveToNext()){
            product= registros.getInt(0);
        }

        db.close();
        if (product != 0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean addProductShoppingCart(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.insert(DataBaseConstants.TABLE_SHOPPING_CART,null,contentValues);
            db.close();
            return  true;
        }catch (Exception e){
            Log.i(null,e.toString());
            db.close();
            return false;
        }

    }

    //Metodos de shopping cart

    public ArrayList<ShoppingCart> getShoppingCartValid(){
        ArrayList<ShoppingCart> shoppingCarts = new ArrayList<>();

        String query = "SELECT S.*, P." + DataBaseConstants.TABLE_PRODUCTS_NAME + ",P." +DataBaseConstants.TABLE_PRODUCTS_PRICE +
                        " FROM " + DataBaseConstants.TABLE_SHOPPING_CART+ " S"+
                        " INNER JOIN " + DataBaseConstants.TABLE_PRODUCTS + " P ON P." + DataBaseConstants.TABLE_PRODUCTS_ID +
                        " = S." + DataBaseConstants.TABLE_SHOPPING_CART_PRODUCT_ID
                        +" WHERE " + DataBaseConstants.TABLE_SHOPPING_CART_STATE + " like '" + DataBaseConstants.TABLE_SHOPPING_CART_STATE_VALID + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor registros = db.rawQuery(query,null);

        while (registros.moveToNext()){
            ShoppingCart actuallyShopping =new ShoppingCart();
            actuallyShopping.setId(registros.getInt(0));
            actuallyShopping.setQuantity(registros.getInt(1));
            actuallyShopping.setState(registros.getString(2));
            actuallyShopping.setTotal_price(registros.getInt(3));
            actuallyShopping.setProduct_id(registros.getInt(4));
            actuallyShopping.setProduct_name(registros.getString(5));
            actuallyShopping.setProduct_price(registros.getInt(6));
            shoppingCarts.add(actuallyShopping);
        }

        db.close();
        return shoppingCarts;
    }

    public Boolean purchare(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.update(DataBaseConstants.TABLE_SHOPPING_CART,contentValues,null,null);
            db.close();
            return true;
        }catch (Exception e){
            Log.i(null,e.toString());
            db.close();
            return false;
        }
    }

    public int sumShoppingCart(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT SUM("+DataBaseConstants.TABLE_SHOPPING_CART_TOTAL_PRICE+") FROM " + DataBaseConstants.TABLE_SHOPPING_CART +
                " WHERE " + DataBaseConstants.TABLE_SHOPPING_CART_STATE + " like '" + DataBaseConstants.TABLE_SHOPPING_CART_STATE_VALID + "'";
        Cursor registros = db.rawQuery(query,null);

        int sum = 0;
        if (registros.moveToNext()) {
            sum = registros.getInt(0);
        }

        db.close();
        return sum;
    }
}
