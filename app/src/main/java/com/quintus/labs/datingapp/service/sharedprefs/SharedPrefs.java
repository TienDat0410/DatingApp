package com.quintus.labs.datingapp.service.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    private static final String PREFS_NAME = "tinhong";
    private static SharedPrefs mInstance;
    private SharedPreferences mSharedPreferences;

    private SharedPrefs() {
        mSharedPreferences = App.self().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferences getInstance() {
        if (mInstance == null) {
            mInstance = new SharedPrefs();
        }
        return mInstance.mSharedPreferences;
    }

    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }
}
