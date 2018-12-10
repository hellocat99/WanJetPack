package com.example.testing.wanjetpack;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.testing.wanjetpack.listener.main.MainObserver;

//AppCompatActivity继承SupportActivity，SupportActivity实现了LifecycleOwner
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //main_activity中使用navigation实现fragment跳转到Actvity或者fragment跳fragment
        setContentView(R.layout.main_activity);

        //添加检测Activity生命周期变化的观察者
        getLifecycle().addObserver(new MainObserver());
    }

    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }
}
