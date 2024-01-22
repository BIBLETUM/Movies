package com.example.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {
    @Headers({
            "Accept: application/json",
            "X-API-KEY: F6SMH2H-CAMMFSB-Q5CB6VE-53FH3R0"
    })
    @GET("movie?rating.kp=7-10&sortField=votes.kp&sortType=-1")
    //@GET("movie?rating.kp=4-8&sortField=votes.kp&sortType=1&limit=100")
    Single<MovieResponce> loadMovies(@Query("page") int page);
}
