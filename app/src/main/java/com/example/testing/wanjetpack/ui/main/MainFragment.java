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

import androidx.navigation.fragment.NavHostFragment;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private View contentView;

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
        //页面要处于STARTED 或者 RESUMED才能接受到哦，如果想要其他状态也收到可以参考observeForever
        mViewModel.getLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(@Nullable Long aLong) {
                Log.d("wkl", "接受数据 ：" + aLong);
            }
        });

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

}
