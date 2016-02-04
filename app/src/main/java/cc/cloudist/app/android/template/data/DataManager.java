package cc.cloudist.app.android.template.data;

import cc.cloudist.app.android.template.data.model.News;
import cc.cloudist.app.android.template.data.remote.RemoteService;
import rx.Observable;

public class DataManager {

    private RemoteService mRemoteService;

    private static class DataManagerHolder {
        private static final DataManager INSTANCE = new DataManager();
    }

    private DataManager() {
        mRemoteService = RemoteService.Factory.makeRemoteService();
    }

    public static final DataManager getInstance() {
        return DataManagerHolder.INSTANCE;
    }

    public Observable<News> getNews() {
        return mRemoteService.getNews();
    }
}
