package com.tsg.tot.main.fragment;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.tsg.tot.R;
import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.main.mainmvp.MainMVP;
import com.tsg.tot.task.TaskDetailActivity;
import com.tsg.tot.task.taskmvp.TaskMVP;

import java.io.File;
import java.util.List;

import static androidx.core.content.FileProvider.getUriForFile;

/**
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
        private RelativeLayout adapterFileKiosco;

        ViewHolder(View itemView){
            super(itemView);
            nameFile = itemView.findViewById(R.id.tv_file_name_kiosco);
            statusFile = itemView.findViewById(R.id.tv_file_estatus_kisoco);
            adapterFileKiosco = itemView.findViewById(R.id.adapterFileKiosco);
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

        if (position % 2 == 0) {
            holder.adapterFileKiosco.setBackgroundColor(Color.parseColor("#FFF1EF"));
        } else {
            holder.adapterFileKiosco.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

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

//            TableRow tablerow = (TableRow) view;
//            TextView sample = (TextView) tablerow.getChildAt(1);
            String result = "archivo";//sample.getText().toString();

            File newFile = new File(filesKioscoList.get(position).getRuta());
            //File filePaths = new File(context.getFilesDir().toString());
            //File newFile = new File(filePaths, result);
            Uri contentUri = getUriForFile(context.getApplicationContext(), "com.mydomain.fileprovider", newFile);
            openFile(contentUri);

        });
    }

    @Override
    public int getItemCount() {
        return size;
    }

    private void openFile(Uri uri){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
