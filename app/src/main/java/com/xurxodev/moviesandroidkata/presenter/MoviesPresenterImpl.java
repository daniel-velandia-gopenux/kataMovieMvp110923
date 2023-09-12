package com.xurxodev.moviesandroidkata.presenter;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.xurxodev.moviesandroidkata.model.Movie;
import com.xurxodev.moviesandroidkata.presenter.boundary.Contract;

import java.util.List;

import javax.inject.Inject;

public class MoviesPresenterImpl implements Contract.MoviesPresenter {

    Contract.MoviesFragmentView fragmentView;
    Contract.MovieRepository movieRepository;

    @Inject
    public MoviesPresenterImpl(Contract.MoviesFragmentView fragmentView,
                               Contract.MovieRepository movieRepository) {
        this.fragmentView = fragmentView;
        this.movieRepository = movieRepository;
    }

    @Override
    public void getMovies() {
        fragmentView.loadingMovies();

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
                            fragmentView.loadedMovies(movies);
                        } else {
                            fragmentView.showError("movies not found");
                        }

                    }
                };

        moviesAsyncTask.execute();
    }

    @Override
    public void onClickItem(int position) {

    }

}
