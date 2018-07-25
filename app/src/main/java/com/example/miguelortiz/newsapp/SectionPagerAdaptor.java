package com.example.miguelortiz.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
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
    private String entertainmentTab;
    private String tab4;

    public SectionPagerAdaptor(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        technologyTab = context.getString(R.string.technologylabel);
        politicsTab = context.getString(R.string.politicsLabel);
        entertainmentTab = context.getString(R.string.entertainmentLabel);

    }


    public void addFragment(Fragment fragment, String title) {


        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
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
                return technologyTab;

            case 1:
                return politicsTab;

            case 2:
                return entertainmentTab;

//            case 3:
//                return ltab4;
        }

        return null;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
//                return new TechnologyFragment();

//            case 1:
//                return new RestaurantFragment();
//
//            case 2:
//                return new HotelsFragment();
//
//            case 3:
//                return new EventFragment();

            default:

                return null;
        }
    }
}
