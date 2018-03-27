package program.java.punch.andr.myapplication.ui.favourite;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import program.java.punch.andr.myapplication.data.model.Movie;
import program.java.punch.andr.myapplication.ui.base.BasePresenter;
import program.java.punch.andr.myapplication.ui.favourite.interfaces.FavouriteMvpInteractor;
import program.java.punch.andr.myapplication.ui.favourite.interfaces.FavouriteMvpPresenter;
import program.java.punch.andr.myapplication.ui.favourite.interfaces.FavouriteMvpView;
import program.java.punch.andr.myapplication.viewModel.MoviesViewModel;

public class FavouritePresenter<V extends FavouriteMvpView, I extends FavouriteMvpInteractor>
        extends BasePresenter<V, I>
        implements FavouriteMvpPresenter<V, I> {


    @Inject
    public FavouritePresenter(I mvpInteractor) {
        super(mvpInteractor);

    }


    @Inject
    public MoviesViewModel viewModel;

    @Override
    public MoviesViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void deleteFavourite(Movie movie) {
        getInteractor().deleteFavouriteCall(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    getView().OnFavouriteDeleted();
                });
    }

    @Override
    public void getFavourite() {
        getView().showProgress();
        getInteractor().getFavouriteCall()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> getView().hideProgress())
                .doOnComplete(() -> getView().hideProgress())
                .subscribe(movies -> FavouritePresenter.this.getView().onMoviesLoaded(movies));
    }


}
