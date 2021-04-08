/*
 * Copyright (c) 2021.
 */

package com.tsg.tot.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.tsg.tot.R;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.data.remote.model.MessageRemote;
import com.tsg.tot.main.mainmvp.MainMVP;
import com.tsg.tot.main.mainmvp.MainView;
import com.tsg.tot.messages.MessageDetailActivity;
import com.tsg.tot.repository.DatabaseRepository;
import com.tsg.tot.storage.TOTPreferences;
import com.tsg.tot.task.TaskDetailActivity;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> implements View.OnClickListener {
    List<MessageRemote> messageList;
    MessageRemote message;
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
        private final TextView messageNameSubject;
        private final TextView messageDescription;
        private final LinearLayout adapterMessages;


        ViewHolder(View itemView) {
            super(itemView);
            messageNameSubject = itemView.findViewById(R.id.messageNameSubject);
            messageDescription  = itemView.findViewById(R.id.messageDescription);
            adapterMessages = itemView.findViewById(R.id.adapterMessages);


        }
    }

    public MessagesAdapter (){
        message = new MessageRemote();
    }

    public void dataSet(List<MessageRemote> messageList, int size, Context context, MainMVP.Presenter presenter) {
        this.messageList = messageList;
        this.size = size;
        this.context = context;
        this.presenter = presenter;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_messages, parent, false);
        v.setOnClickListener(this);
        return new MessagesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.ViewHolder holder, int position) {


        if (position % 2 == 0) {
            holder.adapterMessages.setBackgroundColor(Color.parseColor("#FFF1EF"));
        } else {
            holder.adapterMessages.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        holder.messageDescription.setText(messageList.get(position).getMensajes());
        holder.messageNameSubject.setText(messageList.get(position).getMateriaTitulo());


/*        Integer idEstudiante = taskList.get(position).getEstudiante();
        Integer idMateria  = taskList.get(position).getId();


        DatabaseRepository dbR = new DatabaseRepository();

        List <Task> pendingTasks  =  dbR.getPendingTasks (context,taskList.get(position));

        if (pendingTasks.size()!=0){

        }else{
            //holder.statusTaskImage.setImageResource(R.drawable.x_icon);
            holder.statusTaskImage.setImageResource(R.drawable.warning_icon);
            countTareasPendientes++;

        }

        if (position == taskList.size()-1){
            TOTPreferences.getInstance(context).setTareaspendientes(countTareasPendientes);
        }*/


        holder.itemView.setOnClickListener(view -> {

            Intent intent  = new  Intent (view.getContext(), MessageDetailActivity.class);

            intent.putExtra("idMensajeKiosco",messageList.get(position).getMensajeKioscoId());
            intent.putExtra("idEstudiante",messageList.get(position).getIdEstudiante());
            intent.putExtra("idMateria",messageList.get(position).getIdMateria());
            intent.putExtra("materiaTitulo",messageList.get(position).getMateriaTitulo());
            intent.putExtra("descripcionMensaje", messageList.get(position).getMensajes());
            intent.putExtra("idUsuario",TOTPreferences.getInstance(context).getIdUsuario());
            TOTPreferences.getInstance(context).setIdMensajeKiosco(messageList.get(position).getId());

            context.startActivity(intent);
            notifyDataSetChanged();


        });



    }

    @Override
    public int getItemCount() {
     return size;
    }







}
