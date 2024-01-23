package com.example.movies;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Trailer>> trailers = new MutableLiveData<>();
    private final MutableLiveData<List<Review>> reviews = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MovieDao movieDao;
    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDataBase.getInstance(application).movieDao();
    }
    public LiveData<Movie> getFavMovie(int id){
        return movieDao.getFavMovie(id);
    }
    public LiveData<List<Trailer>> getTrailers() {
        return trailers;
    }
    public LiveData<List<Review>> getReviews() {
        return reviews;
    }
    public void insertMovie(Movie movie){
        Disposable disposable = movieDao.insertMovie(movie)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    public void removeMovie(int movieId){
        Disposable disposable = movieDao.deleteMovie(movieId)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }
    public void loadTrailers(int id){
        Disposable disposable = ApiFactory.apiService.loadTrailers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<VideosResponce, List<Trailer>>() {
                    @Override
                    public List<Trailer> apply(VideosResponce videosResponce) throws Throwable {
                        return videosResponce.getVideos().getTrailerList();
                    }
                })
                .subscribe(new Consumer<List<Trailer>>() {
                    @Override
                    public void accept(List<Trailer> trailersFromAPI) throws Throwable {
                        List<Trailer> tmp = new ArrayList<>(new HashSet<>(trailersFromAPI));
                        trailers.setValue(tmp);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("Error MovieDetailVM", throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void loadReviews(int movieId){
        Disposable disposable = ApiFactory.apiService.loadReviews(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReviewResponse>() {
                    @Override
                    public void accept(ReviewResponse reviewResponce) throws Throwable {
                        reviews.setValue(reviewResponce.getReviews());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("Error MovieDetailVM", throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
