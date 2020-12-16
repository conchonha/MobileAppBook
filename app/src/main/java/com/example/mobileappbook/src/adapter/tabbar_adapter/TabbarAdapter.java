package com.example.mobileappbook.src.adapter.tabbar_adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.mobileappbook.src.fragment.fragment_account.AccountFragment;
import com.example.mobileappbook.src.fragment.fragment_cart.cartFragment;
import com.example.mobileappbook.src.fragment.fragment_featured.FeatureFragment;
import com.example.mobileappbook.src.fragment.fragment_courses.mycoursesFragment;
import com.example.mobileappbook.src.fragment.fragment_search.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class TabbarAdapter extends FragmentStatePagerAdapter {
    private List<String>mListTitle = new ArrayList<>();
    private List<Fragment>mListFragment = new ArrayList<>();

    public TabbarAdapter(@NonNull FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        addData();
    }

    private void addData() {
        //add title tabbar
        mListTitle.add("Featured");
        mListTitle.add("Search");
        mListTitle.add("Courses");
        mListTitle.add("Cart");
        mListTitle.add("Acount");

        //add fragment tuong ứng
        mListFragment.add(new FeatureFragment());
        mListFragment.add(new SearchFragment());
        mListFragment.add(new mycoursesFragment());
        mListFragment.add(new cartFragment());
        mListFragment.add(new AccountFragment());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mListFragment.get(position);
    }

    @Override
    public int getCount() {
        return mListFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mListTitle.get(position);
    }
}
