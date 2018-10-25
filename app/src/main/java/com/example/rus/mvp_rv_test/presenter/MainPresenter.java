package com.example.rus.mvp_rv_test.presenter;

import android.support.v7.util.DiffUtil;

import com.example.rus.mvp_rv_test.view.MyDiffUtilCallback;
import com.example.rus.mvp_rv_test.model.Worker;
import com.example.rus.mvp_rv_test.model.WorkerGenerator;
import com.example.rus.mvp_rv_test.view.MainView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {
    private MainView view;
    private CompositeDisposable compositeDisposables;

    public void attachView(MainView view){
        this.view = view;
        compositeDisposables = new CompositeDisposable();
    }

    public void detachView(){
        view = null;
        if (compositeDisposables != null) {
            compositeDisposables.clear();
        }
    }


    public void addButtonPressed() {
        Worker worker = WorkerGenerator.generateWorker();
        view.addWorker(worker);
    }

    public void shuffle(List<Worker> workersList) {
        List<Worker> newWorkersList = new ArrayList<>(workersList);
        Collections.shuffle(newWorkersList);

        Single<DiffUtil.DiffResult> single = Single.fromCallable(() -> {
            MyDiffUtilCallback diffCallback = new MyDiffUtilCallback(workersList, newWorkersList);
            return DiffUtil.calculateDiff(diffCallback, false);
        });
        Disposable disposable = single.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(diffResult -> {
                    view.shuffle(diffResult, newWorkersList);
                });
        compositeDisposables.add(disposable);
    }
}
