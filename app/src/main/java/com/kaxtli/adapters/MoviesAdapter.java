package com.kaxtli.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kaxtli.awesomemovieapp.R;
import com.kaxtli.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kaxtli on 11/03/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context)
                .load(movies.get(position).getPoster_path())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        final ImageView image;

        public ViewHolder(View view){
            super(view);
            image = (ImageView)view.findViewById(R.id.image_movie_poster);
        }
    }
}
