package com.tsg.tot.data.remote;

import com.tsg.tot.data.model.Lessons;
import com.tsg.tot.data.model.Student;
import com.tsg.tot.data.model.Version;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("versiones.json")
    Call<List<Version>> getVersion();

    @GET("estudiantes.json")
    Call<List<Student>> getStudent();

    @GET("clases.json")
    Call<List<Lessons>> getLessons();
}
