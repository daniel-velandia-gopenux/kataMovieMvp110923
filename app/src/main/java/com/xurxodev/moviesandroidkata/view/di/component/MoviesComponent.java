package com.xurxodev.moviesandroidkata.view.di.component;

import android.app.Application;

import com.xurxodev.moviesandroidkata.view.di.SubComponents;
import com.xurxodev.moviesandroidkata.view.di.subComponent.MovieSubComponent.MovieDetailSubComponent;
import com.xurxodev.moviesandroidkata.view.di.subComponent.MoviesSubComponent.MoviesSubComponent;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules={DataModule.class, NetModule.class, PicassoModule.class, SubComponents.class})
public interface MoviesComponent {

    MoviesSubComponent.Factory getMoviesSubComponent();
    MovieDetailSubComponent.Factory getMovieSubComponent();

    @Component.Factory
    interface Factory {
        MoviesComponent create(@BindsInstance Application application);
    }
}