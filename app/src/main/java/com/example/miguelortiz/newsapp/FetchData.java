package com.example.miguelortiz.newsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class FetchData {

    private static final String LOG_TAG = FetchData.class.getSimpleName();

    public static ArrayList<NewsObject> fetchNewsData(String requestURL) {

        String jsonResponse = null;
        URL url = createURL(requestURL);

        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {

            Log.e(LOG_TAG,"Fetch method failed");
        }

        ArrayList<NewsObject> thisNewsObject = extractFeatureFromJson(jsonResponse);

        return thisNewsObject;

    }

    private static URL createURL(String stringURL) {

        URL url = null;
        try {

            url = new URL(stringURL);

        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error Creating URL");
        }

        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {

            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(40000 /* milliseconds */);
            urlConnection.setConnectTimeout(60000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);

            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the News JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream (InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static ArrayList<NewsObject> extractFeatureFromJson (String newsJason){

        String title;
        String thumbnail;
        String source;
        String dateField = "";
        String author = "";
        ArrayList<NewsObject> newsObjects = new ArrayList<>();

        if (TextUtils.isEmpty(newsJason)) {
            return null;
        }

        try {
            JSONObject baseJsonResponse = new JSONObject(newsJason);
            JSONObject responseJasonObject = baseJsonResponse.getJSONObject("response");
            JSONArray resultsArray = responseJasonObject.getJSONArray("results");
            JSONObject rootJSON2;
            JSONObject rootJSON3;
            JSONObject fields2;
            JSONArray tagsArray;


            if (resultsArray.length() > 0) {

                int size = resultsArray.length();

                for (int i = 0; i < size; i++) {

                    rootJSON2  = resultsArray.getJSONObject(i);
                    dateField = rootJSON2.getString("webPublicationDate");
                    fields2 = rootJSON2.getJSONObject("fields");
                    tagsArray = rootJSON2.getJSONArray("tags");
                    if(tagsArray.length()>0){
                        rootJSON3 = tagsArray.getJSONObject(0);
                        author = rootJSON3.getString("webTitle");
                    }
                    else {
                        author="";
                    }

                    source = fields2.getString("shortUrl");
                    thumbnail = fields2.getString("thumbnail");
                    title = fields2.getString("headline");
                    String trimmedDate = dateField.substring(0,10);
                    newsObjects.add(new NewsObject(getBitmap(thumbnail),title,source,trimmedDate,author));
                }
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the News JSON results", e);
        }
        return newsObjects;
    }

    private static Bitmap getBitmap(String bitmapUrl){

        Bitmap thumbnail = null;
        try {

            InputStream in = new java.net.URL(bitmapUrl).openStream();
            thumbnail = BitmapFactory.decodeStream(in);

        } catch (Exception e) {
            Log.e("URL", "Error fetching thumbnail");
        }

        return thumbnail;
    }

}


