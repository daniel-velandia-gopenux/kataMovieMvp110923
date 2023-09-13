package com.xurxodev.moviesandroidkata.view.di.subComponent.MovieSubComponent;

import com.xurxodev.moviesandroidkata.view.fragment.MovieDetailFragment;

import dagger.BindsInstance;
import dagger.Subcomponent;

@Subcomponent(modules = {MovieDetailModule.class})
public interface MovieDetailSubComponent {

    void inject(MovieDetailFragment movieFragment);

    @Subcomponent.Factory
    interface Factory {
        MovieDetailSubComponent create(@BindsInstance MovieDetailFragment movieFragment);
    }
}
