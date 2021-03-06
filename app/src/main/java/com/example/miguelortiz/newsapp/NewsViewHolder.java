package com.example.miguelortiz.newsapp;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    ImageView newsImage;
    TextView newsTitle;
    TextView newsSource;
    TextView newsDate;
    TextView newsAuthor;

    ConstraintLayout constraintLayout;
    RelativeLayout relativeLayout;

    public NewsViewHolder(View itemView){
        super(itemView);
        newsImage = itemView.findViewById(R.id.newsImage);
        newsTitle = itemView.findViewById(R.id.newsTitle);
        newsSource = itemView.findViewById(R.id.newsSource);
        newsDate = itemView.findViewById(R.id.newsDate);
        newsAuthor = itemView.findViewById(R.id.newsAuthor);
        relativeLayout = itemView.findViewById(R.id.newsHolderLayout);
        constraintLayout = itemView.findViewById(R.id.newshomeLayout);
    }




}
