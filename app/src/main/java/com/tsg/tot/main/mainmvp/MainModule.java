package com.tsg.tot.main.mainmvp;

import android.app.Application;
import android.content.Context;

import com.tsg.tot.repository.ApiRepository;
import com.tsg.tot.repository.DatabaseRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private final Application application;

    public MainModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    public MainMVP.Presenter provideMainPresenter(MainMVP.Model model) {
        return new MainPresenter(model);
    }

    @Provides
    public MainMVP.Model provideMainModel(ApiRepository apiRepository, DatabaseRepository databaseRepository) {
        return new MainModel(apiRepository, databaseRepository);
    }

    @Provides
    public ApiRepository provideApiRepository() {
        return new ApiRepository();
    }

    @Provides
    public DatabaseRepository provideDatabaseRepository() {
        return new DatabaseRepository();
    }
}
