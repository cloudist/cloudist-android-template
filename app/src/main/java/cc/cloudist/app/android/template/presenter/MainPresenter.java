package cc.cloudist.app.android.template.presenter;

import android.os.Bundle;

import cc.cloudist.app.android.template.data.DataManager;
import cc.cloudist.app.android.template.data.model.News;
import cc.cloudist.app.android.template.util.LogUtils;
import cc.cloudist.app.android.template.view.activity.MainActivity;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

public class MainPresenter extends BaseRxPresenter<MainActivity> {

    private static final String TAG = LogUtils.makeLogTag(MainPresenter.class);

    private static final int REQUEST_ITEMS = 1;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        restartableLatestCache(REQUEST_ITEMS,
                new Func0<Observable<News>>() {
                    @Override
                    public Observable<News> call() {
                        return DataManager.getInstance().getNews()
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                },
                new Action2<MainActivity, News>() {
                    @Override
                    public void call(MainActivity activity, News news) {
                        activity.setData(news.getStories());
                    }
                },
                new Action2<MainActivity, Throwable>() {
                    @Override
                    public void call(MainActivity activity, Throwable throwable) {
                        activity.onNetworkError(throwable);
                    }
                });

        if (savedState == null) start(REQUEST_ITEMS);
    }

    public void request() {
        start(REQUEST_ITEMS);
    }
}
