package program.java.punch.andr.myapplication.ui.main;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import program.java.punch.andr.myapplication.data.model.Movie;
import program.java.punch.andr.myapplication.services.RetrofitService;
import program.java.punch.andr.myapplication.ui.base.BasePresenter;
import program.java.punch.andr.myapplication.ui.main.interfaces.MainMvpInteractor;
import program.java.punch.andr.myapplication.ui.main.interfaces.MainMvpPresenter;
import program.java.punch.andr.myapplication.ui.main.interfaces.MainMvpView;
import program.java.punch.andr.myapplication.viewModel.MoviesViewModel;

public class MainPresenter<V extends MainMvpView, I extends MainMvpInteractor>
        extends BasePresenter<V, I>
        implements MainMvpPresenter<V, I> {


    @Inject
    public MainPresenter(I mvpInteractor, CompositeDisposable compositeDisposable) {
        super(mvpInteractor, compositeDisposable);

    }

    @Inject
    protected RetrofitService retrofitService;

    @Inject
    public MoviesViewModel viewModel;

    @Override
    public MoviesViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void getMovies(String title) {
        getView().showProgress();
        getCompositeDisposable().add(retrofitService.getMovies(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            List<Movie> moviesList;
                            moviesList = response.getMovies();
                            getView().onMoviesLoaded(moviesList);
                            getView().hideProgress();
                        }, throwable -> getView().hideProgress()));
    }


    public void insertFavouriteMovie(Movie movie) {

        getCompositeDisposable().add(getInteractor().insertFavouriteMovieCall(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(throwable -> false).
                        subscribe(aBoolean -> {
                            getView().onMovieInserted(aBoolean);
                        }));


    }

}
