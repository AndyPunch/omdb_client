

package program.java.punch.andr.myapplication.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import program.java.punch.andr.myapplication.data.database.AppDatabase;
import program.java.punch.andr.myapplication.data.database.dbHelper.AppDbHelper;
import program.java.punch.andr.myapplication.data.database.dbHelper.interfaces.DbHelper;
import program.java.punch.andr.myapplication.di.scope.DatabaseInfo;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.mockito.Mockito.mock;


@Module
public class ApplicationTestModule {

    private final Context mContext;
    private  final String BASE_URL = "http://www.omdbapi.com";
    private final String DB_NAME = "movieDb";

    public ApplicationTestModule(Context context) {
        mContext = context;

    }

    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }


    @Singleton
    @Provides
    Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory,
                             RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .build();
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return DB_NAME;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName) {
        return Room.databaseBuilder(mContext, AppDatabase.class, dbName)
                .build();
    }


    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
       return mock(DbHelper.class);
    }


}
