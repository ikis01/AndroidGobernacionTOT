package com.tsg.tot.repository;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

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
import com.tsg.tot.data.model.Version;
import com.tsg.tot.data.remote.ApiService;
import com.tsg.tot.data.remote.ApiUtils;
import com.tsg.tot.data.remote.model.GradeRemote;
import com.tsg.tot.data.remote.model.LessonsRemote;
import com.tsg.tot.data.remote.model.StudentRemote;
import com.tsg.tot.data.remote.model.StudyMaterialRemote;
import com.tsg.tot.data.remote.model.SubjectsRemote;
import com.tsg.tot.data.remote.model.TaskRemote;
import com.tsg.tot.data.remote.model.TeacherRemote;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Clase que consulta los endpoint de la API
 */
public class ApiRepository implements RemoteRepository {

    private ApiService mApiService = ApiUtils.getAPIService();
    public float apiVersion;
    TokenCustom tokenCustom = new TokenCustom("", "");

    //List final for uptade DB
    List<Device> deviceListFinal;
    List<Lessons> lessonsListFinal;
    List<Grade> gradeListFinal;
    List<Exercises> exercisesListFinal;
    List<Submissions> submissionsListFinal;
    List<Student> studentListFinal;
    List<Evaluations> evaluationsListFinal;
    List<StudyMaterial> studyMaterialListFinal;
    List<SubjectsRemote> subjectsListFinal;
    List<Planning> planningListFinal;
    List<TeacherRemote> teacherListFinal;
    List<Upload> uploadListFinal;
    //List<Task> taskListFinal;
    List<TaskRemote> taskListFinal;

    GradeRemote gradeRemote;
    List<StudyMaterialRemote> studyMaterialRemoteList;
    StudentRemote studentRemote;
    List<LessonsRemote> lessonsRemoteList;

    //GET calls to API

    @Override
    public float getVersion(Context context) {
        mApiService.getVersion().enqueue(new Callback<List<Version>>() {
            @Override
            public void onResponse(Call<List<Version>> call, Response<List<Version>> response) {
                if (response.isSuccessful()) {
                    List<Version> versionList = response.body();

                    if (versionList != null) {
                        for (Version version : versionList) {
                            try {
                                Log.d("Debug version id ", version.getId().toString());
                                Log.d("Debug version No ", version.getNumero().toString());

                                apiVersion = version.getNumero().floatValue();
                            } catch (NullPointerException e) {
                                Log.d("Debug NullPointerException ", e.toString());
                            }
                        }
                    } else {
                        Log.d("Debug ", "versionList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Version>> call, Throwable t) {
                Log.d("onFailure getVersion", t.toString());
            }
        });

        return apiVersion;
    }

    @Override
    public List<Device> getDevice(Context context) {
        mApiService.getDevice().enqueue(new Callback<List<Device>>() {
            @Override
            public void onResponse(Call<List<Device>> call, Response<List<Device>> response) {
                if (response.isSuccessful()) {
                    List<Device> deviceList = response.body();
                    deviceListFinal = deviceList;
                    if (deviceList != null) {
                        for (Device device : deviceList) {
                            try {
                                Log.d("Debug device id ", device.getId().toString());
                                Log.d("Debug device mac ", device.getMAC());
                            } catch (NullPointerException e) {
                                Log.d("Debug NullPointerException ", e.toString());
                            }
                        }
                    } else {
                        Log.d("Debug ", "deviceList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Device>> call, Throwable t) {
                Log.d("onFailure getDevice", t.toString());
            }
        });

        return deviceListFinal;
    }

    @Override
    public List<Lessons> getLessons(Context context) {
        mApiService.getLessons().enqueue(new Callback<List<Lessons>>() {
            @Override
            public void onResponse(Call<List<Lessons>> call, Response<List<Lessons>> response) {
                if (response.isSuccessful()) {
                    List<Lessons> lessonsList = response.body();
                    lessonsListFinal = lessonsList;
                    if (lessonsList != null) {
                        for (Lessons lessons : lessonsList) {
                            try {
                                Log.d("Debug lessons id ", lessons.getId().toString());
                                Log.d("Debug lessons name ", lessons.getNombre());
                                Log.d("Debug lessons theme ", lessons.getTema());
                            } catch (NullPointerException e) {
                                Log.d("Debug NullPointerException ", e.toString());
                            }
                        }
                    } else {
                        Log.d("Debug ", "lessonsList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Lessons>> call, Throwable t) {
                Log.d("onFailure lessonsList", t.toString());
            }
        });

        return lessonsListFinal;
    }

    @Override
    public List<LessonsRemote> getMyLessons(Context context,String auth) {

        mApiService.getMyLessons(auth).enqueue(new Callback<List<LessonsRemote>>() {
            @Override
            public void onResponse(Call<List<LessonsRemote>> call, Response<List<LessonsRemote>> response) {
                if (response.isSuccessful()) {
                    List<LessonsRemote> lessonsList = response.body();
                    lessonsRemoteList = lessonsList;
                    if (lessonsRemoteList != null) {
                        for (LessonsRemote lesson : lessonsRemoteList) {
                            try {
                                Log.d("Debug LessonRemote id ", lesson.getId().toString());
                                Log.d("Debug LessonRemot name ", lesson.getNombre());
                            } catch (NullPointerException e) {
                                Log.d("Debug NullPointerException ", e.toString());
                            }
                        }
                    } else {
                        Log.d("Debug ", "lessonsRemoteList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<LessonsRemote>> call, Throwable t) {
                Log.d("onFailure getMyLessons", t.toString());
            }
        });

        return lessonsRemoteList;
    }

    @Override
    public GradeRemote getMyGrade(Context context, String auth) {
        mApiService.getMyGrade(auth).enqueue(new Callback<GradeRemote>() {
            @Override
            public void onResponse(Call<GradeRemote> call, Response <GradeRemote> response) {
                if (response.isSuccessful()) {
                    GradeRemote gradeRemoteResponse = response.body();
                    gradeRemote = gradeRemoteResponse;

                                Log.d("Debug grade id ", gradeRemote.getId().toString());
                                Log.d("Debug grade name ", gradeRemote.getNombre());

                    } else {
                        Log.d("Debug ", "gradeList is null");
                    }
                }


            @Override
            public void onFailure(Call<GradeRemote> call, Throwable t) {
                Log.d("onFailure getGrade", t.toString());
            }
        });

        return gradeRemote;
    }


    @Override
    public List<Grade> getGrade(Context context) {
        mApiService.getGrade().enqueue(new Callback<List<Grade>>() {
            @Override
            public void onResponse(Call<List<Grade>> call, Response<List<Grade>> response) {
                if (response.isSuccessful()) {
                    List<Grade> gradeList = response.body();
                    gradeListFinal = gradeList;
                    if (gradeList != null) {
                        for (Grade grade : gradeList) {
                            try {
                                Log.d("Debug grade id ", grade.getId().toString());
                                Log.d("Debug grade name ", grade.getNombre());
                            } catch (NullPointerException e) {
                                Log.d("Debug NullPointerException ", e.toString());
                            }
                        }
                    } else {
                        Log.d("Debug ", "gradeList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Grade>> call, Throwable t) {
                Log.d("onFailure getGrade", t.toString());
            }
        });

        return gradeListFinal;
    }

    @Override
    public List<Exercises> getExercises(Context context) {
        mApiService.getExercises().enqueue(new Callback<List<Exercises>>() {
            @Override
            public void onResponse(Call<List<Exercises>> call, Response<List<Exercises>> response) {
                if (response.isSuccessful()) {
                    List<Exercises> exercisesList = response.body();
                    exercisesListFinal = exercisesList;
                    if (exercisesList != null) {
                        for (Exercises exercises : exercisesList) {
                            try {
                                Log.d("Debug exercise id ", exercises.getId().toString());
                                Log.d("Debug exercise name ", exercises.getNombre());
                                Log.d("Debug exercise date", exercises.getSubida().getFecha());
                            } catch (NullPointerException e) {
                                Log.d("Debug NullPointerException ", e.toString());
                            }
                        }
                    } else {
                        Log.d("Debug ", "exercisesList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Exercises>> call, Throwable t) {
                Log.d("onFailure getExercises", t.toString());
            }
        });

        return exercisesListFinal;
    }

    @Override
    public List<Submissions> getSubmissions(Context context) {
        mApiService.getSubmissions().enqueue(new Callback<List<Submissions>>() {
            @Override
            public void onResponse(Call<List<Submissions>> call, Response<List<Submissions>> response) {
                if (response.isSuccessful()) {
                    List<Submissions> submissionsList = response.body();
                    submissionsListFinal = submissionsList;
                    if (submissionsList != null) {
                        for (Submissions submission : submissionsList) {
                            try {
                                Log.d("Debug submissions id ", submission.getId().toString());
                                Log.d("Debug submissions created at ", submission.getCreado());
                                Log.d("Debug submissions upp", submission.getUpp().toString());
                                Log.d("Debug submissions task ", submission.getTarea().toString());
                                Log.d("Debug submissions upload ", submission.getSubida().toString());
                                Log.d("Debug submissions student", submission.getEstudiante().toString());
                            } catch (NullPointerException e) {
                                Log.d("Debug NullPointerException ", e.toString());
                            }
                        }
                    } else {
                        Log.d("Debug ", "submissionsList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Submissions>> call, Throwable t) {
                Log.d("onFailure getSubmissions", t.toString());
            }
        });

        return submissionsListFinal;
    }

    @Override
    public List<Student> getStudent(Context context) {
        mApiService.getStudent().enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if (response.isSuccessful()) {
                    List<Student> studentList = response.body();
                    studentListFinal = studentList;
                    if (studentList != null) {
                        for (Student student : studentList) {
                            try {
                                Log.d("Debug student id ", student.getId().toString());
                                Log.d("Debug student name ", student.getNombres());
                                Log.d("Debug student last name ", student.getApellidos());
                                Log.d("Debug student curse ", student.getCurso().getNombre());
                            } catch (NullPointerException e) {
                                Log.d("Debug NullPointerException ", e.toString());
                            }
                        }
                    } else {
                        Log.d("Debug ", "studentList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.d("onFailure getStudent", t.toString());
            }
        });

        return studentListFinal;
    }

    @Override
    public List<Evaluations> getEvaluations(Context context) {
        mApiService.getEvaluations().enqueue(new Callback<List<Evaluations>>() {
            @Override
            public void onResponse(Call<List<Evaluations>> call, Response<List<Evaluations>> response) {
                if (response.isSuccessful()) {
                    List<Evaluations> evaluationsList = response.body();
                    evaluationsListFinal = evaluationsList;
                    if (evaluationsList != null) {
                        for (Evaluations evaluations : evaluationsList) {
                            try {
                                Log.d("Debug evaluations id ", evaluations.getId().toString());
                                Log.d("Debug evaluations upload id ", evaluations.getSubida().getId().toString());
                                Log.d("Debug evaluations upload date", evaluations.getSubida().getFecha());
                                Log.d("Debug evaluations name ", evaluations.getNombre());
                                Log.d("Debug evaluations subject ", evaluations.getMaterias().toString());
                            } catch (NullPointerException e) {
                                Log.d("Debug evaluations NullPointerException ", e.toString());
                            }

                        }
                    } else {
                        Log.d("Debug ", "submissionsList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Evaluations>> call, Throwable t) {
                Log.d("onFailure getEvaluations", t.toString());
            }
        });

        return evaluationsListFinal;
    }


    @Override
    public List<StudyMaterial> getStudyMaterial(Context context) {
        mApiService.getStudyMaterial().enqueue(new Callback<List<StudyMaterial>>() {
            @Override
            public void onResponse(Call<List<StudyMaterial>> call, Response<List<StudyMaterial>> response) {
                if (response.isSuccessful()) {
                    List<StudyMaterial> studyMaterialList = response.body();
                    studyMaterialListFinal = studyMaterialList;
                    if (studyMaterialList != null) {
                        for (StudyMaterial studyMaterial : studyMaterialList) {
                            try {
                                Log.d("Debug studyMaterial id ", studyMaterial.getId().toString());
                                Log.d("Debug studyMaterial name ", studyMaterial.getNombre());
                                Log.d("Debug studyMaterial description", studyMaterial.getDescripcion());
                                Log.d("Debug studyMaterial class ", studyMaterial.getClases().toString());
                                Log.d("Debug studyMaterial blob ", studyMaterial.getBlob().toString());
                            } catch (NullPointerException e) {
                                Log.d("Debug NullPointerException ", e.toString());
                            }
                        }
                    } else {
                        Log.d("Debug ", "studyMaterialList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<StudyMaterial>> call, Throwable t) {
                Log.d("onFailure getStudyMaterial", t.toString());
            }
        });

        return studyMaterialListFinal;
    }

    @Override
    public List<SubjectsRemote> getMySubjects(Context context, String auth) {
        mApiService.getMySubjects(auth).enqueue(new Callback<List<SubjectsRemote>>() {
            @Override
            public void onResponse(Call<List<SubjectsRemote>> call, Response<List<SubjectsRemote>> response) {
                if (response.isSuccessful()) {
                    List<SubjectsRemote> subjectsList = response.body();
                    subjectsListFinal = subjectsList;
                    if (subjectsList != null) {
                        for (SubjectsRemote subjects : subjectsList) {
                            try {
                                Log.d("Debug subjects id ", subjects.getId().toString());
                            } catch (NullPointerException e) {
                                Log.d("Debug NullPointerException ", e.toString());
                            }
                        }
                    } else {
                        Log.d("Debug ", "subjectsList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SubjectsRemote>> call, Throwable t) {
                Log.d("onFailure getMySubjects", t.toString());
            }
        });

        return subjectsListFinal;
    }

    @Override
    public List<Planning> getPlanning(Context context) {
        mApiService.getPlanning().enqueue(new Callback<List<Planning>>() {
            @Override
            public void onResponse(Call<List<Planning>> call, Response<List<Planning>> response) {
                if (response.isSuccessful()) {
                    List<Planning> planningList = response.body();
                    planningListFinal = planningList;
                    if (planningList != null) {
                        for (Planning planning : planningList) {
                            try {
                                Log.d("Debug planning id ", planning.getId().toString());
                                Log.d("Debug planning date ", planning.getFecha());
                                Log.d("Debug planning task ", planning.getTarea().toString());
                                Log.d("Debug planning exercise ", planning.getEjercicios().toString());
                                Log.d("Debug planning class ", planning.getClase().toString());
                                Log.d("Debug planning evaluation ", planning.getEvaluacion().toString());
                            } catch (NullPointerException e) {
                                Log.d("Debug NullPointerException ", e.toString());
                            }
                        }
                    } else {
                        Log.d("Debug ", "planningList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Planning>> call, Throwable t) {
                Log.d("onFailure getPlanning", t.toString());
            }
        });

        return planningListFinal;
    }

    @Override
    public List<TeacherRemote> getTeachers(Context context, String auth) {
        mApiService.getTeachers(auth).enqueue(new Callback<List<TeacherRemote>>() {
            @Override
            public void onResponse(Call<List<TeacherRemote>> call, Response<List<TeacherRemote>> response) {
                if (response.isSuccessful()) {
                    List<TeacherRemote> teacherList = response.body();
                    teacherListFinal = teacherList;
                    if (teacherList != null) {
                        for (TeacherRemote teacher : teacherList) {
                            try {
                                Log.d("Debug teacher id ", teacher.getId().toString());
                                //Log.d("Debug teacher code ", teacher.getCodigo().toString());
                                Log.d("Debug teacher name ", teacher.getNombres());
                                Log.d("Debug teacher last name ", teacher.getApellidos());
                                //Log.d("Debug teacher birth date ", teacher.getFechaNacimiento());
                            } catch (NullPointerException e) {
                                Log.d("Debug NullPointerException ", e.toString());
                            }
                        }
                    } else {
                        Log.d("Debug ", "teacherList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<TeacherRemote>> call, Throwable t) {
                Log.d("onFailure getTeachers", t.toString());
            }
        });

        return teacherListFinal;
    }

    @Override
    public List<Upload> getUploads(Context context) {
        mApiService.getUploads().enqueue(new Callback<List<Upload>>() {
            @Override
            public void onResponse(Call<List<Upload>> call, Response<List<Upload>> response) {
                if (response.isSuccessful()) {
                    List<Upload> uploadList = response.body();
                    uploadListFinal = uploadList;
                    if (uploadList != null) {
                        for (Upload upload : uploadList) {
                            try {
                                Log.d("Debug upload id ", upload.getId().toString());
                                Log.d("Debug upload date ", upload.getFecha());
                            } catch (NullPointerException e) {
                                Log.d("Debug NullPointerException ", e.toString());
                            }
                        }
                    } else {
                        Log.d("Debug ", "uploadList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Upload>> call, Throwable t) {
                Log.d("onFailure getUploads", t.toString());
            }
        });

        return uploadListFinal;
    }

    @Override
    public List<TaskRemote> getTasks(Context context, String authKey) {

        mApiService.getTasks(authKey).enqueue(new Callback<List<TaskRemote>>() {

            @Override
            public void onResponse(Call<List<TaskRemote>> call, Response<List<TaskRemote>> response) {
                if (response.isSuccessful()) {
                    List<TaskRemote> taskList = response.body();
                    taskListFinal = taskList;
                    if (taskList != null) {
                        for (TaskRemote task : taskList) {
                            try {
                                Log.d("Debug task id ", task.getTareaId().toString());
                                Log.d("Debug task upload id ", task.getIdArchivoD2L().toString());

                            } catch (NullPointerException e) {
                                Log.d("Debug NullPointerException ", e.toString());
                            }
                        }
                    } else {
                        Log.d("Debug ", "taskList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<TaskRemote>> call, Throwable t) {
                Log.d("onFailure getTasks", t.toString());
            }
        });

        return taskListFinal;

    }

    //POST calls to API

    @Override
    public void updateVersion(float version, Context context) {

    }

    @Override
    public void updateDevice(List<Device> devicesList, Context context) {

    }

    @Override
    public void updateLessons(List<Lessons> lessonsList, Context context) {

    }

    @Override
    public void updateGrade(List<Grade> gradesList, Context context) {

    }

    @Override
    public void updateExercises(List<Exercises> exercisesList, Context context) {

    }

    @Override
    public void updateSubmissions(List<Submissions> submissionsList, Context context) {

    }

    @Override
    public void updateStudent(List<Student> studentList, Context context) {

    }

    @Override
    public void updateEvaluations(List<Evaluations> evaluationsList, Context context) {

    }

    @Override
    public void updateStudyMaterial(List<StudyMaterial> studyMaterialList, Context context) {

    }

    @Override
    public void updateSubjects(List<Subjects> subjectsList, Context context) {

    }

    @Override
    public void updatePlanning(List<Planning> planningList, Context context) {

    }

    @Override
    public void updateTeachers(List<Teacher> teacherList, Context context) {

    }

    @Override
    public void updateUploads(List<Upload> uploadList, Context context) {

    }

    @Override
    public void updateTasks(List<Task> taskList, Context context) {

    }

    @Override
    public void updateBlobs(List<Blob> blobList, Context context) {

    }

    @Override
    public StudentRemote getMyStudent(Context context, String auth) {
        mApiService.getMyStudent(auth).enqueue(new Callback<StudentRemote>() {
            @Override
            public void onResponse(Call<StudentRemote> call, Response<StudentRemote> response) {
                if (response.isSuccessful()) {
                    StudentRemote student = response.body();
                    studentRemote = student;
                    if (student != null) {
                            try {
                                Log.d("Debug my student id ", student.getId().toString());
                                Log.d("Debug my student  name ", student.getNombre());
                            } catch (NullPointerException e) {
                                Log.d("Debug NullPointerException ", e.toString());
                            }

                    } else {
                        Log.d("Debug ", "gradeList is null");
                    }
                }

            }

            @Override
            public void onFailure(Call<StudentRemote> call, Throwable t) {

            }
        });
        return studentRemote;
    }

    @Override
    public List<StudyMaterialRemote> getMyPendingStudyMaterial(Context context, String auth) {

        mApiService.getMyPendingStudyMaterial(auth).enqueue(new Callback<List<StudyMaterialRemote>>() {
            @Override
            public void onResponse(Call<List<StudyMaterialRemote>> call, Response<List<StudyMaterialRemote>> response) {
                if (response.isSuccessful()) {
                    List<StudyMaterialRemote> studyMaterialRemotes = response.body();
                    studyMaterialRemoteList = studyMaterialRemotes;
                    if (studyMaterialRemoteList != null) {
                        for (StudyMaterialRemote material : studyMaterialRemoteList) {
                            try {
                                Log.d("Debug Material Estudio  id ", material.getId().toString());
                                Log.d("Debug Material Estudio name  ", material.getNombreArchivo());
                            } catch (NullPointerException e) {
                                Log.d("Debug NullPointerException ", e.toString());
                            }
                        }
                    } else {
                        Log.d("Debug ", "gradeList is null");
                    }
                }

            }

            @Override
            public void onFailure(Call<List<StudyMaterialRemote>> call, Throwable t) {

            }
        });
        return studyMaterialRemoteList;
    }

    @Override
    public void postTask(RequestBody requestBody) {
        mApiService.postTask(requestBody).enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.isSuccessful()) {
                    //TODO POST Blob
                    Log.d("POST", "post submitted to API." + response.body());
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                Log.d("POST", "Unable to submit post to API.");
            }
        });
    }

    @Override
    public void postEvaluations(RequestBody requestBody) {
        mApiService.postEvaluations(requestBody).enqueue(new Callback<Evaluations>() {
            @Override
            public void onResponse(Call<Evaluations> call, Response<Evaluations> response) {
                if (response.isSuccessful()) {
                    //TODO POST Blob
                    Log.d("POST", "post submitted to API." + response.body());
                }
            }

            @Override
            public void onFailure(Call<Evaluations> call, Throwable t) {
                Log.d("POST", "Unable to submit post to API.");
            }
        });
    }

    @Override
    public void updateUser(ContentValues cv, Context context) {

    }

    @Override
    public List<Users> getUsers(ContentValues cv, Context context) {
        return null;
    }

    @Override
    public void postExercises(RequestBody requestBody) {
        mApiService.postExercises(requestBody).enqueue(new Callback<Exercises>() {
            @Override
            public void onResponse(Call<Exercises> call, Response<Exercises> response) {
                if (response.isSuccessful()) {
                    //TODO POST Blob
                    Log.d("POST", "post submitted to API." + response.body());
                }
            }

            @Override
            public void onFailure(Call<Exercises> call, Throwable t) {
                Log.d("POST", "Unable to submit post to API.");
            }
        });
    }

    @Override
    public void postSubmissions(RequestBody requestBody) {
        mApiService.postSubmissions(requestBody).enqueue(new Callback<Submissions>() {
            @Override
            public void onResponse(Call<Submissions> call, Response<Submissions> response) {
                if (response.isSuccessful()) {
                    //TODO POST Blob
                    Log.d("POST", "post submitted to API." + response.body());
                }
            }

            @Override
            public void onFailure(Call<Submissions> call, Throwable t) {
                Log.d("POST", "Unable to submit post to API.");
            }
        });
    }

    @Override
    public TokenCustom postLogin(JsonObject requestBody) {

        mApiService.postLogin(requestBody).enqueue(new Callback<TokenCustom>() {

            @Override
            public void onResponse(Call<TokenCustom> call, Response<TokenCustom> response) {
                if (response.isSuccessful()) {
                    tokenCustom = response.body();
                }
            }

            @Override
            public void onFailure(Call<TokenCustom> call, Throwable t) {
                Log.d("onFailure getVersion", t.toString());
            }
        });

        return tokenCustom;
    }

    @Override
    public void postBlob(RequestBody body) {
        mApiService.postBlob(body).enqueue(new Callback<Blob>() {
            @Override
            public void onResponse(Call<Blob> call, Response<Blob> response) {
                if (response.isSuccessful()) {
                    Log.d("POST", "post submitted to API." + response.body());
                }
            }

            @Override
            public void onFailure(Call<Blob> call, Throwable t) {
                Log.d("POST", "Unable to submit post to API.");
            }
        });
    }
}
