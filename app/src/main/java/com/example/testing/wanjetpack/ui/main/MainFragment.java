package com.example.testing.wanjetpack.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testing.wanjetpack.R;
import com.example.testing.wanjetpack.work.SyncWork;

import java.util.concurrent.TimeUnit;

import androidx.navigation.fragment.NavHostFragment;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.WorkStatus;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private View contentView;
    private WorkRequest request;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.main_fragment, container, false);
        return contentView;
    }

    private void useViewModleAndLiveData() {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
        //页面要处于STARTED 或者 RESUMED才能接受到哦，如果想要其他状态也收到可以参考observeForever
        mViewModel.getLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(@Nullable Long aLong) {
                Log.d("wkl", "接受数据 ：" + aLong);
            }
        });
    }

    //使用navigtion action实现fragment跳转到fragment
    private void useNavigation() {
        contentView.findViewById(R.id.message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("arg", "pass arg");
                NavHostFragment
                        .findNavController(MainFragment.this)
                        .navigate(R.id.action_mainFragment_to_secondFragment, bundle);
            }
        });
    }

    private void useWorkManager() {
        initWork();

        contentView.findViewById(R.id.tv_add_work).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //执行request
                WorkManager.getInstance().enqueue(request);

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        useViewModleAndLiveData();

        useNavigation();

        useWorkManager();

    }

    //创建work
    private void initWork() {
        //执行work的条件，比如什么充电的时候啊，手机闲置啊
        Constraints constraints = new Constraints.Builder()
//                .setRequiresDeviceIdle(true)//指定{@link WorkRequest}运行时设备是否为空闲
                .setRequiredNetworkType(NetworkType.NOT_ROAMING)
                .setRequiresBatteryNotLow(true)//指定设备电池是否不应低于临界阈值
                .setRequiresCharging(true)//网络状态
//                .setRequiresDeviceIdle(true)//指定{@link WorkRequest}运行时设备是否为空闲
                .setRequiresStorageNotLow(true)//指定设备可用存储是否不应低于临界阈值
                .build();

        PeriodicWorkRequest.Builder builder = new PeriodicWorkRequest
                .Builder(SyncWork.class, PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS);
        //向work传递数据
        Data data = new Data.Builder()
                .putLong("arg1", 5)
                .putString("arg2", "打到D&G")
                .build();
        builder.setInputData(data);
//        builder.setConstraints(constraints);
        request = builder.build();

        WorkManager.getInstance().getStatusById(request.getId()).observe(this, new Observer<WorkStatus>() {
            @Override
            public void onChanged(@Nullable WorkStatus workStatus) {
                if (workStatus != null && workStatus.getState().isFinished()) {
                    //sorry,我不知道为什么收不到output的数据，此处有一个bug
                    Log.d("wkl", workStatus.getOutputData().getString("name"));
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (request != null) {
            WorkManager.getInstance().cancelWorkById(request.getId());
        }
    }
}
