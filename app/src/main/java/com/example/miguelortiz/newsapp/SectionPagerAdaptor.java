package com.example.miguelortiz.newsapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SectionPagerAdaptor extends FragmentPagerAdapter {

    private Context context;
    private String technologyTab;
    private String politicsTab;
    private String sportsTab;
    private String religionTab;
    private String scienceTab;
    private String generalTab;
    private boolean validConnection;
    private String[] tabList;
    private SharedPreferences sharedPreferences;
    private Set<String> selections;
    private Set<String> defaultval = new HashSet<>();
    int [] selectedInteger;
    private String[] selected;
    private int holderint;
    String[] maxsections;
    int maxNumPages;
    int currentNumPages;

    public SectionPagerAdaptor(FragmentManager fm, Context context, boolean internetConnection) {
        super(fm);
        this.context = context;
        technologyTab = context.getString(R.string.technologylabel);
        politicsTab = context.getString(R.string.politicsLabel);
        sportsTab = context.getString(R.string.sportsLabel);
        religionTab = context.getString(R.string.religionLabel);
        scienceTab = context.getString(R.string.scienceLabel);
        generalTab = context.getString(R.string.generalNews);
        tabList = new String[]{politicsTab,sportsTab,technologyTab,scienceTab,religionTab,politicsTab};
        defaultval.add(politicsTab);
        defaultval.add(technologyTab);
        defaultval.add(sportsTab);
        validConnection = internetConnection;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        selections = sharedPreferences.getStringSet(context.getString(R.string.listPreference),null);

        if(selections!=null){
            selected = selections.toArray(new String[]{});
            Arrays.sort(selected);
            currentNumPages = selected.length;
        }
        else {
            currentNumPages = 1;
        }

        holderint = context.getResources().getIdentifier(context.getString(R.string.listArray),"array",context.getPackageName());
        maxsections = context.getResources().getStringArray(holderint);
        maxNumPages = maxsections.length;
        selectedInteger = new int[maxNumPages+1];

        for (int j = 0; j< currentNumPages; j++){

            selectedInteger[j]=Integer.parseInt(selected[j].substring(0,1));

        }

    }

    @Override
    public int getCount() {
        if(currentNumPages>0){
            return currentNumPages;
        }else {

            return 1;
        }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:

                if(currentNumPages>0){

                    return tabList[selectedInteger[0]-1];
                }

            case 1:

                if(currentNumPages>1){

                    return tabList[selectedInteger[1]-1];
                }

            case 2:

                if(currentNumPages>2){

                    return tabList[selectedInteger[2]-1];
                }


            case 3:

                if(currentNumPages>3){

                    return tabList[selectedInteger[3]-1];
                }

            case 4:

                if(currentNumPages>4){

                    return tabList[selectedInteger[4]-1];
                }

            default:

                return generalTab;
        }
    }

    @Override
    public Fragment getItem(int position) {

        if(validConnection == true){

            switch (position) {

                case 0:

                    if(currentNumPages>0){

                        switch (selectedInteger[0]){

                            case 1:

                                return new PoliticsFragment();

                            case 2:

                                return new SportsFragment();

                            case 3:

                                return new TecnologyFragment();

                            case 4:

                                return new ScienceFragment();

                            case 5:

                                return new ReligionFragment();
                        }

                    }

                case 1:

                    if(currentNumPages>1){

                        switch (selectedInteger[1]){

                            case 1:

                                return new PoliticsFragment();

                            case 2:

                                return new SportsFragment();

                            case 3:

                                return new TecnologyFragment();

                            case 4:

                                return new ScienceFragment();

                            case 5:

                                return new ReligionFragment();
                        }

                    }

                case 2:

                    if(currentNumPages>2){

                        switch (selectedInteger[2]){

                            case 1:

                                return new PoliticsFragment();

                            case 2:

                                return new SportsFragment();

                            case 3:

                                return new TecnologyFragment();

                            case 4:

                                return new ScienceFragment();

                            case 5:

                                return new ReligionFragment();
                        }

                    }

                case 3:

                    if(currentNumPages>3){

                        switch (selectedInteger[3]){

                            case 1:

                                return new PoliticsFragment();

                            case 2:

                                return new SportsFragment();

                            case 3:

                                return new TecnologyFragment();

                            case 4:

                                return new ScienceFragment();

                            case 5:

                                return new ReligionFragment();
                        }

                    }

                case 4:

                    if(currentNumPages>4){

                        switch (selectedInteger[4]){

                            case 1:

                                return new PoliticsFragment();

                            case 2:

                                return new SportsFragment();

                            case 3:

                                return new TecnologyFragment();

                            case 4:

                                return new ScienceFragment();

                            case 5:

                                return new ReligionFragment();
                        }

                    }

                default:

                    return new GeneralFragment();
            }

        } else {

            return new noConnectionFragment();

        }

    }
}
