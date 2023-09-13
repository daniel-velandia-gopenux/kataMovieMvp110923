package com.xurxodev.moviesandroidkata.view.di.subComponent.MoviesSubComponent;

import com.xurxodev.moviesandroidkata.view.fragment.MoviesFragment;

import dagger.BindsInstance;
import dagger.Subcomponent;

@Subcomponent(modules = {MoviesModule.class})
public interface MoviesSubComponent {
    void inject(MoviesFragment moviesFragment);

    @Subcomponent.Factory
    interface Factory {
        MoviesSubComponent create(@BindsInstance MoviesFragment moviesFragment);
    }
}
