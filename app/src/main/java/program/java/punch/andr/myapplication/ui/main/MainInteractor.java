package program.java.punch.andr.myapplication.ui.main;


import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import program.java.punch.andr.myapplication.data.database.dbHelper.interfaces.DbHelper;
import program.java.punch.andr.myapplication.data.network.ApiHelper;
import program.java.punch.andr.myapplication.model.Movie;
import program.java.punch.andr.myapplication.model.Movies;
import program.java.punch.andr.myapplication.ui.base.BaseInteractor;
import program.java.punch.andr.myapplication.ui.main.interfaces.MainMvpInteractor;


public class MainInteractor extends BaseInteractor implements MainMvpInteractor {
    private DbHelper appDbHelper;
    private ApiHelper appApiHelper;

    @Inject
    public MainInteractor(DbHelper dbHelper, ApiHelper apiHelper) {
        super(dbHelper);
        appDbHelper = dbHelper;
        appApiHelper = apiHelper;
    }


    @Override
    public DbHelper getAppDbHelper() {
        return appDbHelper;
    }

    @Override
    public Single<Boolean> insertFavouriteMovieCall(Movie movie) {
        return appDbHelper.insertFavouriteMovie(movie);
    }

    public Observable<Movies> getMoviesCall(String apyKey, String type, String title) {
        return appApiHelper.getMovies(apyKey, type, title);
    }
}
