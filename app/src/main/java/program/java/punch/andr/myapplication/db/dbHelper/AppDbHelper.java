package program.java.punch.andr.myapplication.db.dbHelper;


import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import program.java.punch.andr.myapplication.data.model.Movie;
import program.java.punch.andr.myapplication.db.AppDatabase;
import program.java.punch.andr.myapplication.db.dbHelper.interfaces.DbHelper;

public class AppDbHelper implements DbHelper {
    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }



    @Override
    public Single<Boolean> insertFavouriteMovie(Movie movie) {
        return Single.fromCallable(() -> {
            mAppDatabase
                    .favouriteMovieDao().insertFavouriteMovie(movie);

            return true;
        });
    }


    @Override
    public Completable deleteFavourite(Movie movie) {
        return Completable.fromAction(() -> mAppDatabase.favouriteMovieDao().deleteFavourite(movie));
    }


    @Override
    public Flowable<List<Movie>> getFavourite() {
        return  Flowable.fromCallable(() -> mAppDatabase.favouriteMovieDao().getFavouriteMovies());

    }


}
