package com.example.mobileappbook.src.fragment.fragment_cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.mobileappbook.R;
import com.example.mobileappbook.src.page.payment.PaymentActivity;

public class FragmentCart extends Fragment {
    private View mView;
    private CardView mCardPay;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_cart,container,false);
        initView();
        init();
        return mView;
    }

    private void init() {
        mCardPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mCardPay = mView.findViewById(R.id.card_pay);
    }
}
