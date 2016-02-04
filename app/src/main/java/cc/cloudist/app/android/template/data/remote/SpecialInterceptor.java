package cc.cloudist.app.android.template.data.remote;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 针对 HttpClient 的一个拦截器,用于处理一些服务器特殊的响应,例如登出,错误等
 * 具体的处理在 {@link InterceptHandler}
 */
public class SpecialInterceptor implements Interceptor {

    private InterceptHandler mHandler;

    public SpecialInterceptor() {
        mHandler = InterceptHandler.getInstance();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response response = chain.proceed(chain.request());

        switch (response.code()) {
            case HttpStatus.EXAMPLE_OK:
                // 使用 Handler 处理该请求
                break;
        }

        return response;
    }
}
