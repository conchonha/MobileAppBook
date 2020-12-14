package com.example.mobileappbook.src.adapter.search_adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappbook.R;
import com.example.mobileappbook.src.fragment.fragment_search.SearchFragment;
import com.example.mobileappbook.utils.Constain;

public class SearchAdapter extends RecyclerView.Adapter <SearchAdapter.SearchViewholder>{
    private SearchFragment mContext;

    public SearchAdapter(SearchFragment context){
        this.mContext = context;
    }
    @NonNull
    @Override
    public SearchViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.layout_item_search,null);
        return new SearchViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewholder holder, final int position) {
        holder.mTxtText.setText(Constain.arrayRecommand[position]);
        holder.mTxtText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.onClickAdapter(Constain.arrayRecommand[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Constain.arrayRecommand.length;
    }

    public class SearchViewholder extends RecyclerView.ViewHolder{
        private TextView mTxtText;
        public SearchViewholder(@NonNull View itemView) {
            super(itemView);
            mTxtText = itemView.findViewById(R.id.txt_text);
        }
    }
}
