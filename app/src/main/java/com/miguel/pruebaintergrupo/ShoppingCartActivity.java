package com.miguel.pruebaintergrupo;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.miguel.pruebaintergrupo.adapter.ShoppingCartAdapter;
import com.miguel.pruebaintergrupo.pojo.ShoppingCart;
import com.miguel.pruebaintergrupo.presenter.IShoppingCartPresenter;
import com.miguel.pruebaintergrupo.presenter.ShoppingCartPresenter;
import com.miguel.pruebaintergrupo.view.IShoppingCartView;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity implements IShoppingCartView{

    private ArrayList<ShoppingCart> shoppingCarts;
    private RecyclerView shoppingCartsList;
    private IShoppingCartPresenter iShoppingCartPresenter;
    private Activity activity;
    private TextView tvTotalCartInformation;
    private Button btnPurchare;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        activity = this;

        btnPurchare = (Button) findViewById(R.id.btn_purchare);
        tvTotalCartInformation = (TextView) findViewById(R.id.tvTotalPriceSaleCartInformation);

        btnPurchare.setVisibility(View.GONE);

        shoppingCartsList = (RecyclerView) findViewById(R.id.rvShoppingCarts);
        iShoppingCartPresenter = new ShoppingCartPresenter(this,getApplicationContext());
    }

    @Override
    public void generateVerticalLinearLayout() {
        LinearLayoutManager llm = new LinearLayoutManager(activity);
        llm.setOrientation(LinearLayout.VERTICAL);
        shoppingCartsList.setLayoutManager(llm);
    }

    @Override
    public ShoppingCartAdapter createAdapter(ArrayList<ShoppingCart> shoppingCarts) {
        ShoppingCartAdapter shoppingCartAdapter = new ShoppingCartAdapter(shoppingCarts,activity);
        return shoppingCartAdapter;
    }

    @Override
    public void initializerAdapterRV(ShoppingCartAdapter adapter) {
        shoppingCartsList.setAdapter(adapter);

        //Validar si existen productos en el carrito
        if (adapter.getItemCount() > 0){
            btnPurchare.setVisibility(View.VISIBLE);
            tvTotalCartInformation.setVisibility(View.GONE);

        }
    }

    @Override
    public void purchase(View view) {
        this.view = view;
        iShoppingCartPresenter.purchare();
    }

    @Override
    public void purchaseResult(Boolean rest) {
        if (rest) {
            Snackbar.make(view,activity.getResources().getString(R.string.compra_completa),Snackbar.LENGTH_LONG).show();
            setContentView(R.layout.activity_shopping_cart);
            btnPurchare.setVisibility(View.INVISIBLE);
        }else{

            Snackbar.make(view,activity.getResources().getString(R.string.error_compra),Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void sumShoppingCart(int sum) {
        if (sum > 0){

            btnPurchare.setText(getResources().getString(R.string.total_price_information)+String.valueOf(sum) +"   " + getResources().getString(R.string.comprar));
        }
    }


}
