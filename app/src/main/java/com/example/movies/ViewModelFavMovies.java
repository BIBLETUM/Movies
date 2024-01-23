package com.example.movies;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ViewModelFavMovies extends AndroidViewModel {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MovieDao movieDao;
    public ViewModelFavMovies(@NonNull Application application) {
        super(application);
        movieDao = MovieDataBase.getInstance(application).movieDao();
    }

    public LiveData<List<Movie>> getMovies() {
        return movieDao.getAllFavMovies();
    }
}
