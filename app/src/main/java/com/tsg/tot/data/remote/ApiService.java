package com.tsg.tot.data.remote;

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
import com.tsg.tot.data.model.Submissions;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.data.model.TokenCustom;
import com.tsg.tot.data.model.Upload;
import com.tsg.tot.data.model.Version;
import com.tsg.tot.data.remote.model.GradeRemote;
import com.tsg.tot.data.remote.model.LessonsRemote;
import com.tsg.tot.data.remote.model.MessageRegisterRemote;
import com.tsg.tot.data.remote.model.MessageRemote;
import com.tsg.tot.data.remote.model.StudentRemote;
import com.tsg.tot.data.remote.model.StudyMaterialRemote;
import com.tsg.tot.data.remote.model.SubjectsRemote;
import com.tsg.tot.data.remote.model.TaskRegristerRemote;
import com.tsg.tot.data.remote.model.TaskRemote;
import com.tsg.tot.data.remote.model.TeacherRemote;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * End Point del API
 */
public interface ApiService {


    String GET_MESSAGES_PENDING = "api/mensajes/misMensajesPendientesPorDescargar" ;
    String POST_REGISTER_MESSAGES = "api/mensajes/RegistrarMiMensajeDescargado" ;
    String POST_ANSWER_MESSAGE = "api/mensajes/responderMensaje";
    String POST_ANSWER_TASK = "api/mensajes/responderTarea";

    String POST_LOGIN = "api/cuentas/login";
    String GET_VERSION = "api/versiones";
    String POST_UPLOAD_TASK ="api/entregas/entregarMiTarea";

    String GET_DEVICE = "api/disposotivos";

    String GET_LESSONS = "api/clases";
    String GET_MY_LESSONS = "api/clases/misClases";
    String GET_GRADE = "api/grados";
    String GET_MY_GRADE = "api/grados/migrado";

    String GET_EXERCISES = "api/gobernacion/cursos/ejercicios";
    String GET_SUBMISSIONS = "api/entregas";

    String GET_STUDENT = "api/estudiantes";
    String GET_MY_STUDENT = "api/estudiantes/miidentificacion";

    String GET_EVALUATIONS = "api/gobernacion/cursos/evaluaciones";
    String GET_STUDY_MATERIAL = "api/materialestudio";
    String GET_MY_STUDY_MATERIAL = "api/MaterialEstudioRegistros/misMaterialesPendientesPorDescargar";
    String POST_REGISTER_TASK = "api/tarearegistro/RegistrarMiTareaDescargada";
    String GET_MY_SUBJECTS = "api/materias/mismaterias";
    String GET_PLANNING = "api/gobernacion/cursos/planeacion";
    String GET_TEACHERS = "api/profesores";
    String GET_UPLOADS = "api/gobernacion/cursos/subidas";
    String GET_TASKS = "api/tareas";
    String GET_MY_TASK ="api/TareaRegistro/misTareasPendientesPorDescargar";
    String POST_TASK = "api/tareas";

    String POST_EVALUATION = "api/gobernacion/cursos/evaluaciones/";
    String POST_EXERCISES = "api/gobernacion/cursos/ejercicios/";
    String POST_SUBMISSIONS = "api/gobernacion/cursos/entregas/";
    String POST_BLOB = "upload";



    @GET (GET_MESSAGES_PENDING)
    Call<List<MessageRemote>> getMyPendingMessages (@Header("Authorization") String authKey);

    @POST(POST_REGISTER_MESSAGES)
    Call<MessageRegisterRemote> postRegisterMessage(@Header("Authorization") String authKey, @Body JsonObject body );

    @POST(POST_ANSWER_MESSAGE)
    Call<ResponseBody> postAnswerMessage(@Header("Authorization") String authKey, @Body JsonObject body );

    @Multipart
    @POST (POST_UPLOAD_TASK)
    Call<TaskRegristerRemote> postUploadTask(@Header("Authorization") String authKey, @Part MultipartBody.Part file, @Part ("TareaRegistroId")RequestBody tareaRegistroId, @Part ("Mac")RequestBody mac);

    @POST(POST_REGISTER_TASK)
    Call<TaskRegristerRemote> postRegisterTask(@Header("Authorization") String authKey, @Body JsonObject body );
    @GET(GET_VERSION)
    Call<List<Version>> getVersion();

    @GET(GET_DEVICE)
    Call<List<Device>> getDevice();

    @GET(GET_LESSONS)
    Call<List<Lessons>> getLessons();

    @GET(GET_MY_LESSONS)
    Call<List<LessonsRemote>> getMyLessons(@Header("Authorization") String authKey);

    @GET(GET_GRADE)
    Call<List<Grade>> getGrade();

    //@GET(GET_MY_GRADE)
    //Call<List<GradeRemote>> getMyGrade(@Header("Authorization") String authKey);

    @GET(GET_MY_GRADE)
    Call<GradeRemote> getMyGrade(@Header("Authorization") String authKey);


    @GET(GET_EXERCISES)
    Call<List<Exercises>> getExercises();

    @GET(GET_SUBMISSIONS)
    Call<List<Submissions>> getSubmissions();

    @GET(GET_STUDENT)
    Call<List<Student>> getStudent();

    @GET(GET_MY_STUDENT)
    Call <StudentRemote> getMyStudent(@Header("Authorization") String authKey);

    @GET(GET_EVALUATIONS)
    Call<List<Evaluations>> getEvaluations();

    @GET(GET_STUDY_MATERIAL)
    Call<List<StudyMaterial>> getStudyMaterial();

    @GET(GET_MY_STUDY_MATERIAL)
    Call<List<StudyMaterialRemote>> getMyPendingStudyMaterial(@Header("Authorization") String authKey);

    // @GET(GET_SUBJECTS)
    @GET(GET_MY_SUBJECTS)
    Call<List<SubjectsRemote>> getMySubjects(@Header("Authorization") String authKey);

    @GET(GET_PLANNING)
    Call<List<Planning>> getPlanning();

    @GET(GET_TEACHERS)
    Call<List<TeacherRemote>> getTeachers(@Header("Authorization") String authKey);

    @GET(GET_UPLOADS)
    Call<List<Upload>> getUploads();

    @GET(GET_MY_TASK)
    Call<List<TaskRemote>> getTasks(@Header("Authorization") String authKey);

    @POST(POST_TASK)
    Call<Task> postTask(@Body RequestBody body);

    @POST(POST_EVALUATION)
    Call<Evaluations> postEvaluations(@Body RequestBody body);

    @POST(POST_EXERCISES)
    Call<Exercises> postExercises(@Body RequestBody body);

    @POST(POST_SUBMISSIONS)
    Call<TaskRegristerRemote> postSubmissions(@Header("Authorization") String authKey, @Body RequestBody body);

    @POST(POST_BLOB)
    Call<Blob> postBlob(@Body RequestBody body);

    @POST(POST_LOGIN)
    Call<TokenCustom> postLogin(@Body JsonObject body);
}
