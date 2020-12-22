package com.example.mobileappbook.src.page.payment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.cores.reponse.featured.GetAllCourseReponse;
import com.example.mobileappbook.cores.services.APIServices;
import com.example.mobileappbook.cores.services.DataService;
import com.example.mobileappbook.model.CartModel;
import com.example.mobileappbook.src.page.tabbar.TabBarActivity;
import com.example.mobileappbook.src.viewmodel.cart.CartViewModel;
import com.example.mobileappbook.utils.Helpers;
import com.example.mobileappbook.utils.SharePrefs;
import com.google.gson.Gson;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.Stripe;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayActivity extends AppCompatActivity implements View.OnClickListener {
    private CardInputWidget mCardInputWidget;
    private String TAG = "PaymentActivity";
    private Gson mGson = new Gson();
    private SharePrefs mSharePrefs;
    private UserReponse mUserReponse;
    private CartModel mCartModel;
    private Dialog mDialog;
    private CartViewModel mCartViewModel;
    private JSONArray mCartArraySend = new JSONArray();
    private JSONObject mCendJO = new JSONObject();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        initViewModel();
        initView();
        init();
        listenerOnclicked();
    }

    private void initViewModel() {
        mCartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
    }

    private void listenerOnclicked() {
        findViewById(R.id.payBtn).setOnClickListener(this);
    }

    private void init() {
        mSharePrefs = new SharePrefs(getApplicationContext());
        mUserReponse = mGson.fromJson(mSharePrefs.getUser(), UserReponse.class);
        mCartModel = mGson.fromJson(mSharePrefs.getCart(), CartModel.class);
        mCardInputWidget.setPostalCodeEnabled(false);
        //put cart
        for (GetAllCourseReponse reponse : mCartModel.getList()){
            try {
                JSONObject jo = new JSONObject();
                jo.put("_id", reponse.getId());
                mCartArraySend.put(jo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            mCendJO.put("cart", mCartArraySend);
            mCendJO.put("idUser", mUserReponse.getId());
        } catch (JSONException jx) {
            jx.printStackTrace();
        }
    }

    private void initView() {
        mCardInputWidget = findViewById(R.id.cardInput);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.payBtn:
                if (mCartModel != null) {
                    try {
                        final Card card = mCardInputWidget.getCard();
                        Stripe stripe = new Stripe(PayActivity.this, "pk_test_y8urHXEikr7ysm3tk7uRilcp00aTSdh57w");
                        stripe.createCardToken(
                                card,
                                new ApiResultCallback<Token>() {
                                    public void onSuccess(@NonNull final Token token) {
                                        JSONObject tokenJO = new JSONObject();
                                        JSONObject cardJO = new JSONObject();
                                        try {
                                            cardJO.put("id", token.getCard().getId());
                                            cardJO.put("object", "card");

                                        } catch (JSONException jx) {
                                            jx.printStackTrace();
                                        }

                                        try {
                                            tokenJO.put("id", token.getId());
                                            tokenJO.put("object", "token");
                                            tokenJO.put("card", cardJO);
                                            tokenJO.put("client_ip", "");
                                            tokenJO.put("created", token.getCreated().getTime());
                                            tokenJO.put("type", "card");
                                            tokenJO.put("used", token.getUsed());
                                            tokenJO.put("email", mUserReponse.getEmail());
                                            tokenJO.put("livemode", token.getLivemode());
                                            tokenJO.put("name", mUserReponse.getName());
                                            tokenJO.put("bank_account", token.getBankAccount());

                                        } catch (JSONException jx) {
                                            jx.printStackTrace();
                                        }

                                        try {
                                            mCendJO.put("token", tokenJO);
                                        } catch (JSONException jx) {
                                            jx.printStackTrace();
                                        }
                                        pay();
                                    }
                                    public void onError(@NonNull Exception error) {
                                        Log.d(TAG, "onError: " + error.toString());
                                        Toast.makeText(PayActivity.this, "Stripper is incorrect", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                    } catch (Exception e1) {
                        Toast.makeText(PayActivity.this, "card is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "empty cart", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void pay() {
        mDialog = Helpers.showLoadingDialog(this);
        mDialog.show();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), mCendJO.toString());
        DataService dataService = APIServices.getService();
        Call<Map>call = dataService.pay(body);
        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                mDialog.dismiss();
                Log.d(TAG, "onResponse: "+response.toString());
                Toast.makeText(PayActivity.this, "Success", Toast.LENGTH_SHORT).show();
                mCartViewModel.saveCartToBy();
                Helpers.intentClearOnTapSelected3(PayActivity.this, TabBarActivity.class);
//                if(response.isSuccessful()){
//                    Log.d(TAG, "onResponse: -sucess"+mGson.toJson(response.body()));
//                    Toast.makeText(PayActivity.this, "Payment success", Toast.LENGTH_SHORT).show();
//                }else {
//                    Log.d(TAG, "onResponse: -error"+response.errorBody());
//                    try {
//                        String reponse = response.errorBody().string();
//                        JSONObject jsonObject = new JSONObject(reponse);
//                        Toast.makeText(PayActivity.this, jsonObject.getString("message")+"", Toast.LENGTH_SHORT).show();
//                    } catch (IOException | JSONException e) {
//                        e.printStackTrace();
//                        Toast.makeText(PayActivity.this, response.message(), Toast.LENGTH_SHORT).show();
//                    }
//                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                mDialog.dismiss();
                Log.d(TAG, "onFailure: "+t.toString());
                Toast.makeText(PayActivity.this, t.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
