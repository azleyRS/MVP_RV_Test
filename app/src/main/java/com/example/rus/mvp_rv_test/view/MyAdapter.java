package com.example.rus.mvp_rv_test.view;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rus.mvp_rv_test.R;
import com.example.rus.mvp_rv_test.model.Worker;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements ItemTouchHelperAdapter {

    private List<Worker> workersList;
    private int counter = 0;

    public MyAdapter() {
        workersList = new ArrayList<>();
    }

    public List<Worker> getWorkersList() {
        return workersList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_worker_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Worker worker = workersList.get(position);
        holder.photoImageView.setImageResource(worker.getPhoto());
        holder.nameTextView.setText(worker.getName());
        holder.ageTextView.setText("Age: " + worker.getAge());
        holder.positionTextView.setText("Position: " + worker.getPosition());
    }

    @Override
    public int getItemCount() {
        return workersList.size();
    }

    public void addWorker(Worker worker) {
        worker.setId(counter);
        counter++;
        workersList.add(worker);
        notifyItemInserted(workersList.size()-1);
    }

    public void shuffle(DiffUtil.DiffResult diffResult, List<Worker> newWorkersList) {
        workersList.clear();
        workersList.addAll(newWorkersList);
        diffResult.dispatchUpdatesTo(this);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView photoImageView;
        TextView nameTextView, ageTextView, positionTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            photoImageView = itemView.findViewById(R.id.photo_image_view);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            ageTextView = itemView.findViewById(R.id.age_text_view);
            positionTextView = itemView.findViewById(R.id.position_text_view);
        }
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Worker targetWorker = workersList.get(fromPosition);
        workersList.remove(fromPosition);
        workersList.add(toPosition, targetWorker);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        workersList.remove(position);
        notifyItemRemoved(position);
    }
}
