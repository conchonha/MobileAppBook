//package com.example.mobileappbook.compoments;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.os.Handler;
//import android.view.View;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.ItemTouchHelper;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.baseprojectandroid.R;
//import com.example.baseprojectandroid.cores.room.table.RevenueExpenditureTable;
//import com.example.baseprojectandroid.src.viewmodel.revenue_expenditure_viewmodel.RevenueExpenditureViewmodel;
//import com.example.baseprojectandroid.utils.Helpers;
//import com.google.android.material.snackbar.Snackbar;
//
//
//public class ItemTouchHelperSimpleCallback {
//    private static RevenueExpenditureTable mRevenueExpenditureTable;
//
//    public static ItemTouchHelper.SimpleCallback simpleCallBack(final Fragment fragment, final String type, final RevenueExpenditureViewmodel revenueExpenditureViewmodel, final Activity activity, final RecyclerView recyclerView) {
//        ItemTouchHelper.SimpleCallback simpleCallback1 = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
//                switch (direction) {
//                    case ItemTouchHelper.LEFT:
//                        if (type == fragment.getString(R.string.lbl_expenses)) {
//                            mRevenueExpenditureTable = revenueExpenditureViewmodel.getmListExpenditureTable().getValue().get(viewHolder.getAdapterPosition());
//                        } else {
//                            mRevenueExpenditureTable = revenueExpenditureViewmodel.getmListRevenueTable().getValue().get(viewHolder.getAdapterPosition());
//                        }
//                        final Dialog dialog = Helpers.showLoadingDialog(activity);
//                        dialog.show();
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                revenueExpenditureViewmodel.deleteEvenueExpenditure(mRevenueExpenditureTable);
//                                dialog.dismiss();
//                                Snackbar.make(recyclerView, mRevenueExpenditureTable.getmContent(), Snackbar.LENGTH_LONG)
//                                        .setAction(activity.getString(R.string.lbl_under), new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v) {
//                                                dialog.show();
//                                                new Handler().postDelayed(new Runnable() {
//                                                    @Override
//                                                    public void run() {
//                                                        revenueExpenditureViewmodel.insertEvenueExpenditure(mRevenueExpenditureTable);
//                                                        dialog.dismiss();
//                                                    }
//                                                }, 3000);
//                                            }
//                                        }).show();
//                            }
//                        }, 3000);
//                        break;
//                }
//            }
//        };
//        return simpleCallback1;
//    }
//
//}
