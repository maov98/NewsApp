package com.example.miguelortiz.newsapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Setting_Activity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_);
        getFragmentManager().beginTransaction().replace(R.id.fragmentContent, new NewsAppPreferenceFragment()).commit();
        context = getApplication().getBaseContext();
    }


    public static class NewsAppPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{



        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_main);
            Preference datePreference = findPreference(getString(R.string.newsDate));
            Preference newsCategory = findPreference(getString(R.string.listPreference));

            bindPreferenceSummaryToValue(datePreference);
            bindPreferenceSummaryToString(newsCategory);
        }

        private void bindPreferenceSummaryToValue(Preference datePreference) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(datePreference.getContext());
            datePreference.setOnPreferenceChangeListener(this);
            String preferenceString = preferences.getString(datePreference.getKey(), "");
            onPreferenceChange(datePreference, preferenceString);
        }

        private void bindPreferenceSummaryToString(Preference categoryPreference) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(categoryPreference.getContext());
            categoryPreference.setOnPreferenceChangeListener(this);
            Set<String> preferenceString = preferences.getStringSet(categoryPreference.getKey(), null);
            onPreferenceChange(categoryPreference, preferenceString);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {

            String stringValue = newValue.toString();
            preference.setSummary(stringValue);
            return true;
        }
    }
}
