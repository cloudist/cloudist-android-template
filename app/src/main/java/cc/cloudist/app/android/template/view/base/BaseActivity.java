package cc.cloudist.app.android.template.view.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    private Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        // 绑定 ButterKnife 服务
        ButterKnife.bind(this);
    }

    protected void startNewActivity(Class clz) {
        startNewActivity(new Intent(this, clz));
    }

    protected void startNewActivity(Intent intent) {
        startActivity(intent);
        finish();
    }

    protected void showToast(final String message, final int toastType) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(getBaseContext(), message, toastType);
                } else {
                    mToast.setText(message);
                }

                mToast.show();
            }
        });
    }

    protected void showToast(final String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    protected void showToast(final @StringRes int resId, final int toastType) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(getBaseContext(), resId, toastType);
                } else {
                    mToast.setText(resId);
                }

                mToast.show();
            }
        });
    }

    protected void showToast(final @StringRes int resId) {
        showToast(resId, Toast.LENGTH_SHORT);
    }
}
