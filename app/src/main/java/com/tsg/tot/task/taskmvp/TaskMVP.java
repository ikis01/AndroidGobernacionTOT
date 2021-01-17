package com.tsg.tot.task.taskmvp;

import android.content.Context;

import com.tsg.tot.data.model.Student;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.data.model.Task;

import java.util.List;

public interface TaskMVP {
    interface View {
        void notifyRefresh();
        void setInfoStudent (List<Student> studentList);
        void setInfoSubject (Subjects subject);
        void notifyRefreh();

        void showLoadingDialog();

        void dismissLoadingDialog();
    }
    interface Presenter {
        void setView(TaskMVP.View view);
        List<Subjects> getSubjects(Context context);

        List<Task> getTaskSubject(Context context, int idSubject);

        void setInfoStudent(Context context);

        void setInfoSubject(Subjects subjects);

        void notifyRefresh();
    }
    interface Model {

        interface OnFinishedListener {
            void onFailure(Throwable t);
        }

        List<Student> checkStudents(Context context, int from);

        List<Task> checkTasks(Context context, int from);

        List<Subjects> checkSubjects(Context context, int from);

    }
}
