package com.example.mobileappbook.src.adapter.spinner_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mobileappbook.R;
import com.example.mobileappbook.cores.reponse.featured.GetAllCategoryReponse;
import com.example.mobileappbook.utils.Constain;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<GetAllCategoryReponse> {

    public SpinnerAdapter(Context context, List<GetAllCategoryReponse> listSpinner) {
        super(context, 0, listSpinner);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item_spinner, parent,false);
        }

        ImageView imageView = convertView.findViewById(R.id.img_spinner);
        TextView textView = convertView.findViewById(R.id.txt_spinner);

        GetAllCategoryReponse spinnerModel = getItem(position);
        if (spinnerModel != null) {
            Picasso.get().load(Constain.categoryImg+spinnerModel.getImage()).into(imageView);
            textView.setText(spinnerModel.getName());
        }

        return convertView;
    }
}
