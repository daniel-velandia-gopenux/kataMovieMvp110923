package com.xurxodev.moviesandroidkata.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.xurxodev.moviesandroidkata.MoviesApplication;
import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.model.Movie;
import com.xurxodev.moviesandroidkata.presenter.boundary.MoviePresenter;
import com.xurxodev.moviesandroidkata.view.adapter.MoviesAdapter;
import com.xurxodev.moviesandroidkata.presenter.boundary.MovieView;
import com.xurxodev.moviesandroidkata.view.events.OnClickListener;

import java.util.List;

import javax.inject.Inject;

public class MoviesFragment extends Fragment implements MovieView {

    private MoviesAdapter adapter;
    private RecyclerView recyclerView;
    private View rootView;
    private TextView moviesCountTextView;
    private ImageButton refreshButton;
    @Inject
    MoviePresenter moviePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MoviesApplication) getActivity().getApplication()).getMoviesComponent()
                .getMovieSubComponent().create(this).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_movies, container, false);

        initializeTitle();
        initializeRefreshButton();
        initializeAdapter();
        initializeRecyclerView();

        moviePresenter.getMovies();

        return rootView;
    }

    private void initializeTitle() {
        moviesCountTextView = (TextView) rootView.findViewById(
                R.id.movies_title_text_view);
    }

    private void initializeRefreshButton() {
        refreshButton = (ImageButton) rootView.findViewById(
                R.id.refresh_button);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviePresenter.getMovies();
            }
        });
    }

    private void initializeAdapter() {
        adapter = new MoviesAdapter(new OnClickListener() {
            @Override
            public void onClick(Movie movie) {
                moviePresenter.onClickItem(movie);
            }
        });
    }

    private void initializeRecyclerView() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_movies);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadingMovies() {
        adapter.clearMovies();
        moviesCountTextView.setText(R.string.loading_movies_text);
    }

    @Override
    public void loadedMovies(List<Movie> movies) {
        adapter.setMovies(movies);
        refreshTitleWithMoviesCount(movies);
    }

    private void refreshTitleWithMoviesCount(List<Movie> movies) {
        String countText = getString(R.string.movies_count_text);

        moviesCountTextView.setText(String.format(countText, movies.size()));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
