package com.example.hookdemo.proxy;

import android.content.Intent;

import com.example.hookdemo.util.HookHelper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class IActivityManagerProxy implements InvocationHandler {

    private Object mActivityManager;
    private static final String TAG = IActivityManagerProxy.class.getSimpleName();

    public IActivityManagerProxy(Object mActivityManager){
        this.mActivityManager = mActivityManager;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if("startActivity".equals(method.getName())){
            Intent intent = null;
            int index = 0;
            for(int i=0; i<objects.length; i++){
                if(objects[i] instanceof Intent){
                    index = i;
                    break;
                }
            }
            intent = (Intent) objects[index];
            Intent subIntent = new Intent();
            String packageName = "com.example.hookdemo";
            subIntent.setClassName(packageName, packageName + ".activity.StubActivity");
            subIntent.putExtra(HookHelper.TARGET_INTENT, intent);
            objects[index] = subIntent;
        }
        return method.invoke(mActivityManager, objects);
    }
}
