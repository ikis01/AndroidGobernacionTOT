package com.tsg.tot.main;

import com.tsg.tot.repository.MemoryRepository;
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
    public MainMVP.Model provideMainModel(Repository repository) {
        return new MainModel(repository);
    }

    @Provides
    public Repository provideRepository() {
        return new MemoryRepository();
    }
}
