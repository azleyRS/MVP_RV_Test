package com.example.rus.mvp_rv_test.view;

import android.support.v7.util.DiffUtil;

import com.example.rus.mvp_rv_test.model.Worker;

import java.util.List;

public interface MainView {

    void addWorker(Worker worker);

    void shuffle(DiffUtil.DiffResult diffResult, List<Worker> newWorkersList);
}
