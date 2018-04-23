package program.java.punch.andr.myapplication.ui.main.interfaces;

import java.util.List;

import program.java.punch.andr.myapplication.data.model.Movie;
import program.java.punch.andr.myapplication.ui.base.interfaces.BaseMvpView;


public interface MainMvpView extends BaseMvpView {


    void onMoviesLoaded(List<Movie> moviesList);
    void onMovieInserted(boolean aBoolean);
}
