package com.tsg.tot.main;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

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
        MainMVP.Model.OnFinishedListener onFinishedListener = this;
        Log.d("Debug", "checkVersions");
        if (view != null) {
            new Thread(() -> {
                Log.d("Debug", "Thread");
                //Thread for checking versions
                while (model.checkAPIVersion(onFinishedListener, context) == model.checkDbVersion(onFinishedListener, context)) {
                    Log.d("Debug", "Same version");
                }

                Log.d("Debug", "Diferent version");
                model.updateAllDb(model.checkAPIVersion(onFinishedListener, context),
                        model.checkTasks(onFinishedListener, context),
                        model.checkUploads(onFinishedListener, context),
                        model.checkTeachers(onFinishedListener, context),
                        model.checkSubjects(onFinishedListener, context),
                        model.checkStudyMaterials(onFinishedListener, context),
                        model.checkEvaluations(onFinishedListener, context),
                        model.checkStudents(onFinishedListener, context),
                        model.checkSubmissions(onFinishedListener, context),
                        model.checkExercises(onFinishedListener, context),
                        model.checkLessons(onFinishedListener, context),
                        onFinishedListener, context);
            }).start();
        }
    }

    @Override
    public void onCheckVersionFinished(float version, Context context) {
        if (view != null) {
            view.setTextVersion(version);
        }

    }

    @Override
    public void onFailure(Throwable t) {
        Log.d("Debug", t.toString());
    }
}
