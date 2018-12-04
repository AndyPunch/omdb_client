package program.java.punch.andr.myapplication.ui.favourite;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import program.java.punch.andr.myapplication.model.Movie;
import program.java.punch.andr.myapplication.ui.base.BasePresenter;
import program.java.punch.andr.myapplication.ui.favourite.interfaces.FavouriteMvpInteractor;
import program.java.punch.andr.myapplication.ui.favourite.interfaces.FavouriteMvpPresenter;
import program.java.punch.andr.myapplication.ui.favourite.interfaces.FavouriteMvpView;

public class FavouritePresenter<V extends FavouriteMvpView, I extends FavouriteMvpInteractor>
        extends BasePresenter<V, I>
        implements FavouriteMvpPresenter<V, I> {


    @Inject
    public FavouritePresenter(I mvpInteractor, CompositeDisposable compositeDisposable) {
        super(mvpInteractor, compositeDisposable);

    }


    @Override
    public void deleteFavourite(Movie movie) {
        getCompositeDisposable().add(getInteractor().deleteFavouriteCall(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    getView().OnFavouriteDeleted();
                }));
    }

    @Override
    public void getFavourite() {
        getView().showProgress();
        getCompositeDisposable().add(getInteractor().getFavouriteCall()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        movies -> {
                            getView().onMoviesLoaded(movies);
                            getView().hideProgress();
                        },

                        throwable -> {

                            getView().hideProgress();
                        })
        );
    }


}
