package com.tsg.tot.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Message;
import android.util.Log;

import com.google.gson.JsonObject;
import com.tsg.tot.data.model.Blob;
import com.tsg.tot.data.model.Device;
import com.tsg.tot.data.model.Evaluations;
import com.tsg.tot.data.model.Exercises;
import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.data.model.Grade;
import com.tsg.tot.data.model.Lessons;
import com.tsg.tot.data.model.MessageAnswer;
import com.tsg.tot.data.model.Planning;
import com.tsg.tot.data.model.Student;
import com.tsg.tot.data.model.StudyMaterial;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.data.model.SubmissionDisplay;
import com.tsg.tot.data.model.Submissions;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.data.model.Teacher;
import com.tsg.tot.data.model.TokenCustom;
import com.tsg.tot.data.model.Upload;
import com.tsg.tot.data.model.Uploads;
import com.tsg.tot.data.model.Users;
import com.tsg.tot.data.remote.model.FileMessageRemote;
import com.tsg.tot.data.remote.model.FileTaskRemote;
import com.tsg.tot.data.remote.model.GradeRemote;
import com.tsg.tot.data.remote.model.LessonsRemote;
import com.tsg.tot.data.remote.model.MessageRemote;
import com.tsg.tot.data.remote.model.StudentRemote;
import com.tsg.tot.data.remote.model.StudyMaterialRemote;
import com.tsg.tot.data.remote.model.SubjectsRemote;
import com.tsg.tot.data.remote.model.SubmissionPending;
import com.tsg.tot.data.remote.model.TaskRemote;
import com.tsg.tot.data.remote.model.TeacherRemote;
import com.tsg.tot.sqlite.DbOpenHelper;
import com.tsg.tot.storage.TOTPreferences;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;

import static com.tsg.tot.sqlite.DBConstants.*;


/**
 * Clase que consulta los datos en la DB.
 */
public class DatabaseRepository implements LocalRepository {

    //Get info of DB Tables

    @Override
    public float getVersion(Context context) {
        float version;
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT NUMERO FROM VERSION";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        version = c.getFloat(0);

        db.close();
        dbHelper.close();

        return version;
    }

    @Override
    public List<Device> getDevice(Context context) {
        return null;
    }

    @Override
    public List<Lessons> getLessons(Context context) {
        return null;
    }

    @Override
    public List<Grade> getGrade(Context context) {
        return null;
    }

    @Override
    public List<Exercises> getExercises(Context context) {
        return null;
    }

    @Override
    public List<Submissions> getSubmissions(Context context) {
        return null;
    }

    @Override
    public List<Student> getStudent(Context context, Integer idUsuario) {
        List<Student> studentList = new ArrayList<>();

        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM " + STUDENTS_TABLE_NAME + " WHERE "
                + STUDENT_FK_USUARIO + " = " + idUsuario;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //get columns
                    int idStudent = cursor.getColumnIndex(STUDENTS_ID);
                    int condeStudent = cursor.getColumnIndex(STUDENTS_CODE);
                    int studentName = cursor.getColumnIndex(STUDENTS_NAME);
                    int studentLastName = cursor.getColumnIndex(STUDENTS_LAST_NAME);

                    //add row to list
                    studentList.add(new Student(
                            cursor.getInt(idStudent),
                            cursor.getInt(condeStudent),
                            cursor.getString(studentName),
                            cursor.getString(studentLastName)

                    ));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return studentList;
    }

    @Override
    public List<Evaluations> getEvaluations(Context context) {
        return null;
    }

    @Override
    public String getIPEND(Context context) {
        return null;
    }

    @Override
    public List<StudyMaterial> getStudyMaterial(Context context) {
        return null;
    }

    @Override
    public  Grade getGradeByStudent (Context context , Integer idEstudiante){
        Grade grade = null;
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        String query = "SELECT * FROM " + INFO_GRADE_TABLE_NAME +
                " WHERE " + INFO_GRADE_ESTUDIANTE_IDESTUDIANTE + " = " + idEstudiante;

        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        try {
            if (cursor.getCount() > 0) {
                do {
                    //get columns
                    int idGrade = cursor.getColumnIndex(INFO_GRADE_CODIGOGRADO);
                    int titleGrade = cursor.getColumnIndex(INFO_GRADE_NOMBRE);

                    //add row to object
                    grade = new Grade(cursor.getInt(idGrade),
                            cursor.getString(titleGrade));

                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }
        return grade;
    }

    @Override
    public List<Subjects> getSubjects(Context context, Integer codeGrado) {
        List<Subjects> subjectsList = new ArrayList<>();

        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT DISTINCT * FROM " + SUBJECTS_TABLE_NAME
                + " , " + REL_STUDENT_SUBJECT_TABLE_NAME +
                " WHERE " + REL_STUDENT_SUBJECT_FK_STUDENT + " = " + codeGrado +
                " AND " + REL_STUDENT_SUBECT_FK_SUBJECT + " = " + SUBJECTS_ID;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //get columns
                    int idSubject = cursor.getColumnIndex(SUBJECTS_ID);
                    int titleSubject = cursor.getColumnIndex(SUBJECTS_TITLE);
                    int codeSubject = cursor.getColumnIndex(SUBJECTS_CODE);
                    int idGrade = cursor.getColumnIndex(SUBJECTS_SUBTITLE);
                    int idTeacher = cursor.getColumnIndex(SUBJECTS_TEACHER_ID);
                    int subjectSubtitle = cursor.getColumnIndex(SUBJECTS_SUBTITLE);
                    int descriptionSubject = cursor.getColumnIndex(SUBJECTS_DESCRIPTION);
                    int imageSubject = cursor.getColumnIndex(SUBJECTS_IMAGE);

                    //add row to list
                    subjectsList.add(new Subjects(
                            Integer.parseInt(cursor.getString(idSubject)),
                            getGrade(db, cursor.getString(idGrade)),
                            getTeacher(db, cursor.getString(idTeacher)),
                            cursor.getString(codeSubject),
                            cursor.getString(titleSubject),
                            cursor.getString(subjectSubtitle),
                            cursor.getString(descriptionSubject),
                            cursor.getString(imageSubject)
                    ));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return subjectsList;
    }


    @Override
    public List<Planning> getPlanning(Context context) {
        return null;
    }

    @Override
    public List<Teacher> getTeachers(Context context) {
        return null;
    }

    @Override
    public List<Upload> getUploads(Context context) {
        return null;
    }

    @Override
    public List<Task> getTasks(Context context, String authKey, Integer idEstudiante) {
        List<Task> taskList = new ArrayList<>();

        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM " + TASK_TABLE_NAME +
                " WHERE " + TASK_STUDENT_ID + " = " + idEstudiante;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //get columns
                    int idTask = cursor.getColumnIndex(TASK_ID);
                    int nameTask = cursor.getColumnIndex(TASK_NAME);
                    int idSubjectTask = cursor.getColumnIndex(TASK_SUBJECT_ID);
                    int idUploadTask = cursor.getColumnIndex(TASK_UPLOAD_ID);
                    int codeTask = cursor.getColumnIndex(TASK_CODE);
                    int idStudent = cursor.getColumnIndex(TASK_STUDENT_ID);
                    int tareaKiosc = cursor.getColumnIndex(TASK_KIOSCO);
                    int tareaRegistro = cursor.getColumnIndex(TASK_REGISTER);
                    //add row to list
                    taskList.add(new Task(
                            Integer.parseInt(cursor.getString(idTask)),
                            getUpload(db, cursor.getString(idUploadTask)),
                            cursor.getString(nameTask),
                            cursor.getString(codeTask),
                            Integer.parseInt(cursor.getString(idSubjectTask)),
                            Integer.parseInt(cursor.getString(idStudent)),
                            Integer.parseInt(cursor.getString(tareaKiosc)),
                            Integer.parseInt(cursor.getString(tareaRegistro) == null ? "0" : cursor.getString(tareaRegistro))
                    ));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return taskList;
    }

    //UPDATE info of DB Tables

    @Override
    public List<Users> getUsers(ContentValues cv, Context context) {

        String userNameString = (String) cv.get("UserName");
        String passwordString = (String) cv.get("Password");
        List<Users> usersList = new ArrayList<>();

        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM " + USERS_TABLE_NAME + " WHERE "
                + USERS_USER_NAME + " = '" + userNameString + "'"
                + " AND " + USERS_PASSWORD + " = '" + passwordString + "'";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //get columns
                    int idUsuario = cursor.getColumnIndex(USERS_ID);
                    int userName = cursor.getColumnIndex(USERS_USER_NAME);
                    int password = cursor.getColumnIndex(USERS_PASSWORD);

                    //add row to list
                    usersList.add(new Users(
                            Integer.parseInt(cursor.getString(idUsuario)),
                            cursor.getString(userName),
                            cursor.getString(password)));

                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return usersList;
    }

    @Override
    public void updateUser(ContentValues cv, Context context) {

        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.insert("Usuarios", null, cv);
        db.close();
        dbHelper.close();
    }

    @Override
    public void updateVersion(float version, Context context) {
        try {
            DbOpenHelper dbHelper = new DbOpenHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(VERSION_NUMBER, version);

            db.update(VERSION_TABLE_NAME, cv, null, null);

            db.close();
            dbHelper.close();
        } catch (SQLiteException ex) {

            Log.d("exceptiom sqlite version ", ex.getMessage());
        }
    }

    @Override
    public void updateDevice(List<Device> devicesList, Context context) {

    }

    @Override
    public TokenCustom postLogin(JsonObject requestBody) {
        return null;
    }

    @Override
    public void updateMyGrade(GradeRemote grade, Context context, StudentRemote student) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (grade != null) {
            // for (GradeRemote grade : gradesList) {
            if (checkId(db, INFO_GRADE_TABLE_NAME, INFO_GRADE_IDINFOGRADO, grade.getId().toString()) == 0) {
                cv.put(INFO_GRADE_IDINFOGRADO, grade.getId());
                cv.put(INFO_GRADE_CODIGOGRADO, grade.getCodigo());
                cv.put(INFO_GRADE_INSTITUCION, grade.getInstitucion().getNombre());
                cv.put(INFO_GRADE_NOMBRE, grade.getNombre());
                cv.put(INFO_GRADE_ESTUDIANTE_IDESTUDIANTE, student.getId());
                //cv.put(INFO_GRADE_UBICACIONINSTITUCION, grade.getInstitucion().getDireccion());
                db.insert(INFO_GRADE_TABLE_NAME, null, cv);
            }
            //}
        }

        db.close();
        dbHelper.close();
    }

    @Override
    public void updateMyStudent(StudentRemote studentRemote, Context context, Integer idUsuario) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (studentRemote != null) {
            // for (GradeRemote grade : gradesList) {
            if (checkId(db, STUDENT_TABLE_NAME, STUDENT_IDESTUDIANTE, studentRemote.getId().toString()) == 0) {
                cv.put(STUDENTS_ID, studentRemote.getId());
                cv.put(STUDENT_APELLIDOS, studentRemote.getApellidos());
                cv.put(STUDENT_EDAD, studentRemote.getFecha_nacimiento());
                cv.put(STUDENT_NOMBRES, studentRemote.getNombre());
                cv.put(STUDENTS_CODE, studentRemote.getIdD2L());
                cv.put(STUDENT_FK_USUARIO, idUsuario);
                db.insert(STUDENT_TABLE_NAME, null, cv);
            }
            //}
        }

        db.close();
        dbHelper.close();

    }


    @Override
    public void updateMyTasks(List<TaskRemote> taskList, Context context, StudentRemote studentRemote, Integer regist) {
        Log.d("updateMyTasks : ", "inicio DatabaseRepository updateMyTasks");
        try {
            DbOpenHelper dbHelper = new DbOpenHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            if (taskList != null) {
                for (TaskRemote task : taskList) {
                    List<FileTaskRemote> fileTaskRemoteList = task.getFile();
                    if (fileTaskRemoteList != null) {
                        if (fileTaskRemoteList.size() > 0) {
                            for (FileTaskRemote fileTaskRemote : fileTaskRemoteList) {

                                if (checkRelations(db, TASK_TABLE_NAME, TASK_CODE, task.getIdD2L().toString(), TASK_STUDENT_ID, studentRemote.getId().toString()) == 0) {
                                    //cv.put(TASK_ID, task.getIdD2L());
                                    cv.put(TASK_ID, task.getTareaId());
                                    //cv.put(TASK_CODE, task.getIdArchivoD2L());
                                    cv.put(TASK_CODE, task.getIdD2L());
                                    cv.put(TASK_NAME, task.getNombre());
                                    cv.put(TASK_REGISTER, task.getIdRegistro());
                                    cv.put(TASK_KIOSCO, task.getTareaId());
                                    cv.put(TASK_STUDENT_ID, studentRemote.getId());
                                    cv.put(TASK_SUBJECT_ID, task.getMateria().getId());
                                    cv.put(TASK_UPLOAD_ID, fileTaskRemote.getId());
                                    cv.put(TASK_UPLOAD_ID, task.getIdSubida().intValue());
                                    db.insert(TASK_TABLE_NAME, null, cv);
                                }
                            }

                        } else {
                            if (checkRelations(db, TASK_TABLE_NAME, TASK_CODE, task.getIdD2L().toString(), TASK_STUDENT_ID, studentRemote.getId().toString()) == 0) {
                                //cv.put(TASK_ID, task.getIdD2L());
                                cv.put(TASK_ID, task.getTareaId());
                                //cv.put(TASK_CODE, task.getIdArchivoD2L());
                                cv.put(TASK_CODE, task.getIdD2L());
                                cv.put(TASK_NAME, task.getNombre());
                                cv.put(TASK_REGISTER, task.getIdRegistro());
                                cv.put(TASK_KIOSCO, task.getTareaId());
                                cv.put(TASK_STUDENT_ID, studentRemote.getId());
                                cv.put(TASK_SUBJECT_ID, task.getMateria().getId());
                                cv.put(TASK_UPLOAD_ID, 0);
                                cv.put(TASK_UPLOAD_ID, task.getIdSubida() == null ? 0 : task.getIdSubida().intValue());
                                db.insert(TASK_TABLE_NAME, null, cv);
                            }
                        }
                    }

                }
            }

            db.close();
            dbHelper.close();
        } catch (SQLiteException wex) {
            Log.d("error al insertar ", wex.getMessage());
        }
    }

    @Override
    public void updateMyFileMessage(MessageRemote messageRemote, FileMessageRemote fileMessageRemote, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        if (checkId(db, FILE_MESSAGE_KIOSCO_TABLE_NAME, FILE_MESSAGE_ID_ARCHIVO_KIOSCO, messageRemote.getId().toString()) == 0) {
            cv.put(FILE_MESSAGE_FECHA_DESCARGA, fileMessageRemote.getFechaDescarga());
            cv.put(FILE_MESSAGE_ID_ARCHIVO_MENSAJE, messageRemote.getId());
            cv.put(FILE_MESSAGE_ID_ARCHIVO_KIOSCO, fileMessageRemote.getId());
            cv.put(FILE_MESSAGE_IDD2L, fileMessageRemote.getIdD2L());
            cv.put(FILE_MESSAGE_NOMBRE, fileMessageRemote.getNombre());
            cv.put(FILE_MESSAGE_URL, fileMessageRemote.getUrl());
            cv.put(FILE_MESSAGE_ID_MENSAJE_KIOSCO, messageRemote.getMensajeKioscoId());
            db.insert(FILE_MESSAGE_KIOSCO_TABLE_NAME, null, cv);
        }

        db.close();
        dbHelper.close();

    }

    @Override
    public List<MessageRemote> updateMyMessages(List<MessageRemote> messageRemoteList, Context context, StudentRemote studentRemote) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        List<MessageRemote> messageRemoteListResult = new ArrayList<>();
        if (messageRemoteList != null) {
            for (MessageRemote messageRemote : messageRemoteList) {
                MessageRemote messageRemoteResult;
                if (checkId(db, MESSAGE_KIOSCO_TABLE_NAME, MESSAGE_KIOSCO_ID_MENSAJE_KIOSCO, messageRemote.getId().toString()) == 0) {
                    cv.put(MESSAGE_KIOSCO_FECHA_DESCARGA, messageRemote.getFechaDescarga());
                    cv.put(MESSAGE_KIOSCO_IDD2L, messageRemote.getIdD2L());
                    cv.put(MESSAGE_KIOSCO_ID_MENSAJE_KIOSCO, messageRemote.getId());
                    cv.put(MESSAGE_KIOSCO_NOMBRE, messageRemote.getMensajes());
                    cv.put(MESSAGE_KIOSCO_REGISTRO_MENSAJE_KIOSCO, 0);
                    cv.put(MESSAGE_KIOSCO_ID_ESTUDIANTE, studentRemote.getId());
                    cv.put(MESSAGE_KIOSCO_ID_MATERIA, messageRemote.getMateria().getId());
                    Long resultId = db.insert(MESSAGE_KIOSCO_TABLE_NAME, null, cv);
                    messageRemote.setMensajeKioscoId(resultId.intValue());
                    messageRemoteResult = messageRemote;
                    messageRemoteListResult.add(messageRemoteResult);
                }
            }
        }

        db.close();
        dbHelper.close();
        return messageRemoteListResult;

    }


    @Override
    public void updateMyLessons(List<LessonsRemote> lessonsRemoteList, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (lessonsRemoteList != null) {
            for (LessonsRemote lessons : lessonsRemoteList) {
                if (checkId(db, LESSONS_TABLE_NAME, LESSONS_ID, lessons.getId().toString()) == 0) {
                    cv.put(LESSONS_ID, lessons.getId());
                    cv.put(LESSONS_CODIGO, lessons.getId()); /// consultar dato
                    cv.put(LESSONS_THEME, lessons.getTema());
                    cv.put(LESSONS_SUBJECT_ID, lessons.getMateriaId());
                    cv.put(LESSONS_TEACHER_ID, lessons.getProfesorId());
                    cv.put(LESSONS_NAME, lessons.getNombre());
                    cv.put(LESSONS_INIT_DATE, lessons.getFecha_inicio());
                    db.insert(LESSONS_TABLE_NAME, null, cv);
                }
            }
        }

        db.close();
        dbHelper.close();
    }


    @Override
    public void updateLessons(List<Lessons> lessonsList, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (lessonsList != null) {
            for (Lessons lessons : lessonsList) {
                if (checkId(db, LESSONS_TABLE_NAME, LESSONS_ID, lessons.getId().toString()) == 0) {
                    cv.put(LESSONS_ID, lessons.getId());
                    cv.put(LESSONS_THEME, lessons.getTema());
                    cv.put(LESSONS_SUBJECT_ID, lessons.getMaterias());
                    cv.put(LESSONS_TEACHER_ID, lessons.getProfesor());
                    cv.put(LESSONS_NAME, lessons.getNombre());
                    cv.put(LESSONS_INIT_DATE, lessons.getFechaInicio());
                    db.insert(LESSONS_TABLE_NAME, null, cv);
                }
            }
        }

        db.close();
        dbHelper.close();
    }


    @Override
    public void updateGrade(List<Grade> gradesList, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (gradesList != null) {
            for (Grade grade : gradesList) {
             //   if (checkId(db, GRADE_TABLE_NAME, GRADE_ID, grade.getId().toString()) == 0) {
                    cv.put(GRADE_ID, grade.getId());
                    cv.put(GRADE_TITLE, grade.getNombre());
                    db.insert(GRADE_TABLE_NAME, null, cv);
              //  }
            }
        }

        db.close();
        dbHelper.close();
    }

    @Override
    public void updateExercises(List<Exercises> exercisesList, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (exercisesList != null) {
            for (Exercises exercises : exercisesList) {
                if (checkId(db, EXERCISES_TABLE_NAME, EXERCISES_ID, exercises.getId().toString()) == 0) {
                    cv.put(EXERCISES_ID, exercises.getId());
                    cv.put(EXERCISES_NAME, exercises.getNombre());
                    cv.put(EXERCISES_CLASSES_ID, exercises.getClases());
                    cv.put(EXERCISES_UPLOAD_ID, exercises.getSubida().getId());
                    db.insert(EXERCISES_TABLE_NAME, null, cv);
                }
            }
        }

        db.close();
        dbHelper.close();
    }

    @Override
    public Long updateSubmissions(List<Submissions> submissionsList, Context context) {
        Long id = 0L;
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (submissionsList != null) {
            for (Submissions submissions : submissionsList) {
                //if (checkId(db, SUBMISSIONS_TABLE_NAME, SUBMISSIONS_ID, submissions.getId().toString()) == 0) {
                // cv.put(SUBMISSIONS_ID, submissions.getId());
                cv.put(SUBMISSIONS_CODE, submissions.getCodigoEntrega());
                cv.put(SUBMISSIONS_CODE_TASK, submissions.getCodigoTarea());
                cv.put(SUBMISSIONS_CREATED, submissions.getCreado());
                cv.put(SUBMISSIONS_RT_SUBMISSION, submissions.getRtEntrega() == null ? 0 : submissions.getRtEntrega());
                cv.put(SUBMISSIONS_UPP, submissions.getUpp() == null ? 0 : submissions.getUpp());
                cv.put(SUBMISSIONS_STUDENT_ID, submissions.getIdEstudiante() == null ? 0 : submissions.getIdEstudiante());
                cv.put(SUBMISSIONS_UPLOAD_ID, submissions.getIdSubida() == null ? 0 : submissions.getIdSubida());
                cv.put(SUBMISSIONS_TASK_ID, submissions.getIdTarea() == null ? 0 : submissions.getIdTarea());


                id = db.insert(SUBMISSIONS_TABLE_NAME, null, cv);
                //}
            }
        }

        db.close();
        dbHelper.close();

        return id;

    }

    @Override
    public void updateStudent(List<Student> studentList, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (studentList != null) {
            for (Student student : studentList) {
                if (checkId(db, STUDENTS_TABLE_NAME, STUDENTS_ID, student.getId().toString()) == 0) {
                    cv.put(STUDENTS_ID, student.getId());
                    cv.put(STUDENTS_NAME, student.getNombres() + " " + student.getApellidos());
                    cv.put(STUDENTS_CURSE_ID, student.getCurso().getId());
                    cv.put(STUDENTS_CODE, student.getCodigo());
                    db.insert(STUDENTS_TABLE_NAME, null, cv);
                }
            }
        }

        db.close();
        dbHelper.close();
    }

    @Override
    public void updateEvaluations(List<Evaluations> evaluationsList, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (evaluationsList != null) {
            for (Evaluations evaluations : evaluationsList) {
                if (checkId(db, EVALUATION_TABLE_NAME, EVALUATION_ID, evaluations.getId().toString()) == 0) {
                    cv.put(EVALUATION_ID, evaluations.getId());
                    cv.put(EVALUATION_NAME, evaluations.getNombre());
                    cv.put(EVALUATION_SUBJECT_ID, evaluations.getMaterias());
                    cv.put(EVALUATION_UPLOAD_ID, evaluations.getSubida().getId());
                    db.insert(EVALUATION_TABLE_NAME, null, cv);
                }
            }
        }

        db.close();
        dbHelper.close();
    }

    @Override
    public void updateStudyMaterial(List<StudyMaterial> studyMaterialList, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (studyMaterialList != null) {
            for (StudyMaterial studyMaterial : studyMaterialList) {
                if (checkId(db, STUDYMATERIAL_TABLE_NAME, STUDYMATERIAL_ID, studyMaterial.getId().toString()) == 0) {
                    cv.put(STUDYMATERIAL_ID, studyMaterial.getId());
                    cv.put(STUDYMATERIAL_CLASSES_ID, studyMaterial.getClases());
                    cv.put(STUDYMATERIAL_NAME, studyMaterial.getNombre());
                    cv.put(STUDYMATERIAL_DESCRIPTION, studyMaterial.getDescripcion());
                    cv.put(STUDYMATERIAL_BLOB_ID, studyMaterial.getBlob());
                    db.insert(STUDYMATERIAL_TABLE_NAME, null, cv);
                }
            }
        }

        db.close();
        dbHelper.close();
    }


    @Override
    public void updateRelStudentSubjects(StudentRemote studentRemote, List<SubjectsRemote> subjectsRemoteList, Context context) {

        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (subjectsRemoteList != null) {
            for (SubjectsRemote subject : subjectsRemoteList) {
                if (checkRelStudentSubject(db, REL_STUDENT_SUBJECT_TABLE_NAME,
                        REL_STUDENT_SUBJECT_FK_STUDENT,
                        studentRemote.getId().toString(),
                        REL_STUDENT_SUBECT_FK_SUBJECT,
                        subject.getId().toString()) == 0) {
                    cv.put(REL_STUDENT_SUBJECT_FK_STUDENT, studentRemote.getId());
                    cv.put(REL_STUDENT_SUBECT_FK_SUBJECT, subject.getId());
                    db.insert(REL_STUDENT_SUBJECT_TABLE_NAME, null, cv);
                }
            }
        }

        db.close();
        dbHelper.close();
    }

    @Override
    public void updateMyStudyMaterial(List<StudyMaterialRemote> studyMaterialList, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (studyMaterialList != null) {
            for (StudyMaterialRemote studyMaterial : studyMaterialList) {
                if (checkId(db, STUDYMATERIAL_TABLE_NAME, STUDYMATERIAL_ID, studyMaterial.getId().toString()) == 0) {
                    cv.put(STUDYMATERIAL_ID, studyMaterial.getId());
                    cv.put(STUDYMATERIAL_DESCRIPTION, studyMaterial.getDescripcion());
                    cv.put(STUDYMATERIAL_NAME, studyMaterial.getTema());
                    cv.put(STUDYMATERIAL_NAME_FILE, studyMaterial.getNombreArchivo());
                    cv.put(STUDYMATERIAL_PATH, studyMaterial.getRuta());
                    cv.put(STUDYMATERIAL_CLASSES_ID, studyMaterial.getClaseId());
                    db.insert(STUDYMATERIAL_TABLE_NAME, null, cv);
                }
            }
        }

        db.close();
        dbHelper.close();
    }


    @Override
    public void updateSubjects(List<Subjects> subjectsList, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (subjectsList != null) {
            for (Subjects subjects : subjectsList) {
                if (checkId(db, SUBJECTS_TABLE_NAME, SUBJECTS_ID, subjects.getId().toString()) == 0) {
                    cv.put(SUBJECTS_ID, subjects.getId());
                    cv.put(SUBJECTS_TITLE, subjects.getTitulo());
                    cv.put(SUBJECTS_CODE, subjects.getCodigo());
                    cv.put(SUBJECTS_TEACHER_ID, subjects.getProfesor().getId());
                    cv.put(SUBJECTS_SUBTITLE, subjects.getSubtitulo());
                    cv.put(SUBJECTS_DESCRIPTION, subjects.getDescripcion());
                    cv.put(SUBJECTS_IMAGE, subjects.getImagen());
                    db.insert(SUBJECTS_TABLE_NAME, null, cv);
                }
            }
        }

        db.close();
        dbHelper.close();
    }

    @Override
    public void updatePlanning(List<Planning> planningList, Context context) {

    }

    @Override
    public void updateTeachers(List<Teacher> teacherList, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (teacherList != null) {
            for (Teacher teacher : teacherList) {
                if (checkId(db, TEACHER_TABLE_NAME, TEACHER_ID, teacher.getId().toString()) == 0) {
                    cv.put(TEACHER_ID, teacher.getId());
                    cv.put(TEACHER_NAME, teacher.getNombres() + " " + teacher.getApellidos());
                    db.insert(TEACHER_TABLE_NAME, null, cv);
                }
            }
        }

        db.close();
        dbHelper.close();
    }

    @Override
    public void updateMyTeachers(List<TeacherRemote> teacherList, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (teacherList != null) {
            for (TeacherRemote teacher : teacherList) {
                if (checkId(db, TEACHER_TABLE_NAME, TEACHER_ID, teacher.getId().toString()) == 0) {
                    cv.put(TEACHER_ID, teacher.getId());
                    cv.put(TEACHER_NAME, teacher.getNombres());
                    cv.put(TEACHER_LAST_NAME, teacher.getApellidos());
                    db.insert(TEACHER_TABLE_NAME, null, cv);
                }
            }
        }

        db.close();
        dbHelper.close();
    }

    @Override
    public void updateMySubjects(List<SubjectsRemote> subjectsList, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (subjectsList != null) {
            for (SubjectsRemote subjects : subjectsList) {
                if (checkId(db, SUBJECTS_TABLE_NAME, SUBJECTS_ID, subjects.getId().toString()) == 0) {
                    cv.put(SUBJECTS_ID, subjects.getId());
                    cv.put(SUBJECTS_CODE, subjects.getIdD2L());
                    cv.put(SUBJECTS_DESCRIPTION, subjects.getDescripcion());
                    cv.put(SUBJECTS_SUBTITLE, subjects.getSubtitulo());
                    cv.put(SUBJECTS_INSTITUTE, subjects.getInstitucionId());
                    cv.put(SUBJECTS_TITLE, subjects.getTitulo());
                    cv.put(SUBJECTS_TEACHER_ID, subjects.getProfesorId());

                    db.insert(SUBJECTS_TABLE_NAME, null, cv);
                }
            }
        }

        db.close();
        dbHelper.close();
    }


    @Override
    public void updateUploads(List<Upload> uploadList, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (uploadList != null) {
            for (Upload upload : uploadList) {
                if (checkId(db, UPLOAD_TABLE_NAME, UPLOAD_ID, upload.getId().toString()) == 0) {
                    cv.put(UPLOAD_ID, upload.getId());
                    cv.put(UPLOAD_DATE, upload.getFecha());
                    db.insert(UPLOAD_TABLE_NAME, null, cv);
                }
            }
        }

        db.close();
        dbHelper.close();
    }

    @Override
    public Long updateMyUpload(Uploads upload, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Long id = new Long(0);

        if (upload != null) {
            //  for (Uploads upload : uploadsList) {
            //if (checkId(db, UPLOADS_TABLE_NAME, UPLOADS_ID, upload.getIdSubida().toString()) == 0) {
            //cv.put(UPLOAD_ID, upload.getId());
            cv.put(UPLOADS_FECHA, upload.getFecha());
            cv.put(UPLOADS_FECHA_DESCARGA, upload.getFechaDescarga());
            cv.put(UPLOADS_SUBIDA_KIOSCO, upload.getSubidaKiosco());
            cv.put(UPLOADS_ID_ESTUDIANTE, upload.getEstudiante_idEstudiante());
            id = db.insert(UPLOAD_TABLE_NAME, null, cv);
            //    }

            //}


        }

        db.close();
        dbHelper.close();
        return id;
    }


    @Override

    public Long updateMyFileKiosco(List<FilesKiosco> filesKioscoList, Context context) {

        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Long id = new Long(0);

        if (filesKioscoList != null) {
            for (FilesKiosco filesKiosco : filesKioscoList) {
                //if (checkId(db, UPLOADS_TABLE_NAME, UPLOADS_ID, upload.getIdSubida().toString()) == 0) {
                cv.put(KIOSCO_ARCHIVO_KIOSCO, filesKiosco.getArchivoKiosco());
                cv.put(KIOSCO_CODIGO, filesKiosco.getCodigo());
                cv.put(KIOSCO_RUTA, filesKiosco.getRuta());
                cv.put(KIOSCO_ID_ENTREGA, filesKiosco.getIdEntrega());
                cv.put(KIOSCO_NOMBRE, filesKiosco.getNombreArchivo());
                cv.put(KIOSCO_ID_SUBIDA, filesKiosco.getSubida_idsubida());


                id = db.insert(KIOSCO_TABLE_NAME, null, cv);
                //    }

            }


        }

        db.close();
        dbHelper.close();
        return id;
    }


    @Override
    public void updateAnswerMessageState(Integer idMessageAnswer, Context context) {

        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // cv.put(MESSAGE_ANSWER_KIOSCO_ID, idMessageAnswer);
        cv.put(MESSAGE_ANSWER_KIOSCO_ESTADO, 1);

        Integer countRows = db.update(MESSAGE_ANSWER_KIOSCO_TABLE_NAME, cv, MESSAGE_ANSWER_KIOSCO_ID + " = " + idMessageAnswer +
                " AND " + MESSAGE_ANSWER_KIOSCO_ESTADO + " = " + 0, null);


        db.close();
        dbHelper.close();
    }

    @Override
    public void updateSubmissionUpp(Integer rTentregaId, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SUBMISSIONS_RT_SUBMISSION, rTentregaId);
        cv.put(SUBMISSIONS_UPP, 1);

        Integer countRows = db.update(SUBMISSIONS_TABLE_NAME, cv, SUBMISSIONS_RT_SUBMISSION + " = " + rTentregaId +
                " AND " + SUBMISSIONS_UPP + " = " + 0, null);


        db.close();
        dbHelper.close();
    }

    @Override
    public void updateTaskRegister(Task task, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TASK_REGISTER, task.getRegistroTarea());


        Integer countRows = db.update(TASK_TABLE_NAME, cv, TASK_KIOSCO + " = " + task.getTareakiosco() +
                " AND " + TASK_STUDENT_ID + " = " + task.getEstudiante(), null);


        db.close();
        dbHelper.close();
    }

    @Override
    public void updateAnswerMessage(String body, Integer idMensajeKiosco, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MESSAGE_ANSWER_KIOSCO_BODY, body);
        cv.put(MESSAGE_ANSWER_KIOSCO_ESTADO, 0);
        cv.put(MESSAGE_ANSWER_KIOSCO_ID_MENSAJE_KIOSCO, idMensajeKiosco);
        db.insert(MESSAGE_ANSWER_KIOSCO_TABLE_NAME, null, cv);

        db.close();
        dbHelper.close();
    }


    @Override
    public void updateTasks(List<Task> taskList, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (taskList != null) {
            for (Task task : taskList) {
                if (checkId(db, TASK_TABLE_NAME, TASK_ID, task.getId().toString()) == 0) {
                    cv.put(TASK_ID, task.getId());
                    cv.put(TASK_NAME, task.getNombre());
                    cv.put(TASK_SUBJECT_ID, task.getMaterias());
                    cv.put(TASK_UPLOAD_ID, task.getSubida().getId());
                    cv.put(TASK_REGISTER, task.getRegistroTarea());
                    cv.put(TASK_CODE, task.getCodigo());
                    cv.put(TASK_STUDENT_ID, task.getEstudiante());
                    db.insert(TASK_TABLE_NAME, null, cv);
                }
            }
        }

        db.close();
        dbHelper.close();
    }

    @Override
    public void updateBlobs(List<Blob> blobList, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (blobList != null) {
            for (Blob blob : blobList) {
                if (checkId(db, BLOB_TABLE_NAME, BLOB_CODE, blob.getCodigo().toString()) == 0) {
                    cv.put(BLOB_CODE, blob.getCodigo());
                    cv.put(BLOB_UPLOAD_ID, blob.getSubida());
                    cv.put(BLOB_SUBMISSION_ID, blob.getEntrega());
                    cv.put(BLOB_RUTE, blob.getFile());
                    db.insert(BLOB_TABLE_NAME, null, cv);
                }
            }
        }

        db.close();
        dbHelper.close();
    }

    @Override
    public void postTask(RequestBody requestBody) {

    }

    @Override
    public void postEvaluations(RequestBody requestBody) {

    }

    @Override
    public void postSubmissions(RequestBody requestBody) {

    }

    @Override
    public void postExercises(RequestBody requestBody) {

    }

    @Override
    public void postBlob(RequestBody requestBody) {

    }

    //Utils

    /**
     * Method that traverses the column of the table
     * and searches if the value is already in that table
     *
     * @param db        SQLiteDatabase
     * @param tableName tableName
     * @param columName columnName
     * @param value     value
     * @return Number of records with the same value
     */
    public int checkId(SQLiteDatabase db, String tableName, String columName, String value) {
        Cursor c = null;
        String query = "SELECT count(*) FROM " + tableName + " WHERE " + columName + " = " + value;
        c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            return c.getInt(0);
        }
        c.close();
        return 0;
    }


    public int checkRelStudentSubject(SQLiteDatabase db, String tableName, String fk_student, String studentValue, String fk_subject, String subjectValue) {
        Cursor c = null;
        String query = "SELECT count(*) FROM " + tableName
                + " WHERE " + fk_student + " = " + studentValue
                + " AND  " + fk_subject + " = " + subjectValue;
        c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            return c.getInt(0);
        }
        c.close();
        return 0;
    }


    public int checkRelations(SQLiteDatabase db, String tableName, String campo1, String campo1Valor, String campo2, String campo2Valor) {
        Cursor c = null;
        String query = "SELECT count(*) FROM " + tableName
                + " WHERE " + campo1 + " = " + campo1Valor
                + " AND  " + campo2 + " = " + campo2Valor;
        c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            return c.getInt(0);
        }
        c.close();
        return 0;
    }


    /**
     * Method that obtains the code and name of the grade of any subject
     *
     * @param db database
     * @param id id grade
     * @return object Grade
     */
    public Grade getGrade(SQLiteDatabase db, String id) {
        Grade grade = null;
        String query = "SELECT * FROM " + INFO_GRADE_TABLE_NAME + " WHERE " + INFO_GRADE_CODIGOGRADO + " = " + id;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        try {
            if (cursor.getCount() > 0) {
                do {
                    //get columns
                    int idGrade = cursor.getColumnIndex(INFO_GRADE_CODIGOGRADO);
                    int titleGrade = cursor.getColumnIndex(INFO_GRADE_NOMBRE);

                    //add row to object
                    grade = new Grade(cursor.getInt(idGrade),
                            cursor.getString(titleGrade));

                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }
        return grade;
    }

    /**
     * Method that obtains the code and name of the teacher of any subject
     *
     * @param db database
     * @param id id teacher
     * @return object teacher
     */
    public Teacher getTeacher(SQLiteDatabase db, String id) {
        Teacher teacher = null;
        String query = "SELECT * FROM " + TEACHER_TABLE_NAME + " WHERE " + TEACHER_ID + " = " + id;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        try {
            if (cursor.getCount() > 0) {
                do {
                    //get columns
                    int idTeacher = cursor.getColumnIndex(TEACHER_ID);
                    int teacherName = cursor.getColumnIndex(TEACHER_NAME);

                    //add row to object
                    teacher = new Teacher(cursor.getInt(idTeacher),
                            cursor.getString(teacherName));

                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }
        return teacher;
    }

    public Upload getUpload(SQLiteDatabase db, String id) {
        Upload upload = null;
        String query = "SELECT * FROM " + UPLOAD_TABLE_NAME + " WHERE " + UPLOAD_ID + " = " + id;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        try {
            if (cursor.getCount() > 0) {
                do {
                    //get columns
                    int idUpload = cursor.getColumnIndex(UPLOAD_ID);
                    int uploadDate = cursor.getColumnIndex(UPLOAD_DATE);

                    //add row to object
                    upload = new Upload(cursor.getInt(idUpload),
                            cursor.getString(uploadDate));

                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }
        return upload;
    }


    @Override
    public List<LessonsRemote> getLessons(Context context, Integer idEstudiante) {
        List<LessonsRemote> lessonsRemoteList = new ArrayList<>();

        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = " SELECT  " + LESSONS_TABLE_NAME + ".*  FROM " + LESSONS_TABLE_NAME
                + " , " + REL_STUDENT_SUBJECT_TABLE_NAME +
                " , " + SUBJECTS_TABLE_NAME +
                " WHERE " + LESSONS_SUBJECT_ID + " = " + SUBJECTS_ID +
                " AND " + LESSONS_SUBJECT_ID + " = " + REL_STUDENT_SUBECT_FK_SUBJECT +
                " AND " + REL_STUDENT_SUBJECT_FK_STUDENT + " = " + idEstudiante;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //get columns

                    //add row to list
                    lessonsRemoteList.add(new LessonsRemote(
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex(LESSONS_ID))),
                            cursor.getString(cursor.getColumnIndex(LESSONS_NAME)),
                            cursor.getString(cursor.getColumnIndex(LESSONS_THEME)),
                            cursor.getString(cursor.getColumnIndex(LESSONS_INIT_DATE)),
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex(LESSONS_SUBJECT_ID))),
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex(LESSONS_TEACHER_ID))),
                            cursor.getString(cursor.getColumnIndex(LESSONS_CODIGO))

                    ));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return lessonsRemoteList;
    }

    @Override
    public List<StudyMaterialRemote> getStudyMaterial(Context context, Integer idLesson) {
        List<StudyMaterialRemote> studyMaterialRemoteList = new ArrayList<>();

        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = " SELECT  *  FROM " + STUDYMATERIAL_TABLE_NAME +
                " WHERE " + STUDYMATERIAL_CLASSES_ID + " =  " + idLesson;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //add row to list
                    studyMaterialRemoteList.add(new StudyMaterialRemote(
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex(STUDYMATERIAL_ID))),
                            cursor.getString(cursor.getColumnIndex(STUDYMATERIAL_NAME)),
                            cursor.getString(cursor.getColumnIndex(STUDYMATERIAL_DESCRIPTION)),
                            cursor.getString(cursor.getColumnIndex(STUDYMATERIAL_NAME_FILE)),
                            cursor.getString(cursor.getColumnIndex(STUDYMATERIAL_CLASSES_ID)),
                            cursor.getString(cursor.getColumnIndex(STUDYMATERIAL_PATH))

                    ));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return studyMaterialRemoteList;
    }

    @Override
    public List<SubmissionPending> getSubmissionsToUpload(Context context, Integer idEstudiante) {
        List<SubmissionPending> submissionsList = new ArrayList<>();
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT  RTentrega AS TareaRegistroID ,ruta AS Archivo FROM " + SUBMISSIONS_TABLE_NAME + "," + KIOSCO_TABLE_NAME +
                " WHERE " + SUBMISSIONS_STUDENT_ID + " = " + idEstudiante +
                " AND   ENTREGAS.idEntrega = ARCHIVOSKIOSCO.idEntrega" +
                " AND " + SUBMISSIONS_UPP + " =  0 ";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //get columns
                    int tareaRegistroID = cursor.getColumnIndex("TareaRegistroID");
                    int archivo = cursor.getColumnIndex("Archivo");

                    //add row to list
                    submissionsList.add(
                            new SubmissionPending(
                                    Integer.parseInt(cursor.getString(tareaRegistroID)),
                                    cursor.getString(archivo),
                                    "macAddress"
                            ));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return submissionsList;
    }

    @Override
    public List<MessageRemote> getAllMessagesToRegist(Context context, Integer idEstudiante) {
        List<MessageRemote> messageRemoteList = new ArrayList<>();
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String query = " SELECT RESPUESTA_MENSAJE.ID AS ID , MENSAJE_KIOSCO.REGISTRO_MENSAJE_KIOSCO AS REGISTRO_MENSAJE_KIOSCO , " +
                " RESPUESTA_MENSAJE.BODY AS BODY " +
                " FROM MENSAJE_KIOSCO,RESPUESTA_MENSAJE " +
                " WHERE ID_ESTUDIANTE = " + idEstudiante +
                " AND MENSAJE_KIOSCO.ID = RESPUESTA_MENSAJE.ID_MENSAJE_KIOSCO" +
                " AND RESPUESTA_MENSAJE.ESTADO = 0";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //get columns
                    int idRegisterMessage = cursor.getColumnIndex(MESSAGE_KIOSCO_REGISTRO_MENSAJE_KIOSCO);
                    int messageDescription = cursor.getColumnIndex(MESSAGE_ANSWER_KIOSCO_BODY);
                    int idRespuestaMensaje = cursor.getColumnIndex(MESSAGE_ANSWER_KIOSCO_ID);


                    //add row to list
                    messageRemoteList.add(new MessageRemote(
                            cursor.getString(messageDescription),
                            Integer.parseInt(cursor.getString(idRegisterMessage)),
                            Integer.parseInt(cursor.getString(idRespuestaMensaje))

                    ));

                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return messageRemoteList;
    }

    @Override
    public List<Task> getAllPendingTasks(Context context, Integer idEstudiante) {

        List<Task> taskList = new ArrayList<>();

        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = " SELECT " + TASK_TABLE_NAME + ".* ," +
                SUBJECTS_TABLE_NAME + "." + SUBJECTS_TITLE +
                " from " + TASK_TABLE_NAME + ", " + SUBJECTS_TABLE_NAME +
                " LEFT JOIN " + SUBMISSIONS_TABLE_NAME +
                " ON ID = Tarea_idTarea" +
                " WHERE Tarea_idTarea IS NULL" +
                " AND TAREAS.ESTUDIANTE_IDESTUDIANTE = " + idEstudiante +
                " AND TAREAS.MATERIA_IDMATERIA = MATERIAS.idMateria ";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //get columns
                    int idTask = cursor.getColumnIndex(TASK_ID);
                    int nameTask = cursor.getColumnIndex(TASK_NAME);
                    int idSubjectTask = cursor.getColumnIndex(TASK_SUBJECT_ID);
                    int idUploadTask = cursor.getColumnIndex(TASK_UPLOAD_ID);
                    int codeTask = cursor.getColumnIndex(TASK_CODE);
                    int idStudent = cursor.getColumnIndex(TASK_STUDENT_ID);
                    int tareaKiosco = cursor.getColumnIndex(TASK_KIOSCO);
                    int tituloMateria = cursor.getColumnIndex(SUBJECTS_TITLE);

                    //add row to list
                    taskList.add(new Task(
                            Integer.parseInt(cursor.getString(idTask)),
                            getUpload(db, cursor.getString(idUploadTask)),
                            cursor.getString(nameTask),
                            cursor.getString(codeTask),
                            Integer.parseInt(cursor.getString(idSubjectTask)),
                            Integer.parseInt(cursor.getString(idStudent)),
                            Integer.parseInt(cursor.getString(tareaKiosco)),
                            cursor.getString(tituloMateria)
                    ));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return taskList;
    }

    @Override
    public List<Task> getPendingTasks(Context context, Task task) {
        List<Task> taskList = new ArrayList<>();

        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT " + TASK_TABLE_NAME + ".* FROM " + TASK_TABLE_NAME +
                "," + UPLOAD_TABLE_NAME + "," + SUBMISSIONS_TABLE_NAME +
                " WHERE TAREAS.MATERIA_IDMATERIA = " + task.getMaterias() +
                " AND ID =" + task.getId() +
                " and TAREAS.ESTUDIANTE_IDESTUDIANTE = " + task.getEstudiante() +
                " AND ENTREGAS.Tarea_idTarea = TAREAS.ID" +
                " and TAREAS.ESTUDIANTE_IDESTUDIANTE =SUBIDA.Estudiante_idEstudiante" +
                " and ENTREGAS.Estudiante_IdEstudiante = TAREAS.ESTUDIANTE_IDESTUDIANTE" +
                " AND SUBIDA.idSubida = ENTREGAS.Subida_idSubida";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //get columns
                    int idTask = cursor.getColumnIndex(TASK_ID);
                    int nameTask = cursor.getColumnIndex(TASK_NAME);
                    int idSubjectTask = cursor.getColumnIndex(TASK_SUBJECT_ID);
                    int idUploadTask = cursor.getColumnIndex(TASK_UPLOAD_ID);
                    int codeTask = cursor.getColumnIndex(TASK_CODE);
                    int idStudent = cursor.getColumnIndex(TASK_STUDENT_ID);
                    int tareaKiosco = cursor.getColumnIndex(TASK_KIOSCO);

                    //add row to list
                    taskList.add(new Task(
                            Integer.parseInt(cursor.getString(idTask)),
                            getUpload(db, cursor.getString(idUploadTask)),
                            cursor.getString(nameTask),
                            cursor.getString(codeTask),
                            Integer.parseInt(cursor.getString(idSubjectTask)),
                            Integer.parseInt(cursor.getString(idStudent)),
                            Integer.parseInt(cursor.getString(tareaKiosco))
                    ));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return taskList;
    }

    @Override
    public List<MessageRemote> getMyMessagesPendingRegist(Context context, Integer idEstudiante) {
        List<MessageRemote> messageList = new ArrayList<>();
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT  MATERIAS.Titulo , MENSAJE_KIOSCO.* from MENSAJE_KIOSCO,MATERIAS " +
                " WHERE ID_ESTUDIANTE =" + idEstudiante +
                " AND ID_MATERIA = MATERIAS.idMateria " +
                " AND REGISTRO_MENSAJE_KIOSCO = 0";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //get columns
                    int tituloMateria = cursor.getColumnIndex(SUBJECTS_TITLE);
                    int idMensaje = cursor.getColumnIndex(MESSAGE_KIOSCO_ID);
                    int idfechaDescarga = cursor.getColumnIndex(MESSAGE_KIOSCO_FECHA_DESCARGA);
                    int idD2l = cursor.getColumnIndex(MESSAGE_KIOSCO_IDD2L);
                    int idMensajeKiosco = cursor.getColumnIndex(MESSAGE_KIOSCO_ID_MENSAJE_KIOSCO);
                    int nombreMensaje = cursor.getColumnIndex(MESSAGE_KIOSCO_NOMBRE);
                    int registroMensajeKiosco = cursor.getColumnIndex(MESSAGE_KIOSCO_REGISTRO_MENSAJE_KIOSCO);
                    int idEstudianteKiosco = cursor.getColumnIndex(MESSAGE_KIOSCO_ID_ESTUDIANTE);
                    int idMateriaKiosco = cursor.getColumnIndex(MESSAGE_KIOSCO_ID_MATERIA);

                    //add row to list
                    messageList.add(new MessageRemote(
                            Integer.parseInt(cursor.getString(idMensaje)),
                            cursor.getString(nombreMensaje),
                            cursor.getString(idD2l),
                            "",
                            cursor.getString(idfechaDescarga),
                            null,
                            null,
                            Integer.parseInt(cursor.getString(idMensajeKiosco)),
                            cursor.getString(tituloMateria),
                            Integer.parseInt(cursor.getString(registroMensajeKiosco)),
                            Integer.parseInt(cursor.getString(idEstudianteKiosco)),
                            Integer.parseInt(cursor.getString(idMateriaKiosco))

                    ));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return messageList;

    }

    @Override
    public void updateMessageKiosco(MessageRemote messageRemote, Context context) {
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MESSAGE_KIOSCO_REGISTRO_MENSAJE_KIOSCO, messageRemote.getRegistroMensajeKiosco());


        Integer countRows = db.update(MESSAGE_KIOSCO_TABLE_NAME, cv, MESSAGE_KIOSCO_ID_MENSAJE_KIOSCO + " = " + messageRemote.getMensajeKioscoId() +
                " AND " + MESSAGE_KIOSCO_ID_ESTUDIANTE + " = " + messageRemote.getIdEstudiante(), null);


        db.close();
        dbHelper.close();
    }


    @Override
    public List<MessageRemote> getMyMessages(Context context, Integer idEstudiante) {
        List<MessageRemote> messageList = new ArrayList<>();
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT  MATERIAS.Titulo , MENSAJE_KIOSCO.* from MENSAJE_KIOSCO,MATERIAS " +
                " WHERE ID_ESTUDIANTE =" + idEstudiante +
                " AND ID_MATERIA = MATERIAS.idMateria ";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //get columns
                    int tituloMateria = cursor.getColumnIndex(SUBJECTS_TITLE);
                    int idMensaje = cursor.getColumnIndex(MESSAGE_KIOSCO_ID);
                    int idfechaDescarga = cursor.getColumnIndex(MESSAGE_KIOSCO_FECHA_DESCARGA);
                    int idD2l = cursor.getColumnIndex(MESSAGE_KIOSCO_IDD2L);
                    int idMensajeKiosco = cursor.getColumnIndex(MESSAGE_KIOSCO_ID_MENSAJE_KIOSCO);
                    int nombreMensaje = cursor.getColumnIndex(MESSAGE_KIOSCO_NOMBRE);
                    int registroMensajeKiosco = cursor.getColumnIndex(MESSAGE_KIOSCO_REGISTRO_MENSAJE_KIOSCO);
                    int idEstudianteKiosco = cursor.getColumnIndex(MESSAGE_KIOSCO_ID_ESTUDIANTE);
                    int idMateriaKiosco = cursor.getColumnIndex(MESSAGE_KIOSCO_ID_MATERIA);

                    //add row to list
                    messageList.add(new MessageRemote(
                            Integer.parseInt(cursor.getString(idMensaje)),
                            cursor.getString(nombreMensaje),
                            cursor.getString(idD2l),
                            "",
                            cursor.getString(idfechaDescarga),
                            null,
                            null,
                            Integer.parseInt(cursor.getString(idMensajeKiosco)),
                            cursor.getString(tituloMateria),
                            Integer.parseInt(cursor.getString(registroMensajeKiosco)),
                            Integer.parseInt(cursor.getString(idEstudianteKiosco)),
                            Integer.parseInt(cursor.getString(idMateriaKiosco))

                    ));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return messageList;

    }

    @Override
    public List<FileMessageRemote> getMyFilesMessage(Context context, Integer idMensajeKiosco) {
        List<FileMessageRemote> fileMessageRemoteList = new ArrayList<>();
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM ARCHIVO_MENSAJE_KIOSCO" +
                " WHERE ID_MENSAJE_KIOSCO =" + idMensajeKiosco;

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //get columns
                    int id = cursor.getColumnIndex(FILE_MESSAGE_KIOSCO_ID);
                    int fechDescarga = cursor.getColumnIndex(FILE_MESSAGE_FECHA_DESCARGA);
                    int idArchivoMensaje = cursor.getColumnIndex(FILE_MESSAGE_ID_ARCHIVO_MENSAJE);
                    int idArchivoKiosco = cursor.getColumnIndex(FILE_MESSAGE_ID_ARCHIVO_KIOSCO);
                    int idD2l = cursor.getColumnIndex(FILE_MESSAGE_IDD2L);
                    int nombre = cursor.getColumnIndex(FILE_MESSAGE_NOMBRE);
                    int url = cursor.getColumnIndex(FILE_MESSAGE_URL);
                    int idMensajeKioscos = cursor.getColumnIndex(FILE_MESSAGE_ID_MENSAJE_KIOSCO);

                    //add row to list
                    fileMessageRemoteList.add(new FileMessageRemote(
                            Integer.parseInt(cursor.getString(id)),
                            cursor.getString(nombre),
                            0,
                            cursor.getString(fechDescarga),
                            cursor.getString(idD2l),
                            cursor.getString(url),
                            Integer.parseInt(cursor.getString(idMensajeKioscos))

                    ));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return fileMessageRemoteList;
    }

    @Override
    public List<Task> getTasksToRegister(Context context, Integer idEstudiante) {
        List<Task> taskList = new ArrayList<>();

        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM " + TASK_TABLE_NAME +
                " WHERE " + TASK_STUDENT_ID + "  = " + idEstudiante +
                " AND " + TASK_REGISTER + " = 0";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //get columns
                    int idTask = cursor.getColumnIndex(TASK_ID);
                    int nameTask = cursor.getColumnIndex(TASK_NAME);
                    int idSubjectTask = cursor.getColumnIndex(TASK_SUBJECT_ID);
                    int idUploadTask = cursor.getColumnIndex(TASK_UPLOAD_ID);
                    int codeTask = cursor.getColumnIndex(TASK_CODE);
                    int idStudent = cursor.getColumnIndex(TASK_STUDENT_ID);
                    int tareaKiosco = cursor.getColumnIndex(TASK_KIOSCO);

                    //add row to list
                    taskList.add(new Task(
                            Integer.parseInt(cursor.getString(idTask)),
                            getUpload(db, cursor.getString(idUploadTask)),
                            cursor.getString(nameTask),
                            cursor.getString(codeTask),
                            Integer.parseInt(cursor.getString(idSubjectTask)),
                            Integer.parseInt(cursor.getString(idStudent)),
                            Integer.parseInt(cursor.getString(tareaKiosco))
                    ));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return taskList;
    }

    @Override
    public List<Task> getTasks(Context context, Integer idEstudiante) {
        List<Task> taskList = new ArrayList<>();

        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM " + TASK_TABLE_NAME +
                " WHERE " + TASK_STUDENT_ID + "  = " + idEstudiante;

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //get columns
                    int idTask = cursor.getColumnIndex(TASK_ID);
                    int nameTask = cursor.getColumnIndex(TASK_NAME);
                    int idSubjectTask = cursor.getColumnIndex(TASK_SUBJECT_ID);
                    int idUploadTask = cursor.getColumnIndex(TASK_UPLOAD_ID);
                    int codeTask = cursor.getColumnIndex(TASK_CODE);
                    int idStudent = cursor.getColumnIndex(TASK_STUDENT_ID);

                    //add row to list
                    taskList.add(new Task(
                            Integer.parseInt(cursor.getString(idTask)),
                            getUpload(db, cursor.getString(idUploadTask)),
                            cursor.getString(nameTask),
                            cursor.getString(codeTask),
                            Integer.parseInt(cursor.getString(idSubjectTask)),
                            Integer.parseInt(cursor.getString(idStudent))
                    ));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return taskList;
    }

    @Override
    public List<Lessons> getLessons(Context context, Integer idEstudiante, Integer idMateria) {
        List<Lessons> lessonsList = new ArrayList<>();

        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT " + LESSONS_TABLE_NAME + ".* FROM  " + LESSONS_TABLE_NAME + " ," + REL_STUDENT_SUBJECT_TABLE_NAME +
                " WHERE " + LESSONS_SUBJECT_ID + " = " + idMateria +
                " AND " + REL_STUDENT_SUBJECT_FK_STUDENT + " = " + idEstudiante +
                " AND " + REL_STUDENT_SUBECT_FK_SUBJECT + " = " + LESSONS_SUBJECT_ID;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //add row to list
                    lessonsList.add(new Lessons(
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex(LESSONS_ID))),
                            cursor.getString(cursor.getColumnIndex(LESSONS_NAME)),
                            cursor.getString(cursor.getColumnIndex(LESSONS_THEME)),
                            cursor.getString(cursor.getColumnIndex(LESSONS_INIT_DATE)),
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex(LESSONS_SUBJECT_ID))),
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex(LESSONS_TEACHER_ID)) == null ? "0" : cursor.getString(cursor.getColumnIndex(LESSONS_TEACHER_ID)))
                    ));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return lessonsList;


    }

    @Override
    public List<FilesKiosco> getFileKioscos(Context context, Integer idEstudiante, Integer idMateria, Integer idTarea) {
        List<FilesKiosco> filesKioscoList = new ArrayList<>();

        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
      /*  String query = " SELECT DISTINCT ARCHIVOSKIOSCO.* FROM TAREAS,ARCHIVOSKIOSCO,REL_ESTUDIANTE_MATERIAS,SUBIDA " +
                " WHERE FK_ESTUDIANTE = " + idEstudiante+
                " AND FK_MATERIA = " + idMateria+
                " AND TAREAKIOSCO = " + idTarea +
                " AND SUBIDA.Estudiante_idEstudiante = FK_ESTUDIANTE " +
                " AND SUBIDA.idSubida = TAREAS.SUBIDA_IDSUBIDA " +
                " AND ARCHIVOSKIOSCO.subida_idsubida = TAREAS.SUBIDA_IDSUBIDA " +
                " AND ARCHIVOSKIOSCO.archivoKiosco = tareas.TAREAKIOSCO ";
       */
        String query = "SELECT ARCHIVOSKIOSCO.* FROM ARCHIVOSKIOSCO,TAREAS" +
                " WHERE ESTUDIANTE_IDESTUDIANTE =" + idEstudiante +
                " AND MATERIA_IDMATERIA = " + idMateria +
                " AND TAREAKIOSCO = " + idTarea +
                " AND idEntrega IS NULL " +  /// VERIFICAR CAMBIO DE OBTENCION DE ARCHIVOS DE TAREAS
                " AND ARCHIVOSKIOSCO.subida_idsubida = TAREAS.SUBIDA_IDSUBIDA";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //add row to list
                    filesKioscoList.add(new FilesKiosco(
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex(KIOSCO_ID_ARCHIVOS))),
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex(KIOSCO_ARCHIVO_KIOSCO))),
                            cursor.getString(cursor.getColumnIndex(KIOSCO_CODIGO)),
                            cursor.getString(cursor.getColumnIndex(KIOSCO_RUTA)),
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex(KIOSCO_ID_ENTREGA)) == null ? "0" : cursor.getString(cursor.getColumnIndex(KIOSCO_ID_ENTREGA))),
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex(KIOSCO_ID_SUBIDA))),
                            cursor.getString(cursor.getColumnIndex(KIOSCO_NOMBRE))
                    ));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return filesKioscoList;
    }

    @Override
    public List<MessageAnswer> getMessageAnswers(Context context, Integer idMensajeKiosco) {
        List<MessageAnswer> messageAnswerList = new ArrayList<>();
        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT RESPUESTA_MENSAJE.ID AS ID ," +
                " RESPUESTA_MENSAJE.BODY AS BODY," +
                "        CASE ESTADO WHEN 0 THEN  'SIN ENTREGAR' WHEN 1 THEN 'ENTREGADO' END AS ESTADO, " +
                "        RESPUESTA_MENSAJE.ID_MENSAJE_KIOSCO AS ID_MENSAJE_KIOSCO " +
                " FROM RESPUESTA_MENSAJE " +
                " WHERE ID_MENSAJE_KIOSCO = " + idMensajeKiosco;

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //add row to list
                    messageAnswerList.add(new MessageAnswer(
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex("ID"))),
                            cursor.getString(cursor.getColumnIndex("BODY")),
                            cursor.getString(cursor.getColumnIndex("ESTADO")),
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex("ID_MENSAJE_KIOSCO")))
                    ));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return messageAnswerList;
    }


    public List<SubmissionDisplay> getSubmissionsDisplay(Context context, Integer idEstudiante, Integer idMateria, Integer idTarea) {
        List<SubmissionDisplay> submissionDisplayList = new ArrayList<>();

        DbOpenHelper dbHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT ENTREGAS.idEntrega,ENTREGAS.upp as upp ,ARCHIVOSKIOSCO.nombreArchivo as nombreArchivo , ARCHIVOSKIOSCO.ruta AS ruta" +
                ", CASE " +
                " upp " +
                " WHEN 0 " +
                " THEN 'PENDIENTE' " +
                " WHEN 1 " +
                " THEN 'ENTREGADO' " +
                " END AS estatus " +
                " from TAREAS,ENTREGAS,ARCHIVOSKIOSCO " +
                " where TAREAS.ESTUDIANTE_IDESTUDIANTE = " + idEstudiante +
                " and MATERIA_IDMATERIA = " + idMateria +
                " and id = " + idTarea +
                " and entregas.Subida_idSubida = TAREAS.SUBIDA_IDSUBIDA " +
                " and ARCHIVOSKIOSCO.idEntrega = ENTREGAS.idEntrega ";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                do {
                    //add row to list
                    submissionDisplayList.add(new SubmissionDisplay(
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex("idEntrega"))),
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex("upp"))),
                            cursor.getString(cursor.getColumnIndex("nombreArchivo")),
                            cursor.getString(cursor.getColumnIndex("estatus")),
                            cursor.getString(cursor.getColumnIndex("ruta"))
                    ));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        db.close();
        dbHelper.close();
        return submissionDisplayList;
    }

}
