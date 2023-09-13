package com.xurxodev.moviesandroidkata.view.di.subComponent.MoviesSubComponent;

import com.xurxodev.moviesandroidkata.presenter.MoviesPresenter;
import com.xurxodev.moviesandroidkata.presenter.boundary.MovieRepository;
import com.xurxodev.moviesandroidkata.view.fragment.MoviesFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesModule {

    @Provides
    MoviesPresenter providesMoviePresenter(MoviesFragment moviesFragment,
                                           MovieRepository movieRepository) {
        return new MoviesPresenter(moviesFragment, movieRepository);
    }

}
