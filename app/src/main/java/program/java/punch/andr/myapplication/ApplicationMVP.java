package program.java.punch.andr.myapplication;

import android.app.Application;

import program.java.punch.andr.myapplication.di.component.ApplicationComponent;
import program.java.punch.andr.myapplication.di.component.DaggerApplicationComponent;
import program.java.punch.andr.myapplication.di.module.ApplicationModule;


public class ApplicationMVP extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    // for testing
    public void setApplicationComponent(ApplicationComponent appComponent) {
        applicationComponent = appComponent;
    }
}
