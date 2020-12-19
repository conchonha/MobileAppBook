package com.example.mobileappbook.src.adapter.featured_adapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.featured.GetAllCourseReponse;
import com.example.mobileappbook.model.CallbackFeatured;
import com.example.mobileappbook.src.fragment.fragment_featured.FeatureFragment;
import com.example.mobileappbook.utils.Constain;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewhodler> {
    private Fragment mFeatureFragment;
    private List<GetAllCourseReponse>mListCourseReponse  = new ArrayList<>();
    private View mView;
    private int mLayout;

    public FeaturedAdapter(Fragment featureFragment,int layout) {
        this.mFeatureFragment = featureFragment;
        this.mLayout = layout;
    }

    @NonNull
    @Override
    public FeaturedViewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = View.inflate(parent.getContext(), mLayout,null);
        return new FeaturedViewhodler(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewhodler holder, int position) {
        try {
            final GetAllCourseReponse reponse = mListCourseReponse.get(position);
            holder.mTxtPriceSale.setText(reponse.getDiscount().toString()+"%");
            holder.mtxtPrice.setText(reponse.getPrice().toString());
            holder.mTxtNameCategory.setText(reponse.getCategory().getName());
            holder.mTxtName.setText(reponse.getName());
            Picasso.get().load(Constain.coursesUrlImg+reponse.getImage()).placeholder(R.drawable.empty23).error(R.drawable.empty23).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(holder.mImageAvatar);

            holder.mCardBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CallbackFeatured callbackFeatured = (CallbackFeatured) mFeatureFragment;
                    callbackFeatured.onClickItem(reponse);
                }
            });

            if(reponse.getDiscount() != null){
                int price=reponse.getPrice()-(reponse.getPrice()*reponse.getDiscount())/100;
                holder.mtxtPrice.setText(price+"");
            }

            if(reponse.getPrice()==0){
                holder.mtxtPrice.setText("Miễn phí");
            }
        }catch (Exception e){

        }

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
            mImageAvatar = itemView.findViewById(R.id.img_img_courses);
        }
    }
}
