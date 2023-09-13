package com.xurxodev.moviesandroidkata.view.di.subComponent;

import com.xurxodev.moviesandroidkata.presenter.MoviesPresenter;
import com.xurxodev.moviesandroidkata.view.fragment.MoviesFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentersModule {

    @Provides
    MoviesPresenter providesMoviePresenter(MoviesFragment moviesFragment,
                                           MoviesPresenter.MovieRepository movieRepository) {
        return new MoviesPresenter(moviesFragment, movieRepository);
    }

}
