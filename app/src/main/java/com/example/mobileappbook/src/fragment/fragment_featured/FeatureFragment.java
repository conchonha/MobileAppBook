package com.example.mobileappbook.src.fragment.fragment_featured;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCourseReponse;
import com.example.mobileappbook.model.CallbackFeatured;
import com.example.mobileappbook.src.adapter.featured_adapter.FeaturedAdapter;
import com.example.mobileappbook.src.viewmodel.featured.FeaturedViewModel;
import com.example.mobileappbook.utils.Constain;

import java.util.List;

public class FeatureFragment extends Fragment implements CallbackFeatured, View.OnClickListener {
    private View mView;
    //variable
    private FeaturedViewModel mFeaturedViewModel;
    private FeaturedAdapter mFeaturedAdapter;
    private RecyclerView mRecyclerviewFeatured;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_featured, container, false);
        initViewModel();
        initView();
        init();
        listenerOnclicked();
        return mView;
    }
    //lang nghe su kien onclicked
    private void listenerOnclicked() {
        mView.findViewById(R.id.img_back).setOnClickListener(this);
    }

    private void init() {
        mFeaturedAdapter = new FeaturedAdapter(this);
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
        mFeaturedViewModel.getAllCourseReponse().observe(getViewLifecycleOwner(), new Observer<List<GetAllCourseReponse>>() {
            @Override
            public void onChanged(List<GetAllCourseReponse> getAllCourseReponse) {
                mFeaturedAdapter.setmListCourseReponse(getAllCourseReponse);
                mFeaturedAdapter.notifyDataSetChanged();
            }
        });

        mFeaturedViewModel.getListSearch().observe(getViewLifecycleOwner(), new Observer<List<GetAllCourseReponse>>() {
            @Override
            public void onChanged(List<GetAllCourseReponse> getAllCourseReponse) {
                mFeaturedAdapter.setmListCourseReponse(getAllCourseReponse);
                mFeaturedAdapter.notifyDataSetChanged();
                if(getAllCourseReponse.size() == 0){
                    Toast.makeText(getContext(), "Không tìm thấy kết quả nào", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClickItem(GetAllCourseReponse reponse) {
        DetailBuyFragment detailBuyFragment = new DetailBuyFragment();
        CallbackFeatured callbackFeatured = detailBuyFragment;
        callbackFeatured.onClickItem(reponse);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_fragment, R.anim.slide_out_right)
                .add(R.id.relative_featured, detailBuyFragment, Constain.fragmentDetailBuy)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                Fragment prev = getFragmentManager().findFragmentByTag(Constain.feauterFragment);
                if (prev != null) {
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.slide_out_right,R.anim.slide_out_fragment)
                            .remove(prev)
                            .commit();
                }
                mFeaturedViewModel.setDataAllSearch();
                break;
        }
    }
}
