package com.example.mobileappbook.src.fragment.fragment_cart;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCategoryReponse;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCourseReponse;
import com.example.mobileappbook.model.CartModel;
import com.example.mobileappbook.src.viewmodel.cart.CartViewModel;
import com.example.mobileappbook.src.adapter.cart_adapter.CartAdapter;
import com.example.mobileappbook.src.page.payment.PayActivity;
import com.example.mobileappbook.utils.Helpers;

import java.util.ArrayList;
import java.util.List;

public class cartFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private TextView mTxtTotal;
    private RecyclerView mRecyclerCart;
    //variable
    private CartAdapter mAdapter;
    private CartViewModel mCartViewModel;
    private Dialog mDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_cart, container, false);
        initView();
        initViewModel();
        init();
        listenerOnclicked();
        return mView;
    }

    //khoi tao viewmodel
    private void initViewModel() {
        mCartViewModel = ViewModelProviders.of(getActivity()).get(CartViewModel.class);
        mCartViewModel.initstalise(mTxtTotal);

        mCartViewModel.getDataCart().observe(getViewLifecycleOwner(), new Observer<CartModel>() {
            @Override
            public void onChanged(CartModel cartModel) {
                Log.d("TAG", "onChanged: "+cartModel.getList().size());
                mDialog.dismiss();
                mAdapter.setList(cartModel.getList());
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    //lang nghe su kien onclicked
    private void listenerOnclicked() {
        mView.findViewById(R.id.card_pay).setOnClickListener(this);
    }

    private void init() {
        mDialog = Helpers.showLoadingDialog(getActivity());
        mAdapter = new CartAdapter();
        if(mAdapter.getItemCount() == 0){
            mDialog.show();
        }
        mRecyclerCart.setHasFixedSize(true);
        mRecyclerCart.setLayoutManager(new GridLayoutManager(getContext(), 1));
        mRecyclerCart.setAdapter(mAdapter);
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
                if (mAdapter.getItemCount() != 0) {
                    startActivity(new Intent(getActivity(), PayActivity.class));
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                }else{
                    Toast.makeText(getContext(), "Your cart is empty", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
