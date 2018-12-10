package com.example.testing.wanjetpack.listener.main;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

//根据具体逻辑，实现需要被检测的生命周期方法，比如做一些权限检查之类的
public class MainObserver implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void connectOnCreate() {
        p("connectOnCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void connectOnResume() {
        p("connectOnResume");

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void disConnectOnDestroy() {
        p("disConnectOnDestroy");
    }

    private void p(String str) {
//        callback.update(str);
        Log.d("wkl", "MainObserver get: " + str);
    }

}
