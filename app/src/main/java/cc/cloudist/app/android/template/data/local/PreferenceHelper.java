package cc.cloudist.app.android.template.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import cc.cloudist.app.android.template.TemplateApplication;

public class PreferenceHelper {

    private static final String PREF_FILE_NAME = "android_template_pref_file";

    private final SharedPreferences mPref;

    private static final class PreferenceHelperHolder {
        private static final PreferenceHelper INSTANCE = new PreferenceHelper();
    }

    private PreferenceHelper() {
        mPref = TemplateApplication.getInstance()
                .getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public static PreferenceHelper getInstance() {
        return PreferenceHelperHolder.INSTANCE;
    }

    public void clear() {
        mPref.edit().clear().apply();
    }
}
