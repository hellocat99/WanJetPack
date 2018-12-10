package com.example.testing.wanjetpack.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

//绑定到Activity或者Fragment对象上，不随Activity或者Fragment生命周期影响。
public class MainViewModel extends ViewModel {

    private final static long FINE_SECOND = 1000 * 5;

    //变化可以被检测到的数据
    private MutableLiveData<Long> mLiveData = new MutableLiveData<>();
    private long currentTime;

    public LiveData<Long> getLiveData() {
        return mLiveData;
    }

    public MainViewModel() {
        //发射数据
        Timer timer = new Timer();
        currentTime = System.currentTimeMillis();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final long newValue = (System.currentTimeMillis() - currentTime) / 1000;
                // setValue() cannot be called from a background thread so post to main thread.
                mLiveData.postValue(newValue);
            }
        }, FINE_SECOND, FINE_SECOND);
    }

}
