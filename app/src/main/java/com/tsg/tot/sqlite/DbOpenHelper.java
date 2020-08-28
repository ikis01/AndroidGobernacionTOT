package com.tsg.tot.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.tsg.tot.sqlite.DBConstants.IPEND_IP;
import static com.tsg.tot.sqlite.DBConstants.IPEND_PORT;
import static com.tsg.tot.sqlite.DBConstants.IPEND_TABLE_NAME;
import static com.tsg.tot.sqlite.DBConstants.VERSION_NUMBER;
import static com.tsg.tot.sqlite.DBConstants.VERSION_TABLE_NAME;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String VERSION_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `VERSION` (`NUMERO` FLOAT)";

    private static final String TASK_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `Tarea` (`idTarea` INTEGER," +
            " `NombreTarea` TEXT," +
            " `Materias_idMaterias` INTEGER," +
            " `Subida_idSubida` INTEGER)";

    private static final String UPLOAD_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `Subida` (`idSubida` INTEGER," +
            "`Fecha` TEXT)";

    private static final String TEACHER_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `Profesor` (`idProfesor` INTEGER," +
            "`Nombres` TEXT)";

    private static final String SUBJECTS_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `Materias` (`idMaterias` INTEGER," +
            "`Titulo` TEXT, " +
            "`Cursos_idCursos` INTEGER, " +
            "`Profesor_idProfesor` INTEGER, " +
            "`Subtitulo` TEXT, " +
            "`Descripcion` TEXT, " +
            "`Imagen` TEXT)";

    private static final String STUDY_MATERIAL_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `MaterialEstudio` (" +
            "  `idMaterialEstudio` INTEGER," +
            "  `Clases_id` INTEGER," +
            "  `Nombre` TEXT," +
            "  `Descripcion` TEXT," +
            "  `Blob_idBlob` INTEGER)";

    private static final String IPEND_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `IPEND` (`PORT` TEXT, `IP` TEXT)";

    private static final String EVALUATION_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `Evaluacion` (" +
            "  `idEvaluacion` INTEGER," +
            "  `Nombre` TEXT," +
            "  `Materias_idMaterias` INTEGER," +
            "  `Subida_idSubida` INTEGER)";

    private static final String STUDENT_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `Estudiante` (" +
            "  `idEstudiante` INTEGER," +
            "  `Nombres` INTEGER," +
            "  `Cursos_idCursos` INTEGER," +
            "  `CodigoEstudiante` TEXT)";

    private static final String SUBMISSION_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `Entrega` (" +
            "  `idEntrega` INTEGER," +
            "  `Ejercicios_idEjercicios` INTEGER," +
            "  `Tarea_idTarea` INTEGER," +
            "  `Evaluacion_idEvaluacion` INTEGER," +
            "  `Subida_idSubida` INTEGER," +
            "  `Upp` INTEGER," +
            "  `Creado` DATETIME)";

    private static final String EXERCISES_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `Ejercicios` (" +
            "  `idEjercicios` INTEGER," +
            "  `Nombre` TEXT," +
            "  `Clases_idClases` INTEGER," +
            "  `Subida_idSubida` INTEGER)";

    private static final String CLASSES_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `Clases` (" +
            "  `idClase` INTEGER," +
            "  `Tema` TEXT," +
            "  `Materias_idMaterias` INTEGER," +
            "  `Profesor_idProfesor` INTEGER," +
            "  `NombreClase` TEXT," +
            "  `FechaInicio` TEXT)";

    private static final String BLOB_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `Blob` (" +
            "  `idBlob` INTEGER," +
            "  `Codigo` INTEGER," +
            "  `Subida_idSubida` INTEGER," +
            "  `Entrega_id` INTEGER," +
            "  `Ruta` TEXT)";

    private static final String DB_NAME = "TOT";
    private static final int DB_VERSION = 1;

    public DbOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creo DB
        db.execSQL(VERSION_TABLE_CREATE);
        db.execSQL(TASK_TABLE_CREATE);
        db.execSQL(UPLOAD_TABLE_CREATE);
        db.execSQL(TEACHER_TABLE_CREATE);
        db.execSQL(SUBJECTS_TABLE_CREATE);
        db.execSQL(STUDY_MATERIAL_TABLE_CREATE);
        db.execSQL(IPEND_TABLE_CREATE);
        db.execSQL(EVALUATION_TABLE_CREATE);
        db.execSQL(STUDENT_TABLE_CREATE);
        db.execSQL(SUBMISSION_TABLE_CREATE);
        db.execSQL(EXERCISES_TABLE_CREATE);
        db.execSQL(CLASSES_TABLE_CREATE);
        db.execSQL(BLOB_TABLE_CREATE);

        //Asigno Version 0.1 a la tabla
        ContentValues cv = new ContentValues();
        cv.put(VERSION_NUMBER, 1.0);
        db.insert(VERSION_TABLE_NAME, null, cv);

        cv.clear();
        cv.put(IPEND_PORT, "8000");
        cv.put(IPEND_IP, "192.168.0.27");
        db.insert(IPEND_TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
