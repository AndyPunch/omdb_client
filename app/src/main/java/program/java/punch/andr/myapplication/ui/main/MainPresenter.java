package program.java.punch.andr.myapplication.ui.main;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import program.java.punch.andr.myapplication.model.Movie;
import program.java.punch.andr.myapplication.ui.base.BasePresenter;
import program.java.punch.andr.myapplication.ui.main.interfaces.MainMvpInteractor;
import program.java.punch.andr.myapplication.ui.main.interfaces.MainMvpPresenter;
import program.java.punch.andr.myapplication.ui.main.interfaces.MainMvpView;

import static program.java.punch.andr.myapplication.utils.AppConstants.API_KEY;
import static program.java.punch.andr.myapplication.utils.AppConstants.TYPE;

public class MainPresenter<V extends MainMvpView, I extends MainMvpInteractor>
        extends BasePresenter<V, I>
        implements MainMvpPresenter<V, I> {


    @Inject
    public MainPresenter(I mvpInteractor, CompositeDisposable compositeDisposable) {
        super(mvpInteractor, compositeDisposable);

    }

    @Override
    public void getMovies(String title) {
        getView().showProgress();
        getCompositeDisposable().add(getInteractor().getMoviesCall(API_KEY, TYPE, title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            getView().onMoviesLoaded(response.getMovies());
                            getView().hideProgress();
                        }, throwable -> {
                            getView().hideProgress();
                        }));
    }

    @Override
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
