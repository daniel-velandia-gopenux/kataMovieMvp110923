package com.xurxodev.moviesandroidkata.di.subComponent;

import com.xurxodev.moviesandroidkata.presenter.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.presenter.MoviePresenterImpl;
import com.xurxodev.moviesandroidkata.presenter.boundary.MoviePresenter;
import com.xurxodev.moviesandroidkata.presenter.boundary.MovieView;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesModule {


    @Provides
    MoviePresenter providesMoviePresenter(MovieView movieView, MovieRepository movieRepository) {
        return new MoviePresenterImpl(movieView, movieRepository);
    }
}
