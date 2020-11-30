package com.example.mobileappbook.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.baseprojectandroid.R;
import com.example.baseprojectandroid.models.callback.CallbackToRevenueExpenditure;

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

    public static void showDatePickerDialog(Context context, final CallbackToRevenueExpenditure callbackToRevenueExpenditure){
        Calendar Calenda = Calendar.getInstance();
        int year = Calenda.get(Calendar.YEAR);
        int mounth = Calenda.get(Calendar.MONTH) + 1;
        int date = Calenda.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                callbackToRevenueExpenditure.getTimePickerDialog(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        },year,mounth,date);
        datePickerDialog.show();
    }
}
