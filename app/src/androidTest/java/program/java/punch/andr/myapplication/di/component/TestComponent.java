package program.java.punch.andr.myapplication.di.component;


import javax.inject.Singleton;

import dagger.Component;
import program.java.punch.andr.myapplication.di.module.ApplicationTestModule;
import program.java.punch.andr.myapplication.ui.main.MainActivityTest;
import retrofit2.Retrofit;


@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
