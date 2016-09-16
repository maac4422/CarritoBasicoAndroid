package com.miguel.pruebaintergrupo.db;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

/**
 * Created by miguel on 9/14/16.
 */
public class UsersConstructor {

    private Context context;
    private Boolean existUser;
    private String username,password;
    private DataBase db;

    public UsersConstructor(Context context){
        this.context = context;
         db = new DataBase(context);
    }

    public Boolean ValidateLogin(String username, String password){

        existUser = db.ValidateExistUser();

        if (existUser == false){
            InsertUsers(db);
        }

        this.username = username;
        this.password = password;

        return db.ValidateLogin(username,password);
    }

    public void InsertUsers(DataBase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.TABLE_USERS_USERNAME, "admin");
        contentValues.put(DataBaseConstants.TABLE_USERS_PASSWORD, "123");

        db.insertUsers(contentValues);
       // ValidateLogin(username,password);
    }
}
