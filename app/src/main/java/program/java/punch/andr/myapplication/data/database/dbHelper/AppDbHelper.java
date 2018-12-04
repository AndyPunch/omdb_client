package program.java.punch.andr.myapplication.data.database.dbHelper;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import program.java.punch.andr.myapplication.data.database.AppDatabase;
import program.java.punch.andr.myapplication.data.database.dbHelper.interfaces.DbHelper;
import program.java.punch.andr.myapplication.model.Movie;

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
        return Completable.fromAction(() -> mAppDatabase.favouriteMovieDao().deleteFavourite
                (movie));
    }


    @Override
    public Observable<List<Movie>> getFavourite() {
        return Observable.fromCallable(() -> mAppDatabase.favouriteMovieDao().getFavouriteMovies());

    }


}
