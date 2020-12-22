package com.example.mobileappbook.src.fragment.fragment_courses;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
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
import com.example.mobileappbook.model.CallbackFeatured;
import com.example.mobileappbook.model.CartModel;
import com.example.mobileappbook.src.adapter.featured_adapter.FeaturedAdapter;
import com.example.mobileappbook.src.fragment.fragment_featured.detail_buy.DetailBuyFragment;
import com.example.mobileappbook.src.page.course.CourseList2Activity;
import com.example.mobileappbook.src.page.course.CreateCourseActivity;
import com.example.mobileappbook.src.page.tabbar.TabBarActivity;
import com.example.mobileappbook.src.viewmodel.cart.CartViewModel;
import com.example.mobileappbook.utils.Constain;
import com.example.mobileappbook.utils.Helpers;
import com.google.gson.Gson;

import static android.app.Activity.RESULT_OK;

public class mycoursesFragment extends Fragment implements CallbackFeatured {
    private View mView;
    private Button mBtnSwipe;
    private RecyclerView mRecyclerView;
    private CartViewModel mCartViewModel;
    private FeaturedAdapter mAdapter;
    private String TAG = "mycoursesFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_mycourses,container,false);
        initViewModel();
        initView();
        init();
        listenerOnclick();
        return mView;
    }

    private void init() {
        mAdapter = new FeaturedAdapter(this,R.layout.row_item_mycourse);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        mRecyclerView.setAdapter(mAdapter);
    }

    //khoi tao viewmodel
    private void initViewModel() {
        mCartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        mCartViewModel.initCartToPay();
        //lang nghe va quan sat du lieu
        mCartViewModel.getDataCartToPay().observe(getViewLifecycleOwner(), new Observer<CartModel>() {
            @Override
            public void onChanged(CartModel cartModel) {
                mAdapter.setmListCourseReponse(cartModel.getList());
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void listenerOnclick() {
        mBtnSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animTranslate = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_translate);
                mBtnSwipe.startAnimation(animTranslate);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getContext(), CourseList2Activity.class));
                        getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                    }
                },2000);

            }
        });
    }

    private void initView() {
        mBtnSwipe = mView.findViewById(R.id.btn_swipe);
        mRecyclerView = mView.findViewById(R.id.recycler);
    }

    @Override
    public void onClickItem(GetAllCourseReponse reponse, boolean check) {
        DetailBuyFragment detailBuyFragment = new DetailBuyFragment();
        CallbackFeatured callbackFeatured = detailBuyFragment;
        callbackFeatured.onClickItem(reponse,true);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_fragment, R.anim.slide_out_right)
                .add(R.id.relative_featured, detailBuyFragment, Constain.fragmentDetailBuy)
                .commit();
    }
}
