package com.xurxodev.moviesandroidkata.view.di.component;

import android.app.Application;
import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xurxodev.moviesandroidkata.model.data.MovieRepositoryImpl;
import com.xurxodev.moviesandroidkata.presenter.MoviesPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {


    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    MoviesPresenter.MovieRepository provideMovieRepository(Application applicationContext, Gson gson) {
        MoviesPresenter.MovieRepository movieRepository = new MovieRepositoryImpl(applicationContext, gson);
        return movieRepository;
    }

    @Provides
    @Singleton
    Context providesContext(Application application) {
        return application.getApplicationContext();
    }
}