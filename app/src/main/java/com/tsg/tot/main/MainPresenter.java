package com.tsg.tot.main;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.tsg.tot.data.model.Subjects;

import java.util.List;

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
    public void checkVersions(Context context) {
        Log.d("checkVersions", "checkVersions");
        if (view != null) {
            new Thread(() -> {
                Log.d("checkVersions", "Thread");
                //Thread for checking versions
                while (true) {

                    //Set version No.
                    float apiVersion = model.checkAPIVersion(context);
                    float dbVersion = model.checkDbVersion(context);

                    //Waiting time between queries
                    try {
                        Thread.sleep(15 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (apiVersion == dbVersion || apiVersion == 0) {
                        Log.d("checkVersions", "Same version");
                    } else {
                        Log.d("checkVersions", "Diferent version");
                        model.updateAllDb(model.checkAPIVersion(context),
                                model.checkTasks(context, API_REPOSITORY),
                                model.checkUploads(context, API_REPOSITORY),
                                model.checkTeachers(context, API_REPOSITORY),
                                model.checkSubjects(context, API_REPOSITORY),
                                model.checkGrades(context, API_REPOSITORY),
                                model.checkStudyMaterials(context, API_REPOSITORY),
                                model.checkEvaluations(context, API_REPOSITORY),
                                model.checkStudents(context, API_REPOSITORY),
                                model.checkSubmissions(context, API_REPOSITORY),
                                model.checkExercises(context, API_REPOSITORY),
                                model.checkLessons(context, API_REPOSITORY),
                                context);

                        try {
                            Thread.sleep(15 * 1000);
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
    public List<Subjects> getSubjects(Context context) {
        return model.checkSubjects(context, DATABASE_REPOSITORY);
    }

    @Override
    public void setInfoStudent(Context context) {
        if (view != null) {
            view.setInfoStudent(model.checkStudents(context, DATABASE_REPOSITORY));
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
        }
    }

    @Override
    public void testPOST(Context context) {
        model.postSubmissions("", "3", "224", "2", "269", "1");
    }
}
