package cc.cloudist.android.mvp.nucleus.presenter;

public interface PresenterFactory<P extends Presenter> {
    P createPresenter();
}
