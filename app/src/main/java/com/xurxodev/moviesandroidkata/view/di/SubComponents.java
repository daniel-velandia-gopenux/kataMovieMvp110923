package com.xurxodev.moviesandroidkata.view.di;

import com.xurxodev.moviesandroidkata.view.di.subComponent.MovieSubComponent.MovieDetailSubComponent;
import com.xurxodev.moviesandroidkata.view.di.subComponent.MoviesSubComponent.MoviesSubComponent;

import dagger.Module;

@Module(subcomponents = {MoviesSubComponent.class, MovieDetailSubComponent.class})
public class SubComponents {
}
