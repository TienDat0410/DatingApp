package com.quintus.labs.datingapp.service.sharedprefs;

import android.app.Application;

public class App extends Application {
    private static App mSelf;

    public static App self() {
        return mSelf;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSelf = this;
    }
}