package com.tsg.tot.repository;

import com.tsg.tot.main.MainMVP;

import okhttp3.RequestBody;

public interface Repository {

    void getVersion(MainMVP.Model.OnFinishedListener onFinishedListener);

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

    void uploadBlob(RequestBody requestBody, MainMVP.Model.OnFinishedListener onFinishedListener);
}
