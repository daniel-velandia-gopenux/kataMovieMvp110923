package com.xurxodev.moviesandroidkata.presenter.boundary;

import com.xurxodev.moviesandroidkata.model.Movie;

import java.util.List;

public interface MovieView {

    void loadedMovies(List<Movie> movies);
    void loadingMovies();
    void showError(String message);

}
