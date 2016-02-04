package cc.cloudist.app.android.template.data.remote;


import java.io.IOException;

import cc.cloudist.app.android.template.util.LogUtils;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 针对 HttpClient 的一个拦截器,用于处理一些服务器特殊的响应,例如登出,错误等
 * 具体的处理在 {@link InterceptHandler}
 */
public class SpecialInterceptor implements Interceptor {

    private static final String TAG = LogUtils.makeLogTag(SpecialInterceptor.class);

    private InterceptHandler mHandler;

    public SpecialInterceptor() {
        mHandler = InterceptHandler.getInstance();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response response = chain.proceed(chain.request());
        switch (response.code()) {
            case HttpStatus.CODE_OK:
                // 使用 Handler 处理该请求
                mHandler.sendEmptyMessage(InterceptHandler.ACTION_OK);
                break;
        }

        return response;
    }
}
