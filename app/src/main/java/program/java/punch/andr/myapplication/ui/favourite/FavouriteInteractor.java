package program.java.punch.andr.myapplication.ui.favourite;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import program.java.punch.andr.myapplication.data.model.Movie;
import program.java.punch.andr.myapplication.db.dbHelper.interfaces.DbHelper;
import program.java.punch.andr.myapplication.ui.base.BaseInteractor;
import program.java.punch.andr.myapplication.ui.favourite.interfaces.FavouriteMvpInteractor;


public class FavouriteInteractor extends BaseInteractor implements FavouriteMvpInteractor {
    private DbHelper appDbHelper;


    @Inject
    public FavouriteInteractor(DbHelper dbHelper) {
        super(dbHelper);
        this.appDbHelper = dbHelper;


    }


    @Override
    public DbHelper getAppDbHelper() {
        return appDbHelper;
    }


    @Override
    public Completable deleteFavouriteCall(Movie movie) {
        return appDbHelper.deleteFavourite(movie);
    }


    @Override
    public Flowable<List<Movie>> getFavouriteCall() {
        return appDbHelper.getFavourite();
    }


}