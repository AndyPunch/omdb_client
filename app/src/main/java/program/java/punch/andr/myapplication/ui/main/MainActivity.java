package program.java.punch.andr.myapplication.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import program.java.punch.andr.myapplication.R;
import program.java.punch.andr.myapplication.adapters.MoviesAdapter;
import program.java.punch.andr.myapplication.model.Movie;
import program.java.punch.andr.myapplication.ui.base.BaseActivity;
import program.java.punch.andr.myapplication.ui.favourite.FavouriteActivity;
import program.java.punch.andr.myapplication.ui.main.interfaces.MainMvpInteractor;
import program.java.punch.andr.myapplication.ui.main.interfaces.MainMvpPresenter;
import program.java.punch.andr.myapplication.ui.main.interfaces.MainMvpView;
import program.java.punch.andr.myapplication.ui.main.interfaces.OnAddFavouriteClick;
import program.java.punch.andr.myapplication.viewModel.MoviesViewModel;


public class MainActivity extends BaseActivity implements MainMvpView, OnAddFavouriteClick {
    @BindView(R.id.recyclerview)
    protected RecyclerView moviesRecycler;

    @BindView(R.id.search_edittext)
    protected EditText searchEditText;


    @BindView(R.id.list_empty)
    protected TextView emptyTv;


    private MoviesAdapter moviesAdapter;

    @Inject
    public MainMvpPresenter<MainMvpView, MainMvpInteractor> mPresenter;

    public MoviesViewModel viewModel;


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
        return R.layout.activity_main;
    }

    @Override
    protected void setUp() {
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        setTitle(R.string.app_name_public);
        setAdapter();
    }


    public void setAdapter() {
        moviesAdapter = new MoviesAdapter(this);
        moviesRecycler.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        moviesRecycler.setAdapter(moviesAdapter);
        setListFomViewModel();

    }

    @Override
    public void onMoviesLoaded(List<Movie> moviesList) {
        if (moviesList != null) {
            viewModel.setMovieViewModelList(moviesList);
            moviesAdapter.addMoviesToAdapter(moviesList);
        } else {
            moviesAdapter.clearMovies();
            emptyTv.setText(R.string.no_results);
        }
    }


    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        setListFomViewModel();
    }

    void setListFomViewModel() {
        if (!viewModel.getMovieViewModelList().isEmpty()) {
            if (moviesAdapter != null) {
                moviesAdapter.addMoviesToAdapter(viewModel.getMovieViewModelList());
            }

        }
    }

    @OnClick(R.id.search_button)
    public void startSearch() {
        if (isNetworkConnected()) {
            hideSoftKeyboard(this);
            String movieTitle = searchEditText.getText().toString().trim();
            if (!TextUtils.isEmpty(movieTitle)) {
                viewModel.getMovieViewModelList().clear();
                mPresenter.getMovies(movieTitle);
            } else
                Toast.makeText(this, R.string.title_is_empty, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, R.string.network_not_aviable, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void OnAddFavouriteMovieClick(Movie movie) {
        mPresenter.insertFavouriteMovie(movie);

    }


    @Override
    public void onMovieInserted(boolean aBoolean) {
        if (aBoolean) {
            Toast.makeText(getApplication(), R.string.movie_is_added, Toast.LENGTH_LONG)
                    .show();
        } else {
            Toast.makeText(getApplication(), R.string.already_added, Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favourite, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favourie) {

            Intent favouriteIntent = new Intent(this, FavouriteActivity.class);
            startActivity(favouriteIntent);

        }

        return super.onOptionsItemSelected(item);
    }
}


