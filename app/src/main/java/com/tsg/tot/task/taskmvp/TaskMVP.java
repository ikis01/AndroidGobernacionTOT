package com.tsg.tot.task.taskmvp;

import android.content.Context;

import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.data.model.Lessons;
import com.tsg.tot.data.model.Student;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.data.model.SubmissionDisplay;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.data.remote.model.StudyMaterialRemote;

import java.util.List;

import dagger.Provides;

public interface TaskMVP {
    interface View {
        void notifyRefresh();
        void setInfoStudent (List<Student> studentList);
        void setInfoSubject (Subjects subject);
        void notifyRefreh();
        void setFileKiosco(FilesKiosco fileKiosco);
        void showLoadingDialog();

        void dismissLoadingDialog();
    }

    interface Presenter {

        void setLessons (Lessons lessons);



        void setFileKiosco(FilesKiosco fileKiosco);

        void setView(TaskMVP.View view);


        List<Task> getTaskSubject(Context context, int idSubject,String token,Integer idEstudiante);

        void setInfoStudent(Context context);

        void setInfoSubject(Subjects subjects);

        void notifyRefresh();

        List<FilesKiosco> getFileKioscos(Context context, int idEstudiante, int idMateria, int idTarea);
        List<Lessons> getLessons(Context context, int idEstudiante, int idMateria);
        List<StudyMaterialRemote> getStudyMaterials(Context context, int idClase);
        List<SubmissionDisplay> getSubmissionDisplay (Context context ,int idEstudiante,int idMateria,int idTarea);
    }
    interface Model {

        interface OnFinishedListener {
            void onFailure(Throwable t);
        }

        List<FilesKiosco> checkMyFileskioscos (Context  context, Integer idEstudiante,  Integer idMateria,Integer idTarea);
        List<SubmissionDisplay> checkMySubmissionDisplay (Context  context, Integer idEstudiante,  Integer idMateria,Integer idTarea);

        List<Lessons> checkMyLessons (Context context, Integer idEstudiante,Integer idMateria);

        List<Student> checkStudents(Context context, int from,Integer idUsuario);

        List<Task> checkTasks(Context context, int from,String Token, Integer idEstudiante);

        List<StudyMaterialRemote> checkMyStudyMaterial (Context context , Integer idClase);

    }
}
