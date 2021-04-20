/*
 * Copyright (c) 2021.
 */

package com.tsg.kot.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tsg.kot.R;
import com.tsg.kot.data.model.MessageAnswer;
import com.tsg.kot.main.mainmvp.MainMVP;

import java.util.List;

public class MessagesAnswerAdapter extends RecyclerView.Adapter<MessagesAnswerAdapter.ViewHolder> implements View.OnClickListener {
    List<MessageAnswer> messageAnswerList;
    MessageAnswer messageAnswer;
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
        //private final ImageView statusMessageAnswerImage;
        private final TextView messageAnswerName;
        private final TextView messageAnswerEstado;
        private final LinearLayout adapterMessagesAnswers;


        ViewHolder(View itemView) {
            super(itemView);
          //  statusMessageAnswerImage = itemView.findViewById(R.id.statusMessageAnswerImage);
            messageAnswerName = itemView.findViewById(R.id.messageAnswerName);
            messageAnswerEstado  = itemView.findViewById(R.id.messageAnswerEstado);
            adapterMessagesAnswers = itemView.findViewById(R.id.adapterMessagesAnswers);
           // attachFileMessages =  itemView.findViewById(R.id.bt_attachFileMessages);


        }
    }

    public MessagesAnswerAdapter(){

        messageAnswer = new MessageAnswer();
    }

    public void dataSet(List<MessageAnswer> messageAnswerList, int size, Context context, MainMVP.Presenter presenter) {
        this.messageAnswerList = messageAnswerList;
        this.size = size;
        this.context = context;
        this.presenter = presenter;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MessagesAnswerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_answer_messages, parent, false);
        v.setOnClickListener(this);
        return new MessagesAnswerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAnswerAdapter.ViewHolder holder, int position) {


        if (position % 2 == 0) {
            holder.adapterMessagesAnswers.setBackgroundColor(Color.parseColor("#FFF1EF"));
        } else {
            holder.adapterMessagesAnswers.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        holder.messageAnswerName.setText(messageAnswerList.get(position).getBody());
        holder.messageAnswerEstado.setText(messageAnswerList.get(position).getEstadoDescripcion());


   /*       holder.itemView.setOnClickListener(view -> {

          //Toast.makeText(context,"click en material: "+taskList.get(position).getNombre(),Toast.LENGTH_SHORT).show();
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

            context.startActivity(intent);

        });*/






    }

    @Override
    public int getItemCount() {
     return size;
    }







}
