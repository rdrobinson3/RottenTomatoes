package com.rottentomatoes.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rottentomatoes.app.R;
import com.rottentomatoes.app.api.vo.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Trey Robinson on 4/30/14.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private List<Movie> mData;
    private Context mContext;

    public MovieArrayAdapter(Context context, List<Movie> data) {
        super(context, R.layout.movie_list_item);
        this.mContext = context;
        this.mData = data;

    }

    @Override
    public int getCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = convertView;

        ViewHolder viewHolder;
        if(v != null){
            viewHolder = (ViewHolder)v.getTag();
        } else {
            v = inflater.inflate( R.layout.movie_list_item, parent, false);
            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);
        }

        if (mData != null) {
            Movie movie = mData.get(position);

            if (movie != null) {
                viewHolder.movieTitle.setText(movie.title);
                viewHolder.criticRating.setText(movie.ratings.critics_rating);
                viewHolder.criticScore.setText(movie.ratings.critics_score);
                viewHolder.mpaaRating.setText(movie.mpaa_rating);
                Picasso.with(mContext).load(movie.posters.thumbnail).into(viewHolder.movieImage);
            }
        }

        return v;
    }

    static class ViewHolder{
        @InjectView(R.id.moveTitle)TextView movieTitle;
        @InjectView(R.id.movieImage)ImageView movieImage;
        @InjectView(R.id.criticRating)TextView criticRating;
        @InjectView(R.id.criticScore)TextView criticScore;
        @InjectView(R.id.mpaaRating)TextView mpaaRating;
        ViewHolder(View view){
            ButterKnife.inject(this, view);
        }
    }
}
