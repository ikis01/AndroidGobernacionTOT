package com.tsg.kot.root;

import android.app.Application;

import com.tsg.kot.main.mainmvp.MainModule;
import com.tsg.kot.task.taskmvp.TaskModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .mainModule(new MainModule(this))
                .taskModule(new TaskModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
