package com.example.mobileappbook.utils;

import android.app.Activity;
import android.app.Dialog;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.mobileappbook.R;

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

    public static String getTime(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int mounth = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);

        String time = year + "-"+mounth + "-"+date;
        return time;
    }
}
