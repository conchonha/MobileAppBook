package com.example.mobileappbook.src.fragment.fragment_featured.detail_buy;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.featured.GetAllCourseReponse;
import com.example.mobileappbook.model.CallbackFeatured;
import com.example.mobileappbook.src.page.tabbar.TabBarActivity;
import com.example.mobileappbook.src.page.webview.WebView;
import com.example.mobileappbook.src.viewmodel.featured.detail_buy.DetailBuyViewModel;
import com.example.mobileappbook.utils.Constain;
import com.example.mobileappbook.utils.Helpers;
import com.example.mobileappbook.utils.SharePrefs;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class DetailBuyFragment extends Fragment implements View.OnClickListener, CallbackFeatured {
    private View mView;
    private TextView mTxtName, mTxtNameCategory, mTxtPrice, mTxtPriceSale, mTxtTime, mTxtDescription;
    private ImageView mImageAvatar,mImg1,mImg2,mImg3,mImg4,mImg5;
    //variable
    private GetAllCourseReponse mReponse;
    private Dialog mDialog;
    private DetailBuyViewModel mDetailBuyViewModel;
    private CardView mCardRating;
    private RelativeLayout mRelativeLayout;
    private LinearLayout mLinearLayout;
    private int mTotal = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_fragment_detail_buy, container, false);
        initView();
        initViewModel();
        init();
        listenerOnclicked();
        return mView;
    }

    //khởi tạo viewmodel
    private void initViewModel() {
        mDetailBuyViewModel = ViewModelProviders.of(getActivity()).get(DetailBuyViewModel.class);
    }

    //lang nghe su kien onclick
    private void listenerOnclicked() {
        mView.findViewById(R.id.img_back).setOnClickListener(this);
        mView.findViewById(R.id.card_4).setOnClickListener(this);
        mView.findViewById(R.id.relative1).setOnClickListener(this);
        mView.findViewById(R.id.relative2).setOnClickListener(this);
        mView.findViewById(R.id.relative3).setOnClickListener(this);
        mView.findViewById(R.id.relative4).setOnClickListener(this);
        mView.findViewById(R.id.relative5).setOnClickListener(this);
        mCardRating.setOnClickListener(this);
    }

    private void init() {
        if (mReponse != null) {
            mTxtName.setText(mReponse.getName());
            mTxtDescription.setText(mReponse.getDescription());
            mTxtNameCategory.setText(mReponse.getCategory().getName());
            mTxtPrice.setText(mReponse.getPrice().toString());
            mTxtPrice.setPaintFlags(mTxtPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            mTxtPriceSale.setText(mReponse.getDiscount().toString() + "%");
            mTxtTime.setText(mReponse.getCreatedAt());
            Picasso.get().load(Constain.coursesUrlImg + mReponse.getImage()).placeholder(R.drawable.empty23).error(R.drawable.empty23).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(mImageAvatar);
            if (mReponse.getDiscount() != 0) {
                int price = mReponse.getPrice() - (mReponse.getPrice() * mReponse.getDiscount()) / 100;
                mTxtPrice.setText(price + "");
            }
            if (mReponse.getPrice() == 0) {
                mTxtPrice.setText("Miễn phí");
            }
            mDetailBuyViewModel.getComment(mReponse.getId());
            mDetailBuyViewModel.getSuggestedCourses(mReponse.getIdUser().getId().toString());
        }
    }

    //anh xa view
    private void initView() {
        mLinearLayout = mView.findViewById(R.id.linner);
        mRelativeLayout = mView.findViewById(R.id.relative_3);
        mTxtName = mView.findViewById(R.id.txt_name);
        mTxtNameCategory = mView.findViewById(R.id.txt_name_category);
        mTxtPrice = mView.findViewById(R.id.txt_price_featured);
        mTxtPriceSale = mView.findViewById(R.id.txt_price_sale_featured);
        mTxtTime = mView.findViewById(R.id.txt_time);
        mTxtDescription = mView.findViewById(R.id.txt_description);
        mImageAvatar = mView.findViewById(R.id.img_avatar);
        mCardRating = mView.findViewById(R.id.card_rating);
        mImg1 = mView.findViewById(R.id.img1);
        mImg2 = mView.findViewById(R.id.img2);
        mImg3 = mView.findViewById(R.id.img3);
        mImg4 = mView.findViewById(R.id.img4);
        mImg5 = mView.findViewById(R.id.img5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                TabBarActivity.mTabLayout.setVisibility(View.GONE);
                Helpers.removeFragment(getFragmentManager(), R.anim.slide_out_right, R.anim.slide_out_fragment, Constain.fragmentDetailBuy);
                break;
            case R.id.card_4:
                mDialog = Helpers.showLoadingDialog(getActivity());
                mDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDialog.dismiss();
                        SharePrefs sharePrefs = new SharePrefs(getContext());
                        if(!sharePrefs.saveCart(mReponse, getContext())){
                            mCardRating.setVisibility(View.VISIBLE);
                            mView.findViewById(R.id.relative_3).setVisibility(View.GONE);
                            mRelativeLayout.setVisibility(View.GONE);
                            mLinearLayout.setVisibility(View.VISIBLE);
                        }
                    }
                }, 3000);
                break;
            case R.id.relative1:
                mImg1.setBackgroundResource(R.drawable.ic_path);
                mView.findViewById(R.id.relative1).setEnabled(false);
                startActivity(new Intent(getActivity(), WebView.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                mTotal+= 20;
                break;
            case R.id.relative2:
                mImg2.setBackgroundResource(R.drawable.ic_path);
                mView.findViewById(R.id.relative2).setEnabled(false);
                mTotal+= 20;
                startActivity(new Intent(getActivity(), WebView.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                break;
            case R.id.relative3:
                mImg3.setBackgroundResource(R.drawable.ic_path);
                mView.findViewById(R.id.relative3).setEnabled(false);
                mTotal+= 20;
                startActivity(new Intent(getActivity(), WebView.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                break;
            case R.id.relative4:
                mImg4.setBackgroundResource(R.drawable.ic_path);
                mView.findViewById(R.id.relative4).setEnabled(false);
                mTotal+= 20;
                startActivity(new Intent(getActivity(), WebView.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                break;
            case R.id.relative5:
                mView.findViewById(R.id.img5).setBackgroundResource(R.drawable.ic_path);
                mView.findViewById(R.id.relative5).setEnabled(false);
                mTotal+= 20;
                startActivity(new Intent(getActivity(), WebView.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                break;
            case R.id.card_rating:
                if(mTotal >= 80){
                    
                }else{
                    Toast.makeText(getContext(), "Bạn chưa hoàn thành 80% khoá học", Toast.LENGTH_SHORT).show();
                }
                break;
                

        }
    }

    @Override
    public void onClickItem(GetAllCourseReponse reponse) {
        mReponse = reponse;
    }
}
