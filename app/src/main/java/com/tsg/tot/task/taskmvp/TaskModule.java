package com.tsg.tot.task.taskmvp;

import android.app.Application;
import android.content.Context;

import com.tsg.tot.repository.ApiRepository;
import com.tsg.tot.repository.DatabaseRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TaskModule {
    private Application application;

    public TaskModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    public TaskMVP.Presenter provideTaskPresenter(TaskMVP.Model model) {
        return new TaskPresenter(model);
    }

    @Provides

    public TaskMVP.Model provideTaskModel(ApiRepository apiRepository, DatabaseRepository databaseRepository) {
        return new TaskModel(apiRepository, databaseRepository);
    }
//
//    @Provides
//    @Named("provideTaskApiRepository")
//    public ApiRepository provideApiRepository() {
//        return new ApiRepository();
//    }
//
//    @Provides
//    @Named("provideTaskDatabaseRepository")
//    public DatabaseRepository provideDatabaseRepository() {
//        return new DatabaseRepository();
//    }
}
