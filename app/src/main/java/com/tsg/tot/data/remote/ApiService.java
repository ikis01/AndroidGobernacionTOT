package com.tsg.tot.data.remote;

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
import com.tsg.tot.data.model.Token;
import com.tsg.tot.data.model.Upload;
import com.tsg.tot.data.model.Version;

import java.io.StringReader;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * End Point del API
 */
public interface ApiService {
    String POST_LOGIN = "api/cuentas/login";
    String GET_VERSION ="api/gobernacion/cursos/versiones";
    String GET_DEVICE = "api/gobernacion/cursos/disposotivos";
    String GET_LESSONS = "api/gobernacion/cursos/clases" ;
    String GET_GRADE = "api/gobernacion/cursos/grados";
    String GET_EXERCISES = "api/gobernacion/cursos/ejercicios";
    String GET_SUBMISSIONS = "api/gobernacion/cursos/entregas";
    String GET_STUDENT = "api/gobernacion/cursos/estudiantes";
    String GET_EVALUATIONS = "api/gobernacion/cursos/evaluaciones";
    String GET_STUDY_MATERIAL="api/gobernacion/cursos/materialestudio";
    String GET_SUBJECTS = "api/gobernacion/cursos/materias";
    String GET_PLANNING = "api/gobernacion/cursos/planeacion";
    String GET_TEACHERS = "api/gobernacion/cursos/profesores";
    String GET_UPLOADS ="api/gobernacion/cursos/subidas";
    String GET_TASKS = "api/gobernacion/cursos/tareas";
    String POST_TASK = "api/gobernacion/cursos/tareas/";
    String POST_EVALUATION = "api/gobernacion/cursos/evaluaciones/";
    String POST_EXERCISES ="api/gobernacion/cursos/ejercicios/";
    String POST_SUBMISSIONS ="api/gobernacion/cursos/entregas/";
    String POST_BLOB =  "upload";

    @GET(GET_VERSION)
    Call<List<Version>> getVersion();

    @GET(GET_DEVICE)
    Call<List<Device>> getDevice();

    @GET(GET_LESSONS)
    Call<List<Lessons>> getLessons();

    @GET(GET_GRADE)
    Call<List<Grade>> getGrade();

    @GET(GET_EXERCISES)
    Call<List<Exercises>> getExercises();

    @GET(GET_SUBMISSIONS)
    Call<List<Submissions>> getSubmissions();

    @GET(GET_STUDENT)
    Call<List<Student>> getStudent();

    @GET(GET_EVALUATIONS)
    Call<List<Evaluations>> getEvaluations();

    @GET(GET_STUDY_MATERIAL)
    Call<List<StudyMaterial>> getStudyMaterial();

    @GET(GET_SUBJECTS)
    Call<List<Subjects>> getSubjects();

    @GET(GET_PLANNING)
    Call<List<Planning>> getPlanning();

    @GET(GET_TEACHERS)
    Call<List<Teacher>> getTeachers();

    @GET(GET_UPLOADS)
    Call<List<Upload>> getUploads();

    @GET(GET_TASKS)
    Call<List<Task>> getTasks();

    @POST(POST_TASK)
    Call<Task> postTask(@Body RequestBody body);

    @POST(POST_EVALUATION)
    Call<Evaluations> postEvaluations(@Body RequestBody body);

    @POST(POST_EXERCISES)
    Call<Exercises> postExercises(@Body RequestBody body);

    @POST(POST_SUBMISSIONS)
    Call<Submissions> postSubmissions(@Body RequestBody body);

    @POST(POST_BLOB)
    Call<Blob> postBlob(@Body RequestBody body);

    @POST(POST_LOGIN)
    Call<Token> postLogin(@Body RequestBody body);
}
