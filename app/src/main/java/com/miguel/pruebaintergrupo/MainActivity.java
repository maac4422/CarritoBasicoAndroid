package com.miguel.pruebaintergrupo;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.miguel.pruebaintergrupo.presenter.IMainPresenter;

import com.miguel.pruebaintergrupo.presenter.MainPresenter;
import com.miguel.pruebaintergrupo.view.IMainView;

public class MainActivity extends AppCompatActivity implements IMainView{
    private Activity activity;
    private View view;
    private IMainPresenter iMainPresenter;
    private String username,password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iMainPresenter = new MainPresenter(this);
    }

    @Override
    public void Login(View view){
        this.view = view;
        username = ((EditText) findViewById(R.id.login_username)).getText().toString();
        password = ((EditText) findViewById(R.id.login_pass)).getText().toString();

        iMainPresenter.ValidateLogin(username,password,getApplicationContext());

    }

    @Override
    public void LoginResult(Boolean result) {
        if (result){
            Intent intent = new Intent(getApplicationContext(),ProductListActivity.class);
            startActivity(intent);
            finish();
        }else{

            Snackbar.make(view,getResources().getString(R.string.login_failed),Snackbar.LENGTH_LONG).show();
        }
    }
}
