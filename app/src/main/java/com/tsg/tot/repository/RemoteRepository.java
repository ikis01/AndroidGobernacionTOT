package com.tsg.tot.repository;

import android.content.ContentValues;
import android.content.Context;

import com.google.gson.JsonObject;
import com.tsg.tot.data.model.Blob;
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
import com.tsg.tot.data.model.TokenCustom;
import com.tsg.tot.data.model.Upload;
import com.tsg.tot.data.model.Users;
import com.tsg.tot.data.remote.model.GradeRemote;
import com.tsg.tot.data.remote.model.LessonsRemote;
import com.tsg.tot.data.remote.model.StudentRemote;
import com.tsg.tot.data.remote.model.StudyMaterialRemote;
import com.tsg.tot.data.remote.model.SubjectsRemote;
import com.tsg.tot.data.remote.model.TaskRegristerRemote;
import com.tsg.tot.data.remote.model.TaskRemote;
import com.tsg.tot.data.remote.model.TeacherRemote;

import java.util.List;

import okhttp3.RequestBody;

/**
 * Interfaz que define los métodos utilizados por las clases que la implementan
 */
public interface RemoteRepository {

    //GET

    float getVersion(Context context);

    List<Device> getDevice(Context context);

    List<Lessons> getLessons(Context context);

    List<LessonsRemote> getMyLessons(Context context, String auth);

    List<Grade> getGrade(Context context);

    GradeRemote getMyGrade(Context context, String auth);

    List<StudyMaterialRemote> getMyPendingStudyMaterial(Context context, String auth);

    StudentRemote getMyStudent(Context context, String auth);

    List<Exercises> getExercises(Context context);


    List<Submissions> getSubmissions(Context context);

    List<Student> getStudent(Context context);

    List<Evaluations> getEvaluations(Context context);


    List<StudyMaterial> getStudyMaterial(Context context);

    List<SubjectsRemote> getMySubjects(Context context, String authKey);

    List<Planning> getPlanning(Context context);

    List<TeacherRemote> getTeachers(Context context, String authKey);

    List<Upload> getUploads(Context context);

    List<TaskRemote> getTasks(Context context, String authKey);


    //UPDATE - POST

    List<Users> getUsers(ContentValues cv, Context context);

    void updateUser(ContentValues cv, Context context);


    void updateVersion(float version, Context context);

    void updateDevice(List<Device> devicesList, Context context);

    void updateLessons(List<Lessons> lessonsList, Context context);

    void updateGrade(List<Grade> gradesList, Context context);

    void updateExercises(List<Exercises> exercisesList, Context context);

    void updateSubmissions(List<Submissions> submissionsList, Context context);

    void updateStudent(List<Student> studentList, Context context);

    void updateEvaluations(List<Evaluations> evaluationsList, Context context);

    void updateStudyMaterial(List<StudyMaterial> studyMaterialList, Context context);

    void updateSubjects(List<Subjects> subjectsList, Context context);

    void updatePlanning(List<Planning> planningList, Context context);

    void updateTeachers(List<Teacher> teacherList, Context context);

    void updateUploads(List<Upload> uploadList, Context context);

    void updateTasks(List<Task> taskList, Context context);

    void updateBlobs(List<Blob> blobList, Context context);

    void postTask(RequestBody requestBody);

    void postEvaluations(RequestBody requestBody);

    void postExercises(RequestBody requestBody);

    void postSubmissions(String auth,RequestBody requestBody);

    void postBlob(RequestBody requestBody);

    TokenCustom postLogin(JsonObject requestBody);

    TaskRegristerRemote postRegisterTask (String token , JsonObject requestBody);

}
