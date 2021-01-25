package com.tsg.tot.main.mainmvp;

import android.content.Context;
import android.media.session.MediaSession;
import android.util.Log;

import androidx.annotation.Nullable;

import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.data.model.TokenCustom;
import com.tsg.tot.data.remote.model.GradeRemote;
import com.tsg.tot.data.remote.model.StudentRemote;
import com.tsg.tot.data.remote.model.SubjectsRemote;
import com.tsg.tot.data.remote.model.TaskRemote;
import com.tsg.tot.data.remote.model.TeacherRemote;
import com.tsg.tot.main.fragment.CustomProgressDialog;

import java.util.ArrayList;
import java.util.List;

import kotlin.reflect.TypeOfKt;

import static com.tsg.tot.sqlite.DBConstants.API_REPOSITORY;
import static com.tsg.tot.sqlite.DBConstants.DATABASE_REPOSITORY;

public class MainPresenter implements MainMVP.Presenter, MainMVP.Model.OnFinishedListener {

    @Nullable
    private MainMVP.View view;
    private MainMVP.Model model;

    public MainPresenter(MainMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(MainMVP.View view) {
        this.view = view;
    }

    @Override
    public void createDB(Context context) {
        model.createDb(context);
    }


    @Override
    public void checkVersions(Context context, CustomProgressDialog dialog, String token, String idUsuario) {

        Log.d("checkVersions", "checkVersions");
        if (view != null) {
            new Thread(() -> {
                Log.d("checkVersions", "Thread");
                //Thread for checking versions
                while (true) {
                    //Set version No.
                    float apiVersion = model.checkAPIVersion(context);
                    float dbVersion = model.checkDbVersion(context);

                    if (dbVersion == apiVersion) {
                        dismissLoadingDialog();
                    }

                    //Waiting time between queries
                    try {
                        Thread.sleep(15 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (apiVersion == dbVersion || apiVersion == 0) {
                        Log.d("checkVersions", "Same version");
                        /// en caso de tener la misma version cerrar dialog o no conectarse al kiosco imc
                        dialog.dismiss();
                    } else {
                        Log.d("checkVersions", "Diferent version");



                        model.updateDatabase(idUsuario, context,
                                model.checkAPIVersion(context),
                                model.checkMyGrade(context, API_REPOSITORY, token),
                                model.checkMyStudent(context, API_REPOSITORY, token),
                                model.checkMyTeachers(context, API_REPOSITORY, token),
                                model.checkMySubjects(context, API_REPOSITORY, token),
                                model.checkMyTasks(context, API_REPOSITORY,token),
                                model.checkMyPendingStudyMaterials(context, API_REPOSITORY,token),
                                model.checkMyLessons(context,API_REPOSITORY,token));


                        /*
                         // model.checkMyGrade(context,API_REPOSITORY, token);inserto
                         model.checkMyStudent(context,API_REPOSITORY,token);inserto
                         model.checkTeachers(context, API_REPOSITORY,token); inserto
                         model.checkMySubjects(context,API_REPOSITORY,token); inserto

                        model.checkTasks(context, API_REPOSITORY,token);/// inserto correcto



                        model.checkMyPendingStudyMaterials(context,API_REPOSITORY, token);

                        model.checkMyLessons(context,API_REPOSITORY,token);


                        model.updateAllDb(model.checkAPIVersion(context),
                                model.checkTasks(context, API_REPOSITORY,token),//ok
                                model.checkUploads(context, API_REPOSITORY),/// no aply
                                model.checkTeachers(context, API_REPOSITORY,token),//ok
                                model.checkSubjects(context, API_REPOSITORY,token),//ok
                                model.checkGrades(context, API_REPOSITORY),//ok
                                model.checkStudyMaterials(context, API_REPOSITORY),//ok pending
                                model.checkEvaluations(context, API_REPOSITORY),// no aplica
                                model.checkStudents(context, API_REPOSITORY),// solo info del estudiante registrado
                                model.checkSubmissions(context, API_REPOSITORY),// por el momento no aplica
                                model.checkExercises(context, API_REPOSITORY),// No aplica ya no existe
                                model.checkLessons(context, API_REPOSITORY),// Probado y obtenido
                                context,dialog);
*/
                        try {
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
    public List<Task> getTaskSubject(Context context, int idSubject, String token) {
        List<Task> taskList = model.checkTasks(context, DATABASE_REPOSITORY, token);
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

    @Override
    public void testPOST(Context context) {
        model.postSubmissions("", "3", "224", "2", "269", "1");
    }

    private void dismissLoadingDialog() {
        if (view != null) {
            view.dismissLoadingDialog();
        }
    }

    public void updateEverything(Context context, CustomProgressDialog dialog, String token) {

        if (view != null) {
            new Thread(() -> {
                Log.d("checkVersions", "Thread");
                //Thread for checking versions

                Log.d("checkVersions", "Diferent version");
                model.updateAllDb(model.checkAPIVersion(context),
                        model.checkTasks(context, API_REPOSITORY, token),
                        model.checkUploads(context, API_REPOSITORY),
                        model.checkTeachers(context, API_REPOSITORY, token),
                        model.checkSubjects(context, API_REPOSITORY, token,0),
                        model.checkGrades(context, API_REPOSITORY),
                        model.checkStudyMaterials(context, API_REPOSITORY),
                        model.checkEvaluations(context, API_REPOSITORY),
                        model.checkStudents(context, API_REPOSITORY,1),
                        model.checkSubmissions(context, API_REPOSITORY),
                        model.checkExercises(context, API_REPOSITORY),
                        model.checkLessons(context, API_REPOSITORY),
                        context, dialog);

                try {
                    Thread.sleep(15 * 1000);
                    dialog.setProgress(dialog.getProgress() + 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.notifyRefresh();


            }).start();
        }
    }
    // @Override
    // public void uploadFileTest (Context context){
    //     model.postBlob("679049","archvioEnTexto","897","12");
    // }
}
