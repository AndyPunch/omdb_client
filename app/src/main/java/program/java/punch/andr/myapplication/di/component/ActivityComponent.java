package program.java.punch.andr.myapplication.di.component;


import dagger.Component;
import program.java.punch.andr.myapplication.di.module.ActivityModule;
import program.java.punch.andr.myapplication.di.scope.PerActivity;
import program.java.punch.andr.myapplication.ui.favourite.FavouriteActivity;
import program.java.punch.andr.myapplication.ui.main.MainActivity;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

        void inject(MainActivity activity);
        void inject(FavouriteActivity activity);



}
