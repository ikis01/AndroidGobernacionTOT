package com.tsg.tot.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tsg.tot.R;
import com.tsg.tot.data.model.Lessons;
import com.tsg.tot.task.taskmvp.TaskMVP;

import java.util.List;

public class LessonsAdapter extends RecyclerView.Adapter<LessonsAdapter.ViewHolder> {

    List<Lessons> lessonsList;
    Lessons lessons;
    int size =0;
    Context context ;
    TaskMVP.Presenter presenter ;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombreClase ;
        private TextView tema ;
        private Button  verMas ;
        private RelativeLayout adapterLessons;

        ViewHolder(View itemView) {
            super(itemView);
            nombreClase = itemView.findViewById(R.id.tv_registro_nombre_clase);
            tema =  itemView.findViewById(R.id.tv_registro_tema_clase);
            verMas =  itemView.findViewById(R.id.bt_registro_ver_mas);
            adapterLessons = itemView.findViewById(R.id.adapterLesson);
        }
    }

    public LessonsAdapter (){
        lessons  = new Lessons();
    }

    public void dataSet (List<Lessons> lessonsList, int size, Context context, TaskMVP.Presenter presenter){
        this.lessonsList = lessonsList;
        this.size = size;
        this.context = context;
        this.presenter = presenter;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_lessons, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position % 2 == 0) {
             holder.adapterLessons.setBackgroundColor(Color.parseColor("#FFF1EF"));
        } else {
             holder.adapterLessons.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        holder.nombreClase.setText(lessonsList.get(position).getNombre());
        holder.tema.setText(lessonsList.get(position).getTema());

        holder.verMas.setOnClickListener(view -> {
            Lessons lessons = new Lessons(
                    lessonsList.get(position).getId(),
                    lessonsList.get(position).getNombre(),
                    lessonsList.get(position).getTema(),
                    lessonsList.get(position).getFechaInicio(),
                    lessonsList.get(position).getMaterias(),
                    lessonsList.get(position).getProfesor()

            );

            presenter.setLessons(lessons);
            notifyDataSetChanged();

        });
    }

    @Override
    public int getItemCount() {
        return size;
    }



}