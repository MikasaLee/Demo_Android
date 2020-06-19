package com.example.coursedesign.app;

import android.app.Application;
import android.content.Context;

public class ServerApplication extends Application {


    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    /**
     * 获取应用程序的上下文环境
     *
     * @return
     */
    public static Context getContext() {
        return mContext;
    }
}
