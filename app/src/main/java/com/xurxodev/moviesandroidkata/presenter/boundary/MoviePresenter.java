package com.xurxodev.moviesandroidkata.presenter.boundary;

import com.xurxodev.moviesandroidkata.model.Movie;

public interface MoviePresenter {

    void getMovies();

    void onClickItem(Movie movie);
}
