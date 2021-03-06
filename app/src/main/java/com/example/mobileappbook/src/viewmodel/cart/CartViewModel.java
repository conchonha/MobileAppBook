package com.example.mobileappbook.src.viewmodel.cart;

import android.app.Application;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.cores.reponse.featured.GetAllCourseReponse;
import com.example.mobileappbook.model.CartModel;
import com.example.mobileappbook.utils.SharePrefs;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends AndroidViewModel {
    private SharePrefs mSharePrefs = new SharePrefs(getApplication());
    private Gson mGson = new Gson();
    private CartModel mCartModel,mCartModelToPay;
    private MutableLiveData<CartModel>getmCartModel = new MutableLiveData<>();
    private MutableLiveData<CartModel>getmCartModelToPay = new MutableLiveData<>();

    public CartViewModel(@NonNull Application application) {
        super(application);
    }

    public void initCart(TextView editText){
        int total  = 0;
        mCartModel = mGson.fromJson(mSharePrefs.getCart(), CartModel.class);
        if (mCartModel != null) {
            total = 0;
            for (GetAllCourseReponse reponse : mCartModel.getList()) {
                total += reponse.getPrice();
            }
            getmCartModel.setValue(mCartModel);
        }else{
            List<GetAllCourseReponse>list = new ArrayList<>();
            CartModel cartModel = new CartModel();
            cartModel.setList(list);
            getmCartModel.setValue(cartModel);
        }

        if(editText != null){
            editText.setText(total+"");
        }

    }

    public void initCartToPay(){
        mCartModelToPay = mGson.fromJson(mSharePrefs.getCartToPay(), CartModel.class);
        if (mCartModelToPay != null) {
            getmCartModelToPay.setValue(mCartModelToPay);
        }else{
            List<GetAllCourseReponse>list = new ArrayList<>();
            CartModel cartModel = new CartModel();
            cartModel.setList(list);
            getmCartModelToPay.setValue(cartModel);
        }
    }

    public LiveData<CartModel> getDataCartToPay() {
        return getmCartModelToPay;
    }

    public LiveData<CartModel> getDataCart() {
        return getmCartModel;
    }

    public void saveCartToBy(){
        CartModel mCartModel = mGson.fromJson(mSharePrefs.getCart(), CartModel.class);
        for (GetAllCourseReponse reponse : mCartModel.getList()){
            mSharePrefs.saveCartToPay(reponse);
        }
        mSharePrefs.removeCart();
    }

}
