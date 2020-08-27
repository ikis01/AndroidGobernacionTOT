package com.tsg.tot.repository;

import android.util.Log;

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
import com.tsg.tot.data.remote.ApiService;
import com.tsg.tot.data.remote.ApiUtils;
import com.tsg.tot.main.MainMVP;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemoryRepository implements Repository {
    private ApiService mApiService = ApiUtils.getAPIService();

    @Override
    public void getVersion(MainMVP.Model.OnFinishedListener onFinishedListener) {
        mApiService.getVersion().enqueue(new Callback<List<Version>>() {
            @Override
            public void onResponse(Call<List<Version>> call, Response<List<Version>> response) {
                if (response.isSuccessful()) {
                    List<Version> versionList = response.body();

                    if (versionList != null) {
                        for (Version version : versionList) {
                            Log.d("Debug version id ", version.getId().toString());
                            Log.d("Debug version No ", version.getNumero().toString());
                        }

                        onFinishedListener.onFinished(versionList);
                    } else {
                        Log.d("Debug ", "versionList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Version>> call, Throwable t) {
                Log.d("onFailure getVersion", t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }

    @Override
    public void getDevice(MainMVP.Model.OnFinishedListener onFinishedListener) {
        mApiService.getDevice().enqueue(new Callback<List<Device>>() {
            @Override
            public void onResponse(Call<List<Device>> call, Response<List<Device>> response) {
                if (response.isSuccessful()) {
                    List<Device> deviceList = response.body();

                    if (deviceList != null) {
                        for (Device device : deviceList) {
                            Log.d("Debug device id ", device.getId().toString());
                            Log.d("Debug device mac ", device.getMAC());
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
    }

    @Override
    public void getLessons(MainMVP.Model.OnFinishedListener onFinishedListener) {
        mApiService.getLessons().enqueue(new Callback<List<Lessons>>() {
            @Override
            public void onResponse(Call<List<Lessons>> call, Response<List<Lessons>> response) {
                if (response.isSuccessful()) {
                    List<Lessons> lessonsList = response.body();

                    if (lessonsList != null) {
                        for (Lessons lessons : lessonsList) {
                            Log.d("Debug lessons id ", lessons.getId().toString());
                            Log.d("Debug lessons name ", lessons.getNombre());
                            Log.d("Debug lessons theme ", lessons.getTema());
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
    }

    @Override
    public void getGrade(MainMVP.Model.OnFinishedListener onFinishedListener) {
        mApiService.getGrade().enqueue(new Callback<List<Grade>>() {
            @Override
            public void onResponse(Call<List<Grade>> call, Response<List<Grade>> response) {
                if (response.isSuccessful()) {
                    List<Grade> gradeList = response.body();

                    if (gradeList != null) {
                        for (Grade grade : gradeList) {
                            Log.d("Debug grade id ", grade.getId().toString());
                            Log.d("Debug grade name ", grade.getNombre());
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
    }

    @Override
    public void getExercises(MainMVP.Model.OnFinishedListener onFinishedListener) {
        mApiService.getExercises().enqueue(new Callback<List<Exercises>>() {
            @Override
            public void onResponse(Call<List<Exercises>> call, Response<List<Exercises>> response) {
                if (response.isSuccessful()) {
                    List<Exercises> exercisesList = response.body();

                    if (exercisesList != null) {
                        for (Exercises exercises : exercisesList) {
                            Log.d("Debug exercise id ", exercises.getId().toString());
                            Log.d("Debug exercise name ", exercises.getNombre());
                            Log.d("Debug exercise date", exercises.getSubida().getFecha());
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
    }

    @Override
    public void getSubmissions(MainMVP.Model.OnFinishedListener onFinishedListener) {
        mApiService.getSubmissions().enqueue(new Callback<List<Submissions>>() {
            @Override
            public void onResponse(Call<List<Submissions>> call, Response<List<Submissions>> response) {
                if (response.isSuccessful()) {
                    List<Submissions> submissionsList = response.body();

                    if (submissionsList != null) {
                        for (Submissions submission : submissionsList) {
                            Log.d("Debug submissions id ", submission.getId().toString());
                            Log.d("Debug submissions created at ", submission.getCreado());
                            Log.d("Debug submissions upp", submission.getUpp().toString());
                            Log.d("Debug submissions exercises ", submission.getEjercios().toString());
                            Log.d("Debug submissions task ", submission.getTarea().toString());
                            Log.d("Debug submissions evaluation", submission.getEvaluacion().toString());
                            Log.d("Debug submissions upload ", submission.getSubida().toString());
                            Log.d("Debug submissions student", submission.getEstudiante().toString());
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
    }

    @Override
    public void getStudent(MainMVP.Model.OnFinishedListener onFinishedListener) {
        mApiService.getStudent().enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if (response.isSuccessful()) {
                    List<Student> studentList = response.body();

                    if (studentList != null) {
                        for (Student student : studentList) {
                            Log.d("Debug student id ", student.getId().toString());
                            Log.d("Debug student name ", student.getNombres());
                            Log.d("Debug student last name ", student.getApellidos());
                            Log.d("Debug student curse ", student.getCurso().getNombre());
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
    }

    @Override
    public void getEvaluations(MainMVP.Model.OnFinishedListener onFinishedListener) {
        mApiService.getEvaluations().enqueue(new Callback<List<Evaluations>>() {
            @Override
            public void onResponse(Call<List<Evaluations>> call, Response<List<Evaluations>> response) {
                if (response.isSuccessful()) {
                    List<Evaluations> evaluationsList = response.body();

                    if (evaluationsList != null) {
                        for (Evaluations evaluations : evaluationsList) {
                            Log.d("Debug evaluations id ", evaluations.getId().toString());
                            Log.d("Debug evaluations upload id ", evaluations.getSubida().getId().toString());
                            Log.d("Debug evaluations upload date", evaluations.getSubida().getFecha());
                            Log.d("Debug evaluations name ", evaluations.getNombre());
                            Log.d("Debug evaluations subject ", evaluations.getMaterias().toString());
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
    }

    @Override
    public void getStudyMaterial(MainMVP.Model.OnFinishedListener onFinishedListener) {
        mApiService.getStudyMaterial().enqueue(new Callback<List<StudyMaterial>>() {
            @Override
            public void onResponse(Call<List<StudyMaterial>> call, Response<List<StudyMaterial>> response) {
                if (response.isSuccessful()) {
                    List<StudyMaterial> studyMaterialList = response.body();

                    if (studyMaterialList != null) {
                        for (StudyMaterial studyMaterial : studyMaterialList) {
                            Log.d("Debug studyMaterial id ", studyMaterial.getId().toString());
                            Log.d("Debug studyMaterial name ", studyMaterial.getNombre());
                            Log.d("Debug studyMaterial description", studyMaterial.getDescripcion());
                            Log.d("Debug studyMaterial class ", studyMaterial.getClases().toString());
                            Log.d("Debug studyMaterial blob ", studyMaterial.getBlob().toString());
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
    }

    @Override
    public void getSubjects(MainMVP.Model.OnFinishedListener onFinishedListener) {
        mApiService.getSubjects().enqueue(new Callback<List<Subjects>>() {
            @Override
            public void onResponse(Call<List<Subjects>> call, Response<List<Subjects>> response) {
                if (response.isSuccessful()) {
                    List<Subjects> subjectsList = response.body();

                    if (subjectsList != null) {
                        for (Subjects subjects : subjectsList) {
                            Log.d("Debug subjects id ", subjects.getId().toString());
                            Log.d("Debug subjects curse id ", subjects.getCurso().getId().toString());
                            Log.d("Debug subjects curse name ", subjects.getCurso().getNombre());
                            Log.d("Debug subjects teacher id ", subjects.getProfesor().getId().toString());
                            Log.d("Debug subjects teacher code ", subjects.getProfesor().getCodigo().toString());
                            Log.d("Debug subjects teacher name ", subjects.getProfesor().getNombres());
                            Log.d("Debug subjects teacher last name ", subjects.getProfesor().getApellidos());
                            Log.d("Debug subjects teacher birth date", subjects.getProfesor().getFechaNacimiento());
                            Log.d("Debug subjects code ", subjects.getCodigo());
                            Log.d("Debug subjects title ", subjects.getTitulo());
                            Log.d("Debug subjects subtitle", subjects.getSubtitulo());
                            Log.d("Debug subjects description ", subjects.getDescripcion());
                            Log.d("Debug subjects image ", subjects.getImagen());
                        }
                    } else {
                        Log.d("Debug ", "subjectsList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Subjects>> call, Throwable t) {
                Log.d("onFailure getSubjects", t.toString());
            }
        });
    }

    @Override
    public void getPlanning(MainMVP.Model.OnFinishedListener onFinishedListener) {
        mApiService.getPlanning().enqueue(new Callback<List<Planning>>() {
            @Override
            public void onResponse(Call<List<Planning>> call, Response<List<Planning>> response) {
                if (response.isSuccessful()) {
                    List<Planning> planningList = response.body();

                    if (planningList != null) {
                        for (Planning planning : planningList) {
                            Log.d("Debug planning id ", planning.getId().toString());
                            Log.d("Debug planning date ", planning.getFecha());
                            Log.d("Debug planning task ", planning.getTarea().toString());
                            Log.d("Debug planning exercise ", planning.getEjercicios().toString());
                            Log.d("Debug planning class ", planning.getClase().toString());
                            Log.d("Debug planning evaluation ", planning.getEvaluacion().toString());
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
    }

    @Override
    public void getTeachers(MainMVP.Model.OnFinishedListener onFinishedListener) {
        mApiService.getTeachers().enqueue(new Callback<List<Teacher>>() {
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                if (response.isSuccessful()) {
                    List<Teacher> teacherList = response.body();

                    if (teacherList != null) {
                        for (Teacher teacher : teacherList) {
                            Log.d("Debug teacher id ", teacher.getId().toString());
                            Log.d("Debug teacher code ", teacher.getCodigo().toString());
                            Log.d("Debug teacher name ", teacher.getNombres());
                            Log.d("Debug teacher last name ", teacher.getApellidos());
                            Log.d("Debug teacher birth date ", teacher.getFechaNacimiento());
                        }
                    } else {
                        Log.d("Debug ", "teacherList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable t) {
                Log.d("onFailure getTeachers", t.toString());
            }
        });
    }

    @Override
    public void getUploads(MainMVP.Model.OnFinishedListener onFinishedListener) {
        mApiService.getUploads().enqueue(new Callback<List<Upload>>() {
            @Override
            public void onResponse(Call<List<Upload>> call, Response<List<Upload>> response) {
                if (response.isSuccessful()) {
                    List<Upload> uploadList = response.body();

                    if (uploadList != null) {
                        for (Upload upload : uploadList) {
                            Log.d("Debug upload id ", upload.getId().toString());
                            Log.d("Debug upload date ", upload.getFecha());
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
    }

    @Override
    public void getTasks(MainMVP.Model.OnFinishedListener onFinishedListener) {
        mApiService.getTasks().enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (response.isSuccessful()) {
                    List<Task> taskList = response.body();

                    if (taskList != null) {
                        for (Task task : taskList) {
                            Log.d("Debug task id ", task.getId().toString());
                            Log.d("Debug task upload id ", task.getSubida().getId().toString());
                            Log.d("Debug task upload date ", task.getSubida().getFecha());
                            Log.d("Debug task name ", task.getNombre());
                            Log.d("Debug task code ", task.getCodigo());
                            Log.d("Debug task subject ", task.getMaterias().toString());
                        }
                    } else {
                        Log.d("Debug ", "taskList is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                Log.d("onFailure getTasks", t.toString());
            }
        });
    }

    @Override
    public void uploadBlob(RequestBody body, MainMVP.Model.OnFinishedListener onFinishedListener) {
        mApiService.uploadBlob(body).enqueue(new Callback<Blob>() {
            @Override
            public void onResponse(Call<Blob> call, Response<Blob> response) {
                if (response.isSuccessful()) {
                    Log.d("Debug", "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Blob> call, Throwable t) {
                Log.d("Debug", "Unable to submit post to API.");
            }
        });
    }
}
