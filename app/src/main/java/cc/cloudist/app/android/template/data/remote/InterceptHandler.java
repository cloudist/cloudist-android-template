package cc.cloudist.app.android.template.data.remote;

import android.os.Handler;
import android.os.Message;

public class InterceptHandler extends Handler {

    private static class InterceptHandlerHolder {
        private static final InterceptHandler INSTANCE = new InterceptHandler();
    }

    public static InterceptHandler getInstance() {
        return InterceptHandlerHolder.INSTANCE;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
    }
}
