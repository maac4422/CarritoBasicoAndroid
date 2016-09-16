package com.miguel.pruebaintergrupo.presenter;

import android.content.Context;

import com.miguel.pruebaintergrupo.view.IProductDetailView;

/**
 * Created by miguel on 9/15/16.
 */
public interface IProductDetailPresenter {

    void addProduct(int id,int quantity ,int price,Context context);

}
