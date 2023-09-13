package com.xurxodev.moviesandroidkata.presenter;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.xurxodev.moviesandroidkata.model.Movie;
import com.xurxodev.moviesandroidkata.presenter.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.view.fragment.MoviesFragment;

import java.util.List;

import javax.inject.Inject;

public class MoviesPresenter {

    private final MoviesFragment moviesFragment;
    private final MovieRepository movieRepository;

    @Inject
    public MoviesPresenter(MoviesFragment moviesFragment, MovieRepository movieRepository) {
        this.moviesFragment = moviesFragment;
        this.movieRepository = movieRepository;
    }

    public void getMovies() {
        moviesFragment.loadingMovies();

        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, List<Movie>> moviesAsyncTask =
                new AsyncTask<Void, Void, List<Movie>>() {
                    @Override
                    protected List<Movie> doInBackground(Void... params) {

                        return movieRepository.getMovies();
                    }

                    @Override
                    protected void onPostExecute(List<Movie> movies) {
                        if(movies != null) {
                            moviesFragment.loadedMovies(movies);
                        } else {
                            moviesFragment.showError("movies not found");
                        }

                    }
                };

        moviesAsyncTask.execute();
    }

}
