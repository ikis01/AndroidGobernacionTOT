package com.tsg.tot.main;

import com.tsg.tot.repository.ApiRepository;
import com.tsg.tot.repository.DatabaseRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

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
