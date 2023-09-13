package com.xurxodev.moviesandroidkata.presenter;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.xurxodev.moviesandroidkata.model.Movie;
import com.xurxodev.moviesandroidkata.presenter.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.view.fragment.MovieDetailFragment;


import javax.inject.Inject;

public class MovieDetailPresenter {

    private final MovieDetailFragment movieDetailFragment;
    private final MovieRepository movieRepository;

    @Inject
    public MovieDetailPresenter(MovieDetailFragment movieDetailFragment, MovieRepository movieRepository) {
        this.movieDetailFragment = movieDetailFragment;
        this.movieRepository = movieRepository;
    }

    public void getMovie(int position) {

        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, Movie> movieAsyncTask =
                new AsyncTask<Void, Void, Movie>() {
                    @Override
                    protected Movie doInBackground(Void... params) {

                        return movieRepository.getMovie(position);
                    }

                    @Override
                    protected void onPostExecute(Movie movie) {
                        if(movie != null) {
                            movieDetailFragment.loadedMovie(movie);
                        } else {
                            movieDetailFragment.showError("movie not found");
                        }

                    }
                };

        movieAsyncTask.execute();
    }
}
