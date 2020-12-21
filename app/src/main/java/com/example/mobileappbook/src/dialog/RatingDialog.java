package com.example.mobileappbook.src.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.featured.GetAllCourseReponse;
import com.example.mobileappbook.src.viewmodel.featured.detail_buy.DetailBuyViewModel;
import com.example.mobileappbook.utils.Constain;
import com.example.mobileappbook.utils.Helpers;

import java.util.Map;

public class RatingDialog extends DialogFragment implements View.OnClickListener {
    private View mView;
    private EditText mEdtComment;
    private Dialog mDialog;
    //variable
    private GetAllCourseReponse mReponse;
    private DetailBuyViewModel mDetailBuyViewModel;
    private RatingBar mRatingBar;
    private int mRating = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.dialog_rating,container,false);
        initViewModel();
        initView();
        init();
        listenerOnclicked();
        return mView;
    }
    //ánh xạ view
    private void initView() {
        mRatingBar = mView.findViewById(R.id.rating);
        mEdtComment  = mView.findViewById(R.id.edt_comment);
    }

    //khởi tạo viewmodel
    private void initViewModel() {
        mDetailBuyViewModel = ViewModelProviders.of(getActivity()).get(DetailBuyViewModel.class);
        //lắng nghe và quan sát dữ liệu
        mDetailBuyViewModel.getPostRatingRepone().observe(getViewLifecycleOwner(), new Observer<Map>() {
            @Override
            public void onChanged(Map map) {
                mDialog.dismiss();
                if(map.get(Constain.keyMapErr) != null){
                    Toast.makeText(getContext(),map.get(Constain.keyMapErr)+"" , Toast.LENGTH_SHORT).show();
                }else{
                    Helpers.hideFragmentDialog(RatingDialog.this,Constain.keyRatingDialog);
                }
            }
        });
    }

    //lắng nghe sự kiện onclicked view
    private void listenerOnclicked() {
        mView.findViewById(R.id.card_4).setOnClickListener(this);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mRating = (int) rating;
            }
        });
    }

    private void init() {
        mReponse = (GetAllCourseReponse) getArguments().getSerializable(Constain.keyGetAllcourseReponse);
    }

    @Override
    public void onResume() {
        Window window = getDialog().getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        super.onResume();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.colorPickerStyle);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_4:
                mDialog = Helpers.showLoadingDialog(getActivity());
                mDialog.show();
                String comment = mEdtComment.getText().toString().equals(null) ? " " : mEdtComment.getText().toString();
                mDetailBuyViewModel.postRating(comment,mRating,mReponse.getId());
                break;
        }
    }
}
