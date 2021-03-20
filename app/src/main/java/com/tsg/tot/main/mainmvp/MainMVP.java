package com.tsg.tot.main.mainmvp;

import android.content.Context;

import com.google.gson.JsonObject;
import com.tsg.tot.data.model.Device;
import com.tsg.tot.data.model.Evaluations;
import com.tsg.tot.data.model.Exercises;
import com.tsg.tot.data.model.FilesKiosco;
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
import com.tsg.tot.data.remote.model.GradeRemote;
import com.tsg.tot.data.remote.model.LessonsRemote;
import com.tsg.tot.data.remote.model.StudentRemote;
import com.tsg.tot.data.remote.model.StudyMaterialRemote;
import com.tsg.tot.data.remote.model.SubjectsRemote;
import com.tsg.tot.data.remote.model.TaskRegristerRemote;
import com.tsg.tot.data.remote.model.TaskRemote;
import com.tsg.tot.data.remote.model.TeacherRemote;
import com.tsg.tot.main.fragment.CustomProgressDialog;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;

public interface MainMVP {

    interface View {

        void setInfoStudent(List<Student> studentList);

        void setInfoSubject(Subjects subjects);


        void notifyRefresh();

        void showLoadingDialog();

        void dismissLoadingDialog();

        void setFileKiosco(FilesKiosco fileKiosco);

    }

    interface Presenter {



        void setView(MainMVP.View view);

        void createDB(Context context);

        void checkVersions(Context context, CustomProgressDialog dialog,String token,String idUsuario);
        void checkVersionsSync(Context context, CustomProgressDialog dialog,String token,String idUsuario);

        List<Subjects> getSubjects(Context context,String token,Integer code);
        List<FilesKiosco>getFileKioscos(Context context,Integer idEstudiante,Integer idMateria,Integer idTarea);

        List<Task> getTaskSubject(Context context, int idSubject,String token,Integer idEstudiante);

        void setInfoStudent(Context context, Integer idUsuario);

        void setInfoSubject(Subjects subjects);


        void setFileKiosco(FilesKiosco fileKiosco);

        void notifyRefresh();
        void updateEverything (Context context, CustomProgressDialog dialog,String token,Integer idUsuario);
        void testPOST(Context context);
        //void uploadFileTest (Context context);


    }

    interface Model {

        interface OnFinishedListener {
            void onFailure(Throwable t);
        }

        void createDb(Context context);

        float checkAPIVersion(Context context);

        float checkDbVersion(Context context);

        Boolean updateDatabase(String idUsuario ,
                            Context context ,
                            float version,
                            GradeRemote gradeRemote,
                            StudentRemote studentRemote,
                            List<TeacherRemote> teacherRemoteList,
                            List<SubjectsRemote> subjectsRemoteList,
                            List<TaskRemote>taskRemoteList,
                            List<StudyMaterialRemote> materialRemoteList,
                            List<LessonsRemote> lessonsRemoteList,
                            String token,
                            CustomProgressDialog dialog);



        void updateAllDb(float versionList,
                         List<Task> taskList,
                         List<Upload> uploadList,
                         List<Teacher> teacherList,
                         List<Subjects> subjectsList,
                         List<Grade> gradeList,
                         List<StudyMaterial> studyMaterialList,
                         List<Evaluations> evaluationsList,
                         List<Student> studentList,
                         List<Submissions> submissionsList,
                         List<Exercises> exercisesList,
                         List<Lessons> lessonsList,
                         Context context,CustomProgressDialog dialog);

        //Methods for downloading data from Endpoints

        List<Device> checkDevices(Context context, int from);

        List<Lessons> checkLessons(Context context, int from);
        List<LessonsRemote> checkMyLessons (Context context, int from ,String token);

        List<Grade> checkGrades(Context context, int from);

        List<Exercises> checkExercises(Context context, int from);

        List<Submissions> checkSubmissions(Context context, int from);

        List<Student> checkStudents(Context context, int from, Integer idUsuario);

        List<Evaluations> checkEvaluations(Context context, int from);

        List<StudyMaterial> checkStudyMaterials(Context context, int from);




        List<Subjects> checkSubjects(Context context, int from, String auth,Integer code);

        List<Planning> checkPlanning(Context context, int from);

        List<Teacher> checkTeachers(Context context, int from,String auth);

        List<TeacherRemote> checkMyTeachers(Context context, int from, String auth);



        List<Upload> checkUploads(Context context, int from);

        List<Task> checkTasks(Context context, int from,String token,Integer idEstudiante);
        List<TaskRemote> checkMyTasks(Context context, int from,String token);

        GradeRemote checkMyGrade (Context context, int from , String auth);

        StudentRemote checkMyStudent(Context context,int from,String auth);

        List<SubjectsRemote> checkMySubjects(Context context ,int from ,String auth);


        List<StudyMaterialRemote> checkMyPendingStudyMaterials(Context context,int from ,String token);
         TaskRegristerRemote registerTask(Context context, Integer idTarea, String token, JsonObject body);
        TaskRegristerRemote  postUploadTask(Context context, String token,MultipartBody.Part file,MultipartBody tareaRegistroId, MultipartBody mac);

        List<FilesKiosco> checkMyFileskioscos (Context  context, Integer idEstudiante,  Integer idMateria,Integer idTarea);

        //Methods for POST data on Endpoints

        void postTask(String uploadId, String subjectId, String name, String code);

        void postEvaluations(String uploadId, String name, String subjectId);

        void postExercises(String uploadId, String name, String lessonsId);

        void postSubmissions(String upp, String exercisesId, String taskId, String evaluationId, String uploadId, String studentId);

        void postBlob(String file, String subida_id, String entrega_id);
    }

}
