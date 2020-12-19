package com.example.mobileappbook.src.fragment.fragment_featured.comment;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.comment.GetCommentReponse;
import com.example.mobileappbook.src.adapter.featured_adapter.comment.CommentAdapter;
import com.example.mobileappbook.src.viewmodel.featured.detail_buy.DetailBuyViewModel;
import com.example.mobileappbook.utils.Constain;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class GetCommentFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private View mView;
    //variable
    private DetailBuyViewModel mDetailBuyViewModel;
    private CommentAdapter mAdapter;
    private String TAG = "GetCommentFragment";
    private Gson mGson = new Gson();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_comment,container,false);
        initView();
        init();
        initViewModel();
        return mView;
    }

    private void init() {
        mAdapter = new CommentAdapter();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
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
        mDetailBuyViewModel.getCommentReponse().observe(getActivity(), new Observer<Map>() {
            @Override
            public void onChanged(Map map) {
                if(map.get(Constain.keyMapErr)!= null){
                    Toast.makeText(getContext(), map.get(Constain.keyMapErr).toString(), Toast.LENGTH_SHORT).show();
                }else{
                    List<GetCommentReponse> list = (List<GetCommentReponse>) map.get(200);
                    mAdapter.setmList(list);
                    mAdapter.notifyDataSetChanged();
                    Log.d(TAG, "onChanged: "+mGson.toJson(list));
                }
            }
        });
    }
}
