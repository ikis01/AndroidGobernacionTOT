package com.tsg.tot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tsg.tot.R;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.main.mainmvp.MainMVP;

import java.util.List;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.ViewHolder> {

    List<Subjects> subjectsList;
    Subjects subjects;
    int size = 0;
    Context context;
    MainMVP.Presenter presenter;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameSubject;

        ViewHolder(View itemView) {
            super(itemView);
            nameSubject = itemView.findViewById(R.id.nameSubject);
        }
    }

    public SubjectsAdapter() {
        subjects = new Subjects();
    }

    public void dataSet(List<Subjects> subjectsList, int size, Context context, MainMVP.Presenter presenter) {
        this.subjectsList = subjectsList;
        this.size = size;
        this.context = context;
        this.presenter = presenter;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubjectsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_subject, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameSubject.setText(subjectsList.get(position).getTitulo());

        holder.itemView.setOnClickListener(view -> {
            Subjects subjects = new Subjects(null,
                    subjectsList.get(position).getTitulo(),
                    null,
                    null,
                    subjectsList.get(position).getSubtitulo(),
                    subjectsList.get(position).getDescripcion(),
                    subjectsList.get(position).getImagen());

            presenter.setInfoSubject(subjects);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return size;
    }
}
