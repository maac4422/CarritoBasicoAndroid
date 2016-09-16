package com.miguel.pruebaintergrupo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.miguel.pruebaintergrupo.adapter.ProductListAdapter;
import com.miguel.pruebaintergrupo.pojo.Product;
import com.miguel.pruebaintergrupo.presenter.IProductListPresenter;
import com.miguel.pruebaintergrupo.presenter.ProductListPresenter;
import com.miguel.pruebaintergrupo.view.IProductListView;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity implements IProductListView {

    private ArrayList<Product> products;
    private RecyclerView productsList;
    private IProductListPresenter iProductListPresenter;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_product_list);

        activity = this;
        productsList = (RecyclerView) findViewById(R.id.rvProducts);
        iProductListPresenter = new ProductListPresenter(this,getApplicationContext());
    }

    @Override
    public void generateVerticalLinearLayout() {
        LinearLayoutManager llm = new LinearLayoutManager(activity);
        llm.setOrientation(LinearLayout.VERTICAL);
        productsList.setLayoutManager(llm);
    }

    @Override
    public ProductListAdapter createAdapter(ArrayList<Product> products) {
        ProductListAdapter productListAdapter = new ProductListAdapter(products,activity);
        return productListAdapter;
    }

    @Override
    public void initializerAdapterRV(ProductListAdapter adapter) {
        productsList.setAdapter(adapter);
    }


    //Menú de opciones


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.mShoppingCart:
                intent = new Intent(this,ShoppingCartActivity.class);
                startActivity(intent);
                break;
            case R.id.mLogOut:
                intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
