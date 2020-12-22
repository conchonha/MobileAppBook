package com.example.mobileappbook.src.fragment.fragment_featured.suggested_courses;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.featured.GetAllCourseReponse;
import com.example.mobileappbook.model.CallbackFeatured;
import com.example.mobileappbook.src.adapter.featured_adapter.FeaturedAdapter;
import com.example.mobileappbook.src.fragment.fragment_featured.detail_buy.DetailBuyFragment;
import com.example.mobileappbook.src.page.tabbar.TabBarActivity;
import com.example.mobileappbook.src.viewmodel.featured.detail_buy.DetailBuyViewModel;
import com.example.mobileappbook.utils.Constain;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class SuggestedCoursesFragment extends Fragment implements CallbackFeatured{
    private RecyclerView mRecyclerView;
    private View mView;
    //variable
    private DetailBuyViewModel mDetailBuyViewModel;
    private FeaturedAdapter mAdapter;
    private String TAG = "SuggestedCoursesFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_fragment_suggested_courses_fragment, container, false);
        initView();
        init();
        initViewModel();
        return mView;
    }

    private void init() {
        mAdapter = new FeaturedAdapter(this,R.layout.row_tem_suggested_course);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        mRecyclerView.setAdapter(mAdapter);
    }

    //ánh xạ view
    private void initView() {
        mRecyclerView = mView.findViewById(R.id.recycler);
    }

    //khởi tạo viewmodel
    private void initViewModel() {
        mDetailBuyViewModel = ViewModelProviders.of(getActivity()).get(DetailBuyViewModel.class);
        //lang nghe va quan sat du lieu
        mDetailBuyViewModel.getmGetSuggestedReponse().observe(getActivity(), new Observer<Map>() {
            @Override
            public void onChanged(Map map) {
                if (map.get(Constain.keyMapErr) != null) {
                    Toast.makeText(getContext(), map.get(Constain.keyMapErr).toString(), Toast.LENGTH_SHORT).show();
                } else {
                    List<GetAllCourseReponse> list = (List<GetAllCourseReponse>) map.get("200");
                    Log.d(TAG, "onChanged: "+new Gson().toJson(list));
                    mAdapter.setmListCourseReponse(list);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    @Override
    public void onClickItem(GetAllCourseReponse reponse,boolean check) {
        DetailBuyFragment detailBuyFragment = new DetailBuyFragment();
        CallbackFeatured callbackFeatured = detailBuyFragment;
        callbackFeatured.onClickItem(reponse,false);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_fragment, R.anim.slide_out_right)
                .add(R.id.relative_featured, detailBuyFragment, Constain.fragmentDetailBuy)
                .commit();
    }

}
