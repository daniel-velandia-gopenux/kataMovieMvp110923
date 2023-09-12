package com.xurxodev.moviesandroidkata.di.subComponent;

import com.xurxodev.moviesandroidkata.presenter.MoviesPresenterImpl;
import com.xurxodev.moviesandroidkata.presenter.boundary.Contract;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentersModule {

    @Provides
    Contract.MoviesPresenter providesMoviePresenter(Contract.MoviesFragmentView fragmentView,
                                                    Contract.MovieRepository movieRepository) {
        return new MoviesPresenterImpl(fragmentView, movieRepository);
    }

}
