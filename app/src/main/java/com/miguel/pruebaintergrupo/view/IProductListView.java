package com.miguel.pruebaintergrupo.view;

import com.miguel.pruebaintergrupo.adapter.ProductListAdapter;
import com.miguel.pruebaintergrupo.pojo.Product;

import java.util.ArrayList;

/**
 * Created by miguel on 9/15/16.
 */
public interface IProductListView {

    void generateVerticalLinearLayout();

    ProductListAdapter createAdapter(ArrayList<Product> products);

    void initializerAdapterRV(ProductListAdapter adapter);
}
