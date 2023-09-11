package com.xurxodev.moviesandroidkata.presenter;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.xurxodev.moviesandroidkata.presenter.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.model.Movie;
import com.xurxodev.moviesandroidkata.presenter.boundary.MoviePresenter;
import com.xurxodev.moviesandroidkata.presenter.boundary.MovieView;

import java.util.List;

import javax.inject.Inject;

public class MoviePresenterImpl implements MoviePresenter {

    MovieView movieView;
    MovieRepository movieRepository;

    @Inject
    public MoviePresenterImpl(MovieView movieView, MovieRepository movieRepository) {
        this.movieView = movieView;
        this.movieRepository = movieRepository;
    }

    @Override
    public void getMovies() {
        movieView.loadingMovies();

        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, List<Movie>> moviesAsyncTask =
                new AsyncTask<Void, Void, List<Movie>>() {
                    @Override
                    protected List<Movie> doInBackground(Void... params) {

                         // String.valueOf(movieRepository.getMovies().size())
                        return movieRepository.getMovies();
                    }

                    @Override
                    protected void onPostExecute(List<Movie> movies) {
                        if(movies != null) {
                            movieView.loadedMovies(movies);
                        } else {
                            movieView.showError("movies not found");
                        }

                    }
                };

        moviesAsyncTask.execute();
    }

    @Override
    public void onClickItem(Movie movie) {
        movieView.showError("click item");
    }

}
