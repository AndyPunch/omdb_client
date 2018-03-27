package program.java.punch.andr.myapplication.ui.main;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import program.java.punch.andr.myapplication.data.model.Movie;
import program.java.punch.andr.myapplication.data.model.Movies;
import program.java.punch.andr.myapplication.services.RetrofitService;
import program.java.punch.andr.myapplication.ui.base.BasePresenter;
import program.java.punch.andr.myapplication.ui.main.interfaces.MainMvpInteractor;
import program.java.punch.andr.myapplication.ui.main.interfaces.MainMvpPresenter;
import program.java.punch.andr.myapplication.ui.main.interfaces.MainMvpView;
import program.java.punch.andr.myapplication.viewModel.MoviesViewModel;

public class MainPresenter<V extends MainMvpView, I extends MainMvpInteractor>
        extends BasePresenter<V, I>
        implements MainMvpPresenter<V, I>, Observer<Movies> {


    @Inject
    public MainPresenter(I mvpInteractor) {
        super(mvpInteractor);

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
        Observable<Movies> moviesObservable = retrofitService.getMovies(title);
        subscribe(moviesObservable, this);

    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull Movies response) {
        List<Movie> moviesList;
        moviesList = response.getMovies();
        getView().onMoviesLoaded(moviesList);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        getView().hideProgress();

    }

    @Override
    public void onComplete() {
        getView().hideProgress();
    }


    @Override
    public Single<Boolean> insertFavouriteMovie(Movie movie) {
        return getInteractor().insertFavouriteMovieCall(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(throwable -> false);
    }


}
