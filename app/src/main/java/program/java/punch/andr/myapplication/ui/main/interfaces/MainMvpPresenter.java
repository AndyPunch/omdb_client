package program.java.punch.andr.myapplication.ui.main.interfaces;


import program.java.punch.andr.myapplication.model.Movie;
import program.java.punch.andr.myapplication.ui.base.interfaces.BaseMvpPresenter;

public interface MainMvpPresenter<V extends MainMvpView, I extends MainMvpInteractor>
        extends BaseMvpPresenter<V, I> {


    void getMovies(String title);

    void insertFavouriteMovie(Movie movie);


}
