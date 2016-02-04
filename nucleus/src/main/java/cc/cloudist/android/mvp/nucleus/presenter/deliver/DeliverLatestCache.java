package cc.cloudist.android.mvp.nucleus.presenter.deliver;

import rx.Notification;
import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

public class DeliverLatestCache<V, T> implements Observable.Transformer<T, Delivery<V, T>> {

    private final Observable<V> view;

    public DeliverLatestCache(Observable<V> view) {
        this.view = view;
    }

    @Override
    public Observable<Delivery<V, T>> call(Observable<T> observable) {
        return Observable
                .combineLatest(
                        view,
                        observable
                                .materialize()
                                .filter(new Func1<Notification<T>, Boolean>() {
                                    @Override
                                    public Boolean call(Notification<T> notification) {
                                        return !notification.isOnCompleted();
                                    }
                                }),
                        new Func2<V, Notification<T>, Delivery<V, T>>() {
                            @Override
                            public Delivery<V, T> call(V view, Notification<T> notification) {
                                return view == null ? null : new Delivery<>(view, notification);
                            }
                        })
                .filter(new Func1<Delivery<V, T>, Boolean>() {
                    @Override
                    public Boolean call(Delivery<V, T> delivery) {
                        return delivery != null;
                    }
                });
    }
}
