package com.example.hookdemo;

import android.app.Application;
import android.content.Context;

import com.example.hookdemo.util.HookHelper;

import java.util.logging.Handler;

public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
         //   HookHelper.hookAMS();
         //   HookHelper.hookHandler();
            HookHelper.hookInstrumentation(base);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
