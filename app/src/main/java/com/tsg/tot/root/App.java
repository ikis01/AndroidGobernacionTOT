package com.tsg.tot.root;

import android.app.Application;

import com.tsg.tot.main.MainModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .mainModule(new MainModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
