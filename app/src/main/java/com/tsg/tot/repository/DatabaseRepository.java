package com.tsg.tot.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tsg.tot.data.model.Version;
import com.tsg.tot.main.MainMVP;
import com.tsg.tot.sqlite.DbOpenHelper;

import java.util.List;

import okhttp3.RequestBody;

import static com.tsg.tot.sqlite.DBConstants.VERSION_TABLE_NAME;

public class DatabaseRepository implements Repository {

    //GET

    @Override
    public float getVersion(MainMVP.Model.OnFinishedListener onFinishedListener, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT NUMERO FROM VERSION";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        return c.getFloat(0);
    }

    @Override
    public void getStudent(MainMVP.Model.OnFinishedListener onFinishedListener) {

    }

    @Override
    public void getLessons(MainMVP.Model.OnFinishedListener onFinishedListener) {

    }

    @Override
    public void getDevice(MainMVP.Model.OnFinishedListener onFinishedListener) {

    }

    @Override
    public void getGrade(MainMVP.Model.OnFinishedListener onFinishedListener) {

    }

    @Override
    public void getExercises(MainMVP.Model.OnFinishedListener onFinishedListener) {

    }

    @Override
    public void getSubmissions(MainMVP.Model.OnFinishedListener onFinishedListener) {

    }

    @Override
    public void getEvaluations(MainMVP.Model.OnFinishedListener onFinishedListener) {

    }

    @Override
    public void getStudyMaterial(MainMVP.Model.OnFinishedListener onFinishedListener) {

    }

    @Override
    public void getSubjects(MainMVP.Model.OnFinishedListener onFinishedListener) {

    }

    @Override
    public void getPlanning(MainMVP.Model.OnFinishedListener onFinishedListener) {

    }

    @Override
    public void getTeachers(MainMVP.Model.OnFinishedListener onFinishedListener) {

    }

    @Override
    public void getUploads(MainMVP.Model.OnFinishedListener onFinishedListener) {

    }

    @Override
    public void getTasks(MainMVP.Model.OnFinishedListener onFinishedListener) {

    }

    //UPDATE

    @Override
    public void updateVersion(List<Version> versionList, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (versionList != null) {
            for (Version version : versionList) {
                cv.put("NUMERO", version.getNumero());
            }
        }
        db.update(VERSION_TABLE_NAME, cv, null, null);
    }

    @Override
    public void updateStudent() {

    }

    @Override
    public void updateLessons() {

    }

    @Override
    public void updateDevice() {

    }

    @Override
    public void updateGrade() {

    }

    @Override
    public void updateExercises() {

    }

    @Override
    public void updateSubmissions() {

    }

    @Override
    public void updateEvaluations() {

    }

    @Override
    public void updateStudyMaterial() {

    }

    @Override
    public void updateSubjects() {

    }

    @Override
    public void updatePlanning() {

    }

    @Override
    public void updateTeachers() {

    }

    @Override
    public void updateUploads() {

    }

    @Override
    public void updateTasks() {

    }

    @Override
    public void updateBlobs() {

    }

    @Override
    public void uploadBlob(RequestBody requestBody, MainMVP.Model.OnFinishedListener onFinishedListener) {

    }
}
