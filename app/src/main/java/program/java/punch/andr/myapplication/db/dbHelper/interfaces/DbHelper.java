package program.java.punch.andr.myapplication.db.dbHelper.interfaces;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import program.java.punch.andr.myapplication.data.model.Movie;


@Singleton
public interface DbHelper {
    Single<Boolean> insertFavouriteMovie(Movie movie);
    Completable deleteFavourite(Movie movie);
    Flowable<List<Movie>> getFavourite();
}