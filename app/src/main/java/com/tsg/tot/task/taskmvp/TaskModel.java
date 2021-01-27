package com.tsg.tot.task.taskmvp;

import android.content.Context;

import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.data.model.Student;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.data.remote.model.TaskRemote;
import com.tsg.tot.repository.ApiRepository;
import com.tsg.tot.repository.DatabaseRepository;
import com.tsg.tot.sqlite.DbOpenHelper;

import java.util.List;

import static com.tsg.tot.sqlite.DBConstants.API_REPOSITORY;
import static com.tsg.tot.sqlite.DBConstants.DATABASE_REPOSITORY;

public class TaskModel implements TaskMVP.Model {
    DbOpenHelper dbHelper;
    private ApiRepository apiRepository;
    private DatabaseRepository databaseRepository;

    public TaskModel(ApiRepository apiRepository, DatabaseRepository databaseRepository) {
        this.apiRepository = apiRepository;
        this.databaseRepository = databaseRepository;
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
    public List<Task> checkTasks(Context context, int from,String token) {
        List<Task> taskList = null;
        List<TaskRemote> taskListRemote = null;

        switch (from) {
            case API_REPOSITORY:
                taskListRemote = apiRepository.getTasks(context,token);
                break;
            case DATABASE_REPOSITORY:
                taskList = databaseRepository.getTasks(context,token);
                break;
            default:
                break;
        }

        return taskList;
    }



}
