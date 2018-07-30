package com.example.miguelortiz.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private ArrayList<NewsObject> newsArray;
    private Context context;
    private String dateField;
    private String sourceField;
    private String authorField;
    Intent i = new Intent(Intent.ACTION_VIEW);

    public NewsRecyclerViewAdapter(Context context,ArrayList<NewsObject> newsArray) {

        this.newsArray = newsArray;
        this.context = context;
        dateField = context.getResources().getString(R.string.date) + " ";
        sourceField = context.getResources().getString(R.string.source) + " ";
        authorField = context.getResources().getString(R.string.author) + " ";

    }

    public boolean updateData(ArrayList<NewsObject> updatedArray){

        this.newsArray = updatedArray;
        return newsArray.isEmpty();

    }

    public void cleanArray(){

        this.newsArray.clear();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_layout,parent,false);
        final NewsViewHolder holder = new NewsViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String parsedURL = newsArray.get(holder.getPosition()).getSource();
                i.setData(Uri.parse(parsedURL));
                context.startActivity(i);
            }

        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        String dateField1 = (dateField + newsArray.get(position).newsDate);
        String authorField1 = (authorField + newsArray.get(position).newsAuthor);
        String sourceField1 = (sourceField + newsArray.get(position).newsSource);
        holder.newsTitle.setText(newsArray.get(position).newsTitle);
        holder.newsSource.setText(sourceField1);
        holder.newsAuthor.setText(authorField1);
        holder.newsDate.setText(dateField1);
        holder.newsImage.setImageBitmap(newsArray.get(position).newsImage);
    }

    @Override
    public int getItemCount() {
        return newsArray.size();
    }

}
