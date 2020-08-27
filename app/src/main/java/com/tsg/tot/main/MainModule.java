package com.tsg.tot.main;

import com.tsg.tot.repository.ApiRepository;
import com.tsg.tot.repository.DatabaseRepository;
import com.tsg.tot.repository.Repository;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    public MainMVP.Presenter provideMainPresenter(MainMVP.Model model) {
        return new MainPresenter(model);
    }

    @Provides
    public MainMVP.Model provideMainModel(Repository repository, DatabaseRepository databaseRepository) {
        return new MainModel(repository, databaseRepository);
    }

    @Provides
    public Repository provideRepository() {
        return new ApiRepository();
    }

    @Provides
    public DatabaseRepository provideDatabaseRepository(){
        return new DatabaseRepository();
    }
}
