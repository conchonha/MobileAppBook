package com.example.mobileappbook.src.fragment.fragment_featured;

import android.app.Dialog;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCategoryReponse;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCourseReponse;
import com.example.mobileappbook.model.CallbackFeatured;
import com.example.mobileappbook.src.adapter.featured_adapter.FeaturedAdapter;
import com.example.mobileappbook.src.viewmodel.featured.FeaturedViewModel;
import com.example.mobileappbook.utils.Constain;
import com.example.mobileappbook.utils.Helpers;

import java.util.List;

public class FragmentFeatured extends Fragment implements CallbackFeatured {
    private View mView;
    private Dialog mDialog;
    //variable
    private FeaturedViewModel mFeaturedViewModel;
    private String TAG = "FragmentFeatured";
    private FeaturedAdapter mFeaturedAdapter;
    private RecyclerView mRecyclerviewFeatured;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_featured, container, false);
        initViewModel();
        initView();
        init();
        return mView;
    }

    private void init() {
        mFeaturedAdapter = new FeaturedAdapter(this);
        if(mFeaturedAdapter.getItemCount() == 0){
            mDialog = Helpers.showLoadingDialog(getActivity());
            mDialog.show();
        }

        mRecyclerviewFeatured.setLayoutManager(new GridLayoutManager(getContext(), 1));
        mRecyclerviewFeatured.setHasFixedSize(true);
        mRecyclerviewFeatured.setAdapter(mFeaturedAdapter);
    }

    //anh xa view
    private void initView() {
        mRecyclerviewFeatured = mView.findViewById(R.id.recycler_featured);
    }

    //khoi tao viewmodel
    private void initViewModel() {
        mFeaturedViewModel = ViewModelProviders.of(getActivity()).get(FeaturedViewModel.class);

        //lang nghe va quan sat du lieu
        mFeaturedViewModel.getAllCategory().observe(getViewLifecycleOwner(), new Observer<List<GetAllCategoryReponse>>() {
            @Override
            public void onChanged(List<GetAllCategoryReponse> getAllCategoryReponse) {
            }
        });

        mFeaturedViewModel.getAllCourseReponse().observe(getViewLifecycleOwner(), new Observer<List<GetAllCourseReponse>>() {
            @Override
            public void onChanged(List<GetAllCourseReponse> getAllCourseReponse) {
                Log.d(TAG, "onChanged: getAllCourseReponse - " + getAllCourseReponse.size());
                mDialog.dismiss();
                mFeaturedAdapter.setmListCourseReponse(getAllCourseReponse);
                mFeaturedAdapter.notifyDataSetChanged();
            }
        });

        mFeaturedViewModel.getmGetFreeCourseReponse().observe(getViewLifecycleOwner(), new Observer<List<GetAllCourseReponse>>() {
            @Override
            public void onChanged(List<GetAllCourseReponse> getAllCourseReponses) {

            }
        });

        mFeaturedViewModel.getmGetTopCourseReponse().observe(getViewLifecycleOwner(), new Observer<List<GetAllCourseReponse>>() {
            @Override
            public void onChanged(List<GetAllCourseReponse> getAllCourseReponses) {
            }
        });
    }

    @Override
    public void onClickItem(GetAllCourseReponse reponse) {
        FragmentDetailBuy fragmentDetailBuy = new FragmentDetailBuy();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constain.courseReponse,reponse);
        fragmentDetailBuy.setArguments(bundle);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_fragment,R.anim.slide_out_right)
                .add(R.id.relative_featured,fragmentDetailBuy,Constain.fragmentDetailBuy)
                .commit();
    }
}
