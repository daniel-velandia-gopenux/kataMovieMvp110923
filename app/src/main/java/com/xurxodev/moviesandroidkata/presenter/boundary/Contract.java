package com.xurxodev.moviesandroidkata.presenter.boundary;

import com.xurxodev.moviesandroidkata.model.Movie;

import java.util.List;

public interface Contract {

    interface MovieRepository {

        List<Movie> getMovies();
    }

    interface MoviesFragmentView {

        void loadedMovies(List<Movie> movies);
        void loadingMovies();
        void showError(String message);

    }

    interface MoviesPresenter {

        void getMovies();

        void onClickItem(int position);
    }
}
