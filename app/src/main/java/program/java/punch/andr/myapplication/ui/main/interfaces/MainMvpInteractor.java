package program.java.punch.andr.myapplication.ui.main.interfaces;


import io.reactivex.Observable;
import io.reactivex.Single;
import program.java.punch.andr.myapplication.model.Movie;
import program.java.punch.andr.myapplication.model.Movies;
import program.java.punch.andr.myapplication.ui.base.interfaces.BaseMvpInteractor;


public interface MainMvpInteractor extends BaseMvpInteractor {
    Single<Boolean> insertFavouriteMovieCall(Movie movie);

    Observable<Movies> getMoviesCall(String apyKey, String type, String title);


}
