package com.example.mobileappbook.utils;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Base64;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.mobileappbook.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

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

    public static void intentClear(Activity context,Class activitity){
        Intent intent = new Intent(context, activitity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
    }

    public static void intentClearOnTapSelected4(Activity context, Class activitity){
        Intent intent = new Intent(context, activitity);
        intent.putExtra(Constain.keyTap,4);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
    }

    public static void intentClearOnTapSelected3(Activity context, Class activitity){
        Intent intent = new Intent(context, activitity);
        intent.putExtra(Constain.keyTap,3);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
    }

    public static File savebitmap(Bitmap bmp) {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        OutputStream outStream = null;
        // String temp = null;
        File file = new File(extStorageDirectory, "temp.png");
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, "temp.png");

        }

        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 0, outStream);
            outStream.flush();
            outStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

    public static String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[]hinhanh=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(hinhanh,Base64.DEFAULT);
    }

    public static void removeFragment(FragmentManager fragmentManager,int anim_out,int anim_in,String tag){
        Fragment prev = fragmentManager.findFragmentByTag(tag);
        if (prev != null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(anim_out,anim_in)
                    .remove(prev)
                    .commit();
        }
    }
}
