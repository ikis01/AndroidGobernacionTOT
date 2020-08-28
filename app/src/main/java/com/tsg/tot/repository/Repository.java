package com.tsg.tot.repository;

import android.content.Context;

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
import com.tsg.tot.data.model.Upload;
import com.tsg.tot.main.MainMVP;

import java.util.List;

import okhttp3.RequestBody;

public interface Repository {

    //GET

    float getVersion(MainMVP.Model.OnFinishedListener onFinishedListener, Context context);

    List<Device> getDevice(MainMVP.Model.OnFinishedListener onFinishedListener, Context context);

    List<Lessons> getLessons(MainMVP.Model.OnFinishedListener onFinishedListener, Context context);

    List<Grade> getGrade(MainMVP.Model.OnFinishedListener onFinishedListener, Context context);

    List<Exercises> getExercises(MainMVP.Model.OnFinishedListener onFinishedListener, Context context);

    List<Submissions> getSubmissions(MainMVP.Model.OnFinishedListener onFinishedListener, Context context);

    List<Student> getStudent(MainMVP.Model.OnFinishedListener onFinishedListener, Context context);

    List<Evaluations> getEvaluations(MainMVP.Model.OnFinishedListener onFinishedListener, Context context);

    String getIPEND(Context context);

    List<StudyMaterial> getStudyMaterial(MainMVP.Model.OnFinishedListener onFinishedListener, Context context);

    List<Subjects> getSubjects(MainMVP.Model.OnFinishedListener onFinishedListener, Context context);

    List<Planning> getPlanning(MainMVP.Model.OnFinishedListener onFinishedListener, Context context);

    List<Teacher> getTeachers(MainMVP.Model.OnFinishedListener onFinishedListener, Context context);

    List<Upload> getUploads(MainMVP.Model.OnFinishedListener onFinishedListener, Context context);

    List<Task> getTasks(MainMVP.Model.OnFinishedListener onFinishedListener, Context context);

    //UPDATE - POST

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

    void uploadBlob(RequestBody requestBody, MainMVP.Model.OnFinishedListener onFinishedListener);
}
