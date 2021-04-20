package com.tsg.kot.main.mainmvp;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.tsg.kot.data.model.FilesKiosco;
import com.tsg.kot.data.model.MessageAnswer;
import com.tsg.kot.data.model.Subjects;
import com.tsg.kot.data.model.Task;
import com.tsg.kot.data.remote.model.FileMessageRemote;
import com.tsg.kot.data.remote.model.MessageRemote;
import com.tsg.kot.main.fragment.CustomProgressDialog;

import java.util.ArrayList;
import java.util.List;

import static com.tsg.kot.sqlite.DBConstants.API_REPOSITORY;
import static com.tsg.kot.sqlite.DBConstants.DATABASE_REPOSITORY;

public class MainPresenter implements MainMVP.Presenter, MainMVP.Model.OnFinishedListener {

    @Nullable
    private MainMVP.View view;
    private final MainMVP.Model model;

    public MainPresenter(MainMVP.Model model) {
        this.model = model;
    }

    volatile boolean ejecutar = true ;

    @Override
    public void setView(MainMVP.View view) {
        this.view = view;
    }

    @Override
    public void createDB(Context context) {
        model.createDb(context);
    }

    @Override
    public void checkMessages(Context context, String token) {
        Log.d("checkMessages", "Initialize Check Messages ");
        if (view != null) {

            new Thread(() -> {
                try {
                    //Thread.sleep(25 *1000);
                    Thread.sleep(25 *1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("checkVersions", "Thread Checking Messages ");
                //this.notifyRefresh();
                //Thread for checking versions
                while (true) {

                    model.updateMessages(context, token, model.checkMyStudent(context, API_REPOSITORY, token), model.checkMyPendingMessages(context, API_REPOSITORY, token));


                    //Waiting time between queries
                    try {
                        //Thread.sleep(25 *1000);
                        Thread.sleep(20 *1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }




                        //this.notifyRefresh();

                    }
            }).start();
        }
    }




    @Override
    public void checkVersionsSync(Context context, CustomProgressDialog dialog, String token, String idUsuario) {

        Log.d("checkVersions", "checkVersions");
        if (view != null) {
            new Thread(() -> {
                Log.d("checkVersions", "Thread Sincronizacion");
                //this.notifyRefresh();
                //Thread for checking versions
                while (ejecutar) {
                    //Set version No.
                    float apiVersion = model.checkAPIVersion(context);
                    float dbVersion = model.checkDbVersion(context);

                    if (dbVersion == apiVersion) {
                        dismissLoadingDialog();
                    }

                    //Waiting time between queries
                     try {
                        //Thread.sleep(25 *1000);
                       Thread.sleep(10 *1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (apiVersion == dbVersion || apiVersion == 0) {
                        Log.d("checkVersions", "Same version");
                        /// en caso de tener la misma version cerrar dialog o no conectarse al kiosco imc
                        //dialog.dismiss();
                        //dismissLoadingDialog();
                        //return;
                    } else {
                        Log.d("checkVersions", "Diferent version");


                        Boolean terminado = model.updateDatabase(idUsuario, context,
                                model.checkAPIVersion(context),
                                model.checkMyGrade(context, API_REPOSITORY, token),
                                model.checkMyStudent(context, API_REPOSITORY, token),
                                model.checkMyTeachers(context, API_REPOSITORY, token),
                                model.checkMySubjects(context, API_REPOSITORY, token),
                                model.checkMyTasks(context, API_REPOSITORY,token),
                                model.checkMyPendingStudyMaterials(context, API_REPOSITORY,token),
                                model.checkMyLessons(context,API_REPOSITORY,token),
                                model.checkMyPendingMessages(context,API_REPOSITORY,token),
                                token,dialog);
                        if (terminado){
                            ejecutar = false ;
                            dismissLoadingDialog();
                            this.notifyRefresh();
                            if (Thread.currentThread().isAlive()) {
                                Thread.currentThread().interrupt();
                            }
                        }

                        try {
                            //Thread.sleep(10 * 1000);
                            Thread.sleep(25 * 1000);
                            //dialog.setIcon(dialog.getProgress() + 10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //this.notifyRefresh();

                    }
                }
            }).start();
        }
    }



    @Override
    public void checkVersions(Context context, CustomProgressDialog dialog, String token, String idUsuario) {

        Log.d("checkVersions", "checkVersions");
        if (view != null) {

            new Thread(() -> {
                Log.d("checkVersions", "Thread");
                  //Thread for checking versions
                while (ejecutar) {
                    //Set version No.
                    float apiVersion = model.checkAPIVersion(context);
                    float dbVersion = model.checkDbVersion(context);

                    if (dbVersion == apiVersion) {
                        dismissLoadingDialog();
                    }

                    //Waiting time between queries
                    try {
                        //Thread.sleep(25 *1000);
                        Thread.sleep(15 *1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (apiVersion == dbVersion || apiVersion == 0) {
                        Log.d("checkVersions", "Same version");
                        /// en caso de tener la misma version cerrar dialog o no conectarse al kiosco imc
                        //dialog.dismiss();
                        dismissLoadingDialog();
                        //return;
                    } else {
                        Log.d("checkVersions", "Diferent version");


                        Boolean terminado = model.updateDatabase(idUsuario, context,
                                model.checkAPIVersion(context),
                                model.checkMyGrade(context, API_REPOSITORY, token),
                                model.checkMyStudent(context, API_REPOSITORY, token),
                                model.checkMyTeachers(context, API_REPOSITORY, token),
                                model.checkMySubjects(context, API_REPOSITORY, token),
                                model.checkMyTasks(context, API_REPOSITORY,token),
                                model.checkMyPendingStudyMaterials(context, API_REPOSITORY,token),
                                model.checkMyLessons(context,API_REPOSITORY,token),
                                model.checkMyPendingMessages(context,API_REPOSITORY,token),
                                token,dialog);
                           if (terminado){
                               ejecutar = false ;
                           }

                        try {
                            //Thread.sleep(10 * 1000);
                            Thread.sleep(25 * 1000);
                           // dialog.setIcon(dialog.getProgress() + 10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        this.notifyRefresh();

                    }
                }
            }).start();
        }
    }

    @Override
    public List<Subjects> getSubjects(Context context, String token,Integer code) {
        return model.checkSubjects(context, DATABASE_REPOSITORY, token,code);
    }

    @Override
    public List<FilesKiosco> getFileKioscos(Context context, Integer idEstudiante, Integer idMateria,Integer idTarea) {
        return model.checkMyFileskioscos ( context,  idEstudiante,  idMateria, idTarea);
    }

    @Override
    public List<Task> getTaskSubject(Context context, int idSubject, String token,Integer idEstudiante) {
        List<Task> taskList = model.checkTasks(context, DATABASE_REPOSITORY, token,idEstudiante);
        List<Task> taskListFinal = new ArrayList<>();

        if (taskList != null) {
            for (Task task : taskList) {
                try {
                    if (task.getMaterias() == idSubject) {
                        taskListFinal.add(task);
                    }
                } catch (NullPointerException e) {
                    Log.d("Debug NullPointerException ", e.toString());
                }
            }
        }

        return taskListFinal;
    }

    @Override
    public List<Task> getAllPendingTaskSubject(Context context, Integer idEstudiante) {
        return model.getAllMyPendingTasks(context,idEstudiante);
    }

    @Override
    public void setInfoStudent(Context context,Integer idUsuario) {
        if (view != null) {
            view.setInfoStudent(model.checkStudents(context, DATABASE_REPOSITORY,idUsuario));
        }
    }

    @Override
    public void setInfoSubject(Subjects subjects) {
        if (view != null) {
            view.setInfoSubject(subjects);
        }
    }

    @Override
    public void setFileKiosco(FilesKiosco fileKiosco) {
        if (view != null) {
            view.setFileKiosco(fileKiosco);
        }
    }


    @Override
    public void onFailure(Throwable t) {
        Log.d("onFailure", t.toString());
    }

    @Override
    public void notifyRefresh() {
        if (view != null) {
            view.notifyRefresh();
            dismissLoadingDialog();
        }
    }


    private void dismissLoadingDialog() {
        if (view != null) {
            view.dismissLoadingDialog();
        }
    }

    public void updateEverything(Context context, CustomProgressDialog dialog, String token,Integer idUsuario) {

        if (view != null) {
            new Thread(() -> {
                Log.d("checkVersions", "Thread");
                //Thread for checking versions

                Log.d("checkVersions", "Diferent version");
                model.updateAllDb(model.checkAPIVersion(context),
                        model.checkTasks(context, DATABASE_REPOSITORY, token,0),
                        model.checkUploads(context, DATABASE_REPOSITORY),
                        model.checkTeachers(context, DATABASE_REPOSITORY, token),
                        model.checkSubjects(context, DATABASE_REPOSITORY, token,0),
                        model.checkGrades(context, DATABASE_REPOSITORY),
                        model.checkStudyMaterials(context, DATABASE_REPOSITORY),
                        model.checkEvaluations(context, DATABASE_REPOSITORY),
                        model.checkStudents(context, DATABASE_REPOSITORY,1),
                        model.checkSubmissions(context, DATABASE_REPOSITORY),
                        model.checkExercises(context, DATABASE_REPOSITORY),
                        model.checkLessons(context, DATABASE_REPOSITORY),
                        context, dialog);

                try {
                    Thread.sleep(20 * 1000);
                    dialog.setProgress(dialog.getProgress() + 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.notifyRefresh();


            }).start();
        }
    }

    @Override
    public List<MessageRemote> getMessagesStudent(Context context, Integer idEstudiante) {
        return model.getMyMessages(context,idEstudiante);
    }

    @Override
    public List<FileMessageRemote> getFilesMessage (Context context, Integer idMensajeKiosco){
        return model.getMyFileMessage (context,idMensajeKiosco);
    }

    @Override
    public List<MessageAnswer> getMessageAnswer (Context context,Integer idMensajeKiosco){
        return model.getMyMessagesAnswers(context,idMensajeKiosco);
    }

}
