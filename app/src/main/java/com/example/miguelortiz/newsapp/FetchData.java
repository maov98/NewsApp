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

    public static final String LOG_TAG = FetchData.class.getSimpleName();


    public static ArrayList<NewsObject> fetchNewsData(String requestURL) {

        URL url = createURL(requestURL);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
        ArrayList<NewsObject> newsObject = extractFeatureFromJson(jsonResponse);

        // Return the {@link Event}
        return newsObject;


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
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
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

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
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


    /**
     * Return an {@link NewsObject} object by parsing out information
     * about the first earthquake from the input earthquakeJSON string.
     */
    private static ArrayList<NewsObject> extractFeatureFromJson(String newsJSON) {

        ArrayList<JSONObject> jsonObjectArrayList;
        ArrayList<JSONObject> jsonObjectFields;
        ArrayList<NewsObject> newsObjects;
        String title;
        String thumbnail;
        String source;
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }

        try {
            JSONObject baseJsonResponse = new JSONObject(newsJSON);
            JSONArray featureArray = baseJsonResponse.getJSONArray("results");

            // If there are results in the features array
            if (featureArray.length() > 0) {

                int size = featureArray.length();

                jsonObjectArrayList = new ArrayList<>();
                jsonObjectFields = new ArrayList<>();
                newsObjects = new ArrayList<>();


                for(int i=0;i<= size;i++){

                    jsonObjectArrayList.add(featureArray.getJSONObject(i));
                    jsonObjectFields.add(jsonObjectArrayList.get(i).getJSONObject("fields"));
                    source = jsonObjectArrayList.get(i).getString("webUrl");
                    thumbnail = jsonObjectFields.get(i).getString("thumbnail");
                    title = jsonObjectFields.get(i).getString("headline");
                    newsObjects.add(new NewsObject(getBitmap(thumbnail),title,source));

                }

                // Create a new {@link Event} object
                return newsObjects;
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }
        return null;
    }

    private static Bitmap getBitmap(String bitmapUrl){

        Bitmap thumbnail = null;
        try{

            InputStream in = new java.net.URL(bitmapUrl).openStream();
            thumbnail = BitmapFactory.decodeStream(in);

        }catch (Exception e){
            Log.e("URL","Error fetching thumbnail");
        }

        return thumbnail;







    }

}
