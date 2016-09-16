package com.miguel.pruebaintergrupo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miguel.pruebaintergrupo.R;
import com.miguel.pruebaintergrupo.pojo.ShoppingCart;

import java.util.ArrayList;

/**
 * Created by miguel on 9/16/16.
 */
public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.cartViewHolder> {

    private ArrayList<ShoppingCart> shoppingCarts;
    Activity activity;

    public ShoppingCartAdapter(ArrayList<ShoppingCart> shoppingCarts, Activity activity){
        this.shoppingCarts = shoppingCarts;
        this.activity = activity;
    }


    public static class cartViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName,tvPrice,tvTotalPrice,tvQuantity;

        public cartViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvProductNameCart);
            tvPrice = (TextView) itemView.findViewById(R.id.tvProductPriceCart);
            tvTotalPrice = (TextView) itemView.findViewById(R.id.tvProductTotalPriceCart);
            tvQuantity = (TextView) itemView.findViewById(R.id.tvProductQuantityCart);

        }
    }

    @Override
    public ShoppingCartAdapter.cartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_shopping_cart,parent,false);
        return new cartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ShoppingCartAdapter.cartViewHolder cartViewHolder, int position) {
        final ShoppingCart shoppingCart = shoppingCarts.get(position);

        cartViewHolder.tvName.setText(shoppingCart.getProduct_name());
        cartViewHolder.tvPrice.setText("$"+String.valueOf(shoppingCart.getProduct_price()));
        cartViewHolder.tvQuantity.setText(String.valueOf(shoppingCart.getQuantity()));
        cartViewHolder.tvTotalPrice.setText("$"+String.valueOf(shoppingCart.getTotal_price()));
    }

    @Override
    public int getItemCount() {
        return shoppingCarts.size();

    }
}
