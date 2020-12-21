package com.example.mobileappbook.src.fragment.fragment_search;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.featured.GetAllCourseReponse;
import com.example.mobileappbook.src.adapter.search_adapter.SearchAdapter;
import com.example.mobileappbook.src.fragment.fragment_featured.FeatureFragment;
import com.example.mobileappbook.src.viewmodel.featured.FeaturedViewModel;
import com.example.mobileappbook.utils.Constain;
import com.example.mobileappbook.utils.Helpers;

import java.util.List;

public class SearchFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private RecyclerView mRecyclerView;
    private FeaturedViewModel mFeaturedViewModel;
    private EditText mEdtSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search,container,false);
        initView();
        initViewModel();
        init();
        listenerOnclicked();
        return mView;
    }

    //lang nghe su kien onclick
    private void listenerOnclicked() {
        mView.findViewById(R.id.cart_search).setOnClickListener(this);
    }

    // khởi tạo viewmodel
    private void initViewModel() {
        mFeaturedViewModel = ViewModelProviders.of(getActivity()).get(FeaturedViewModel.class);

        mFeaturedViewModel.getListSearch().observe(getViewLifecycleOwner(), new Observer<List<GetAllCourseReponse>>() {
            @Override
            public void onChanged(List<GetAllCourseReponse> getAllCourseReponse) {
                if(getAllCourseReponse.size() == 0){
                    Toast.makeText(getContext(), "Không tìm thấy kết quả nào", Toast.LENGTH_SHORT).show();
                }else {
                    getFragmentManager().beginTransaction().
                            setCustomAnimations(R.anim.slide_in_fragment, R.anim.slide_out_right).
                            add(R.id.relative_2,new FeatureFragment(), Constain.feauterFragment).
                            commit();
                }
            }
        });
    }

    private void init() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(new SearchAdapter(this));
    }

    public void onClickAdapter(String content){
        search(content);
    }

    //ánh xạ view
    private void initView() {
        mRecyclerView = mView.findViewById(R.id.recycler);
        mEdtSearch = mView.findViewById(R.id.editText_search);
    }

    private void search(final String content){
        final Dialog dialog = Helpers.showLoadingDialog(getActivity());
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                mFeaturedViewModel.searchCoure(content);
            }
        },3000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cart_search:
                search(mEdtSearch.getText().toString().equals("") ? "0" : mEdtSearch.getText().toString());
                break;
        }
    }
}
