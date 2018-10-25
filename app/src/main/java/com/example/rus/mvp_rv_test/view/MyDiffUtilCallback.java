package com.example.rus.mvp_rv_test.view;

import android.support.v7.util.DiffUtil;

import com.example.rus.mvp_rv_test.model.Worker;

import java.util.List;

public class MyDiffUtilCallback extends DiffUtil.Callback {

    private List<Worker> oldWorkersList;
    private List<Worker> newWorkersList;

    public MyDiffUtilCallback(List<Worker> oldWorkersList, List<Worker> newWorkersList) {
        this.oldWorkersList = oldWorkersList;
        this.newWorkersList = newWorkersList;
    }


    @Override
    public int getOldListSize() {
        return oldWorkersList.size();
    }

    @Override
    public int getNewListSize() {
        return newWorkersList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldWorkersList.get(oldItemPosition).getId() == newWorkersList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldWorkersList.get(oldItemPosition).getName().equals(newWorkersList.get(newItemPosition).getName());
    }
}
