package program.java.punch.andr.myapplication.ui.base.interfaces;


public interface BaseMvpPresenter<V extends BaseMvpView, I extends BaseMvpInteractor> {
    V getView();

    void onAttach(V mvpView);

    void onDetach();

    I getInteractor();
}
