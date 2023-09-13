package com.xurxodev.moviesandroidkata.view.di.subComponent.MovieSubComponent;

import com.xurxodev.moviesandroidkata.presenter.MovieDetailPresenter;
import com.xurxodev.moviesandroidkata.presenter.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.view.fragment.MovieDetailFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailModule {

    @Provides
    MovieDetailPresenter providesMovieDetailPresenter(MovieDetailFragment movieDetailFragment,
                                                MovieRepository movieRepository) {
        return new MovieDetailPresenter(movieDetailFragment, movieRepository);
    }
}
