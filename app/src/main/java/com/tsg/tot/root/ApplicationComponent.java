package com.tsg.tot.root;

import com.tsg.tot.main.MainModule;
import com.tsg.tot.main.MainView;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, MainModule.class})
public interface ApplicationComponent {

    void inject(MainView target);
}
