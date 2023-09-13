package com.xurxodev.moviesandroidkata.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.xurxodev.moviesandroidkata.MoviesApplication;
import com.xurxodev.moviesandroidkata.databinding.FragmentMovieDetailBinding;
import com.xurxodev.moviesandroidkata.model.Movie;
import com.xurxodev.moviesandroidkata.presenter.MovieDetailPresenter;

import javax.inject.Inject;

public class MovieDetailFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private int mPosition;
    private FragmentMovieDetailBinding binding;
    @Inject
    MovieDetailPresenter movieDetailPresenter;
    @Inject
    Picasso picasso;

    public static MovieDetailFragment newInstance(int position) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public MovieDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARG_POSITION);
        }
        ((MoviesApplication) getActivity().getApplication())
                .getMoviesComponent().getMovieSubComponent().create(this).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false);

        movieDetailPresenter.getMovie(mPosition);

        return binding.getRoot();
    }

    public void loadedMovie(Movie movie) {
        binding.progressBar.setVisibility(View.GONE);
        picasso.get().load(movie.getImage()).into(binding.itemMoviePoster);
        binding.itemMovieTitle.setText(movie.getTitle());
        binding.itemMovieOverview.setText("Overview");
        binding.itemMovieDescription.setText(movie.getDescription());
    }

    public void showError(String movieNotFound) {
        Toast.makeText(getContext(), movieNotFound, Toast.LENGTH_SHORT).show();
    }

}