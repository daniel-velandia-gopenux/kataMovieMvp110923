package com.xurxodev.moviesandroidkata.di.subComponent;

import com.xurxodev.moviesandroidkata.presenter.boundary.MovieView;
import com.xurxodev.moviesandroidkata.view.fragment.MoviesFragment;

import dagger.BindsInstance;
import dagger.Subcomponent;

@Subcomponent(modules = {MoviesModule.class})
public interface MoviesSubComponent {
    void inject(MoviesFragment moviesFragment);

    @Subcomponent.Factory
    interface Factory {
        MoviesSubComponent create(@BindsInstance MovieView movieView);
    }
}
