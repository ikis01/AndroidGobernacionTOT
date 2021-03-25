package com.tsg.tot.main.mainmvp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import androidx.core.app.ActivityCompat;

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
import com.tsg.tot.data.model.Uploads;
import com.tsg.tot.data.remote.model.GradeRemote;
import com.tsg.tot.data.remote.model.LessonsRemote;
import com.tsg.tot.data.remote.model.StudentRemote;
import com.tsg.tot.data.remote.model.StudyMaterialRemote;
import com.tsg.tot.data.remote.model.SubjectsRemote;
import com.tsg.tot.data.remote.model.TaskRegristerRemote;
import com.tsg.tot.data.remote.model.TaskRemote;
import com.tsg.tot.data.remote.model.TeacherRemote;
import com.tsg.tot.main.fragment.CustomProgressDialog;
import com.tsg.tot.repository.ApiRepository;
import com.tsg.tot.repository.DatabaseRepository;
import com.tsg.tot.sqlite.DbOpenHelper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.tsg.tot.sqlite.DBConstants.API_REPOSITORY;
import static com.tsg.tot.sqlite.DBConstants.DATABASE_REPOSITORY;

public class MainModel implements MainMVP.Model {

    DbOpenHelper dbHelper;
    private ApiRepository apiRepository;
    private DatabaseRepository databaseRepository;


    public MainModel(ApiRepository apiRepository, DatabaseRepository databaseRepository) {
        this.apiRepository = apiRepository;
        this.databaseRepository = databaseRepository;
    }

    @Override
    public void createDb(Context context) {
        dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            Log.d("Debug", "Database created");
        }
    }

    @Override
    public float checkAPIVersion(Context context) {
        return apiRepository.getVersion(context);
    }

    @Override
    public float checkDbVersion(Context context) {
        return databaseRepository.getVersion(context);
    }

    @Override
    public Boolean updateDatabase(String idUsusario, Context context,
                               float version,
                               GradeRemote gradeRemote,
                               StudentRemote studentRemote,
                               List<TeacherRemote> teacherRemoteList,
                               List<SubjectsRemote> subjectsRemoteList,
                               List<TaskRemote> taskRemoteList,
                               List<StudyMaterialRemote> materialRemoteList,
                               List<LessonsRemote> lessonsRemoteList,
                               String token,
                               CustomProgressDialog dialog
    ) {

        Boolean sincronizado = false;
        File storageDirMaterialRemote = null;
        File storageDirectoryStudent = null;
        File storageDirTask = null;
        List<TaskRemote> taskRemoteListAux = new ArrayList<>();
        List<FilesKiosco> filesKioscoList = new ArrayList<>();
        dialog.setProgress(dialog.getProgress() + 5);

        if (studentRemote != null) {
            databaseRepository.updateMyStudent(studentRemote, context, Integer.parseInt(idUsusario));
            databaseRepository.updateMySubjects(subjectsRemoteList, context);


            databaseRepository.updateRelStudentSubjects(studentRemote, subjectsRemoteList, context);

            databaseRepository.updateVersion(version, context);
            databaseRepository.updateMyGrade(gradeRemote, context, studentRemote);

            databaseRepository.updateMyTeachers(teacherRemoteList, context);

            databaseRepository.updateMyLessons(lessonsRemoteList, context);


            //if (studentRemote != null) {

            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                //RUNTIME PERMISSION Android M
                if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    storageDirectoryStudent = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Data", studentRemote.getNombre().replace(" ", "") + studentRemote.getIdD2L());
                    if (!storageDirectoryStudent.exists()) {
                        storageDirectoryStudent.mkdir();
                    }
                } else {
                    // requestPermissions(new String[]{READ_EXTERNAL_STORAGE, READ_PHONE_STATE}, 1);
                }

            }

        //} el studen null original regresar por si las moscas

        if (taskRemoteList != null && studentRemote != null) {

            for (TaskRemote taskRemote : taskRemoteList) {

                if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                    //RUNTIME PERMISSION Android M
                    if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        //storageDirMaterialRemote = new File(storageDirectoryStudent.getPath(), material.getIdD2L()+"BLOB");
                        storageDirTask = new File(storageDirectoryStudent.getPath(), taskRemote.getIdD2L() + "BLOB");

                        if (!storageDirTask.exists()) {
                            storageDirTask.mkdir();
                        }


                        if (storageDirTask.exists()) {
                            String tareaPath = null;
                            tareaPath = Download(taskRemote.getFile().getUrl(), storageDirTask, taskRemote.getNombreArchivo(), "Tarea_");
                            if (tareaPath != null) {
                                /// insertar registro en SUBIDA
                                TaskRemote taskAux = taskRemote;
                                FilesKiosco filesKiosco = new FilesKiosco();
                                java.util.Date fecha = new Date();
                                Uploads upload = new Uploads();
                                upload.setFecha(fecha.toString());
                                upload.setFechaDescarga(fecha.toString());
                                upload.setSubidaKiosco(taskRemote.getFile().getId());
                                upload.setEstudiante_idEstudiante(studentRemote.getId());
                                Long id = databaseRepository.updateMyUpload(upload, context);
                                taskRemote.setIdSubida(id);
                                taskRemote.setMateriaId(taskRemote.getMateria().getId());


                                JsonObject jsonObject = new JsonObject();
                                jsonObject.addProperty("tareaId", taskRemote.getTareaId());


                                //                               TaskRegristerRemote taskRegristerRemote=  registerTask( context, taskRemote.getTareaId(), token, jsonObject);

                                //if (taskRegristerRemote!=null) {
//                                    Log.d("resultado Registrar TaskRemote ",taskRegristerRemote.toString());
//                                    Log.d("TaskIdRegistro result  --> ",taskRegristerRemote.getId().toString());
                                //   taskRemote.setIdRegistro(taskRegristerRemote.getId());
                                //} else {
                                taskRemote.setIdRegistro(0);
                                //  }
                                taskRemoteListAux.add(taskRemote);
                                filesKiosco.setArchivoKiosco(taskRemote.getFile().getId());
                                filesKiosco.setCodigo(taskRemote.getTareaId().toString());
                                filesKiosco.setRuta(tareaPath);
                                filesKiosco.setSubida_idsubida(id.intValue());
                                filesKiosco.setNombreArchivo(taskRemote.getNombreArchivo());
                                filesKioscoList.add(filesKiosco);

                            }


                        }


                    }

                }

            }

            databaseRepository.updateMyFileKiosco(filesKioscoList, context);
            databaseRepository.updateMyTasks(taskRemoteListAux, context, studentRemote, 0);

            if (taskRemoteListAux != null) {
                for (TaskRemote task : taskRemoteListAux) {
                    File storageAllTask = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Data", "Tareas");
                    if (!storageAllTask.exists()) {
                        storageAllTask.mkdir();
                    } else {

                        File storageDirectory = new File(storageAllTask.getAbsolutePath(), task.getNombreActividad());
                        if (!storageDirectory.exists()) {
                            storageDirectory.mkdir();
                        }
                    }

                }

            }


        }

        List<StudyMaterialRemote> materialSList = new ArrayList<StudyMaterialRemote>();
        if (materialRemoteList != null) {
            for (StudyMaterialRemote material : materialRemoteList) {

                if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                    //RUNTIME PERMISSION Android M
                    if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        //storageDirMaterialRemote = new File(storageDirectoryStudent.getPath(), material.getIdD2L()+"BLOB");
                        storageDirMaterialRemote = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Data", material.getIdD2L() + "BLOB");

                        if (!storageDirMaterialRemote.exists()) {
                            storageDirMaterialRemote.mkdir();
                        }


                        String pathMaterial = null;
                        pathMaterial = Download(material.getUrl(), storageDirMaterialRemote, material.getNombreArchivo(), "Material_");
                        if (pathMaterial != null) {
                            material.setRuta(pathMaterial);
                            materialSList.add(material);
                        }
                        ///// INSERTAR MATERIAL DE ESTUDIO


                    }

                }


            }
            databaseRepository.updateMyStudyMaterial(materialSList, context);
        }

        sincronizado = true;
        }


        return sincronizado;
    }

    @Override
    public void updateAllDb(float version, List<Task> taskList,
                            List<Upload> uploadList, List<Teacher> teacherList,
                            List<Subjects> subjectsList, List<Grade> gradeList, List<StudyMaterial> studyMaterialList,
                            List<Evaluations> evaluationsList, List<Student> studentList,
                            List<Submissions> submissionsList, List<Exercises> exercisesList,
                            List<Lessons> lessonsList, Context context, CustomProgressDialog dialog) {

        dialog.setProgress(dialog.getProgress() + 5);
        databaseRepository.updateVersion(version, context);
        dialog.setProgress(dialog.getProgress() + 5);
        databaseRepository.updateTasks(taskList, context);
        dialog.setProgress(dialog.getProgress() + 5);
        databaseRepository.updateUploads(uploadList, context);
        dialog.setProgress(dialog.getProgress() + 5);
        databaseRepository.updateTeachers(teacherList, context);
        dialog.setProgress(dialog.getProgress() + 5);
        databaseRepository.updateSubjects(subjectsList, context);
        dialog.setProgress(dialog.getProgress() + 5);
        databaseRepository.updateGrade(gradeList, context);
        dialog.setProgress(dialog.getProgress() + 5);
        databaseRepository.updateStudyMaterial(studyMaterialList, context);
        dialog.setProgress(dialog.getProgress() + 5);
        databaseRepository.updateEvaluations(evaluationsList, context);
        dialog.setProgress(dialog.getProgress() + 5);
        databaseRepository.updateStudent(studentList, context);
        dialog.setProgress(dialog.getProgress() + 5);
        databaseRepository.updateSubmissions(submissionsList, context);
        dialog.setProgress(dialog.getProgress() + 5);
        databaseRepository.updateExercises(exercisesList, context);
        dialog.setProgress(dialog.getProgress() + 5);
        databaseRepository.updateLessons(lessonsList, context);
        dialog.setProgress(dialog.getProgress() + 5);
    }

    @Override
    public List<Device> checkDevices(Context context, int from) {
        List<Device> deviceList = null;

        switch (from) {
            case API_REPOSITORY:
                deviceList = apiRepository.getDevice(context);
                break;
            case DATABASE_REPOSITORY:
                deviceList = databaseRepository.getDevice(context);
                break;
            default:
                break;
        }

        return deviceList;
    }

    @Override
    public List<Lessons> checkLessons(Context context, int from) {
        List<Lessons> lessonsList = null;

        switch (from) {
            case API_REPOSITORY:
                lessonsList = apiRepository.getLessons(context);
                break;
            case DATABASE_REPOSITORY:
                lessonsList = databaseRepository.getLessons(context);
                break;
            default:
                break;
        }

        return lessonsList;
    }

    @Override
    public List<LessonsRemote> checkMyLessons(Context context, int from, String token) {
        List<LessonsRemote> lessonsList = null;
        switch (from) {
            case API_REPOSITORY:
                lessonsList = apiRepository.getMyLessons(context, token);
                break;
            case DATABASE_REPOSITORY:
                //lessonsList = databaseRepository.getGrade(context);
                break;
            default:
                break;
        }
        return lessonsList;
    }

    @Override
    public List<Grade> checkGrades(Context context, int from) {
        List<Grade> gradeList = null;

        switch (from) {
            case API_REPOSITORY:
                gradeList = apiRepository.getGrade(context);
                break;
            case DATABASE_REPOSITORY:
                gradeList = databaseRepository.getGrade(context);
                break;
            default:
                break;
        }

        return gradeList;
    }

    @Override
    public List<Exercises> checkExercises(Context context, int from) {
        List<Exercises> exercisesList = null;

        switch (from) {
            case API_REPOSITORY:
                exercisesList = apiRepository.getExercises(context);
                break;
            case DATABASE_REPOSITORY:
                exercisesList = databaseRepository.getExercises(context);
                break;
            default:
                break;
        }

        return exercisesList;
    }

    @Override
    public List<Submissions> checkSubmissions(Context context, int from) {
        List<Submissions> submissionsList = null;

        switch (from) {
            case API_REPOSITORY:
                submissionsList = apiRepository.getSubmissions(context);
                break;
            case DATABASE_REPOSITORY:
                submissionsList = databaseRepository.getSubmissions(context);
                break;
            default:
                break;
        }
        return submissionsList;
    }

    @Override
    public List<Student> checkStudents(Context context, int from, Integer idUsuario) {
        List<Student> studentList = null;

        switch (from) {
            case API_REPOSITORY:
                studentList = apiRepository.getStudent(context);
                break;
            case DATABASE_REPOSITORY:
                studentList = databaseRepository.getStudent(context, idUsuario);
                break;
            default:
                break;
        }
        return studentList;
    }

    @Override
    public List<Evaluations> checkEvaluations(Context context, int from) {
        List<Evaluations> evaluationsList = null;

        switch (from) {
            case API_REPOSITORY:
                evaluationsList = apiRepository.getEvaluations(context);
                break;
            case DATABASE_REPOSITORY:
                evaluationsList = databaseRepository.getEvaluations(context);
                break;
            default:
                break;
        }

        return evaluationsList;
    }

    @Override
    public StudentRemote checkMyStudent(Context context, int from, String auth) {
        StudentRemote student = null;

        switch (from) {
            case API_REPOSITORY:
                student = apiRepository.getMyStudent(context, auth);

                break;
            case DATABASE_REPOSITORY:
                //studyMaterialList = databaseRepository.getStudyMaterial(context);
                break;
            default:
                break;
        }


        return student;
    }


    @Override
    public List<StudyMaterialRemote> checkMyPendingStudyMaterials(Context context, int from, String token) {


        List<StudyMaterialRemote> studyMaterialList = null;

        switch (from) {
            case API_REPOSITORY:
                studyMaterialList = apiRepository.getMyPendingStudyMaterial(context, token);


                //// AQUI SE TENDRIAN QUE DESCARGAR LOS ARCHIVOS DE MATERIAL ESTUDIO

                break;
            case DATABASE_REPOSITORY:
                //studyMaterialList = databaseRepository.getStudyMaterial(context);
                break;
            default:
                break;
        }

        return studyMaterialList;
    }

    @Override
    public List<StudyMaterial> checkStudyMaterials(Context context, int from) {
        List<StudyMaterial> studyMaterialList = null;

        switch (from) {
            case API_REPOSITORY:
                studyMaterialList = apiRepository.getStudyMaterial(context);
                break;
            case DATABASE_REPOSITORY:
                studyMaterialList = databaseRepository.getStudyMaterial(context);
                break;
            default:
                break;
        }

        return studyMaterialList;
    }

    @Override
    public List<Subjects> checkSubjects(Context context, int from, String auth, Integer code) {
        List<Subjects> subjectsList = null;
        List<SubjectsRemote> subjectsRemoteList = null;

        switch (from) {
            case API_REPOSITORY:
                subjectsRemoteList = apiRepository.getMySubjects(context, auth);
                break;
            case DATABASE_REPOSITORY:
                subjectsList = databaseRepository.getSubjects(context, code);
                break;
            default:
                break;
        }

        return subjectsList;
    }

    @Override
    public List<SubjectsRemote> checkMySubjects(Context context, int from, String auth) {

        List<SubjectsRemote> subjectsRemoteList = null;

        switch (from) {
            case API_REPOSITORY:
                subjectsRemoteList = apiRepository.getMySubjects(context, auth);
                break;
            case DATABASE_REPOSITORY:
                //subjectsList = databaseRepository.getSubjects(context);
                break;
            default:
                break;
        }

        return subjectsRemoteList;
    }

    @Override
    public List<Planning> checkPlanning(Context context, int from) {
        List<Planning> planningList = null;
        switch (from) {
            case API_REPOSITORY:
                planningList = apiRepository.getPlanning(context);
                break;
            case DATABASE_REPOSITORY:
                planningList = databaseRepository.getPlanning(context);
                break;
            default:
                break;
        }

        return planningList;
    }


    @Override
    public List<Teacher> checkTeachers(Context context, int from, String auth) {
        List<Teacher> teacherList = null;
        List<TeacherRemote> teacherRemoteList = null;
        switch (from) {
            case API_REPOSITORY:
                teacherRemoteList = apiRepository.getTeachers(context, auth);
                break;
            case DATABASE_REPOSITORY:
                teacherList = databaseRepository.getTeachers(context);
                break;
            default:
                break;
        }

        return teacherList;
    }


    @Override
    public List<TeacherRemote> checkMyTeachers(Context context, int from, String auth) {

        List<TeacherRemote> teacherRemoteList = null;
        switch (from) {
            case API_REPOSITORY:
                teacherRemoteList = apiRepository.getTeachers(context, auth);
                break;
            case DATABASE_REPOSITORY:
                //      teacherList = databaseRepository.getTeachers(context);
                break;
            default:
                break;
        }

        return teacherRemoteList;
    }


    @Override
    public List<FilesKiosco> checkMyFileskioscos(Context context, Integer idEstudiante, Integer idMateria, Integer idTarea) {
        List<FilesKiosco> filesKioscoList = null;
        filesKioscoList = databaseRepository.getFileKioscos(context, idEstudiante, idMateria, idTarea);
        return filesKioscoList;
    }


    @Override
    public List<Upload> checkUploads(Context context, int from) {
        List<Upload> uploadList = null;

        switch (from) {
            case API_REPOSITORY:
                uploadList = apiRepository.getUploads(context);
                break;
            case DATABASE_REPOSITORY:
                uploadList = databaseRepository.getUploads(context);
                break;
            default:
                break;
        }

        return uploadList;
    }


    @Override
    public GradeRemote checkMyGrade(Context context, int from, String token) {
        GradeRemote gradeRemoteList = null;
        switch (from) {

            case API_REPOSITORY:
                gradeRemoteList = apiRepository.getMyGrade(context, token);
                break;
            case DATABASE_REPOSITORY:
                //taskList = databaseRepository.getTasks(context,token);
                break;
            default:
                break;
        }
        return gradeRemoteList;
    }

    @Override
    public List<Task> checkTasks(Context context, int from, String token,Integer idEstudiante) {
        List<Task> taskList = null;
        List<TaskRemote> taskRemoteList = null;

        switch (from) { 
            case API_REPOSITORY:
                taskRemoteList = apiRepository.getTasks(context, token);
                break;
            case DATABASE_REPOSITORY:
                taskList = databaseRepository.getTasks(context, token,idEstudiante);
                break;
            default:
                break;
        }

        return taskList;
    }

    @Override
    public TaskRegristerRemote  postUploadTask(Context context, String token,MultipartBody.Part file, MultipartBody tareaRegistroId,MultipartBody mac ){
        TaskRegristerRemote taskRegristerRemote = apiRepository.postUploadTask(token,file,tareaRegistroId,mac);
        return taskRegristerRemote;
    }

    @Override
    public TaskRegristerRemote registerTask(Context context, Integer idTarea, String token, JsonObject body) {
        TaskRegristerRemote taskRegristerRemote = apiRepository.postRegisterTask(token, body);
        return taskRegristerRemote;
    }

    @Override
    public List<TaskRemote> checkMyTasks(Context context, int from, String token) {

        List<TaskRemote> taskRemoteList = null;

        switch (from) {
            case API_REPOSITORY:
                taskRemoteList = apiRepository.getTasks(context, token);
                break;
            case DATABASE_REPOSITORY:
                //taskList = databaseRepository.getTasks(context,token);
                break;
            default:
                break;
        }

        return taskRemoteList;
    }


    @Override
    public void postTask(String uploadId, String subjectId, String name, String code) {
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("subida_id", uploadId)
                .addFormDataPart("materias_id", subjectId)
                .addFormDataPart("nombre", name)
                .addFormDataPart("codigo", code)
                .addFormDataPart("materias", subjectId)
                .build();
        apiRepository.postTask(body);
    }

    @Override
    public void postEvaluations(String uploadId, String name, String subjectId) {
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("subida_id", uploadId)
                .addFormDataPart("nombre", name)
                .addFormDataPart("materias", subjectId)
                .build();
        apiRepository.postEvaluations(body);
    }

    @Override
    public void postExercises(String uploadId, String name, String lessonsId) {
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("subida_id", uploadId)
                .addFormDataPart("nombre", name)
                .addFormDataPart("clases", lessonsId)
                .build();
        apiRepository.postExercises(body);
    }


    @Override
    public void postSubmissions(String upp, String exercisesId, String taskId, String evaluationId, String uploadId, String studentId) {
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("upp", upp)
                .addFormDataPart("ejercicios", exercisesId)
                .addFormDataPart("tarea", taskId)
                .addFormDataPart("evaluacion", evaluationId)
                .addFormDataPart("subida", uploadId)
                .addFormDataPart("estudiante", studentId)
                .build();
        //  apiRepository.postSubmissions(body);
    }

    @Override
    public void postBlob(String file, String subida_id, String entrega_id) {
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                //.addFormDataPart("codigo", code)
                //.addFormDataPart("File", file, RequestBody.create(MediaType.parse("*/*"), new File(file)))
                .addFormDataPart("Subida_id", subida_id)
                .addFormDataPart("Entrega_id", entrega_id)
                .build();
        apiRepository.postBlob(body);
    }


    public static String Download(String urlStr, File filePath, String nombreArchivo, String tipo) {

        String rutaGuardada = null;
        try {
            URL url = new URL(urlStr);

            String fileAppend = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()).concat("_");
            File file = new File(filePath, tipo + fileAppend + nombreArchivo);
            if (!file.exists()) { // Si no existe, crea el archivo.
                file.createNewFile();
                rutaGuardada = file.getAbsolutePath();
            }

            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            FileOutputStream fis = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = bis.read(buffer, 0, 1024)) != -1) {
                fis.write(buffer, 0, count);
            }

            fis.close();
            bis.close();

            return rutaGuardada;

        } catch (FileNotFoundException e) {
            return null; // swallow a 404
        } catch (IOException e) {
            return null; // swallow a 404
        }
    }

}
