/*
 * Copyright (c) 2021.
 */

package com.tsg.kot.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tsg.kot.R;
import com.tsg.kot.data.remote.ApiUtils;
import com.tsg.kot.data.remote.model.FileMessageRemote;
import com.tsg.kot.main.mainmvp.MainMVP;

import java.io.File;
import java.util.List;

import static androidx.core.content.FileProvider.getUriForFile;

public class MessagesFileAdapter extends RecyclerView.Adapter<MessagesFileAdapter.ViewHolder> implements View.OnClickListener {
    List<FileMessageRemote> messageFileList;
    FileMessageRemote fileMessageRemote;
    int size = 0 ;
    Context context;
    MainMVP.Presenter presenter;
    private View.OnClickListener listener;

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
        private final ImageView statusFileMessageImage;
        private final TextView messageFileName;
        private final TextView messageEstado;
        private final LinearLayout adapterFileMessages;
       // private final Button  attachFileMessages;


        ViewHolder(View itemView) {
            super(itemView);
            statusFileMessageImage = itemView.findViewById(R.id.statusFileMessageImage);
            messageFileName = itemView.findViewById(R.id.messageFileName);
            messageEstado  = itemView.findViewById(R.id.messageEstado);
            adapterFileMessages = itemView.findViewById(R.id.adapterFileMessages);
           // attachFileMessages =  itemView.findViewById(R.id.bt_attachFileMessages);



        }
    }

    public MessagesFileAdapter(){

        fileMessageRemote = new FileMessageRemote();
    }

    public void dataSet(List<FileMessageRemote> messageFileList, int size, Context context, MainMVP.Presenter presenter) {
        this.messageFileList = messageFileList;
        this.size = size;
        this.context = context;
        this.presenter = presenter;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MessagesFileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_file_messages, parent, false);
        v.setOnClickListener(this);
        return new MessagesFileAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesFileAdapter.ViewHolder holder, int position) {


        if (position % 2 == 0) {
            holder.adapterFileMessages.setBackgroundColor(Color.parseColor("#FFF1EF"));
        } else {
            holder.adapterFileMessages.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        holder.messageFileName.setText(messageFileList.get(position).getNombre());
        holder.messageEstado.setText("ARCHIVO_MENSAJE");


        holder.itemView.setOnClickListener(view -> {

/*            //Toast.makeText(context,"click en material: "+taskList.get(position).getNombre(),Toast.LENGTH_SHORT).show();
            Intent intent  = new  Intent (view.getContext(), TaskDetailActivity.class);
            intent.putExtra( "holder",String.valueOf(holder.adapterTask));
            intent.putExtra("task_name",taskList.get(position).getNombre());
            intent.putExtra("student_name", ((MainView)context).tv_studentName.getText());
            intent.putExtra("tareakiosco",taskList.get(position).getTareakiosco());
            intent.putExtra("idMateria",taskList.get(position).getMaterias());
            intent.putExtra("idEstudiante",taskList.get(position).getEstudiante());
            intent.putExtra("nombreTarea",taskList.get(position).getNombre());
            intent.putExtra("idSubida",taskList.get(position).getSubida().getId());
            intent.putExtra("idTarea",taskList.get(position).getId());
            intent.putExtra("registroTarea",taskList.get(position).getRegistroTarea());
            //   intent.putExtra("tareasPendientes",countTareasPendientes);

            context.startActivity(intent);*/

            File newFile = new File(messageFileList.get(position).getUrl());
            Uri contentUri = getUriForFile(context.getApplicationContext(), "com.tot.fileprovider", newFile);
            ApiUtils.openFile(contentUri,context);
            notifyDataSetChanged();


        });


/*        holder.attachFileMessages.setOnClickListener(view ->{

            File newFile = new File(messageFileList.get(position).getUrl());
            Uri contentUri = getUriForFile(context.getApplicationContext(), "com.tot.fileprovider", newFile);
            ApiUtils.openFile(contentUri,context);
        });*/



    }

    @Override
    public int getItemCount() {
     return size;
    }







}
