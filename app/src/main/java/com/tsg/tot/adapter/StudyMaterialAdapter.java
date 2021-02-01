package com.tsg.tot.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tsg.tot.R;
import com.tsg.tot.data.remote.ApiUtils;
import com.tsg.tot.data.remote.model.StudyMaterialRemote;
import com.tsg.tot.task.taskmvp.TaskMVP;

import java.io.File;
import java.util.List;

import static androidx.core.content.FileProvider.getUriForFile;

public class StudyMaterialAdapter extends RecyclerView.Adapter<StudyMaterialAdapter.ViewHolder> {

    List<StudyMaterialRemote> studyMaterialRemoteList;
    StudyMaterialRemote studyMaterialRemote;
    int size = 0;
    Context context;
    TaskMVP.Presenter presenter;


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nombre;
        private TextView descripcion;
        private Button archivo_adjunto;
        private RelativeLayout adapterStudyMaterial;


        ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tv_material_nombre);
            descripcion = itemView.findViewById(R.id.tv_material_descripcion);
            archivo_adjunto = itemView.findViewById(R.id.bt_material_archivo_adjunto);
            adapterStudyMaterial = itemView.findViewById(R.id.adapterStudyMaterial);
        }
    }

    public StudyMaterialAdapter() {
        studyMaterialRemote = new StudyMaterialRemote();
    }

    public void dataSet (List<StudyMaterialRemote> studyMaterialRemoteList, int size, Context context, TaskMVP.Presenter presenter){
        this.studyMaterialRemoteList = studyMaterialRemoteList;
        this.size = size;
        this.context = context;
        this.presenter = presenter;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_study_material, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        if (position % 2 == 0) {
            holder.adapterStudyMaterial.setBackgroundColor(Color.parseColor("#FFF1EF"));
        } else {
            holder.adapterStudyMaterial.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        holder.nombre.setText(studyMaterialRemoteList.get(position).getTema());
        holder.descripcion.setText(studyMaterialRemoteList.get(position).getDescripcion());

        holder.archivo_adjunto.setOnClickListener(view ->{

            File newFile = new File(studyMaterialRemoteList.get(position).getRuta());
            Uri contentUri = getUriForFile(context.getApplicationContext(), "com.tot.fileprovider", newFile);
            ApiUtils.openFile(contentUri,context);
        });

    }

    @Override
    public int getItemCount() {
        return size;
    }
}

