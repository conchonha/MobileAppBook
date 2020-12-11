package com.example.mobileappbook.src.viewmodel.cart;

import android.app.Application;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCourseReponse;
import com.example.mobileappbook.model.CartModel;
import com.example.mobileappbook.utils.SharePrefs;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends AndroidViewModel {
    private SharePrefs mSharePrefs;
    private Gson mGson = new Gson();
    private CartModel mCartModel;
    private MutableLiveData<CartModel>getmCartModel = new MutableLiveData<>();

    public CartViewModel(@NonNull Application application) {
        super(application);
    }

    public void initstalise(TextView editText){
        int total  = 0;
        mSharePrefs = new SharePrefs(getApplication());
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

    public MutableLiveData<CartModel> getDataCart() {
        return getmCartModel;
    }

    public void setmCartModel(CartModel mCartModel) {
        this.getmCartModel.postValue(mCartModel);
    }
}
