package com.tsg.tot.main;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tsg.tot.data.model.Version;
import com.tsg.tot.repository.DatabaseRepository;
import com.tsg.tot.repository.Repository;
import com.tsg.tot.sqlite.DbOpenHelper;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainModel implements MainMVP.Model {

    DbOpenHelper dbHelper;
    private Repository repository;
    private DatabaseRepository databaseRepository;

    public MainModel(Repository repository, DatabaseRepository databaseRepository) {
        this.repository = repository;
        this.databaseRepository = databaseRepository;
    }

    @Override
    public float checkAPIVersion(OnFinishedListener onFinishedListener, Context context) {
        return repository.getVersion(onFinishedListener, context);
    }

    @Override
    public float checkDbVersion(OnFinishedListener onFinishedListener, Context context) {
        return databaseRepository.getVersion(onFinishedListener, context);
    }

    @Override
    public void sendBlob(OnFinishedListener onFinishedListener) {
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("codigo", "187")
                .addFormDataPart("file", "/storage/self/primary/Download/sample.pdf",
                        RequestBody.create(MediaType.parse("*/*"),
                                new File("/storage/self/primary/Download/sample.pdf")))
                .addFormDataPart("subida", "289")
                .addFormDataPart("entrega_id", "13")
                .build();
        repository.uploadBlob(body, onFinishedListener);
    }

    @Override
    public void createDb(Context context) {
        dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            Log.d("Debug", "Database created");
        }
    }

    @Override
    public void setDbVersion(List<Version> versionList, Context context) {
        databaseRepository.updateVersion(versionList, context);
    }
}
