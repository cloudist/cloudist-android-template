package cc.cloudist.android.mvp.nucleus.presenter.deliver;

import rx.Notification;
import rx.Observable;
import rx.functions.Func1;

public class DeliverFirst<V, T> implements Observable.Transformer<T, Delivery<V, T>> {

    private final Observable<V> view;

    public DeliverFirst(Observable<V> view) {
        this.view = view;
    }

    @Override
    public Observable<Delivery<V, T>> call(Observable<T> observable) {
        return observable.materialize()
                .take(1)
                .switchMap(new Func1<Notification<T>, Observable<? extends Delivery<V, T>>>() {
                    @Override
                    public Observable<? extends Delivery<V, T>> call(final Notification<T> notification) {
                        return view.map(new Func1<V, Delivery<V, T>>() {
                            @Override
                            public Delivery<V, T> call(V view) {
                                return view == null ? null : new Delivery<>(view, notification);
                            }
                        });
                    }
                })
                .filter(new Func1<Delivery<V, T>, Boolean>() {
                    @Override
                    public Boolean call(Delivery<V, T> delivery) {
                        return delivery != null;
                    }
                })
                .take(1);
    }
}
