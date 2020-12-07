package com.example.mobileappbook.src.adapter.cart_adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCourseReponse;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter <CartAdapter.CartViewhodler>{
    private List<GetAllCourseReponse>list = new ArrayList<>();

    public CartAdapter(List<GetAllCourseReponse> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CartViewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),R.layout.layout_item_cart,null);
        return new CartViewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewhodler holder, int position) {
        GetAllCourseReponse reponse = list.get(position);
        holder.mTxtName.setText(reponse.getName());
        holder.mTxtNameCategory.setText(reponse.getCategory().getName());
        holder.mTxtPrice.setText(reponse.getPrice().toString());
        holder.mTxtPriceSale.setText(reponse.getV().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CartViewhodler extends RecyclerView.ViewHolder{
        private TextView mTxtName,mTxtPrice,mTxtNameCategory,mTxtPriceSale;
        public CartViewhodler(@NonNull View itemView) {
            super(itemView);
            mTxtName = itemView.findViewById(R.id.txt_name);
            mTxtPrice = itemView.findViewById(R.id.txt_price);
            mTxtNameCategory = itemView.findViewById(R.id.txt_name_category);
            mTxtPriceSale = itemView.findViewById(R.id.txt_price_sale);
        }
    }
}
