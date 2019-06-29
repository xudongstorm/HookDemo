package com.example.hookdemo.proxy;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.hookdemo.util.FieldUtil;
import com.example.hookdemo.util.HookHelper;

public class HCallback implements Handler.Callback {

    public static final int LAUNCH_ACTIVITY = 100;
    private Handler mHandler;

    public HCallback(Handler mHandler){
        this.mHandler = mHandler;
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        if(message.what == LAUNCH_ACTIVITY){
            Object r = message.obj;
            try {
                Intent intent = (Intent) FieldUtil.getField(r.getClass(), r, "intent");
                Intent targer = intent.getParcelableExtra(HookHelper.TARGET_INTENT);
                intent.setComponent(targer.getComponent());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mHandler.handleMessage(message);
        return true;
    }
}
