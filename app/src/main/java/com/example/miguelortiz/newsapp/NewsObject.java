package com.example.miguelortiz.newsapp;

import android.graphics.Bitmap;

public class NewsObject {

    Bitmap newsImage;
    String newsTitle;
    String newsSource;
    String newsDate;
    String newsAuthor;

    public NewsObject(Bitmap image, String title, String source, String date, String author){

        newsImage = image;
        newsTitle = title;
        newsSource = source;
        newsDate = date;
        newsAuthor = author;

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

    public String getAuthor(){

        return newsAuthor;

    }

    public String getDate(){

        return newsDate;

    }




}
