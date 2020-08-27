package com.tsg.tot.main;

import android.util.Log;

import androidx.annotation.Nullable;

import com.tsg.tot.data.model.Version;

import java.util.List;

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
    public void mainButtonClicked() {
        if (view != null) {
            model.checkInfo(this);
        }
    }

    @Override
    public void sendBlob() {
        if (view != null) {
            model.sendBlob(this);
        }
    }

    @Override
    public void onFinished(List<Version> versionList) {
        view.setContent(versionList);
    }

    @Override
    public void onFailure(Throwable t) {
        Log.d("Debug", t.toString());
    }
}
