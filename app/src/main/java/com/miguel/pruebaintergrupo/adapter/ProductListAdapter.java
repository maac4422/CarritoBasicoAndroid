package com.miguel.pruebaintergrupo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.miguel.pruebaintergrupo.ProductDetailActivity;
import com.miguel.pruebaintergrupo.R;
import com.miguel.pruebaintergrupo.pojo.Product;

import java.util.ArrayList;

/**
 * Created by miguel on 9/15/16.
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.productViewHolder> {

    private ArrayList<Product> products;
    Activity activity;

    public ProductListAdapter(ArrayList<Product> products, Activity activity){
        this.products = products;
        this.activity = activity;
    }



    public static class productViewHolder extends RecyclerView.ViewHolder{

        private TextView tvProduct,tvPrice;

        public productViewHolder(View itemView) {
            super(itemView);
            tvProduct = (TextView) itemView.findViewById(R.id.tvProductList);
            tvPrice = (TextView) itemView.findViewById(R.id.tvProductPriceList);
        }
    }



    @Override
    public productViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_products,parent,false);
        return new productViewHolder(v);
    }

    @Override
    public void onBindViewHolder(productViewHolder productViewHolder, int position) {
        final Product product = products.get(position);

        productViewHolder.tvProduct.setText(product.getName());
        productViewHolder.tvPrice.setText("$"+String.valueOf(product.getPrice()));


        //Click el nombre del producto
        productViewHolder.tvProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ProductDetailActivity.class);
                intent.putExtra(activity.getResources().getString(R.string.product_id_intent),product.getId());
                intent.putExtra(activity.getResources().getString(R.string.product_name_intent),product.getName());
                intent.putExtra(activity.getResources().getString(R.string.product_price_intent),product.getPrice());
                activity.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return products.size();
    }


}
