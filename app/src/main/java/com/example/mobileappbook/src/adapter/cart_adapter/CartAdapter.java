package com.example.mobileappbook.src.adapter.cart_adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.featured.GetAllCourseReponse;
import com.example.mobileappbook.utils.Constain;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter <CartAdapter.CartViewhodler>{
    private List<GetAllCourseReponse>list = new ArrayList<>();

    public void setList(List<GetAllCourseReponse> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CartViewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),R.layout.row_item_cart,null);
        return new CartViewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewhodler holder, int position) {
       try {
           GetAllCourseReponse reponse = list.get(position);
           holder.mTxtName.setText(reponse.getName());
           holder.mTxtNameCategory.setText(reponse.getCategory().getName());
           holder.mTxtPrice.setText(reponse.getPrice().toString());
           holder.mTxtPriceSale.setText(reponse.getV().toString());
           Picasso.get().load(Constain.coursesUrlImg+reponse.getImage()).placeholder(R.drawable.empty23).error(R.drawable.empty23).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(holder.mRoundImg);
       }catch (Exception e){
           Log.d("AAA", "onBindViewHolder: "+e.toString());
       }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CartViewhodler extends RecyclerView.ViewHolder{
        private TextView mTxtName,mTxtPrice,mTxtNameCategory,mTxtPriceSale;
        private RoundedImageView mRoundImg;
        public CartViewhodler(@NonNull View itemView) {
            super(itemView);
            mTxtName = itemView.findViewById(R.id.txt_name);
            mTxtPrice = itemView.findViewById(R.id.txt_price);
            mTxtNameCategory = itemView.findViewById(R.id.txt_name_category);
            mTxtPriceSale = itemView.findViewById(R.id.txt_price_sale);
            mRoundImg = itemView.findViewById(R.id.round_img);
        }
    }
}
