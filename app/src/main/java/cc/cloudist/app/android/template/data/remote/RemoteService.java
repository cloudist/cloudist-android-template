package cc.cloudist.app.android.template.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cc.cloudist.app.android.template.data.model.News;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import rx.Observable;


public interface RemoteService {

    String ENDPOINT = "http://news-at.zhihu.com/api/4/";

    @GET("news/latest")
    Observable<News> getNews();

    class Factory {

        public static RemoteService makeRemoteService() {
//            OkHttpClient okHttpClient = new OkHttpClient();
//            okHttpClient.interceptors().add(new SpecialInterceptor());
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
//                    : HttpLoggingInterceptor.Level.NONE);
//            okHttpClient.interceptors().add(logging);

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RemoteService.ENDPOINT)
//                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(RemoteService.class);
        }
    }
}
