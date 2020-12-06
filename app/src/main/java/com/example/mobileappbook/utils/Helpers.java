package com.example.mobileappbook.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.mobileappbook.R;
import com.example.mobileappbook.src.page.tabbar.TabBarActivity;

import java.util.Calendar;

public class Helpers {
    public static Dialog showLoadingDialog(Activity activity){
        Dialog dialog = new Dialog(activity);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_loading_dialog);
        return dialog;
    }

    public static void hideFragmentDialog(Fragment fragmentManager,String content){
        Fragment prev = fragmentManager.getFragmentManager().findFragmentByTag(content);
        if (prev != null) {
            DialogFragment df = (DialogFragment) prev;
            df.dismiss();
        }
    }

    public static void intentClear(Activity context){
        Intent intent = new Intent(context, TabBarActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
    }
}
