package com.xurxodev.moviesandroidkata.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xurxodev.moviesandroidkata.databinding.ActivityMoviesBinding;
import com.xurxodev.moviesandroidkata.view.fragment.MovieDetailFragment;
import com.xurxodev.moviesandroidkata.view.fragment.MoviesFragment;

public class MoviesActivity extends AppCompatActivity {

    private ActivityMoviesBinding binding;
    private MoviesFragment moviesFragment;
    private MovieDetailFragment movieDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoviesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeToolBar();

        if (savedInstanceState == null) {
            showMoviesFragment();
        }
    }

    private void showMoviesFragment() {
        moviesFragment = new MoviesFragment();

        getSupportFragmentManager().beginTransaction()
                .add(binding.moviesListContainer.getId(), moviesFragment)
                .commit();

    }

    private void initializeToolBar() {
        setSupportActionBar(binding.toolbar);
    }

    public void showMovieFragment(int position) {
        movieDetailFragment = MovieDetailFragment.newInstance(position);
        getSupportFragmentManager().beginTransaction()
                .hide(moviesFragment)
                .add(binding.moviesListContainer.getId(), movieDetailFragment)
                .addToBackStack(null)
                .commit();
    }

    private void goBack() {
        if(moviesFragment.isHidden()) {
            getSupportFragmentManager().beginTransaction()
                    .hide(movieDetailFragment)
                    .show(moviesFragment)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goBack();
    }
}
