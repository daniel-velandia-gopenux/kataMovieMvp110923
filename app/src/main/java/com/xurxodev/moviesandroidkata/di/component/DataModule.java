package com.xurxodev.moviesandroidkata.di.component;

import android.app.Application;
import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xurxodev.moviesandroidkata.model.data.MovieRepositoryImpl;
import com.xurxodev.moviesandroidkata.presenter.boundary.Contract;

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
    Contract.MovieRepository provideMovieRepository(Application applicationContext, Gson gson) {
        Contract.MovieRepository diskMovieRepository = new MovieRepositoryImpl(applicationContext, gson);
        return diskMovieRepository;
    }

    @Provides
    @Singleton
    Context providesContext(Application application) {
        return application.getApplicationContext();
    }
}