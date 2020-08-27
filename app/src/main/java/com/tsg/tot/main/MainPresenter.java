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
    public void checkVersions(Context context) {
        MainMVP.Model.OnFinishedListener onFinishedListener = this;
        if (view != null) {
            new Thread(() -> {
                while (true) {
                    if (model.checkAPIVersion(onFinishedListener, context) == model.checkDbVersion(onFinishedListener, context)) {
                        Log.d("Debug", "Same version");
                    } else {
                        //TODO: Update tables.
                    }
                }
            }).start();
        }
    }

    @Override
    public void sendBlob() {
        if (view != null) {
            model.sendBlob(this);
        }
    }

    @Override
    public void createDB(Context context) {
        model.createDb(context);
    }

    @Override
    public void onFinished(float version, Context context) {
        view.setContent(version);
    }

    @Override
    public void onFailure(Throwable t) {
        Log.d("Debug", t.toString());
    }
}
