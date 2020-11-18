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
import com.tsg.tot.data.model.Upload;
import com.tsg.tot.data.model.Version;

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

    //@GET("api/gobernacion/cursos/versiones.json")
    @GET("api/gobernacion/cursos/versiones")
    Call<List<Version>> getVersion();

   // @GET("api/gobernacion/cursos/disposotivos.json")
    @GET("api/gobernacion/cursos/disposotivos")
    Call<List<Device>> getDevice();

    //@GET("api/gobernacion/cursos/clases.json")
    @GET("api/gobernacion/cursos/clases")
    Call<List<Lessons>> getLessons();

    //@GET("api/gobernacion/cursos/grados.json")
    @GET("api/gobernacion/cursos/grados")
    Call<List<Grade>> getGrade();

    //@GET("api/gobernacion/cursos/ejercicios.json")
    @GET("api/gobernacion/cursos/ejercicios")
    Call<List<Exercises>> getExercises();

    // @GET("api/gobernacion/cursos/entregas.json")
    @GET("api/gobernacion/cursos/entregas")
    Call<List<Submissions>> getSubmissions();

    //@GET("api/gobernacion/cursos/estudiantes.json")
    @GET("api/gobernacion/cursos/estudiantes")
    Call<List<Student>> getStudent();

    //@GET("api/gobernacion/cursos/evaluaciones.json")
    @GET("api/gobernacion/cursos/evaluaciones")
    Call<List<Evaluations>> getEvaluations();

    //@GET("api/gobernacion/cursos/materialestudio.json")
    @GET("api/gobernacion/cursos/materialestudio")
    Call<List<StudyMaterial>> getStudyMaterial();

    //@GET("api/gobernacion/cursos/materias.json")
    @GET("api/gobernacion/cursos/materias")
    Call<List<Subjects>> getSubjects();

    //@GET("api/gobernacion/cursos/planeacion.json")
    @GET("api/gobernacion/cursos/planeacion")
    Call<List<Planning>> getPlanning();

    //@GET("api/gobernacion/cursos/profesores.json")
    @GET("api/gobernacion/cursos/profesores")
    Call<List<Teacher>> getTeachers();

    //@GET("api/gobernacion/cursos/subidas.json")
    @GET("api/gobernacion/cursos/subidas")
    Call<List<Upload>> getUploads();

    //@GET("api/gobernacion/cursos/tareas.json")
    @GET("api/gobernacion/cursos/tareas")
    Call<List<Task>> getTasks();

    @POST("api/gobernacion/cursos/tareas/")
    Call<Task> postTask(@Body RequestBody body);

    @POST("api/gobernacion/cursos/evaluaciones/")
    Call<Evaluations> postEvaluations(@Body RequestBody body);

    @POST("api/gobernacion/cursos/ejercicios/")
    Call<Exercises> postExercises(@Body RequestBody body);

    @POST("api/gobernacion/cursos/entregas/")
    Call<Submissions> postSubmissions(@Body RequestBody body);

    @POST("upload/")
    Call<Blob> postBlob(@Body RequestBody body);
}
