package program.java.punch.andr.myapplication.ui.main.interfaces;


import io.reactivex.Single;
import program.java.punch.andr.myapplication.data.model.Movie;
import program.java.punch.andr.myapplication.ui.base.interfaces.BaseMvpPresenter;
import program.java.punch.andr.myapplication.viewModel.MoviesViewModel;

public interface MainMvpPresenter<V extends MainMvpView, I extends MainMvpInteractor>
        extends BaseMvpPresenter<V, I> {


    void getMovies(String title);

    MoviesViewModel getViewModel();

    Single<Boolean> insertFavouriteMovie(Movie movie);


}
