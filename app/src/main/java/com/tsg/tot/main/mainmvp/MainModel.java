package com.tsg.tot.main.mainmvp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tsg.tot.data.model.Device;
import com.tsg.tot.data.model.Evaluations;
import com.tsg.tot.data.model.Exercises;
import com.tsg.tot.data.model.Grade;
import com.tsg.tot.data.model.Lessons;
import com.tsg.tot.data.model.Planning;
import com.tsg.tot.data.model.Student;
import com.tsg.tot.data.model.StudyMaterial;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.data.model.Submissions;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.data.model.Teacher;
import com.tsg.tot.data.model.Upload;
import com.tsg.tot.repository.ApiRepository;
import com.tsg.tot.repository.DatabaseRepository;
import com.tsg.tot.sqlite.DbOpenHelper;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.tsg.tot.sqlite.DBConstants.API_REPOSITORY;
import static com.tsg.tot.sqlite.DBConstants.DATABASE_REPOSITORY;

public class MainModel implements MainMVP.Model {

    DbOpenHelper dbHelper;
    private ApiRepository apiRepository;
    private DatabaseRepository databaseRepository;


    public MainModel(ApiRepository apiRepository, DatabaseRepository databaseRepository) {
        this.apiRepository = apiRepository;
        this.databaseRepository = databaseRepository;
    }

    @Override
    public void createDb(Context context) {
        dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            Log.d("Debug", "Database created");
        }
    }

    @Override
    public float checkAPIVersion(Context context) {
        return apiRepository.getVersion(context);
    }

    @Override
    public float checkDbVersion(Context context) {
        return databaseRepository.getVersion(context);
    }

    @Override
    public void updateAllDb(float version, List<Task> taskList,
                            List<Upload> uploadList, List<Teacher> teacherList,
                            List<Subjects> subjectsList, List<Grade> gradeList, List<StudyMaterial> studyMaterialList,
                            List<Evaluations> evaluationsList, List<Student> studentList,
                            List<Submissions> submissionsList, List<Exercises> exercisesList,
                            List<Lessons> lessonsList, Context context) {

        databaseRepository.updateVersion(version, context);
        databaseRepository.updateTasks(taskList, context);
        databaseRepository.updateUploads(uploadList, context);
        databaseRepository.updateTeachers(teacherList, context);
        databaseRepository.updateSubjects(subjectsList, context);
        databaseRepository.updateGrade(gradeList, context);
        databaseRepository.updateStudyMaterial(studyMaterialList, context);
        databaseRepository.updateEvaluations(evaluationsList, context);
        databaseRepository.updateStudent(studentList, context);
        databaseRepository.updateSubmissions(submissionsList, context);
        databaseRepository.updateExercises(exercisesList, context);
        databaseRepository.updateLessons(lessonsList, context);
    }

    @Override
    public List<Device> checkDevices(Context context, int from) {
        List<Device> deviceList = null;

        switch (from) {
            case API_REPOSITORY:
                deviceList = apiRepository.getDevice(context);
                break;
            case DATABASE_REPOSITORY:
                deviceList = databaseRepository.getDevice(context);
                break;
            default:
                break;
        }

        return deviceList;
    }

    @Override
    public List<Lessons> checkLessons(Context context, int from) {
        List<Lessons> lessonsList = null;

        switch (from) {
            case API_REPOSITORY:
                lessonsList = apiRepository.getLessons(context);
                break;
            case DATABASE_REPOSITORY:
                lessonsList = databaseRepository.getLessons(context);
                break;
            default:
                break;
        }

        return lessonsList;
    }

    @Override
    public List<Grade> checkGrades(Context context, int from) {
        List<Grade> gradeList = null;

        switch (from) {
            case API_REPOSITORY:
                gradeList = apiRepository.getGrade(context);
                break;
            case DATABASE_REPOSITORY:
                gradeList = databaseRepository.getGrade(context);
                break;
            default:
                break;
        }

        return gradeList;
    }

    @Override
    public List<Exercises> checkExercises(Context context, int from) {
        List<Exercises> exercisesList = null;

        switch (from) {
            case API_REPOSITORY:
                exercisesList = apiRepository.getExercises(context);
                break;
            case DATABASE_REPOSITORY:
                exercisesList = databaseRepository.getExercises(context);
                break;
            default:
                break;
        }

        return exercisesList;
    }

    @Override
    public List<Submissions> checkSubmissions(Context context, int from) {
        List<Submissions> submissionsList = null;

        switch (from) {
            case API_REPOSITORY:
                submissionsList = apiRepository.getSubmissions(context);
                break;
            case DATABASE_REPOSITORY:
                submissionsList = databaseRepository.getSubmissions(context);
                break;
            default:
                break;
        }
        return submissionsList;
    }

    @Override
    public List<Student> checkStudents(Context context, int from) {
        List<Student> studentList = null;

        switch (from) {
            case API_REPOSITORY:
                studentList = apiRepository.getStudent(context);
                break;
            case DATABASE_REPOSITORY:
                studentList = databaseRepository.getStudent(context);
                break;
            default:
                break;
        }
        return studentList;
    }

    @Override
    public List<Evaluations> checkEvaluations(Context context, int from) {
        List<Evaluations> evaluationsList = null;

        switch (from) {
            case API_REPOSITORY:
                evaluationsList = apiRepository.getEvaluations(context);
                break;
            case DATABASE_REPOSITORY:
                evaluationsList = databaseRepository.getEvaluations(context);
                break;
            default:
                break;
        }

        return evaluationsList;
    }

    @Override
    public List<StudyMaterial> checkStudyMaterials(Context context, int from) {
        List<StudyMaterial> studyMaterialList = null;

        switch (from) {
            case API_REPOSITORY:
                studyMaterialList = apiRepository.getStudyMaterial(context);
                break;
            case DATABASE_REPOSITORY:
                studyMaterialList = databaseRepository.getStudyMaterial(context);
                break;
            default:
                break;
        }

        return studyMaterialList;
    }

    @Override
    public List<Subjects> checkSubjects(Context context, int from) {
        List<Subjects> subjectsList = null;

        switch (from) {
            case API_REPOSITORY:
                subjectsList = apiRepository.getSubjects(context);
                break;
            case DATABASE_REPOSITORY:
                subjectsList = databaseRepository.getSubjects(context);
                break;
            default:
                break;
        }

        return subjectsList;
    }

    @Override
    public List<Planning> checkPlanning(Context context, int from) {
        List<Planning> planningList = null;
        switch (from) {
            case API_REPOSITORY:
                planningList = apiRepository.getPlanning(context);
                break;
            case DATABASE_REPOSITORY:
                planningList = databaseRepository.getPlanning(context);
                break;
            default:
                break;
        }

        return planningList;
    }

    @Override
    public List<Teacher> checkTeachers(Context context, int from) {
        List<Teacher> teacherList = null;

        switch (from) {
            case API_REPOSITORY:
                teacherList = apiRepository.getTeachers(context);
                break;
            case DATABASE_REPOSITORY:
                teacherList = databaseRepository.getTeachers(context);
                break;
            default:
                break;
        }

        return teacherList;
    }

    @Override
    public List<Upload> checkUploads(Context context, int from) {
        List<Upload> uploadList = null;

        switch (from) {
            case API_REPOSITORY:
                uploadList = apiRepository.getUploads(context);
                break;
            case DATABASE_REPOSITORY:
                uploadList = databaseRepository.getUploads(context);
                break;
            default:
                break;
        }

        return uploadList;
    }

    @Override
    public List<Task> checkTasks(Context context, int from) {
        List<Task> taskList = null;

        switch (from) {
            case API_REPOSITORY:
                taskList = apiRepository.getTasks(context);
                break;
            case DATABASE_REPOSITORY:
                taskList = databaseRepository.getTasks(context);
                break;
            default:
                break;
        }

        return taskList;
    }

    @Override
    public void postTask(String uploadId, String subjectId, String name, String code) {
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("subida_id", uploadId)
                .addFormDataPart("materias_id", subjectId)
                .addFormDataPart("nombre", name)
                .addFormDataPart("codigo", code)
                .addFormDataPart("materias", subjectId)
                .build();
        apiRepository.postTask(body);
    }

    @Override
    public void postEvaluations(String uploadId, String name, String subjectId) {
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("subida_id", uploadId)
                .addFormDataPart("nombre", name)
                .addFormDataPart("materias", subjectId)
                .build();
        apiRepository.postEvaluations(body);
    }

    @Override
    public void postExercises(String uploadId, String name, String lessonsId) {
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("subida_id", uploadId)
                .addFormDataPart("nombre", name)
                .addFormDataPart("clases", lessonsId)
                .build();
        apiRepository.postExercises(body);
    }

    @Override
    public void postSubmissions(String upp, String exercisesId, String taskId, String evaluationId, String uploadId, String studentId) {
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("upp", upp)
                .addFormDataPart("ejercicios", exercisesId)
                .addFormDataPart("tarea", taskId)
                .addFormDataPart("evaluacion", evaluationId)
                .addFormDataPart("subida", uploadId)
                .addFormDataPart("estudiante", studentId)
                .build();
        apiRepository.postSubmissions(body);
    }

    @Override
    public void postBlob(String code, String file, String upload, String submissionId) {
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("codigo", code)
                .addFormDataPart("file", file, RequestBody.create(MediaType.parse("*/*"), new File(file)))
                .addFormDataPart("subida", upload)
                .addFormDataPart("entrega_id", submissionId)
                .build();
        apiRepository.postBlob(body);
    }
}
