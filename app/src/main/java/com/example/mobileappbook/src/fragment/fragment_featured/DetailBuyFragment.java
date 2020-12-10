package com.example.mobileappbook.src.fragment.fragment_featured;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCourseReponse;
import com.example.mobileappbook.model.CallbackFeatured;
import com.example.mobileappbook.utils.Constain;
import com.example.mobileappbook.utils.Helpers;
import com.example.mobileappbook.utils.SharePrefs;

public class DetailBuyFragment extends Fragment implements View.OnClickListener,CallbackFeatured{
    private View mView;
    private TextView mTxtName,mTxtNameCategory,mTxtPrice,mTxtPriceSale,mTxtTime,mTxtDescription;
    //variable
    private GetAllCourseReponse mReponse;
    private Dialog mDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_fragment_detail_buy,container,false);
        initView();
        init();
        listenerOnclicked();
        return mView;
    }

    //lang nghe su kien onclick
    private void listenerOnclicked() {
        mView.findViewById(R.id.img_back).setOnClickListener(this);
        mView.findViewById(R.id.card_4).setOnClickListener(this);
    }

    private void init() {
        if(mReponse != null){
            mTxtName.setText(mReponse.getName());
            mTxtDescription.setText(mReponse.getDescription());
            mTxtNameCategory.setText(mReponse.getCategory().getName());
            mTxtPrice.setText(mReponse.getPrice().toString());
            mTxtPriceSale.setText(mReponse.getV().toString());
            mTxtTime.setText(mReponse.getCreatedAt());
        }
    }

    //anh xa view
    private void initView() {
        mTxtName = mView.findViewById(R.id.txt_name);
        mTxtNameCategory = mView.findViewById(R.id.txt_name_category);
        mTxtPrice = mView.findViewById(R.id.txt_price_featured);
        mTxtPriceSale = mView.findViewById(R.id.txt_price_sale_featured);
        mTxtTime = mView.findViewById(R.id.txt_time);
        mTxtDescription = mView.findViewById(R.id.txt_description);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                Fragment prev = getFragmentManager().findFragmentByTag(Constain.fragmentDetailBuy);
                if (prev != null) {
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.slide_out_right,R.anim.slide_out_fragment)
                            .remove(prev)
                            .commit();
                }
                break;
            case R.id.card_4:
                mDialog = Helpers.showLoadingDialog(getActivity());
                mDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SharePrefs sharePrefs = new SharePrefs(getContext());
                        sharePrefs.saveCart(mReponse,getContext());
                        mDialog.dismiss();
                    }
                },3000);
                break;
        }
    }

    @Override
    public void onClickItem(GetAllCourseReponse reponse) {
        mReponse = reponse;
    }
}
