package com.xurxodev.moviesandroidkata.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.model.Movie;
import com.xurxodev.moviesandroidkata.view.events.OnClickListener;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter
        extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private OnClickListener onClickListener;

    public MoviesAdapter(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void clearMovies() {
        movies = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movies, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Movie movie = movies.get(position);
        holder.bind(movie, onClickListener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView movieImageView;
        public final TextView titleTextView;

        public ViewHolder(View view) {
            super(view);

            movieImageView = (ImageView) view.findViewById(R.id.item_movie_poster);
            titleTextView = (TextView) view.findViewById(R.id.item_movie_title);
        }

        public void bind(Movie movieItem, OnClickListener onClickListener) {
            Picasso.get()
                    .load(movieItem.getImage())
                    .into(movieImageView);

            titleTextView.setText(movieItem .getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onClick(movieItem);
                }
            });
        }
    }
}
