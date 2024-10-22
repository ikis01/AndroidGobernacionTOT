package com.tsg.tot.repository;

import android.content.ContentValues;
import android.content.Context;

import com.google.gson.JsonObject;
import com.tsg.tot.data.model.Blob;
import com.tsg.tot.data.model.Device;
import com.tsg.tot.data.model.Evaluations;
import com.tsg.tot.data.model.Exercises;
import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.data.model.Grade;
import com.tsg.tot.data.model.Lessons;
import com.tsg.tot.data.model.MessageAnswer;
import com.tsg.tot.data.model.Planning;
import com.tsg.tot.data.model.Student;
import com.tsg.tot.data.model.StudyMaterial;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.data.model.Submissions;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.data.model.Teacher;
import com.tsg.tot.data.model.TokenCustom;
import com.tsg.tot.data.model.Upload;
import com.tsg.tot.data.model.Uploads;
import com.tsg.tot.data.model.Users;
import com.tsg.tot.data.remote.model.FileMessageRemote;
import com.tsg.tot.data.remote.model.GradeRemote;
import com.tsg.tot.data.remote.model.LessonsRemote;
import com.tsg.tot.data.remote.model.MessageRemote;
import com.tsg.tot.data.remote.model.StudentRemote;
import com.tsg.tot.data.remote.model.StudyMaterialRemote;
import com.tsg.tot.data.remote.model.SubjectsRemote;
import com.tsg.tot.data.remote.model.SubmissionPending;
import com.tsg.tot.data.remote.model.TaskRemote;
import com.tsg.tot.data.remote.model.TeacherRemote;

import java.util.List;

import okhttp3.RequestBody;

public interface LocalRepository {

    //GET

    float getVersion(Context context);

    List<Device> getDevice(Context context);

    List<Lessons> getLessons(Context context);

    List<Grade> getGrade(Context context);

    List<Exercises> getExercises(Context context);

    List<Submissions> getSubmissions(Context context);

    List<Student> getStudent(Context context, Integer idUsuario);

    List<Evaluations> getEvaluations(Context context);

    String getIPEND(Context context);

    List<StudyMaterial> getStudyMaterial(Context context);

    List<Subjects> getSubjects(Context context, Integer code);

    List<FilesKiosco>getFileKioscos(Context context,Integer idEstudiante,Integer idMateria,Integer idTarea);


    List<Planning> getPlanning(Context context);

    List<Teacher> getTeachers(Context context);

    List<Upload> getUploads(Context context);

    List<Task> getTasks(Context context, String authKey,Integer idEstudiante);


    //UPDATE - POST

    List<Users> getUsers(ContentValues cv, Context context);

    void updateUser(ContentValues cv, Context context);


    void updateVersion(float version, Context context);

    void updateDevice(List<Device> devicesList, android.content.Context context);

    void updateLessons(List<Lessons> lessonsList, android.content.Context context);

    void updateGrade(List<Grade> gradesList, android.content.Context context);

    void updateExercises(List<Exercises> exercisesList, android.content.Context context);

    Long updateSubmissions(List<Submissions> submissionsList, android.content.Context context);

    void updateStudent(List<Student> studentList, android.content.Context context);

    void updateEvaluations(List<Evaluations> evaluationsList, android.content.Context context);

    void updateStudyMaterial(List<StudyMaterial> studyMaterialList, android.content.Context context);

    void updateSubjects(List<Subjects> subjectsList, android.content.Context context);

    void updatePlanning(List<Planning> planningList, android.content.Context context);

    void updateTeachers(List<Teacher> teacherList, android.content.Context context);

    void updateUploads(List<Upload> uploadList, android.content.Context context);

    void updateTasks(List<Task> taskList, android.content.Context context);

    void updateBlobs(List<Blob> blobList, android.content.Context context);

    void postTask(RequestBody requestBody);

    void postEvaluations(RequestBody requestBody);

    void postExercises(RequestBody requestBody);

    void postSubmissions(RequestBody requestBody);

    void postBlob(RequestBody requestBody);

    TokenCustom postLogin(JsonObject requestBody);


    ///// redefinicion base de datos

    void updateMyGrade(GradeRemote gradesRemote, android.content.Context context, StudentRemote student);

    void updateMyStudent(StudentRemote studentRemote, android.content.Context context, Integer idUsuario);

    void updateMyTeachers(List<TeacherRemote> teacherList, android.content.Context context);

    void updateMySubjects(List<SubjectsRemote> subjectsList, Context context);

    void updateMyTasks(List<TaskRemote> taskList, Context context, StudentRemote student, Integer registro);

    void updateMyStudyMaterial(List<StudyMaterialRemote> studyMaterialList, android.content.Context context);

    void updateRelStudentSubjects(StudentRemote studentRemote, List<SubjectsRemote> subjectsRemoteList, android.content.Context context);

    void updateMyLessons(List<LessonsRemote> lessonsRemoteList, Context context);
    List<MessageRemote> updateMyMessages(List<MessageRemote> messageRemoteList, Context context,StudentRemote studentRemote);
    void updateMyFileMessage ( MessageRemote remoteMessage, FileMessageRemote fileMessageRemote ,Context context );

    Long updateMyUpload(Uploads upload, Context context);

    Long updateMyFileKiosco(List<FilesKiosco> filesKioscoList, Context context);
    List<LessonsRemote> getLessons(Context context, Integer idEstudiante);
    List<StudyMaterialRemote> getStudyMaterial(Context context,Integer idLesson);
    List<Task> getTasks(Context context, Integer idEstudiante);
    List<Lessons>getLessons(Context context, Integer idEstudiante,Integer idMateria);

    List<Task> getTasksToRegister (Context context, Integer idEstudiante);
    List<SubmissionPending> getSubmissionsToUpload (Context context , Integer idEstudiante);
    void updateTaskRegister(Task task, Context context);
    void updateSubmissionUpp(Integer RTentregaId , Context context);
    List<Task> getPendingTasks (Context context,Task task);
    List<MessageRemote> getMyMessages (Context context ,Integer idEstudiante);
    List<FileMessageRemote> getMyFilesMessage(Context context, Integer idMensajeKiosco);
    List<MessageRemote> getMyMessagesPendingRegist (Context context ,Integer idEstudiante);
    void updateMessageKiosco (MessageRemote messageRemote,Context context);
    List<MessageAnswer> getMessageAnswers(Context context,Integer idMensajeKiosco);
    void updateAnswerMessage (String body,Integer idMensajeKiosco , Context context);
    List<Task> getAllPendingTasks (Context context,Integer idEstudiante);
    List<MessageRemote> getAllMessagesToRegist (Context context ,Integer idEstudiante);
    void updateAnswerMessageState (Integer idMessageAnswer,Context context);
    Grade getGradeByStudent(Context context,Integer idEstudiante);
}