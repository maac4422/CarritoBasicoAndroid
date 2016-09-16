package com.miguel.pruebaintergrupo;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.miguel.pruebaintergrupo.presenter.IProductDetailPresenter;
import com.miguel.pruebaintergrupo.presenter.ProductDetailPresenter;
import com.miguel.pruebaintergrupo.view.IProductDetailView;

public class ProductDetailActivity extends AppCompatActivity implements IProductDetailView {


    private TextView tvName,tvPrice;
    private EditText etQuantty;
    private int quantity,id;
    private IProductDetailPresenter iProductDetailPresenter;
    private int price;
    private String name;

    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        //Recibir los parametros del ProductList

        Bundle parametros = getIntent().getExtras();
        name = parametros.getString(getResources().getString(R.string.product_name_intent));
        price = parametros.getInt(getResources().getString(R.string.product_price_intent));
        id = parametros.getInt(getResources().getString(R.string.product_id_intent));

        tvName = (TextView) findViewById(R.id.tvProductDetail);
        tvPrice = (TextView) findViewById(R.id.tvProductPriceDetail);

        //Setear los datos del producto
        tvName.setText(name);
        tvPrice.setText(String.valueOf(price));

        iProductDetailPresenter = new ProductDetailPresenter(this);


    }

    @Override
    public void addProduct(View view){
        etQuantty = (EditText) findViewById(R.id.etQuantityDetail);
        this.view = view;
        if (etQuantty.getText().toString().length()>0) {
            quantity = Integer.parseInt(etQuantty.getText().toString());

            if (quantity > 0) {
                iProductDetailPresenter.addProduct(id, quantity, price, getApplicationContext());
            } else {
                Toast.makeText(ProductDetailActivity.this, getResources().getString(R.string.error_cantidad), Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(ProductDetailActivity.this,getResources().getString(R.string.cantidad_vacia), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void addProductResult(Boolean result) {
        if (result){

            Snackbar.make(view,getResources().getString(R.string.product_add_shopping_cart_sucess),Snackbar.LENGTH_LONG).show();
            etQuantty.setText("1");
        }else{

            Snackbar.make(view,getResources().getString(R.string.product_add_shopping_cart_fail),Snackbar.LENGTH_LONG).show();
        }
    }
}
