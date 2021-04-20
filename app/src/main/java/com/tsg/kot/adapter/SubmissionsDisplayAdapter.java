package com.tsg.kot.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tsg.kot.R;
import com.tsg.kot.data.model.SubmissionDisplay;
import com.tsg.kot.data.remote.ApiUtils;
import com.tsg.kot.task.taskmvp.TaskMVP;

import java.io.File;
import java.util.List;

import static androidx.core.content.FileProvider.getUriForFile;


public class SubmissionsDisplayAdapter extends RecyclerView.Adapter<SubmissionsDisplayAdapter.ViewHolder> {

    List<SubmissionDisplay> submissionDisplayList;
    SubmissionDisplay submissionDisplay ;
    int size = 0;
    Context context;
    TaskMVP.Presenter presenter;

    static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView nameFile ;
        private final TextView statusFile;
        private final LinearLayout adapterSubmissionDisplay;

        ViewHolder(View itemView){
            super(itemView);
            nameFile = itemView.findViewById(R.id.tv_file_name_submission_display);
            statusFile = itemView.findViewById(R.id.tv_file_estatus_submission_display);
            adapterSubmissionDisplay = itemView.findViewById(R.id.adapterSubmissionDisplay);
        }
    }

    public SubmissionsDisplayAdapter(){
        submissionDisplay = new SubmissionDisplay();
    }

    public void dataSet (List<SubmissionDisplay> submissionDisplayList, int size, Context context, TaskMVP.Presenter presenter){
        this.submissionDisplayList = submissionDisplayList;
        this.size = size;
        this.context = context;
        this.presenter = presenter;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubmissionsDisplayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_submission_display, parent, false);
        return new SubmissionsDisplayAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.adapterSubmissionDisplay.setBackgroundColor(Color.parseColor("#FFF1EF"));
        } else {
            holder.adapterSubmissionDisplay.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        holder.nameFile.setText(submissionDisplayList.get(position).getNombreArchivo());
        holder.statusFile.setText(submissionDisplayList.get(position).getEstatus());

        holder.itemView.setOnClickListener(view -> {
            notifyDataSetChanged();

            File newFile = new File(submissionDisplayList.get(position).getRuta());
            Uri contentUri = getUriForFile(context.getApplicationContext(), "com.tot.fileprovider", newFile);
            ApiUtils.openFile(contentUri,context);

        });

    }

    @Override
    public int getItemCount() {
        return size;
    }


}