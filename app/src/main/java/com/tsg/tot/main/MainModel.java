package com.tsg.tot.main;

import com.tsg.tot.repository.Repository;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainModel implements MainMVP.Model {

    private Repository repository;

    public MainModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void checkInfo(OnFinishedListener onFinishedListener) {
        repository.getVersion(onFinishedListener);
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
}
