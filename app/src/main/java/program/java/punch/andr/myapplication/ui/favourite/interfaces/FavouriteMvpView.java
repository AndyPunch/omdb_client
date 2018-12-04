package program.java.punch.andr.myapplication.ui.favourite.interfaces;

import java.util.List;

import program.java.punch.andr.myapplication.model.Movie;
import program.java.punch.andr.myapplication.ui.base.interfaces.BaseMvpView;


public interface FavouriteMvpView extends BaseMvpView {

    void onMoviesLoaded(List<Movie> moviesList);

    void OnFavouriteDeleted();
}
