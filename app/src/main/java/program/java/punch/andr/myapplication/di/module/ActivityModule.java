package program.java.punch.andr.myapplication.di.module;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import program.java.punch.andr.myapplication.di.scope.PerActivity;
import program.java.punch.andr.myapplication.services.RetrofitService;
import program.java.punch.andr.myapplication.ui.favourite.FavouriteInteractor;
import program.java.punch.andr.myapplication.ui.favourite.FavouritePresenter;
import program.java.punch.andr.myapplication.ui.favourite.interfaces.FavouriteMvpInteractor;
import program.java.punch.andr.myapplication.ui.favourite.interfaces.FavouriteMvpPresenter;
import program.java.punch.andr.myapplication.ui.favourite.interfaces.FavouriteMvpView;
import program.java.punch.andr.myapplication.ui.main.MainInteractor;
import program.java.punch.andr.myapplication.ui.main.MainPresenter;
import program.java.punch.andr.myapplication.ui.main.interfaces.MainMvpInteractor;
import program.java.punch.andr.myapplication.ui.main.interfaces.MainMvpPresenter;
import program.java.punch.andr.myapplication.ui.main.interfaces.MainMvpView;
import program.java.punch.andr.myapplication.viewModel.MoviesViewModel;
import retrofit2.Retrofit;


@Module
public class ActivityModule {


    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }


    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }


    @PerActivity
    @Provides
    RetrofitService provideApiService(Retrofit retrofit) {
        return retrofit.create(RetrofitService.class);
    }

    @Provides
    @PerActivity
    public MoviesViewModel provideViewModel() {
        MoviesViewModel model = ViewModelProviders.of(mActivity).get(MoviesViewModel.class);
        return model;
    }


    //PRESENTERS

    @PerActivity
    @Provides
    MainMvpPresenter<MainMvpView, MainMvpInteractor>
    provideMainPresenter(MainPresenter<MainMvpView, MainMvpInteractor> presenter) {
        return presenter;
    }

    @PerActivity
    @Provides
    FavouriteMvpPresenter<FavouriteMvpView, FavouriteMvpInteractor>
    provideFavouritePresenter(FavouritePresenter<FavouriteMvpView, FavouriteMvpInteractor>
                                      presenter) {
        return presenter;
    }


    //INTERACTORS
    @Provides
    @PerActivity
    MainMvpInteractor provideMainMvpInteractor(MainInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    FavouriteMvpInteractor provideFavouriteMvpInteractor(FavouriteInteractor interactor) {
        return interactor;
    }


}
