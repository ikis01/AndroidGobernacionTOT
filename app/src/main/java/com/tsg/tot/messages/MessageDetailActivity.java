/*
 * Copyright (c) 2021.
 */

package com.tsg.tot.messages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.tsg.tot.R;
import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.data.model.Student;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.data.model.Submissions;
import com.tsg.tot.data.remote.model.MessageRemote;
import com.tsg.tot.main.fragment.InformationMessageFragment;
import com.tsg.tot.main.fragment.ListMessageAnswerFragment;
import com.tsg.tot.main.fragment.ListMessageFilesFragment;
import com.tsg.tot.main.mainmvp.MainMVP;
import com.tsg.tot.repository.DatabaseRepository;
import com.tsg.tot.root.App;
import com.tsg.tot.storage.TOTPreferences;
import com.tsg.tot.task.TaskDetailActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class MessageDetailActivity extends AppCompatActivity
implements MainMVP.View,ListMessageFilesFragment.OnFragmentInteractionListener,View.OnClickListener{

    @Inject
    MainMVP.Presenter presenter;
    FragmentTransaction fragmentTransaction;
    ListMessageFilesFragment listMessageFilesFragment;
    ListMessageAnswerFragment listMessageAnswerFragment ;
    LinearLayout mainLayout;
    public Button btn_volver;
    String token, idUsuario ,descripcionMensaje,materiaTitulo,idMensajeKiosco= "";
    String nombreEstudiante = "";
    public TextView tv_studentCode, tv_studentNameMessageDetail, tv_institutionName,
            tv_location, tv_messageDescription,tv_materia_title_message;

    Button bt_subir_respuestas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ((App) getApplication()).getComponent().injectMessageDetailActivity(this);
         super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_message_detail);
        initViewElements();
        btn_volver = findViewById(R.id.btn_volver_message_detail);
        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_subir_respuestas = findViewById(R.id.bt_subir_respuestas);
        bt_subir_respuestas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadAnswer();
            }
        });

        if (getIntent().getExtras() != null) {
            token = getIntent().getExtras().getString("token");
            idUsuario = getIntent().getExtras().getString("idUsuario");
            descripcionMensaje = getIntent().getExtras().getString("descripcionMensaje");
            materiaTitulo = getIntent().getExtras().getString("materiaTitulo");


        }

        DatabaseRepository dbR = new DatabaseRepository();

        List<Student> studentList = dbR.getStudent(MessageDetailActivity.this, Integer.parseInt(idUsuario==null?"0":idUsuario.equals("")?"0":idUsuario));
        this.setInfoStudent(studentList);


        listMessageFilesFragment = new ListMessageFilesFragment(presenter);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentMaterialMensajeList, listMessageFilesFragment);
        fragmentTransaction.commit();

        listMessageAnswerFragment = new ListMessageAnswerFragment(presenter);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentRespuestasList,listMessageAnswerFragment);
        fragmentTransaction.commit();

    }


    private void initViewElements() {
        mainLayout = findViewById(R.id.mainLayout);
        tv_studentNameMessageDetail = findViewById(R.id.tv_studentNameMessageDetail);
        tv_messageDescription = findViewById(R.id.tv_messageDescription);
        tv_studentCode = findViewById(R.id.tv_studentCode);
        tv_materia_title_message= findViewById(R.id.tv_materia_title_message);

    }

    @Override
    public void setInfoStudent(List<Student> studentList) {
        if (studentList.size() > 0) {
            String code = "Código: "
                    + studentList.get(0).getId().toString();
            // tv_studentCode.setText(code);
            tv_studentNameMessageDetail.setText(studentList.get(0).getNombres() + " " + studentList.get(0).getApellidos());
            tv_messageDescription.setText(descripcionMensaje);
            tv_materia_title_message.setText(materiaTitulo);
        }

        //  tv_pendingTask.setText(getResources().getString(R.string.main_view_PendingTasks) + ": ");
    }

    @Override
    public void setInfoSubject(Subjects subjects) {

    }

    @Override
    public void notifyRefresh() {

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void setFileKiosco(FilesKiosco fileKiosco) {

    }

    @Override
    public void setMessages(List<MessageRemote> messagesList) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void uploadAnswer (){
        final EditText input = new EditText(this);
        AlertDialog.Builder dialogoConfirmarSubida = new AlertDialog.Builder(MessageDetailActivity.this);
        dialogoConfirmarSubida.setTitle(descripcionMensaje);
        dialogoConfirmarSubida.setMessage( "Responda : " );
        dialogoConfirmarSubida.setView(input);
        dialogoConfirmarSubida.setCancelable(false);
        dialogoConfirmarSubida.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                Integer idMensajeKiosco = TOTPreferences.getInstance(MessageDetailActivity.this).getIdMensajeKiosco();
                DatabaseRepository dbR = new DatabaseRepository();
                dbR.updateAnswerMessage(String.valueOf(input.getText()),idMensajeKiosco,MessageDetailActivity.this);
                Log.d("texto en input de dialogo " , String.valueOf(input.getText()));

/*                List<Submissions> submissionsList = new ArrayList<>();


                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                submissions.setUpp(0);
                submissions.setIdEstudiante(idEstudiante);
                submissions.setIdSubida(idSubida);
                submissions.setIdTarea(idTarea);
                submissions.setRtEntrega(registroTarea);
                submissions.setCreado(date);
                String fileAppend = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()).concat("_");
                submissionsList.add(submissions);
                Long idSubmission = dbR.updateSubmissions(submissionsList,TaskDetailActivity.this);

                /// Archivo Destino
                File dstFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Data/Tareas/"+nombreTarea+"/"+idEstudiante+"_"+ fileAppend+file.getName());
                ///Archivo Origen
                File srcFile = new File(srcName);*/
/*                try {
                    copy(srcFile,dstFile);

                    List<FilesKiosco> filesKioscoList = new ArrayList<>();
                    FilesKiosco filesKiosco = new FilesKiosco();
                    filesKiosco.setArchivoKiosco(0);
                    filesKiosco.setCodigo("0");
                    filesKiosco.setRuta(dstFile.getAbsolutePath());
                    filesKiosco.setSubida_idsubida(idSubida);
                    filesKiosco.setIdEntrega(idSubmission.intValue());
                    filesKiosco.setNombreArchivo(file.getName());
                    filesKioscoList.add(filesKiosco);
                    dbR.updateMyFileKiosco(filesKioscoList,TaskDetailActivity.this);
                    finish();
                    startActivity(getIntent());

                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        });
        dialogoConfirmarSubida.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar();
            }
        });
        dialogoConfirmarSubida.show();

    }

    public void cancelar() {

    }
}
