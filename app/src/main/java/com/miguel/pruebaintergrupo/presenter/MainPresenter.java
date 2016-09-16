package com.miguel.pruebaintergrupo.presenter;

import android.content.Context;
import android.util.Log;

import com.miguel.pruebaintergrupo.db.UsersConstructor;
import com.miguel.pruebaintergrupo.view.IMainView;

/**
 * Created by miguel on 9/14/16.
 */
public class MainPresenter implements IMainPresenter {

    private Context context;
    private UsersConstructor usersConstructor;
    private IMainView iMainView;

    public  MainPresenter(IMainView iMainView){
        this.iMainView = iMainView;
    }


    @Override
    public void ValidateLogin(String username, String password,Context context) {
        this.context = context;
        usersConstructor = new UsersConstructor(context);
        Boolean rest = usersConstructor.ValidateLogin(username,password);
        iMainView.LoginResult(rest);
    }



}
