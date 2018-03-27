package program.java.punch.andr.myapplication.ui.favourite.interfaces;


import program.java.punch.andr.myapplication.data.model.Movie;
import program.java.punch.andr.myapplication.ui.base.interfaces.BaseMvpPresenter;
import program.java.punch.andr.myapplication.viewModel.MoviesViewModel;

public interface FavouriteMvpPresenter<V extends FavouriteMvpView, I extends FavouriteMvpInteractor>
        extends BaseMvpPresenter<V, I> {


    MoviesViewModel getViewModel();

    void deleteFavourite(Movie movie);

    void getFavourite();


}
