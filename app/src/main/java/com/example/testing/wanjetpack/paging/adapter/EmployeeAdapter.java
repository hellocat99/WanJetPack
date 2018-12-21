package com.example.testing.wanjetpack.paging.adapter;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testing.wanjetpack.R;
import com.example.testing.wanjetpack.paging.dao.Employee;

public class EmployeeAdapter extends PagedListAdapter<Employee, EmployeeAdapter.EmployeeViewHolder> {

    public EmployeeAdapter(@NonNull DiffUtil.ItemCallback<Employee> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.employee_item, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        public TextView employeeNameTxt;
        public Employee employee;

        public EmployeeViewHolder(View parent) {
            super(parent);
            employeeNameTxt = itemView.findViewById(R.id.tv_employee_name);
        }

        public void bindTo(Employee employee) {
            this.employee = employee;
            employeeNameTxt.setText(employee.name);
        }
    }

    public static DiffUtil.ItemCallback<Employee> diffCallback = new DiffUtil.ItemCallback<Employee>() {
        @Override
        public boolean areItemsTheSame(Employee oldItem, Employee newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(Employee oldItem, Employee newItem) {
            return false;
        }
    };
}
