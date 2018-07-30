package com.example.miguelortiz.newsapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

public class home extends AppCompatActivity {

    private SectionPagerAdaptor mSectionPageAdaptor;
    private ViewPager mviewPager;
    private boolean networkStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        networkStatus = isNetworkAvailable();
        setContentView(R.layout.activity_home);
        mviewPager = (ViewPager) findViewById(R.id.pager);
        mviewPager.setAdapter(new SectionPagerAdaptor(getSupportFragmentManager(), this,networkStatus));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mviewPager);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
