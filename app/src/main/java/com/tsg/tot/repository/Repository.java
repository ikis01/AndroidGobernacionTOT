package com.tsg.tot.repository;

import android.content.Context;

import com.tsg.tot.data.model.Version;
import com.tsg.tot.main.MainMVP;

import java.util.List;

import okhttp3.RequestBody;

public interface Repository {

    //GET

    float getVersion(MainMVP.Model.OnFinishedListener onFinishedListener, Context context);

    void getStudent(MainMVP.Model.OnFinishedListener onFinishedListener);

    void getLessons(MainMVP.Model.OnFinishedListener onFinishedListener);

    void getDevice(MainMVP.Model.OnFinishedListener onFinishedListener);

    void getGrade(MainMVP.Model.OnFinishedListener onFinishedListener);

    void getExercises(MainMVP.Model.OnFinishedListener onFinishedListener);

    void getSubmissions(MainMVP.Model.OnFinishedListener onFinishedListener);

    void getEvaluations(MainMVP.Model.OnFinishedListener onFinishedListener);

    void getStudyMaterial(MainMVP.Model.OnFinishedListener onFinishedListener);

    void getSubjects(MainMVP.Model.OnFinishedListener onFinishedListener);

    void getPlanning(MainMVP.Model.OnFinishedListener onFinishedListener);

    void getTeachers(MainMVP.Model.OnFinishedListener onFinishedListener);

    void getUploads(MainMVP.Model.OnFinishedListener onFinishedListener);

    void getTasks(MainMVP.Model.OnFinishedListener onFinishedListener);

    //UPDATE

    void uploadBlob(RequestBody requestBody, MainMVP.Model.OnFinishedListener onFinishedListener);

    void updateVersion(List<Version> versionList, Context context);

    void updateStudent();

    void updateLessons();

    void updateDevice();

    void updateGrade();

    void updateExercises();

    void updateSubmissions();

    void updateEvaluations();

    void updateStudyMaterial();

    void updateSubjects();

    void updatePlanning();

    void updateTeachers();

    void updateUploads();

    void updateTasks();

    void updateBlobs();
}
