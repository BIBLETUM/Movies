package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Videos {
    @SerializedName("trailers")
    private List<Trailer> trailerList;

    public List<Trailer> getTrailerList() {
        return trailerList;
    }

    public Videos(List<Trailer> trailerList) {
        this.trailerList = trailerList;
    }

    @Override
    public String toString() {
        return "Videos{" +
                "trailerList=" + trailerList +
                '}';
    }
}
