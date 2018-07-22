package com.example.miguelortiz.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;

public class NewsLoader extends AsyncTaskLoader<ArrayList<NewsObject>> {

    ArrayList<NewsObject> arrayList = new ArrayList<NewsObject>();
    String searcParameter;

    public NewsLoader(Context context, String searchcriteria) {
        super(context);
        searcParameter = searchcriteria;
    }

    @Override
    public ArrayList<NewsObject> loadInBackground() {

        Bitmap b = Bitmap.createBitmap(50,50, Bitmap.Config.ARGB_4444);

//        arrayList = FetchData.fetchNewsData(searcParameter);

        arrayList.add(new NewsObject(b,"News Header XXXX","CNN"));
        arrayList.add(new NewsObject(b,"News Header YYYY","MCBC"));
        arrayList.add(new NewsObject(b,"News Header ZZZZ","Fox"));
        arrayList.add(new NewsObject(b,"News Header UUUU","Univision"));

        return arrayList;
    }
}
