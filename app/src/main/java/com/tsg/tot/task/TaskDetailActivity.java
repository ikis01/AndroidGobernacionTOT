package com.tsg.tot.task;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.tsg.tot.R;
import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.data.model.Student;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.data.model.Submissions;
import com.tsg.tot.main.fragment.ListFileKioscoFragment;
import com.tsg.tot.main.fragment.ListSubmissionDisplayFragment;
import com.tsg.tot.repository.DatabaseRepository;
import com.tsg.tot.root.App;
import com.tsg.tot.task.taskmvp.TaskMVP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import abhishekti7.unicorn.filepicker.UnicornFilePicker;
import abhishekti7.unicorn.filepicker.utils.Constants;

public class TaskDetailActivity extends AppCompatActivity
        implements TaskMVP.View, ListFileKioscoFragment.OnFragmentInteractionListener,View.OnClickListener {

    @Inject
    TaskMVP.Presenter presenter;
    Integer idMateria,tareakiosco ,idEstudiante ,idSubida,idTarea,registroTarea;
    Submissions submissions = new Submissions();
    FragmentTransaction fragmentTransaction;

    ListFileKioscoFragment listFileKioscoFragment;
    ListSubmissionDisplayFragment listSubmissionDisplayFragment;

    TextView tv_studentCode, tv_studentName, tv_institutionName,
            tv_location, tv_pendingTask , tv_taskName;

    Button btn_volver;
    Button btn_subir_archivo;
    String nombreTarea = "";


    @Override
    protected void onResume(){

        super.onResume();
        presenter.setView(this);

       // if (presenter!=null){}
       // presenter.setView((TaskMVP.View) this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String studentName = getIntent().getExtras().getString("student_name");
        String taskName = getIntent().getExtras().getString("task_name");

        idMateria = getIntent().getExtras().getInt("idMateria");
        tareakiosco = getIntent().getExtras().getInt("tareakiosco");
        idEstudiante = getIntent().getExtras().getInt("idEstudiante");
        nombreTarea = getIntent().getExtras().getString("nombreTarea");
        idSubida =  getIntent().getExtras().getInt("idSubida");
        idTarea =  getIntent().getExtras().getInt("idTarea");
        registroTarea =getIntent().getExtras().getInt("registroTarea");

        ((App) getApplication()).getComponent().injectTask(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        btn_volver = findViewById(R.id.btn_volver);
        btn_subir_archivo = findViewById(R.id.bt_subir_archivos);

        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_studentName = findViewById(R.id.tv_studentName);
        tv_studentName.setText(studentName);

        tv_taskName = findViewById(R.id.tv_taskName);
        tv_taskName.setText(taskName);


        //Init Fragment
        listFileKioscoFragment = new ListFileKioscoFragment(presenter,idMateria,tareakiosco ,idEstudiante);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentMaterialTareaList, listFileKioscoFragment);
        fragmentTransaction.commit();

        listSubmissionDisplayFragment = new ListSubmissionDisplayFragment(presenter,idMateria,idTarea,idEstudiante);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentEntregasList,listSubmissionDisplayFragment);
        fragmentTransaction.commit();

        btn_subir_archivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile(nombreTarea);
            }
        });

       /// Toast.makeText(this, " entrando a tarea Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void notifyRefresh() {

    }

    @Override
    public void setInfoStudent(List<Student> studentList) {

    }

    @Override
    public void setInfoSubject(Subjects subject) {

    }

    @Override
    public void notifyRefreh() {

    }

    @Override
    public void setFileKiosco(FilesKiosco fileKiosco) {

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constants.REQ_UNICORN_FILE && resultCode == RESULT_OK){
            if(data!=null){
                ArrayList<String> files = data.getStringArrayListExtra("filePaths");
                for(String fileS : files){

                    File file = new File (fileS);

                    String srcName = file.getAbsolutePath();

                    Log.d("File Name", srcName);
                    // then actually do something in another module

                    AlertDialog.Builder dialogoConfirmarSubida = new AlertDialog.Builder(TaskDetailActivity.this);
                    dialogoConfirmarSubida.setTitle("Archivo");
                    dialogoConfirmarSubida.setMessage("¿ Desea Guardar el Archivo "+ file.getName() + " ?" );
                    dialogoConfirmarSubida.setCancelable(false);
                    dialogoConfirmarSubida.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {


                            List<Submissions> submissionsList = new ArrayList<>();

                            DatabaseRepository dbR = new DatabaseRepository();
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
                            File srcFile = new File(srcName);
                            try {
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
                            }
                        }
                    });
                    dialogoConfirmarSubida.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            cancelar();
                        }
                    });
                    dialogoConfirmarSubida.show();



                }



        }
            }
        }


    private void uploadFile(String nombreTarea){
        TaskDetailActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        UnicornFilePicker.from(TaskDetailActivity.this)
                .addConfigBuilder()
                .selectMultipleFiles(false)
               // .showOnlyDirectory(true)
                .setRootDirectory(Environment.getExternalStorageDirectory().getAbsolutePath())
                .showHiddenFiles(false)
               // .setFilters(new String[]{"pdf", "png", "jpg", "jpeg"})
                .addItemDivider(true)
                .theme(R.style.UnicornFilePicker_Default)
                .build()
                .forResult(Constants.REQ_UNICORN_FILE);

/*        FileChooser fileChooser = new FileChooser(TaskDetailActivity.this);
        fileChooser.setFileListener(new FileChooser.FileSelectedListener() {
            @Override
            public void fileSelected(final File file) {
                // ....do something with the file
                String srcName = file.getAbsolutePath();

                Log.d("File Name", srcName);
                // then actually do something in another module

                AlertDialog.Builder dialogoConfirmarSubida = new AlertDialog.Builder(TaskDetailActivity.this);
                dialogoConfirmarSubida.setTitle("Archivo");
                dialogoConfirmarSubida.setMessage("¿ Desea Guardar el Archivo "+ file.getName() + " ?" );
                dialogoConfirmarSubida.setCancelable(false);
                dialogoConfirmarSubida.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {


                        List<Submissions> submissionsList = new ArrayList<>();

                        DatabaseRepository dbR = new DatabaseRepository();
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
                        File srcFile = new File(srcName);
                        try {
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
                        }
                    }
                });
                dialogoConfirmarSubida.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        cancelar();
                    }
                });
                dialogoConfirmarSubida.show();



            }
        });

        fileChooser.showDialog();*/
    }

    public void aceptar() {
        Toast t=Toast.makeText(this,"Bienvenido a probar el programa.", Toast.LENGTH_SHORT);
        t.show();
    }

    public void cancelar() {

    }

    public static void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }
}