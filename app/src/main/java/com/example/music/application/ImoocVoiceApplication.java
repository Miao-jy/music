package com.example.music.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.example.lib_audio.app.AudioHelper;

public class ImoocVoiceApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static ImoocVoiceApplication mApplication = null;

    @SuppressLint("StaticFieldLeak")
    public static Context context;

    // 彩云api的TOKEN
    public final static String TOKEN = "BDvLM7h1hZViJHe5";

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        AudioHelper.init(this);
        context = mApplication;
    }

    public static ImoocVoiceApplication getInstance() {
        return mApplication;
    }
}
