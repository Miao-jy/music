package com.example.music.application;

import android.app.Application;

import com.example.lib_audio.app.AudioHelper;

public class ImoocVoiceApplication extends Application {
    private static ImoocVoiceApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        AudioHelper.init(this);
    }

    public static ImoocVoiceApplication getInstance() {
        return mApplication;
    }
}
