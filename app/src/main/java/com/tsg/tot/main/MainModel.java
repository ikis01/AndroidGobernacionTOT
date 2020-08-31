package com.tsg.tot.main;

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
    public float checkAPIVersion(OnFinishedListener onFinishedListener, Context context) {
        return apiRepository.getVersion(onFinishedListener, context);
    }

    @Override
    public float checkDbVersion(OnFinishedListener onFinishedListener, Context context) {
        return databaseRepository.getVersion(onFinishedListener, context);
    }

    @Override
    public void updateAllDb(float version, List<Task> taskList,
                            List<Upload> uploadList, List<Teacher> teacherList,
                            List<Subjects> subjectsList, List<StudyMaterial> studyMaterialList,
                            List<Evaluations> evaluationsList, List<Student> studentList,
                            List<Submissions> submissionsList, List<Exercises> exercisesList,
                            List<Lessons> lessonsList, OnFinishedListener onFinishedListener, Context context) {

        databaseRepository.updateVersion(version, context);
        databaseRepository.updateTasks(taskList, context);
        databaseRepository.updateUploads(uploadList, context);
        databaseRepository.updateTeachers(teacherList, context);
        databaseRepository.updateSubjects(subjectsList, context);
        databaseRepository.updateStudyMaterial(studyMaterialList, context);
        databaseRepository.updateEvaluations(evaluationsList, context);
        databaseRepository.updateStudent(studentList, context);
        databaseRepository.updateSubmissions(submissionsList, context);
        databaseRepository.updateExercises(exercisesList, context);
        databaseRepository.updateLessons(lessonsList, context);
    }

    @Override
    public List<Device> checkDevices(OnFinishedListener onFinishedListener, Context context) {
        return apiRepository.getDevice(onFinishedListener, context);
    }

    @Override
    public List<Lessons> checkLessons(OnFinishedListener onFinishedListener, Context context) {
        return apiRepository.getLessons(onFinishedListener, context);
    }

    @Override
    public List<Grade> checkGrades(OnFinishedListener onFinishedListener, Context context) {
        return apiRepository.getGrade(onFinishedListener, context);
    }

    @Override
    public List<Exercises> checkExercises(OnFinishedListener onFinishedListener, Context context) {
        return apiRepository.getExercises(onFinishedListener, context);
    }

    @Override
    public List<Submissions> checkSubmissions(OnFinishedListener onFinishedListener, Context context) {
        return apiRepository.getSubmissions(onFinishedListener, context);
    }

    @Override
    public List<Student> checkStudents(OnFinishedListener onFinishedListener, Context context) {
        return apiRepository.getStudent(onFinishedListener, context);
    }

    @Override
    public List<Evaluations> checkEvaluations(OnFinishedListener onFinishedListener, Context context) {
        return apiRepository.getEvaluations(onFinishedListener, context);
    }

    @Override
    public List<StudyMaterial> checkStudyMaterials(OnFinishedListener onFinishedListener, Context context) {
        return apiRepository.getStudyMaterial(onFinishedListener, context);
    }

    @Override
    public List<Subjects> checkSubjects(OnFinishedListener onFinishedListener, Context context) {
        return apiRepository.getSubjects(onFinishedListener, context);
    }

    @Override
    public List<Planning> checkPlanning(OnFinishedListener onFinishedListener, Context context) {
        return apiRepository.getPlanning(onFinishedListener, context);
    }

    @Override
    public List<Teacher> checkTeachers(OnFinishedListener onFinishedListener, Context context) {
        return apiRepository.getTeachers(onFinishedListener, context);
    }

    @Override
    public List<Upload> checkUploads(OnFinishedListener onFinishedListener, Context context) {
        return apiRepository.getUploads(onFinishedListener, context);
    }

    @Override
    public List<Task> checkTasks(OnFinishedListener onFinishedListener, Context context) {
        return apiRepository.getTasks(onFinishedListener, context);
    }

    @Override
    public void postTask(OnFinishedListener onFinishedListener, String uploadId, String subjectId, String name, String code) {
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("subida_id", uploadId)
                .addFormDataPart("materias_id", subjectId)
                .addFormDataPart("nombre", name)
                .addFormDataPart("codigo", code)
                .addFormDataPart("materias", subjectId)
                .build();
        apiRepository.postTask(body, onFinishedListener);
    }

    @Override
    public void postEvaluations(OnFinishedListener onFinishedListener, String uploadId, String name, String subjectId) {
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("subida_id", uploadId)
                .addFormDataPart("nombre", name)
                .addFormDataPart("materias", subjectId)
                .build();
        apiRepository.postEvaluations(body, onFinishedListener);
    }

    @Override
    public void postExercises(OnFinishedListener onFinishedListener, String uploadId, String name, String lessonsId) {
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("subida_id", uploadId)
                .addFormDataPart("nombre", name)
                .addFormDataPart("clases", lessonsId)
                .build();
        apiRepository.postExercises(body, onFinishedListener);
    }

    @Override
    public void postSubmissions(OnFinishedListener onFinishedListener, String upp, String exercisesId, String taskId, String evaluationId, String uploadId, String studentId) {
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("upp", upp)
                .addFormDataPart("ejercicios", exercisesId)
                .addFormDataPart("tarea", taskId)
                .addFormDataPart("evaluacion", evaluationId)
                .addFormDataPart("subida", uploadId)
                .addFormDataPart("estudiante", studentId)
                .build();
        apiRepository.postSubmissions(body, onFinishedListener);
    }

    @Override
    public void postBlob(OnFinishedListener onFinishedListener, String code, String file, String upload, String submissionId) {
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("codigo", code)
                .addFormDataPart("file", file, RequestBody.create(MediaType.parse("*/*"), new File(file)))
                .addFormDataPart("subida", upload)
                .addFormDataPart("entrega_id", submissionId)
                .build();
        apiRepository.postBlob(body, onFinishedListener);
    }
}
