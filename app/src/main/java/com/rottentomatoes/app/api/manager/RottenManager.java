package com.rottentomatoes.app.api.manager;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rottentomatoes.app.api.response.ServiceResponse;
import com.rottentomatoes.app.api.service.Movies;
import com.rottentomatoes.app.api.vo.Movie;
import com.squareup.okhttp.HttpResponseCache;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Trey Robinson on 4/30/14.
 */
public class RottenManager {

    public static RottenManager mInstance;

    private RestAdapter mRestAdapter;

    private Movies movies;

    private static final String API_URL = "http://api.rottentomatoes.com/";
    private String mPrivateKey;

    public static RottenManager getInstance(){
        return mInstance;
    }

    private RottenManager(String key, Context context, long cacheSize){
        this.mPrivateKey = key;

        OkHttpClient okHttpClient = new OkHttpClient();
        HttpResponseCache cache = null;

        try {
            cache = new HttpResponseCache(context.getCacheDir(), cacheSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        okHttpClient.setResponseCache(cache);

        Gson gson = new GsonBuilder()
                .create();

        mRestAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(okHttpClient))
                .setConverter(new GsonConverter(gson))
                .setServer(API_URL)
                .build();

        //setLogLevel(LogLevel.FULL).setLog(new AndroidLog("YOUR_LOG_TAG"))
        mRestAdapter.setLogLevel(RestAdapter.LogLevel.BASIC);


        movies = mRestAdapter.create(Movies.class);
    }

    public static void create(String privateKey, Context context, long cacheSize){
        mInstance = new RottenManager(privateKey, context, cacheSize);
    }

    public void listBoxOffice(Callback<ServiceResponse> callback){
        movies.getBoxOfficeMovies(mPrivateKey, callback);
    }

}
