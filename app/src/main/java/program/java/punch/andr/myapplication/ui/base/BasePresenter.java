package program.java.punch.andr.myapplication.ui.base;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import program.java.punch.andr.myapplication.ui.base.interfaces.BaseMvpInteractor;
import program.java.punch.andr.myapplication.ui.base.interfaces.BaseMvpPresenter;
import program.java.punch.andr.myapplication.ui.base.interfaces.BaseMvpView;


public class BasePresenter<V extends BaseMvpView, I extends BaseMvpInteractor>
        implements BaseMvpPresenter<V, I> {


    private V mView;

    private I mMvpInteractor;

    @Inject
    public BasePresenter(I mvpInteractor) {
        mMvpInteractor = mvpInteractor;

    }

    @Override
    public void onAttach(V mvpView) {
        mView = mvpView;
    }

    @Override
    public void onDetach() {

        mView = null;
        mMvpInteractor = null;
    }


    protected <T> void subscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public I getInteractor() {
        return mMvpInteractor;
    }
}
