package com.example.mobileappbook.src.adapter.featured_adapter.comment;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.comment.GetCommentReponse;
import com.example.mobileappbook.utils.Constain;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter <CommentAdapter.CommentViewHoldler>{
    private List<GetCommentReponse> mList = new ArrayList<>();

    public void setmList(List<GetCommentReponse> mList){
        this.mList = mList;
    }

    @NonNull
    @Override
    public CommentViewHoldler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.row_item_comment,null);
        return new CommentViewHoldler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHoldler holder, int position) {
        try {
            GetCommentReponse reponse = mList.get(position);
            String []time = reponse.getCreateAt().split("T");
            holder.mTxtTime.setText(time[0]+"");
            holder.mTxtContent.setText(reponse.getContent().toString());
            holder.mRatingComment.setRating(reponse.getNumStar());
            Picasso.get().load(Constain.userUrlImg+reponse.getImage()).placeholder(R.drawable.ic_acount).error(R.drawable.ic_acount).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(holder.mImgAvatar);
        }catch (Exception e){
            Log.d("AAA", "onBindViewHolder: "+e.toString());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class CommentViewHoldler extends RecyclerView.ViewHolder{
        private RoundedImageView mImgAvatar;
        private RatingBar mRatingComment;
        private TextView mTxtContent,mTxtTime;
        public CommentViewHoldler(@NonNull View itemView) {
            super(itemView);
            mImgAvatar = itemView.findViewById(R.id.img_avatar);
            mRatingComment = itemView.findViewById(R.id.rating_comment);
            mTxtContent = itemView.findViewById(R.id.txt_content);
            mTxtTime = itemView.findViewById(R.id.txt_time);
        }
    }
}
