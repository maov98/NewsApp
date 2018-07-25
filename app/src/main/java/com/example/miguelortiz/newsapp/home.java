package com.example.miguelortiz.newsapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

public class home extends FragmentActivity implements LoaderManager.LoaderCallbacks<ArrayList<NewsObject>>{

ArrayList<NewsObject> arrayList = new ArrayList<NewsObject>();
NewsRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    String urlSearch = "https://content.guardianapis.com/search?q=technology&format=json&from-date=2018-07-19&show-fields=headline,thumbnail,short-url&show-refinements=all&order-by=relevance&show-elements=image&api-key=3726085a-e013-4212-8ae5-2811e39ebba2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getLoaderManager().initLoader(1,null,this).forceLoad();
        recyclerView = findViewById(R.id.homeRecyclerView);
        adapter = new NewsRecyclerViewAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public Loader<ArrayList<NewsObject>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this,urlSearch);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<NewsObject>> loader, ArrayList<NewsObject> data) {

        adapter.updateData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<NewsObject>> loader) {

        adapter.cleanArray();
    }
}
