package program.java.punch.andr.myapplication.ui.base.interfaces;


import android.app.Activity;

public interface BaseMvpView {

    void showProgress();

    void hideProgress();

    boolean isNetworkConnected();

    void hideSoftKeyboard(Activity activity);


}
