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

public interface ApiService {

    @GET("api/gobernacion/cursos/versiones.json")
    Call<List<Version>> getVersion();

    @GET("api/gobernacion/cursos/disposotivos.json")
    Call<List<Device>> getDevice();

    @GET("api/gobernacion/cursos/clases.json")
    Call<List<Lessons>> getLessons();

    @GET("api/gobernacion/cursos/grados.json")
    Call<List<Grade>> getGrade();

    @GET("api/gobernacion/cursos/ejercicios.json")
    Call<List<Exercises>> getExercises();

    @GET("api/gobernacion/cursos/entregas.json")
    Call<List<Submissions>> getSubmissions();

    @GET("api/gobernacion/cursos/estudiantes.json")
    Call<List<Student>> getStudent();

    @GET("api/gobernacion/cursos/evaluaciones.json")
    Call<List<Evaluations>> getEvaluations();

    @GET("api/gobernacion/cursos/materialestudio.json")
    Call<List<StudyMaterial>> getStudyMaterial();

    @GET("api/gobernacion/cursos/materias.json")
    Call<List<Subjects>> getSubjects();

    @GET("api/gobernacion/cursos/planeacion.json")
    Call<List<Planning>> getPlanning();

    @GET("api/gobernacion/cursos/profesores.json")
    Call<List<Teacher>> getTeachers();

    @GET("api/gobernacion/cursos/subidas.json")
    Call<List<Upload>> getUploads();

    @GET("api/gobernacion/cursos/tareas.json")
    Call<List<Task>> getTasks();

    @POST("upload/")
    Call<Blob> uploadBlob(@Body RequestBody body);
}
