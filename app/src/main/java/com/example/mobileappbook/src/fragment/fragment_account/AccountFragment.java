package com.example.mobileappbook.src.fragment.fragment_account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.acount.UserReponse;
import com.example.mobileappbook.src.page.setting.ActivitySetting;
import com.example.mobileappbook.utils.Constain;
import com.example.mobileappbook.utils.SharePrefs;
import com.google.gson.Gson;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class AccountFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private UserReponse mUserReponse;
    private TextView mTxtYourName,mTxtPhoneNumber,mTxtEmail,mTxtPhone1,mTxtAddress,mTxtJob,mTxtTime;
    private ImageView mImageAvatar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_account,container,false);
        initView();
        listenerOnclicked();
        init();
        return mView;
    }

    private void initView() {
        mTxtYourName = mView.findViewById(R.id.txt_yourName);
        mTxtPhoneNumber = mView.findViewById(R.id.txt_phoneNumber);
        mTxtEmail = mView.findViewById(R.id.txt_email);
        mTxtPhone1 = mView.findViewById(R.id.txt_phone1);
        mTxtAddress = mView.findViewById(R.id.txtAddress);
        mTxtJob = mView.findViewById(R.id.txt_job);
        mTxtTime = mView.findViewById(R.id.txt_time);
        mImageAvatar = mView.findViewById(R.id.img_avatar);
    }

    private void init() {
        mUserReponse = new Gson().fromJson(new SharePrefs(getContext()).getUser(),UserReponse.class);
        if(mUserReponse != null){
            mTxtYourName.setText(mUserReponse.getName());
            mTxtPhoneNumber.setText(mUserReponse.getPhone());
            mTxtEmail.setText(mUserReponse.getEmail());
            mTxtPhone1.setText(mUserReponse.getPhone());
            mTxtAddress.setText(mUserReponse.getAddress());
            mTxtJob.setText(mUserReponse.getDescription());
            mTxtTime.setText(mUserReponse.getCreatedAt());
            Picasso.get().load(Constain.userUrlImg+mUserReponse.getImage()).placeholder(R.drawable.useravatar).error(R.drawable.useravatar).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(mImageAvatar);
        }
    }

    //lang nghe su kien onclicked view
    private void listenerOnclicked() {
        mView.findViewById(R.id.btn_setting).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_setting:
                startActivity(new Intent(getContext(), ActivitySetting.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                break;
        }
    }
}
