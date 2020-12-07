package com.example.mobileappbook.src.fragment.fragment_cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCourseReponse;
import com.example.mobileappbook.model.CartModel;
import com.example.mobileappbook.src.adapter.cart_adapter.CartAdapter;
import com.example.mobileappbook.src.page.payment.PaymentActivity;
import com.example.mobileappbook.utils.SharePrefs;
import com.google.gson.Gson;

public class FragmentCart extends Fragment implements View.OnClickListener {
    private View mView;
    private TextView mTxtTotal;
    private RecyclerView mRecyclerCart;
    //variable
    private Gson mGson = new Gson();
    private CartModel mCartModel;
    private CartAdapter mAdapter;
    private int mTotal = 0;
    private SharePrefs mSharePrefs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_cart, container, false);
        initView();
        init();
        listenerOnclicked();
        return mView;
    }

    //lang nghe su kien onclicked
    private void listenerOnclicked() {
        mView.findViewById(R.id.card_pay).setOnClickListener(this);
    }

    private void init() {
        mSharePrefs = new SharePrefs(getContext());
        mCartModel = mGson.fromJson(mSharePrefs.getCart(), CartModel.class);
        if (mCartModel != null) {
            for (GetAllCourseReponse reponse : mCartModel.getList()) {
                mTotal += reponse.getPrice();
            }
            mAdapter = new CartAdapter(mCartModel.getList());
            mRecyclerCart.setHasFixedSize(true);
            mRecyclerCart.setLayoutManager(new GridLayoutManager(getContext(), 1));
            mRecyclerCart.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

            mTxtTotal.setText(mTotal + "");
        }
    }

    //anh xa view
    private void initView() {
        mRecyclerCart = mView.findViewById(R.id.recycler_cart);
        mTxtTotal = mView.findViewById(R.id.txt_total1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_pay:
                if (mCartModel != null) {
                    startActivity(new Intent(getActivity(), PaymentActivity.class));
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                }else{
                    Toast.makeText(getContext(), "Your cart is empty", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
