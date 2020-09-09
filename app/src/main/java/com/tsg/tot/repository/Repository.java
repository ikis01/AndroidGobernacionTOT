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

import java.util.List;

import okhttp3.RequestBody;

public interface Repository {

    //GET

    float getVersion(Context context);

    List<Device> getDevice(Context context);

    List<Lessons> getLessons(Context context);

    List<Grade> getGrade(Context context);

    List<Exercises> getExercises(Context context);

    List<Submissions> getSubmissions(Context context);

    List<Student> getStudent(Context context);

    List<Evaluations> getEvaluations(Context context);

    String getIPEND(Context context);

    List<StudyMaterial> getStudyMaterial(Context context);

    List<Subjects> getSubjects(Context context);

    List<Planning> getPlanning(Context context);

    List<Teacher> getTeachers(Context context);

    List<Upload> getUploads(Context context);

    List<Task> getTasks(Context context);

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

    void postTask(RequestBody requestBody);

    void postEvaluations(RequestBody requestBody);

    void postExercises(RequestBody requestBody);

    void postSubmissions(RequestBody requestBody);

    void postBlob(RequestBody requestBody);
}
