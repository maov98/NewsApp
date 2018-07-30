package com.example.miguelortiz.newsapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class SectionPagerAdaptor extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mFragmentTitleList = new ArrayList<>();
    private Context context;
    private String technologyTab;
    private String politicsTab;
    private String sportsTab;
    private boolean validConnection;

    public SectionPagerAdaptor(FragmentManager fm, Context context, boolean internetConnection) {
        super(fm);
        this.context = context;
        technologyTab = context.getString(R.string.technologylabel);
        politicsTab = context.getString(R.string.politicsLabel);
        sportsTab = context.getString(R.string.sportsLabel);
        validConnection = internetConnection;

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return politicsTab;

            case 1:
                return technologyTab;

            case 2:
                return sportsTab;
        }

        return null;
    }

    @Override
    public Fragment getItem(int position) {


        if(validConnection == true){

            switch (position) {


                case 0:
                    return new PoliticsFragment();

                case 1:
                    return new TecnologyFragment();

                case 2:
                    return new SportsFragment();
            }

        } else {

        return new noConnectionFragment();

        }





        return null;
    }
}
