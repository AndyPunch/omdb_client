package program.java.punch.andr.myapplication.ui.main;

import javax.inject.Inject;

import io.reactivex.Single;
import program.java.punch.andr.myapplication.data.model.Movie;
import program.java.punch.andr.myapplication.db.dbHelper.interfaces.DbHelper;
import program.java.punch.andr.myapplication.ui.base.BaseInteractor;
import program.java.punch.andr.myapplication.ui.main.interfaces.MainMvpInteractor;


public class MainInteractor extends BaseInteractor implements MainMvpInteractor {
    private DbHelper appDbHelper;

    @Inject
    public MainInteractor(DbHelper dbHelper) {
        super(dbHelper);
        this.appDbHelper = dbHelper;
    }


    @Override
    public DbHelper getAppDbHelper() {
        return appDbHelper;
    }

    @Override
    public Single<Boolean> insertFavouriteMovieCall(Movie movie) {
        return appDbHelper.insertFavouriteMovie(movie);
    }


}
