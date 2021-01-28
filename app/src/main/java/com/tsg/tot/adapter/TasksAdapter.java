package com.tsg.tot.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.tsg.tot.R;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.main.fragment.ListFileKioscoFragment;
import com.tsg.tot.main.mainmvp.MainMVP;
import com.tsg.tot.main.mainmvp.MainView;
import com.tsg.tot.task.TaskDetailActivity;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> implements View.OnClickListener{
    List<Task> taskList;
    Task task;
    int size = 0;
    Context context;
    MainMVP.Presenter presenter;

    private View.OnClickListener listener ;

    @Override
    public void onClick(View v) {
            if (listener!=null){
                listener.onClick(v);
            }
    }

    public void setOnClickListener(View.OnClickListener listener ){
        this.listener= listener;

    }

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
        v.setOnClickListener(this);
        return new TasksAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (position % 2 == 0) {
            holder.adapterTask.setBackgroundColor(Color.parseColor("#FFF1EF"));
        } else {
            holder.adapterTask.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        holder.nameTask.setText(taskList.get(position).getNombre());

        holder.itemView.setOnClickListener(view -> {

            //Toast.makeText(context,"click en material: "+taskList.get(position).getNombre(),Toast.LENGTH_SHORT).show();
            Intent intent  = new  Intent (view.getContext(), TaskDetailActivity.class);
            intent.putExtra( "holder",String.valueOf(holder.adapterTask));
            intent.putExtra("task_name",taskList.get(position).getNombre());
            intent.putExtra("student_name",((AppCompatTextView)((MainView)context).tv_studentName).getText());
            intent.putExtra("tareakiosco",taskList.get(position).getTareakiosco());
            intent.putExtra("idMateria",taskList.get(position).getMaterias());
            intent.putExtra("idEstudiante",taskList.get(position).getEstudiante());

            context.startActivity(intent);
            notifyDataSetChanged();


        });


    }

    @Override
    public int getItemCount() {
        return size;
    }
}
