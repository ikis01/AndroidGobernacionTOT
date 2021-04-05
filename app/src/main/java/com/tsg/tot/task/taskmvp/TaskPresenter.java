package com.tsg.tot.task.taskmvp;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.data.model.Lessons;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.data.model.SubmissionDisplay;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.data.remote.model.StudyMaterialRemote;

import java.util.ArrayList;
import java.util.List;

import static com.tsg.tot.sqlite.DBConstants.DATABASE_REPOSITORY;

public  class TaskPresenter implements TaskMVP.Presenter, TaskMVP.Model.OnFinishedListener {

    @Nullable
    private TaskMVP.View view;
    private final TaskMVP.Model model;

    public TaskPresenter(TaskMVP.Model model) {
       this.model = model ;
    }

    @Override
    public void setLessons(Lessons lessons) {

    }

    @Override
    public void setFileKiosco(FilesKiosco fileKiosco) {
        if (view != null) {
            view.setFileKiosco(fileKiosco);
        }
    }

    @Override
    public void setView(TaskMVP.View view){
        this.view=view;
    }



    @Override
    public List<Task> getTaskSubject(Context context, int idSubject,String token,Integer idEstudiante) {
        List<Task> taskList = model.checkTasks(context, DATABASE_REPOSITORY,token,idEstudiante);
        List<Task> taskListFinal = new ArrayList<>();

        if (taskList != null) {
            for (Task task : taskList) {
                try {
                    if (task.getMaterias() == idSubject) {
                        taskListFinal.add(task);
                    }
                } catch (NullPointerException e) {
                    Log.d("Debug NullPointerException ", e.toString());
                }
            }
        }

        return taskListFinal;
    }

    @Override
    public void setInfoStudent(Context context) {
        if (view != null) {
         //   view.setInfoStudent(model.checkStudents(context, DATABASE_REPOSITORY,idUsuario));
        }
    }

    @Override
    public void setInfoSubject(Subjects subjects) {
        if (view != null) {
            view.setInfoSubject(subjects);
        }
    }

    @Override
    public void notifyRefresh() {
        if (view != null) {
            view.notifyRefresh();
            dismissLoadingDialog();
        }
    }

    @Override
    public List<FilesKiosco> getFileKioscos(Context context, int idEstudiante, int idMateria, int idTarea) {
        return model.checkMyFileskioscos ( context,  idEstudiante,  idMateria, idTarea);
    }

    @Override
    public List<Lessons> getLessons(Context context, int idEstudiante, int idMateria){
        return model.checkMyLessons (context,idEstudiante,idMateria);
    }

    @Override
    public List<StudyMaterialRemote> getStudyMaterials(Context context, int idClase){
        return model.checkMyStudyMaterial (context,idClase);
    }

    @Override
    public List<SubmissionDisplay> getSubmissionDisplay(Context context, int idEstudiante, int idMateria, int idTarea) {
        return model.checkMySubmissionDisplay(context,idEstudiante,idMateria,idTarea);
    }


    @Override
    public void onFailure(Throwable t) {
        Log.d("onFailure", t.toString());
    }

    private void dismissLoadingDialog() {
        if (view != null) {
            view.dismissLoadingDialog();
        }
    }

}


