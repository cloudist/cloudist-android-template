package cc.cloudist.app.android.template.data.remote;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import cc.cloudist.app.android.template.TemplateApplication;

public class InterceptHandler extends Handler {

    public static final int ACTION_OK = 0;

    private static class InterceptHandlerHolder {
        private static final InterceptHandler INSTANCE = new InterceptHandler();
    }

    public static InterceptHandler getInstance() {
        return InterceptHandlerHolder.INSTANCE;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case ACTION_OK:
                Toast.makeText(TemplateApplication.getInstance(), "ok", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
