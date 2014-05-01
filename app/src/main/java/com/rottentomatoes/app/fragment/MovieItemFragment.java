package com.rottentomatoes.app.fragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.rottentomatoes.app.MovieDetailActivity;
import com.rottentomatoes.app.R;
import com.rottentomatoes.app.adapter.MovieArrayAdapter;
import com.rottentomatoes.app.api.manager.RottenManager;
import com.rottentomatoes.app.api.response.ServiceResponse;
import com.rottentomatoes.app.api.vo.Movie;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A fragment representing a list of Items.
 * <p />
 * <p />
 * interface.
 */
public class MovieItemFragment extends ListFragment {

    private List<Movie> data;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RottenManager.getInstance().listBoxOffice(new Callback<ServiceResponse>() {
            @Override
            public void success(ServiceResponse movies, Response response) {
                data = movies.movies;
                setListAdapter(new MovieArrayAdapter(getActivity(), movies.movies));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(this.getClass().toString(), "wooops");
            }
        });

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra("movie", data.get(position).title);
        intent.putExtra("synopsis", data.get(position).synopsis);
        startActivity(intent);
    }


}
