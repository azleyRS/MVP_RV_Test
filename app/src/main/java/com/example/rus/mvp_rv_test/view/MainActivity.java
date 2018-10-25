package com.example.rus.mvp_rv_test.view;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.rus.mvp_rv_test.R;
import com.example.rus.mvp_rv_test.model.Worker;
import com.example.rus.mvp_rv_test.presenter.MainPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyAdapter adapter;
    private MainPresenter presenter;

    @Override
    public void addWorker(Worker worker) {
        adapter.addWorker(worker);
    }

    @Override
    public void shuffle(DiffUtil.DiffResult diffResult, List<Worker> newWorkersList) {
        adapter.shuffle(diffResult, newWorkersList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.shuffle:
                presenter.shuffle(adapter.getWorkersList());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        presenter = new MainPresenter();
        presenter.attachView(this);

        initToolbar();
        initRecyclerView();
        initFAB();
    }

    private void initFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> presenter.addButtonPressed());
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        adapter = new MyAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        MyItemTouchHelperCallback myItemTouchHelperCallback = new MyItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(myItemTouchHelperCallback);
        touchHelper.attachToRecyclerView(recyclerView);
        MyItemDecoration itemDecoration = new MyItemDecoration(this, R.dimen.item_space);
        recyclerView.addItemDecoration(itemDecoration);
    }

    private void initToolbar() {
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }
}
