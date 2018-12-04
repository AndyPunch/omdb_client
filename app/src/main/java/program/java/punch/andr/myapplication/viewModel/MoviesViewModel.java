package program.java.punch.andr.myapplication.viewModel;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import program.java.punch.andr.myapplication.model.Movie;


public class MoviesViewModel extends ViewModel {
    private List<Movie> movieViewModelList = new ArrayList<>();

    public List<Movie> getMovieViewModelList() {
        return movieViewModelList;
    }

    public void setMovieViewModelList(List<Movie> movieViewModelList) {
        this.movieViewModelList = movieViewModelList;
    }
}
