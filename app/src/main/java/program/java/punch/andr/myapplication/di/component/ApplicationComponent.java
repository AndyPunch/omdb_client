package program.java.punch.andr.myapplication.di.component;

import javax.inject.Singleton;

import dagger.Component;
import program.java.punch.andr.myapplication.data.database.dbHelper.interfaces.DbHelper;
import program.java.punch.andr.myapplication.di.module.ApplicationModule;
import retrofit2.Retrofit;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Retrofit getRetrofit();

    DbHelper dbHelper();


}
