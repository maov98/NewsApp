package com.example.miguelortiz.newsapp;


import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.LoaderManager;

import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class TechnologyFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<NewsObject>>{

    ArrayList<NewsObject> arrayList = new ArrayList<NewsObject>();
    NewsRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    String urlSearch = "https://content.guardianapis.com/search?q=technology&format=json&from-date=2018-07-19&show-fields=headline,thumbnail,short-url&show-refinements=all&order-by=relevance&show-elements=image&api-key=3726085a-e013-4212-8ae5-2811e39ebba2";



    public TechnologyFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Context context = getActivity();
        View view = inflater.inflate(R.layout.fragment_technology,container,false);
//        recyclerView = view.findViewById(R.id.TechnologyRecyclerView);
////        getLoaderManager().initLoader(2,null, context).forceLoad();
////        adapter = new NewsRecyclerViewAdapter(,arrayList);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }


    @NonNull
    @Override
    public Loader<ArrayList<NewsObject>> onCreateLoader(int id, @Nullable Bundle args) {
//        return new NewsLoader(getContext(),urlSearch);
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<NewsObject>> loader, ArrayList<NewsObject> data) {
        adapter.updateData(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<NewsObject>> loader) {
        adapter.cleanArray();

    }



}
