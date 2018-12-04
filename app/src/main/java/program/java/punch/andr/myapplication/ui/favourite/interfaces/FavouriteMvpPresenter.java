package program.java.punch.andr.myapplication.ui.favourite.interfaces;


import program.java.punch.andr.myapplication.model.Movie;
import program.java.punch.andr.myapplication.ui.base.interfaces.BaseMvpPresenter;

public interface FavouriteMvpPresenter<V extends FavouriteMvpView, I extends FavouriteMvpInteractor>
        extends BaseMvpPresenter<V, I> {

    void deleteFavourite(Movie movie);

    void getFavourite();


}
