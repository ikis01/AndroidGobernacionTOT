package com.tsg.tot.root;

import com.tsg.tot.main.mainmvp.MainModule;
import com.tsg.tot.main.mainmvp.MainView;
import com.tsg.tot.messages.MessageDetailActivity;
import com.tsg.tot.messages.MessageMainActivity;
import com.tsg.tot.subject.DetailSubjectActivity;
import com.tsg.tot.task.TaskDetailActivity;
import com.tsg.tot.task.taskmvp.TaskModule;

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
