package com.example.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ActivityFavMovies extends AppCompatActivity {
    private ViewModelFavMovies viewModelFavMovies;
    private RecyclerView recyclerViewFavMovies;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_movies);

        viewModelFavMovies = new ViewModelProvider(this).get(ViewModelFavMovies.class);

        recyclerViewFavMovies = findViewById(R.id.recyclerViewFavMovies);

        moviesAdapter = new MoviesAdapter();
        recyclerViewFavMovies.setAdapter(moviesAdapter);
        recyclerViewFavMovies.setLayoutManager(new GridLayoutManager(this, 2));

        moviesAdapter.setOnPosterClickListener(new MoviesAdapter.OnPosterClickListener() {
            @Override
            public void onPosterClick(Movie movie) {
                Intent intent = MovieDetailActivity.newIntent(ActivityFavMovies.this, movie);
                startActivity(intent);
            }
        });

        viewModelFavMovies.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                moviesAdapter.setMovies(movies);
            }
        });
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, ActivityFavMovies.class);
    }
}