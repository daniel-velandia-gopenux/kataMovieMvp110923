package com.xurxodev.moviesandroidkata.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xurxodev.moviesandroidkata.MoviesApplication;
import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.databinding.FragmentMoviesBinding;
import com.xurxodev.moviesandroidkata.model.Movie;
import com.xurxodev.moviesandroidkata.presenter.MoviesPresenter;
import com.xurxodev.moviesandroidkata.view.activity.MoviesActivity;
import com.xurxodev.moviesandroidkata.view.adapter.MoviesAdapter;
import com.xurxodev.moviesandroidkata.view.event.OnClickItemListener;

import java.util.List;

import javax.inject.Inject;

public class MoviesFragment extends Fragment {

    private MoviesAdapter adapter;
    private FragmentMoviesBinding binding;
    @Inject
    MoviesPresenter moviesPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MoviesApplication) getActivity().getApplication()).getMoviesComponent()
                .getMoviesSubComponent().create(this).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentMoviesBinding.inflate(inflater, container, false);

        initializeRefreshButton();
        initializeAdapter();
        initializeRecyclerView();

        moviesPresenter.getMovies();

        return binding.getRoot();
    }

    private void initializeRefreshButton() {
        binding.refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviesPresenter.getMovies();
            }
        });
    }

    private void initializeAdapter() {
        adapter = new MoviesAdapter(new OnClickItemListener() {
            @Override
            public void onClick(int position) {
                changeFragment(position);
            }
        });
    }

    private void initializeRecyclerView() {
        binding.recyclerviewMovies.setAdapter(adapter);
    }

    public void loadingMovies() {
        adapter.clearMovies();
        binding.moviesTitleTextView.setText(R.string.loading_movies_text);
    }

    public void loadedMovies(List<Movie> movies) {
        adapter.setMovies(movies);
        refreshTitleWithMoviesCount(movies);
    }

    private void refreshTitleWithMoviesCount(List<Movie> movies) {
        String countText = getString(R.string.movies_count_text);

        binding.moviesTitleTextView.setText(String.format(countText, movies.size()));
    }

    public void showError(String moviesNotFound) {
        Toast.makeText(getContext(), moviesNotFound, Toast.LENGTH_SHORT).show();
    }

    public void changeFragment(int position) {
        MoviesActivity moviesActivity = (MoviesActivity) getActivity();
        moviesActivity.showMovieFragment(position);
    }
}
