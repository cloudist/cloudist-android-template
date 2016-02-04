package cc.cloudist.android.mvp.nucleus.presenter.deliver;

import rx.Notification;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.subjects.ReplaySubject;

public class DeliverReplay<V, T> implements Observable.Transformer<T, Delivery<V, T>> {

    private final Observable<V> view;

    public DeliverReplay(Observable<V> view) {
        this.view = view;
    }

    @Override
    public Observable<Delivery<V, T>> call(Observable<T> observable) {
        final ReplaySubject<Notification<T>> subject = ReplaySubject.create();
        final Subscription subscription = observable
                .materialize()
                .filter(new Func1<Notification<T>, Boolean>() {
                    @Override
                    public Boolean call(Notification<T> notification) {
                        return !notification.isOnCompleted();
                    }
                })
                .subscribe(subject);
        return view
                .switchMap(new Func1<V, Observable<Delivery<V, T>>>() {
                    @Override
                    public Observable<Delivery<V, T>> call(final V view) {
                        return view == null ? Observable.<Delivery<V, T>>never() : subject
                                .map(new Func1<Notification<T>, Delivery<V, T>>() {
                                    @Override
                                    public Delivery<V, T> call(Notification<T> notification) {
                                        return new Delivery<>(view, notification);
                                    }
                                });
                    }
                })
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        subscription.unsubscribe();
                    }
                });
    }
}
