

package program.java.punch.andr.myapplication;

import android.content.Context;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import program.java.punch.andr.myapplication.di.component.DaggerTestComponent;
import program.java.punch.andr.myapplication.di.component.TestComponent;
import program.java.punch.andr.myapplication.di.module.ApplicationTestModule;


public class TestComponentRule implements TestRule {

    private TestComponent mTestComponent;
    private Context mContext;

    public TestComponentRule(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    private void setupDaggerTestComponentInApplication() {
        ApplicationMVP application = ((ApplicationMVP) mContext.getApplicationContext());
        mTestComponent = DaggerTestComponent.builder()
                .applicationTestModule(new ApplicationTestModule(application))
                .build();
        application.setApplicationComponent(mTestComponent);
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    setupDaggerTestComponentInApplication();
                    base.evaluate();
                } finally {
                    mTestComponent = null;
                }
            }
        };
    }
}
