package program.java.punch.andr.myapplication.ui.main.interfaces;


import program.java.punch.andr.myapplication.data.model.Movie;
import program.java.punch.andr.myapplication.ui.base.interfaces.BaseMvpPresenter;
import program.java.punch.andr.myapplication.viewModel.MoviesViewModel;

public interface MainMvpPresenter<V extends MainMvpView, I extends MainMvpInteractor>
        extends BaseMvpPresenter<V, I> {


    void getMovies(String title);

    MoviesViewModel getViewModel();

    void insertFavouriteMovie(Movie movie);



}
