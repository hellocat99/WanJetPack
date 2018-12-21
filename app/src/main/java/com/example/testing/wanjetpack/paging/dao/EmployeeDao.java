package com.example.testing.wanjetpack.paging.dao;

import android.arch.paging.DataSource;

public class EmployeeDao {

    public static DataSource.Factory getAllEmployees() {
        return new DataSource.Factory() {
            @Override
            public DataSource create() {
                return new EmployeeDataSource();
            }
        };
    }
}
