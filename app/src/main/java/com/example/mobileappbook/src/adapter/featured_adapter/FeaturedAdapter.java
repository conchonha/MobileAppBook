package com.example.mobileappbook.src.adapter.featured_adapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.featured_reponse.GetAllCourseReponse;
import com.example.mobileappbook.model.CallbackFeatured;
import com.example.mobileappbook.src.fragment.fragment_featured.FeatureFragment;

import java.util.ArrayList;
import java.util.List;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewhodler> {
    private FeatureFragment mFeatureFragment;
    private List<GetAllCourseReponse>mListCourseReponse  = new ArrayList<>();
    private View mView;

    public FeaturedAdapter(FeatureFragment featureFragment) {
        this.mFeatureFragment = featureFragment;
    }

    @NonNull
    @Override
    public FeaturedViewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = View.inflate(parent.getContext(), R.layout.layout_item_featured,null);
        return new FeaturedViewhodler(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewhodler holder, int position) {
        final GetAllCourseReponse reponse = mListCourseReponse.get(position);
        holder.mTxtPriceSale.setText(reponse.getV().toString());
        holder.mtxtPrice.setText(reponse.getPrice().toString());
        holder.mTxtNameCategory.setText(reponse.getCategory().getName());
        holder.mTxtName.setText(reponse.getName());

        holder.mCardBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallbackFeatured callbackFeatured = mFeatureFragment;
                callbackFeatured.onClickItem(reponse);
            }
        });
    }

    public void setmListCourseReponse(List<GetAllCourseReponse>listCourseReponse){
        this.mListCourseReponse = listCourseReponse;
    }

    @Override
    public int getItemCount() {
        return mListCourseReponse.size();
    }

    public class FeaturedViewhodler extends RecyclerView.ViewHolder{
        private ImageView mImageAvatar;
        private TextView mTxtName,mTxtNameCategory,mtxtPrice,mTxtPriceSale;
        private CardView mCardBuy;

        public FeaturedViewhodler(@NonNull View itemView) {
            super(itemView);
            mTxtName = itemView.findViewById(R.id.txt_name);
            mTxtNameCategory = itemView.findViewById(R.id.txt_name_category);
            mtxtPrice = itemView.findViewById(R.id.txt_price1);
            mTxtPriceSale = itemView.findViewById(R.id.txt_sale_price);
            mCardBuy  = itemView.findViewById(R.id.card_buy);
        }
    }
}
