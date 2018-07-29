package com.example.miguelortiz.newsapp;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import java.util.ArrayList;

public class NewsLoader extends AsyncTaskLoader<ArrayList<NewsObject>> {

    ArrayList<NewsObject> arrayList = new ArrayList<NewsObject>();

    private String searcParameter;

    public NewsLoader(Context context, String searchCriteria) {
        super(context);
        searcParameter = searchCriteria;

            }

    @Override
    public ArrayList<NewsObject> loadInBackground() {

        arrayList = FetchData.fetchNewsData(searcParameter);

        return arrayList;
    }
}
