package com.example.mobileappbook.src.page.payment;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.body.PaymentBody;
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
    JSONArray cartArray=new JSONArray();

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
                                        JSONObject jsonCourse = new JSONObject();
                                        for (GetAllCourseReponse reponse : mCartModel.getList()){
                                           // cartArray.put()
                                        }
//                                        try {
//                                            new AsyncTask<Void, Void, Void>() {
//                                                @Override
//                                                protected void onPreExecute() {
//                                                    super.onPreExecute();
//                                                    mDialog = Helpers.showLoadingDialog(PayActivity.this);
//                                                    mDialog.show();
//                                                }
//
//                                                @Override
//                                                protected Void doInBackground(Void... voids) {
//                                                    for (int i = 0; i < mCartModel.getList().size(); i++) {
//                                                        final PaymentBody paymentBody = new PaymentBody(mUserReponse.getName(), mUserReponse.getEmail(),
//                                                                token.getId(), mCartModel.getList().get(i).getPrice().toString(), mCartModel.getList().get(i).getCategory().getId(), mUserReponse.getId());
//                                                                final int finalI = i;
//                                                                DataService dataService = APIServices.getService();
//                                                                Call<Map> call = dataService.pay(paymentBody);
//                                                                call.enqueue(new Callback<Map>() {
//                                                                    @Override
//                                                                    public void onResponse(Call<Map> call, Response<Map> response) {
//                                                                        Log.d(TAG, "onResponse: " + response.toString());
//                                                                        Toast.makeText(PayActivity.this, response.message(), Toast.LENGTH_SHORT).show();
//                                                                        if (response.isSuccessful()) {
//                                                                            Log.d(TAG, "onResponse: -success-" + mGson.toJson(response.body()));
//                                                                            Helpers.intentClearOnTapSelected3(PayActivity.this, TabBarActivity.class);
//                                                                            mCartModel.getList().remove(finalI);
//                                                                        } else {
//                                                                            Log.d(TAG, "onResponse: -err-" + mGson.toJson(response.errorBody()));
//                                                                        }
//                                                                    }
//
//                                                                    @Override
//                                                                    public void onFailure(Call<Map> call, Throwable t) {
//                                                                        Log.d(TAG, "onFailure: --err" + t.toString());
//                                                                        Toast.makeText(PayActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                                                                    }
//                                                                });
//                                                    }
//                                                    return null;
//                                                }
//
//                                                @Override
//                                                protected void onPostExecute(Void aVoid) {
//                                                    super.onPostExecute(aVoid);
//                                                    try {
//                                                        Thread.sleep(3000);
//                                                        mDialog.dismiss();
//                                                        mCartViewModel.remoCart();
//                                                        Helpers.intentClearOnTapSelected3(PayActivity.this,TabBarActivity.class);
//                                                        finish();
//                                                    } catch (InterruptedException e) {
//                                                        e.printStackTrace();
//                                                    }
//                                                }
//                                            }.execute();
//                                        } catch (Exception e) {
//                                            Log.d(TAG, "onError: " + e.toString());
//                                            Toast.makeText(PayActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
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
                    Toast.makeText(this, "giỏ hàng rỗng", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
