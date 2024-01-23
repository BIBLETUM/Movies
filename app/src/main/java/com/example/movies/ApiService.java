package com.example.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @Headers({
            "Accept: application/json",
            "X-API-KEY: F6SMH2H-CAMMFSB-Q5CB6VE-53FH3R0"
    })
    @GET("movie?rating.kp=7-10&sortField=votes.kp&sortType=-1&limit=30")
    Single<MovieResponce> loadMovies(@Query("page") int page);
    @Headers({
            "Accept: application/json",
            "X-API-KEY: F6SMH2H-CAMMFSB-Q5CB6VE-53FH3R0"
    })
    @GET("movie/{id}")
    Single<VideosResponce> loadTrailers(@Path("id") int id);
    @Headers({
            "Accept: application/json",
            "X-API-KEY: F6SMH2H-CAMMFSB-Q5CB6VE-53FH3R0"
    })
    @GET("review?page=1&limit=10&selectFields=type&selectFields=review&selectFields=author")
    Single<ReviewResponse> loadReviews(@Query("movieId") int movieId);
}
