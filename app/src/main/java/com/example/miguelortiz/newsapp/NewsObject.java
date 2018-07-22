package com.example.miguelortiz.newsapp;

import android.graphics.Bitmap;

public class NewsObject {

    Bitmap newsImage;
    String newsTitle;
    String newsSource;

    public NewsObject(Bitmap image, String title, String source){

        newsImage = image;
        newsTitle = title;
        newsSource = source;

    }

    public Bitmap getImage(){

        return newsImage;

    }

    public String getTitle(){

        return newsTitle;

    }

    public String getSource(){

        return newsSource;

    }




}
