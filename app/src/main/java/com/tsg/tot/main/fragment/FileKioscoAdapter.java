package com.tsg.tot.main.fragment;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsg.tot.R;
import com.tsg.tot.main.fragment.dummy.DummyContent.DummyItem;
import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.main.mainmvp.MainMVP;
import com.tsg.tot.task.taskmvp.TaskMVP;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FileKioscoAdapter extends RecyclerView.Adapter<FileKioscoAdapter.ViewHolder> {


    List<FilesKiosco>  filesKioscoList;
    FilesKiosco filesKiosco;
    int size= 0;
    Context context ;
    TaskMVP.Presenter presenter;

    static class ViewHolder extends RecyclerView.ViewHolder{
        private  TextView nameFile ;
        private  TextView statusFile;

        ViewHolder(View itemView){
            super(itemView);
            nameFile = itemView.findViewById(R.id.tv_file_name_kiosco);
            statusFile = itemView.findViewById(R.id.tv_file_estatus_kisoco);

        }
    }
    public FileKioscoAdapter() {
        filesKiosco =  new FilesKiosco();
    }

    public void dataSet (List<FilesKiosco> filesKioscoList, int size, Context context, TaskMVP.Presenter presenter){
        this.filesKioscoList = filesKioscoList;
        this.size = size;
        this.context = context;
        this.presenter = presenter;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public FileKioscoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_filekiosco, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {


        holder.nameFile.setText(filesKioscoList.get(position).getNombreArchivo());
        holder.statusFile.setText("ARCHIVO TAREA");

        holder.itemView.setOnClickListener(view -> {
            FilesKiosco filesKiosco = new FilesKiosco(
                    filesKioscoList.get(position).getIdArchivosTOT(),
                    filesKioscoList.get(position).getArchivoKiosco(),
                    filesKioscoList.get(position).getCodigo(),
                    filesKioscoList.get(position).getRuta(),
                    filesKioscoList.get(position).getIdEntrega(),
                    filesKioscoList.get(position).getSubida_idsubida(),
                    filesKioscoList.get(position).getNombreArchivo());

            presenter.setFileKiosco(filesKiosco);
            notifyDataSetChanged();

        });
    }

    @Override
    public int getItemCount() {
        return size;
    }


}
