package program.java.punch.andr.myapplication.ui.favourite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import program.java.punch.andr.myapplication.R;
import program.java.punch.andr.myapplication.adapters.FavouriteAdapter;
import program.java.punch.andr.myapplication.data.model.Movie;
import program.java.punch.andr.myapplication.ui.base.BaseActivity;
import program.java.punch.andr.myapplication.ui.favourite.interfaces.FavouriteMvpInteractor;
import program.java.punch.andr.myapplication.ui.favourite.interfaces.FavouriteMvpPresenter;
import program.java.punch.andr.myapplication.ui.favourite.interfaces.FavouriteMvpView;
import program.java.punch.andr.myapplication.ui.favourite.interfaces.OnDeleteFavouriteClick;
import program.java.punch.andr.myapplication.utils.RecyclerViewEmptySupport;


public class FavouriteActivity extends BaseActivity implements FavouriteMvpView,
        OnDeleteFavouriteClick {
    @BindView(R.id.recyclerview_favourite)
    protected RecyclerViewEmptySupport moviesRecycler;

    @BindView(R.id.list_empty_favourite)
    protected TextView emptyTv;


    FavouriteAdapter favouriteAdapter;

    @Inject
    public FavouriteMvpPresenter<FavouriteMvpView, FavouriteMvpInteractor> mPresenter;


    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        getActivityComponent().inject(this);

        mPresenter.onAttach(this);

        ButterKnife.bind(this);
        setUp();


    }


    @Override
    protected int getContentView() {
        return R.layout.activity_favourite;
    }

    @Override
    protected void setUp() {

        setTitle(getString(R.string.favourite_movies));
        setAdapter();

    }


    public void setAdapter() {
        favouriteAdapter = new FavouriteAdapter(getApplication(), this);
        moviesRecycler.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        moviesRecycler.setEmptyView(emptyTv);
        moviesRecycler.setAdapter(favouriteAdapter);
        if (mPresenter.getViewModel().getMovieViewModelList().size() > 0) {
            favouriteAdapter.addMoviesToAdapter(mPresenter.getViewModel().getMovieViewModelList());
        } else {
            getFavourite();
        }
    }

    public void getFavourite() {
        mPresenter.getFavourite();
    }

    @Override
    public void onMoviesLoaded(List<Movie> moviesList) {
        mPresenter.getViewModel().setMovieViewModelList(moviesList);
        favouriteAdapter.addMoviesToAdapter(moviesList);

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }


    @Override
    public void OnDeleteFavouriteMovieClick(Movie movie) {
        mPresenter.deleteFavourite(movie);
    }


    @Override
    public void OnFavouriteDeleted() {
        Toast.makeText(this, R.string.favourite_deleted, Toast.LENGTH_LONG).show();
        getFavourite();
    }


}


