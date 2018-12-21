package com.example.testing.wanjetpack.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.testing.wanjetpack.R;
import com.example.testing.wanjetpack.paging.adapter.EmployeeAdapter;
import com.example.testing.wanjetpack.paging.dao.Employee;
import com.example.testing.wanjetpack.paging.viewmodel.PagingViewModel;

public class PagingTextActivity extends AppCompatActivity {

    private ViewModel mViewModel;
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paging_text_activity);

        mViewModel = ViewModelProviders.of(this, new ViewModelProvider.Factory() {
                    @NonNull
                    @Override
                    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                        return (T) new PagingViewModel(getApplication());
                    }
                }
        ).get(PagingViewModel.class);

        //  这里没有
        RecyclerView recyclerView = findViewById(R.id.rv_employee);

        adapter = new EmployeeAdapter(EmployeeAdapter.diffCallback);
        recyclerView.setAdapter(adapter);

        ((PagingViewModel) mViewModel).allEmployees.observe(
                this, new Observer<PagedList<Employee>>() {
                    @Override
                    public void onChanged(@Nullable PagedList<Employee> employees) {
                        adapter.submitList(employees);
                    }
                });
    }


    @Override
    protected void onResume() {
        super.onResume();
        int count = adapter.getItemCount();
    }
}
