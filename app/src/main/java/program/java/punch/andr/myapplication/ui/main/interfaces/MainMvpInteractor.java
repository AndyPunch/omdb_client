package program.java.punch.andr.myapplication.ui.main.interfaces;


import java.util.List;

import io.reactivex.Single;
import program.java.punch.andr.myapplication.data.model.Movie;
import program.java.punch.andr.myapplication.ui.base.interfaces.BaseMvpInteractor;


public interface MainMvpInteractor extends BaseMvpInteractor {
    Single<Boolean> insertFavouriteMovieCall(Movie movie);


}
