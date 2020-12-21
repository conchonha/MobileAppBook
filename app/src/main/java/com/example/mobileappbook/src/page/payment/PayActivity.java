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
import com.example.mobileappbook.src.viewmodel.cart.CartViewModel;
import com.example.mobileappbook.utils.Helpers;
import com.example.mobileappbook.utils.SharePrefs;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.Stripe;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
    JSONArray cartArray = new JSONArray();
    JSONObject sendJO = new JSONObject();

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
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("_id",reponse.getId());
                jsonObject.put("created_at",reponse.getCreatedAt());
                jsonObject.put("discount",reponse.getDiscount());
                jsonObject.put("ranking",reponse.getRanking());
                jsonObject.put("is_checked",reponse.getIsChecked());
                jsonObject.put("is_required",reponse.getRequired());
                jsonObject.put("name",reponse.getName());
                jsonObject.put("price",reponse.getPrice());
                jsonObject.put("image",reponse.getImage());
                cartArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
                                        Log.d(TAG, "onClick: "+mGson.toJson(token));
                                        JSONObject tokenJo = new JSONObject();
                                        JSONObject cardJo = new JSONObject();

                                        try {
                                            cardJo.put("id",token.getCard().getId());
                                            cardJo.put("object","card");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            tokenJo.put("id",token.getId());
                                            tokenJo.put("object","token");
                                            tokenJo.put("card",cardJo);
                                            tokenJo.put("client_ip","");
                                            tokenJo.put("created",token.getCreated().getTime());
                                            tokenJo.put("type","card");
                                            tokenJo.put("used",token.getUsed());
                                            tokenJo.put("email",mUserReponse.getEmail());
                                            tokenJo.put("livemode",token.getLivemode());
                                            tokenJo.put("name",mUserReponse.getName());
                                            tokenJo.put("bank_account",token.getBankAccount());
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            sendJO.put("token",tokenJo);
                                            sendJO.put("cart",cartArray);
                                            sendJO.put("idUser",mUserReponse.getId());
                                            pay();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
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
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), sendJO.toString());
        DataService dataService = APIServices.getService();
        Call<Map>call = dataService.pay(body);
        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                mDialog.dismiss();
                Log.d(TAG, "onResponse: "+response.toString());
                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse: -sucess"+mGson.toJson(response.body()));
                    Toast.makeText(PayActivity.this, "Payment success", Toast.LENGTH_SHORT).show();
                }else {
                    Log.d(TAG, "onResponse: -error"+response.errorBody());
                    try {
                        String reponse = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(reponse);
                        Toast.makeText(PayActivity.this, jsonObject.getString("message")+"", Toast.LENGTH_SHORT).show();
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(PayActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
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
