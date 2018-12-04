package program.java.punch.andr.myapplication.data.database.dbHelper.interfaces;


import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import program.java.punch.andr.myapplication.model.Movie;


@Singleton
public interface DbHelper {
    Single<Boolean> insertFavouriteMovie(Movie movie);

    Completable deleteFavourite(Movie movie);

    Observable<List<Movie>> getFavourite();
}
