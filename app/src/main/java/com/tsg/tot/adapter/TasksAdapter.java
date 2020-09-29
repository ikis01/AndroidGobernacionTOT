package com.tsg.tot.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tsg.tot.R;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.main.mainmvp.MainMVP;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {
    List<Task> taskList;
    Task task;
    int size = 0;
    Context context;
    MainMVP.Presenter presenter;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTask;
        private LinearLayout adapterTask;

        ViewHolder(View itemView) {
            super(itemView);
            nameTask = itemView.findViewById(R.id.nameTask);
            adapterTask = itemView.findViewById(R.id.adapterTask);
        }
    }

    public TasksAdapter() {
        task = new Task();
    }

    public void dataSet(List<Task> taskList, int size, Context context, MainMVP.Presenter presenter) {
        this.taskList = taskList;
        this.size = size;
        this.context = context;
        this.presenter = presenter;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TasksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_tasks, parent, false);
        return new TasksAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksAdapter.ViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.adapterTask.setBackgroundColor(Color.parseColor("#FFF1EF"));
        } else {
            holder.adapterTask.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        holder.nameTask.setText(taskList.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return size;
    }
}
