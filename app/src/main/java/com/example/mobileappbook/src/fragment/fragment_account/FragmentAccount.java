package com.example.mobileappbook.src.fragment.fragment_account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mobileappbook.R;
import com.example.mobileappbook.src.page.setting.ActivitySetting;

public class FragmentAccount extends Fragment implements View.OnClickListener {
    private View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_accountinfo,container,false);
        listenerOnclicked();
        return mView;
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
