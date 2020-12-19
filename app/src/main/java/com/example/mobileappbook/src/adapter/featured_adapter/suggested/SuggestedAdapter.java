package com.example.mobileappbook.src.adapter.featured_adapter.suggested;

import android.graphics.Paint;
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
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SuggestedAdapter extends RecyclerView.Adapter<SuggestedAdapter.SuggestedViewhodler> {
    private List<GetAllCourseReponse> mList = new ArrayList<>();

    public void setmList(List<GetAllCourseReponse>list){
        this.mList = list;
    }

    @NonNull
    @Override
    public SuggestedViewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.row_tem_suggested_course, null);
        return new SuggestedViewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestedViewhodler holder, int position) {
        try {
            GetAllCourseReponse reponse = mList.get(position);
            holder.mTxtName.setText(reponse.getName());
            holder.mTxtNameCategory.setText(reponse.getCategory().getName());
            holder.mTxtPriceSale.setText(reponse.getDiscount().toString() + "%");

            if (reponse.getDiscount() != null) {
                int price = reponse.getPrice() - (reponse.getPrice() * reponse.getDiscount()) / 100;
                holder.mTxtPrice.setText(price + "");
            }

            if (reponse.getPrice() == 0) {
                holder.mTxtPrice.setText("Miễn phí");
            }
            holder.mTxtPrice.setPaintFlags(holder.mTxtPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            Picasso.get().load(Constain.coursesUrlImg+reponse.getImage()).placeholder(R.drawable.empty23).error(R.drawable.empty23).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(holder.mImgAvatar);
        }catch (Exception e){
            Log.d("AAA", "onBindViewHolder: "+e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class SuggestedViewhodler extends RecyclerView.ViewHolder {
        private TextView mTxtName, mTxtNameCategory, mTxtPrice, mTxtPriceSale;
        private ImageView mImgAvatar;

        public SuggestedViewhodler(@NonNull View itemView) {
            super(itemView);
            mTxtName = itemView.findViewById(R.id.txt_name);
            mTxtNameCategory = itemView.findViewById(R.id.txt_name_category);
            mTxtPrice = itemView.findViewById(R.id.txt_price);
            mTxtPriceSale = itemView.findViewById(R.id.txt_price_sale);
            mImgAvatar = itemView.findViewById(R.id.img_avatar);
        }
    }
}
