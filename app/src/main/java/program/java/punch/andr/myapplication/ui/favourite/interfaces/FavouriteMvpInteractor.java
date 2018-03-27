package program.java.punch.andr.myapplication.ui.favourite.interfaces;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import program.java.punch.andr.myapplication.data.model.Movie;
import program.java.punch.andr.myapplication.ui.base.interfaces.BaseMvpInteractor;


public interface FavouriteMvpInteractor extends BaseMvpInteractor {
    Completable deleteFavouriteCall(Movie movie);

    Flowable<List<Movie>> getFavouriteCall();

}
