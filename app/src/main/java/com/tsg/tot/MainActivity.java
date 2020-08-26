package com.tsg.tot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.tsg.tot.data.model.Student;
import com.tsg.tot.data.model.Version;
import com.tsg.tot.data.remote.ApiService;
import com.tsg.tot.data.remote.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiService = ApiUtils.getAPIService();
        getVersion();
        getStudent();
    }

    private void getVersion() {
        mApiService.getVersion().enqueue(new Callback<List<Version>>() {
            @Override
            public void onResponse(Call<List<Version>> call, Response<List<Version>> response) {
                if (response.isSuccessful()) {
                    List<Version> version = response.body();
                    Log.d("Debug version id ", version.get(0).getId().toString());
                    Log.d("Debug version No ", version.get(0).getNumero().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Version>> call, Throwable t) {
                Log.d("onFailure getVersion", t.toString());
            }
        });
    }

    private void getStudent() {
        mApiService.getStudent().enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if (response.isSuccessful()) {
                    List<Student> student = response.body();
                    Log.d("Debug student id ", student.get(0).getId().toString());
                    Log.d("Debug student name ", student.get(0).getNombres());
                    Log.d("Debug student last name ", student.get(0).getApellidos());
                    Log.d("Debug student curse ", student.get(0).getCurso().getNombre());
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.d("onFailure getStudent", t.toString());
            }
        });
    }
}
