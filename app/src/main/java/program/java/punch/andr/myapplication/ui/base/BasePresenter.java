package program.java.punch.andr.myapplication.ui.base;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import program.java.punch.andr.myapplication.ui.base.interfaces.BaseMvpInteractor;
import program.java.punch.andr.myapplication.ui.base.interfaces.BaseMvpPresenter;
import program.java.punch.andr.myapplication.ui.base.interfaces.BaseMvpView;


public class BasePresenter<V extends BaseMvpView, I extends BaseMvpInteractor>
        implements BaseMvpPresenter<V, I> {


    private V mView;

    private I mMvpInteractor;

    private final CompositeDisposable mCompositeDisposable;

    @Inject
    public BasePresenter(I mvpInteractor, CompositeDisposable compositeDisposable) {
        mMvpInteractor = mvpInteractor;
        mCompositeDisposable = compositeDisposable;

    }

    @Override
    public void onAttach(V mvpView) {
        mView = mvpView;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mView = null;
        mMvpInteractor = null;
    }


    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
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
