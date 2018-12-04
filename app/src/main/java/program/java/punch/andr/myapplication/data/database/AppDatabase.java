package program.java.punch.andr.myapplication.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import program.java.punch.andr.myapplication.data.database.dao.FavouriteMovieDao;
import program.java.punch.andr.myapplication.model.Movie;


@Database(entities = {Movie.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FavouriteMovieDao favouriteMovieDao();

}
