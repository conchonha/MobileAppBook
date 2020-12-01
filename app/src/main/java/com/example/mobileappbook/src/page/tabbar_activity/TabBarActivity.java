package com.example.mobileappbook.src.page.tabbar_activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.mobileappbook.R;
import com.example.mobileappbook.src.adapter.tabbar_adapter.TabbarAdapter;
import com.google.android.material.tabs.TabLayout;

public class TabBarActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    //variable
    private TabbarAdapter mTabbarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbar);
        initView();
        init();
    }

    private void init() {
        mTabbarAdapter = new TabbarAdapter(getSupportFragmentManager());

        //set adapter cho viewpager
        mViewPager.setAdapter(mTabbarAdapter);

        //set icon cho tablayout
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_rating);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_search);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_courses);
        mTabLayout.getTabAt(3).setIcon(R.drawable.ic_cart);
        mTabLayout.getTabAt(4).setIcon(R.drawable.ic_acount);

    }

    //ánh xạ view
    private void initView() {
        mViewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tab_layout);
    }
}