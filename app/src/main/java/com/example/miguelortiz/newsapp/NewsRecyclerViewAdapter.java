package com.example.miguelortiz.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private ArrayList<NewsObject> newsArray;
    private Context context;
    private int scenario;

    public NewsRecyclerViewAdapter(Context context,ArrayList<NewsObject> newsArray) {

        this.newsArray = newsArray;
        this.context = context;
    }

    public void updateData(ArrayList<NewsObject> updatedArray){

        this.newsArray = updatedArray;
    }

    public void clearData(ArrayList<NewsObject> updatedArray){

        this.newsArray.clear();
    }

    public void cleanArray(){

        this.newsArray.clear();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_layout,parent,false);
        NewsViewHolder holder = new NewsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {


        holder.newsTitle.setText(newsArray.get(position).newsTitle);
        holder.newsSource.setText(newsArray.get(position).newsSource);
        holder.newsImage.setImageBitmap(newsArray.get(position).newsImage);



    }

    @Override
    public int getItemCount() {
        return newsArray.size();
    }


}
