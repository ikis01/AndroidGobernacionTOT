package com.tsg.kot.root;

import com.tsg.kot.main.mainmvp.MainModule;
import com.tsg.kot.main.mainmvp.MainView;
import com.tsg.kot.messages.MessageDetailActivity;
import com.tsg.kot.messages.MessageMainActivity;
import com.tsg.kot.subject.DetailSubjectActivity;
import com.tsg.kot.task.TaskDetailActivity;
import com.tsg.kot.task.taskmvp.TaskModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, MainModule.class, TaskModule.class})
public interface ApplicationComponent {

    void inject(MainView target);
    void injectTask(TaskDetailActivity target);
    void injectDetailActivity(DetailSubjectActivity target);
    void injectMessageActivity(MessageMainActivity target);
    void injectMessageDetailActivity(MessageDetailActivity target);
}
