package com.example.mobileappbook.src.fragment.fragment_featured;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCategoryReponse;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCourseReponse;
import com.example.mobileappbook.src.page.home.DetailBuyActivity;
import com.example.mobileappbook.src.viewmodel.featured.FeaturedViewModel;

import java.util.List;

public class FragmentFeatured extends Fragment implements View.OnClickListener{
    private View mView;
    private FeaturedViewModel mFeaturedViewModel;
    private String TAG = "FragmentFeatured";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_menu_detail,container,false);
        initViewModel();
        listenerOnclicked();
        return mView;
    }

    private void initViewModel() {
        mFeaturedViewModel = ViewModelProviders.of(getActivity()).get(FeaturedViewModel.class);
        mFeaturedViewModel.getAllCategory().observe(getViewLifecycleOwner(), new Observer<List<GetAllCategoryReponse>>() {
            @Override
            public void onChanged(List<GetAllCategoryReponse> getAllCategoryReponse) {
                Log.d(TAG, "onChanged: getAllCategory - "+getAllCategoryReponse.size());
            }
        });

        mFeaturedViewModel.getAllCourseReponse().observe(getViewLifecycleOwner(), new Observer<List<GetAllCourseReponse>>() {
            @Override
            public void onChanged(List<GetAllCourseReponse> getAllCourseReponse) {
                Log.d(TAG, "onChanged: getAllCategory - "+getAllCourseReponse.size());
            }
        });
    }
    private void listenerOnclicked() {
        mView.findViewById(R.id.card1).setOnClickListener(this);
        mView.findViewById(R.id.card2).setOnClickListener(this);
        mView.findViewById(R.id.card3).setOnClickListener(this);
        mView.findViewById(R.id.card4).setOnClickListener(this);
        mView.findViewById(R.id.card5).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card1:
            case R.id.card2:
            case R.id.card3:
            case R.id.card4:
            case R.id.card5:
                startActivity(new Intent(getContext(), DetailBuyActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                break;
        }

    }
}
