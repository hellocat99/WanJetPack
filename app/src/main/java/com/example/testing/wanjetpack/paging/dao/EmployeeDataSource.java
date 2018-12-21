package com.example.testing.wanjetpack.paging.dao;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.example.testing.wanjetpack.paging.data.PagingData;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDataSource extends PageKeyedDataSource<Integer, Employee> {

    private static final List<Employee> EMPLOYEE_LIST = new ArrayList<>();

    static {
        for (int i = 0; i < PagingData.CHEESE_DATA.length; i++) {
            EMPLOYEE_LIST.add(new Employee(i, PagingData.CHEESE_DATA[i]));
        }
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {
        List<Employee> employeeList = EMPLOYEE_LIST.subList(0, params.requestedLoadSize);
        callback.onResult(employeeList, null, 2);
    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {
        int fromIndex = ((Integer) params.key - 1) * params.requestedLoadSize;
        int toIndex = fromIndex + params.requestedLoadSize;
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (fromIndex > EMPLOYEE_LIST.size()) {
            fromIndex = EMPLOYEE_LIST.size();
        }
        if (toIndex > EMPLOYEE_LIST.size()) {
            toIndex = EMPLOYEE_LIST.size();
        }
        List<Employee> employeeList = EMPLOYEE_LIST.subList(fromIndex,
                toIndex);
        callback.onResult(employeeList, (Integer) params.key - 1);
    }

    @Override
    public void loadAfter(@NonNull LoadParams params, @NonNull LoadCallback callback) {
        int fromIndex = ((Integer) params.key - 1) * params.requestedLoadSize;
        int toIndex = fromIndex + params.requestedLoadSize;
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (fromIndex > EMPLOYEE_LIST.size()) {
            fromIndex = EMPLOYEE_LIST.size();
        }
        if (toIndex > EMPLOYEE_LIST.size()) {
            toIndex = EMPLOYEE_LIST.size();
        }
        List<Employee> employeeList = EMPLOYEE_LIST.subList(fromIndex,
                toIndex);
        callback.onResult(employeeList, (Integer) params.key + 1);
    }
}
