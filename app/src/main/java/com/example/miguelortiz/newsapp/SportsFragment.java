package com.example.miguelortiz.newsapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.ArrayList;

public class SportsFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<NewsObject>> {

    ArrayList<NewsObject> arrayList = new ArrayList<NewsObject>();
    NewsRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    boolean emptyArray;
    String urlSearch = "https://content.guardianapis.com/search?q=sports&format=json&from-date=2018-07-19&show-fields=headline,thumbnail,short-url&show-tags=contributor&show-refinements=all&order-date=published&order-by=newest&show-elements=image&api-key=3726085a-e013-4212-8ae5-2811e39ebba2";
    Context context;
    private ProgressBar spinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        getLoaderManager().initLoader(3,null, this).forceLoad();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sports,container,false);
        recyclerView = view.findViewById(R.id.sportsRecyclerView);
        adapter = new NewsRecyclerViewAdapter(context,arrayList);
        spinner = view.findViewById(R.id.sportsProgressBar);
        return view;
    }

    @NonNull
    @Override
    public android.support.v4.content.Loader<ArrayList<NewsObject>> onCreateLoader(int id, @Nullable Bundle args) {

        return new NewsLoader(getActivity(),urlSearch);
    }

    @Override
    public void onLoadFinished(@NonNull android.support.v4.content.Loader<ArrayList<NewsObject>> loader, ArrayList<NewsObject> data) {


        if (data != null){
            emptyArray = adapter.updateData(data);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            spinner.setVisibility(View.INVISIBLE);

        } else {

            TextView view = getView().findViewById(R.id.noDataTextViewSports);
            recyclerView.setVisibility(View.INVISIBLE);
            spinner.setVisibility(View.INVISIBLE);
            view.setVisibility(View.VISIBLE);

        }
            }

    @Override
    public void onLoaderReset(@NonNull android.support.v4.content.Loader<ArrayList<NewsObject>> loader) {
        adapter.cleanArray();

    }

}
