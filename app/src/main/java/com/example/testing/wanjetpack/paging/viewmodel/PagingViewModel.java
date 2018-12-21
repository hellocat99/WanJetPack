package com.example.testing.wanjetpack.paging.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.example.testing.wanjetpack.paging.dao.Employee;
import com.example.testing.wanjetpack.paging.dao.EmployeeDao;

public class PagingViewModel extends AndroidViewModel {

    private static final int PAGE_SIZE = 15;
    private static final boolean ENABLE_PLACEHOLDERS = false;

    public PagingViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<PagedList<Employee>> allEmployees =
            new LivePagedListBuilder(EmployeeDao.getAllEmployees(),
                    new PagedList.Config.Builder()
                            .setPageSize(PAGE_SIZE)
                            .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
                            .setInitialLoadSizeHint(PAGE_SIZE)
                            .build()).build();

}
