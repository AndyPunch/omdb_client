package program.java.punch.andr.myapplication.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import program.java.punch.andr.myapplication.data.model.Movie;
import program.java.punch.andr.myapplication.db.dao.FavouriteMovieDao;


@Database(entities = {Movie.class}, version = 1)
public abstract class  AppDatabase extends RoomDatabase{

    public abstract FavouriteMovieDao favouriteMovieDao();

}
