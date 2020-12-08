package com.example.mobileappbook.src.page.payment;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.body.PaymentBody;
import com.example.mobileappbook.cores.reponse.user_reponse.UserReponse;
import com.example.mobileappbook.cores.services.APIServices;
import com.example.mobileappbook.cores.services.DataService;
import com.example.mobileappbook.model.CartModel;
import com.example.mobileappbook.src.page.tabbar.TabBarActivity;
import com.example.mobileappbook.utils.Helpers;
import com.example.mobileappbook.utils.SharePrefs;
import com.google.gson.Gson;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.Stripe;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {
    private CardInputWidget mCardInputWidget;
    private String TAG = "PaymentActivity";
    private Gson gson = new Gson();
    private SharePrefs mSharePrefs;
    private UserReponse mUserReponse;
    private CartModel mCartModel;
    private Dialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        initView();
        init();
    }

    private void init() {
        mSharePrefs = new SharePrefs(getApplicationContext());
        mUserReponse = gson.fromJson(mSharePrefs.getUser(), UserReponse.class);
        mCartModel = gson.fromJson(mSharePrefs.getCart(), CartModel.class);
        mCardInputWidget.setPostalCodeEnabled(false);

        findViewById(R.id.payBtn).setOnClickListener(this);
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
                        mDialog = Helpers.showLoadingDialog(this);
                        mDialog.show();
                        Card card = mCardInputWidget.getCard();
                        Stripe stripe = new Stripe(PaymentActivity.this, "pk_test_y8urHXEikr7ysm3tk7uRilcp00aTSdh57w");

                        stripe.createCardToken(
                                card,
                                new ApiResultCallback<Token>() {
                                    public void onSuccess(@NonNull Token token) {
                                        Log.d(TAG, "onSuccess: " + token);
                                        try {
                                            for (int i = 0; i < mCartModel.getList().size(); i++) {
                                                final PaymentBody paymentBody = new PaymentBody(mUserReponse.getName(), mUserReponse.getEmail(),
                                                        token.toString(), mCartModel.getList().get(i).getPrice().toString(), mCartModel.getList().get(i).getCategory().getId(), mUserReponse.getId());
                                                new AsyncTask<Void, Void, Void>() {
                                                    @Override
                                                    protected Void doInBackground(Void... voids) {
                                                        DataService dataService = APIServices.getService();
                                                        Call<Map> call = dataService.pay(paymentBody);
                                                        call.enqueue(new Callback<Map>() {
                                                            @Override
                                                            public void onResponse(Call<Map> call, Response<Map> response) {
                                                                Log.d(TAG, "onResponse: " + response.toString());
                                                                if (response.isSuccessful()) {
                                                                    mSharePrefs.removeCart();
                                                                    Toast.makeText(PaymentActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                                                    finish();
                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(Call<Map> call, Throwable t) {
                                                                Log.d(TAG, "onResponse: " + t.toString());
                                                            }
                                                        });
                                                        return null;
                                                    }
                                                }.execute();
                                            }
                                        } catch (Exception e) {
                                            Log.d(TAG, "onSuccess: "+e.toString());
                                        }
                                    }

                                    public void onError(@NonNull Exception error) {
                                        Log.d(TAG, "onError: "+error.toString());
                                        Toast.makeText(PaymentActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                    mDialog.dismiss();
                    mSharePrefs.removeCart();
                    finish();
                    }catch (Exception e1){
                        mDialog.dismiss();
                        mSharePrefs.removeCart();
                        startActivity(new Intent(getApplicationContext(), TabBarActivity.class));
                        finish();
                    }
                }else{
                    Toast.makeText(this, "giỏ hàng rỗng", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
