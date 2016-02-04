package cc.cloudist.app.android.template;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class TemplateApplication extends Application {

    private static TemplateApplication mApplication;

    public static TemplateApplication getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;

        // 初始化 Facebook 图片库 Fresco
        initialFrescoConfig();
    }

    private void initialFrescoConfig() {
        Fresco.initialize(this);
    }

}
