package com.tsg.tot.task.taskmvp;

import android.content.Context;

import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.data.model.Lessons;
import com.tsg.tot.data.model.Student;
import com.tsg.tot.data.model.SubmissionDisplay;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.data.remote.model.StudyMaterialRemote;
import com.tsg.tot.data.remote.model.TaskRemote;
import com.tsg.tot.repository.ApiRepository;
import com.tsg.tot.repository.DatabaseRepository;
import com.tsg.tot.sqlite.DbOpenHelper;

import java.util.List;

import dagger.Module;

import static com.tsg.tot.sqlite.DBConstants.API_REPOSITORY;
import static com.tsg.tot.sqlite.DBConstants.DATABASE_REPOSITORY;
@Module
public class TaskModel implements TaskMVP.Model {
    DbOpenHelper dbHelper;
    private final ApiRepository apiRepository;
    private final DatabaseRepository databaseRepository;

    public TaskModel(ApiRepository apiRepository, DatabaseRepository databaseRepository) {
        this.apiRepository = apiRepository;
        this.databaseRepository = databaseRepository;
    }



    @Override
    public List<FilesKiosco> checkMyFileskioscos(Context context, Integer idEstudiante, Integer idMateria, Integer idTarea) {
        List<FilesKiosco> filesKioscoList = null;
        filesKioscoList = databaseRepository.getFileKioscos(context,idEstudiante,idMateria,idTarea);
        return filesKioscoList;
    }

    @Override
    public List<SubmissionDisplay> checkMySubmissionDisplay(Context context, Integer idEstudiante, Integer idMateria, Integer idTarea) {
        List<SubmissionDisplay> submissionDisplayList = null;
        submissionDisplayList = databaseRepository.getSubmissionsDisplay(context,idEstudiante,idMateria,idTarea);
        return submissionDisplayList;
    }

    @Override
    public    List<Lessons> checkMyLessons (Context context, Integer idEstudiante,Integer idMateria){
        List<Lessons> lessonsList = null;
        lessonsList = databaseRepository.getLessons(context,idEstudiante,idMateria);
        return lessonsList;
    }

    @Override
    public List<Student> checkStudents(Context context, int from,Integer idUsuario) {
        List<Student> studentList = null;

        switch (from) {
            case API_REPOSITORY:
                studentList = apiRepository.getStudent(context);
                break;
            case DATABASE_REPOSITORY:
                studentList = databaseRepository.getStudent(context,idUsuario);
                break;
            default:
                break;
        }
        return studentList;
    }


    @Override
    public List<StudyMaterialRemote> checkMyStudyMaterial (Context context , Integer idClase){
        List<StudyMaterialRemote> studyMaterialRemoteList = null;
        studyMaterialRemoteList= databaseRepository.getStudyMaterial(context,idClase);
        return studyMaterialRemoteList;
    }

    @Override
    public List<Task> checkTasks(Context context, int from,String token,Integer idEstudiante) {
        List<Task> taskList = null;
        List<TaskRemote> taskListRemote = null;

        switch (from) {
            case API_REPOSITORY:
                taskListRemote = apiRepository.getTasks(context,token);
                break;
            case DATABASE_REPOSITORY:
                taskList = databaseRepository.getTasks(context,token,idEstudiante);
                break;
            default:
                break;
        }

        return taskList;
    }






}
