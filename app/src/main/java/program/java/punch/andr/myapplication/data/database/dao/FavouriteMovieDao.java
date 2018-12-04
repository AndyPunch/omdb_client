package program.java.punch.andr.myapplication.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import program.java.punch.andr.myapplication.model.Movie;


@Dao
public interface FavouriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insertFavouriteMovie(Movie movie);

    @Query("SELECT * FROM favourite_movies")
    List<Movie> getFavouriteMovies();

    @Delete
    void deleteFavourite(Movie movie);
}
