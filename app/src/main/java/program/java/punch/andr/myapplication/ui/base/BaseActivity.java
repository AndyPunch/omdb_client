package program.java.punch.andr.myapplication.ui.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import program.java.punch.andr.myapplication.ApplicationMVP;
import program.java.punch.andr.myapplication.R;
import program.java.punch.andr.myapplication.di.component.ActivityComponent;
import program.java.punch.andr.myapplication.di.component.ApplicationComponent;
import program.java.punch.andr.myapplication.di.component.DaggerActivityComponent;
import program.java.punch.andr.myapplication.di.module.ActivityModule;
import program.java.punch.andr.myapplication.ui.base.interfaces.BaseMvpView;
import program.java.punch.andr.myapplication.utils.GeneralUtils;
import program.java.punch.andr.myapplication.utils.NetworkUtils;


public abstract class BaseActivity extends AppCompatActivity implements BaseMvpView {
    private ProgressDialog mProgressDialog;

    private ActivityComponent mActivityComponent;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        resolveDaggerDependency();
        ButterKnife.bind(this);
        baseSetUp();
        onViewReady(savedInstanceState, getIntent());

    }

    private void baseSetUp() {
        setSupportActionBar(mToolbar);
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {

    }

    protected void resolveDaggerDependency() {
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(getApplicationComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((ApplicationMVP) getApplication()).getApplicationComponent();
    }


    @Override
    public void showProgress() {
        hideProgress();
        mProgressDialog = GeneralUtils.showLoadingDialog(this);
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }


    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }


    @Override
    public void hideSoftKeyboard(Activity activity) {
        GeneralUtils.hideSoftKeyboard(activity);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected abstract int getContentView();

    protected abstract void setUp();
}
