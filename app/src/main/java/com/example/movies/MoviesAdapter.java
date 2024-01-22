package com.example.movies;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    private List<Movie> movies = new ArrayList<>();

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_item,
                parent,
                false
        );
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        Movie movie = movies.get(position);
        if(movie.getPoster().getUrl() != null){
            Glide.with(holder.itemView)
                    .load(movie.getPoster().getUrl())
                    .into(holder.posterImageView);
        } else {
            Glide.with(holder.itemView)
                    .load("https://st.kp.yandex.net/images/no-poster.gif")
                    .into(holder.posterImageView);
        }

        double rating = movie.getRating().getKp();
        int backGroundId;
        if(rating >= 7.2){
            backGroundId = R.drawable.circle_green;
        } else if (rating >= 6.0) {
            backGroundId = R.drawable.circle_orange;
        } else {
            backGroundId = R.drawable.circle_red;
        }
        Drawable background = ContextCompat.getDrawable(holder.itemView.getContext(), backGroundId);
        holder.ratingTextView.setBackground(background);
        holder.ratingTextView.setText(String.format("%.1f", rating));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MoviesViewHolder extends RecyclerView.ViewHolder {
        private final ImageView posterImageView;
        private final TextView ratingTextView;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.posterImageView);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
        }
    }

}
