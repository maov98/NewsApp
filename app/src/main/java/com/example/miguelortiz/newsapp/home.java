package com.example.miguelortiz.newsapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;

public class home extends AppCompatActivity {

    private SectionPagerAdaptor mSectionPageAdaptor;
    private ViewPager mviewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mviewPager = (ViewPager) findViewById(R.id.pager);
        mviewPager.setAdapter(new SectionPagerAdaptor(getSupportFragmentManager(),this));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mviewPager);

    }
}
