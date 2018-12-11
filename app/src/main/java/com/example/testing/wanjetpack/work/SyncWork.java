package com.example.testing.wanjetpack.work;

import android.support.annotation.NonNull;
import android.util.Log;

import androidx.work.Data;
import androidx.work.Worker;

//这个就是我的work,
public class SyncWork extends Worker {

    @NonNull
    @Override
    public Result doWork() {
        //同步数据
        int arg1 = getInputData().getInt("arg1", 0);
        String arg2 = getInputData().getString("arg2");
        //应该多次执行，不知道为什么执行了一次
        Log.d("wkl", "数据同步完毕: arg1 =" + arg1 + ", arg2 = " + arg2);
        setOutputData(new Data.Builder().putString("name", "从 work返回的数据为： mOutputData").build());
        return Result.SUCCESS;
    }
}
