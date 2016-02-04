package cc.cloudist.app.android.template.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cc.cloudist.app.android.template.BuildConfig;
import cc.cloudist.app.android.template.data.model.News;
import cc.cloudist.app.android.template.util.LogUtils;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import rx.Observable;


public interface RemoteService {

    String TAG = LogUtils.makeLogTag(RemoteService.class);

    String ENDPOINT = "http://news-at.zhihu.com/api/4/";

    @GET("news/latest")
    Observable<News> getNews();

    class Factory {

        public static RemoteService makeRemoteService() {

            HttpLoggingInterceptor logging =
                    new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            LogUtils.d(TAG, message);
                        }
                    });
            logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                    : HttpLoggingInterceptor.Level.NONE);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new SpecialInterceptor())
                    .addInterceptor(logging)
                    .build();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RemoteService.ENDPOINT)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(RemoteService.class);
        }
    }
}
