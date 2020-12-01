package com.example.mobileappbook.src.adapter.tabbar_adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.mobileappbook.src.fragment.fragment_account.FragmentAccount;
import com.example.mobileappbook.src.fragment.fragment_cart.FragmentCart;
import com.example.mobileappbook.src.fragment.fragment_featured.FragmentFeatured;
import com.example.mobileappbook.src.fragment.fragment_search.FragmentCourses;
import com.example.mobileappbook.src.fragment.fragment_search.FragmentSearch;

import java.util.ArrayList;
import java.util.List;

public class TabbarAdapter extends FragmentStatePagerAdapter {
    private List<String>mListTitle = new ArrayList<>();
    private List<Fragment>mListFragment = new ArrayList<>();

    public TabbarAdapter(@NonNull FragmentManager fm) {
        super(fm);
        //add title tabbar
        mListTitle.add("Featured");
        mListTitle.add("Search");
        mListTitle.add("Courses");
        mListTitle.add("Cart");
        mListTitle.add("Acount");

        //add fragment tuong ứng
        mListFragment.add(new FragmentFeatured());
        mListFragment.add(new FragmentSearch());
        mListFragment.add(new FragmentCourses());
        mListFragment.add(new FragmentCart());
        mListFragment.add(new FragmentAccount());
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
