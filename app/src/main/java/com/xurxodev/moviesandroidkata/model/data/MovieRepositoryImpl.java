package com.xurxodev.moviesandroidkata.model.data;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.model.Movie;
import com.xurxodev.moviesandroidkata.presenter.boundary.MovieRepository;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class MovieRepositoryImpl implements MovieRepository {

    private Context applicationContext;
    private Gson gson;

    @Inject
    public MovieRepositoryImpl(Application applicationContext, Gson gson){
       this.applicationContext = applicationContext;
       this.gson = gson;
    }

    @Override
    public List<Movie> getMovies() {

        Movie[] movies = transformGson();

        simulateDelay();

        return Arrays.asList(movies);
    }

    private Movie[] transformGson() {
        try {
            String jsonString = getJsonString();
            return gson.fromJson(jsonString, Movie[].class);
        } catch (IOException e){
            Log.e("error while read file", e.getMessage());
        }
        return new Movie[0];
    }

    private String getJsonString() throws IOException {
        InputStream inputStream = applicationContext.getResources().openRawResource(R.raw.movies);
        byte[] b = new byte[inputStream.available()];
        inputStream.read(b);

        return new String(b);
    }

    private void simulateDelay(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
