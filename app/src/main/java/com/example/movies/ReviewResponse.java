package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {
    @SerializedName("docs")
    private List<Review> reviews;

    @Override
    public String toString() {
        return "ReviewResponce{" +
                "reviews=" + reviews +
                '}';
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public ReviewResponse(List<Review> reviews) {
        this.reviews = reviews;
    }
}
