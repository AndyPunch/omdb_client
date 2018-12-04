package program.java.punch.andr.myapplication.data.network;


import io.reactivex.Observable;
import program.java.punch.andr.myapplication.model.Movies;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiHelper {
    @GET("/")
    Observable<Movies> getMovies(
            @Query("apikey") String apikey,
            @Query("type") String type,
            @Query("s") String title
    );

}
