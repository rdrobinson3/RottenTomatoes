package com.rottentomatoes.app.api.service;

import com.rottentomatoes.app.api.response.ServiceResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Trey Robinson on 4/30/14.
 */
public interface Movies {
//http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=69prjq4hdtxhkd2xbgx5mvnp

    @GET("/api/public/v1.0/lists/movies/box_office.json")
    public void getBoxOfficeMovies(@Query("apikey") String apikey, Callback<ServiceResponse> callback);

}
