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

/**
 * Clase que crea la base de datos y todas sus tablas.
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String FILE_KIOSCO_CREATE = " CREATE TABLE IF NOT EXISTS `ARCHIVOSKIOSCO` ( "+
            "`idArchivosTOT` INTEGER constraint `ArchivosKiosco_PK`" +
            "primary key autoincrement,"+
            "`archivoKiosco`  INTEGER,"+
            "`codigo` TEXT," +
            "`ruta` TEXT," +
            "`nombreArchivo` TEXT," +
            "`idEntrega` INTEGER,"  +
            "`subida_idsubida` INTEGER)";

    private static final String CLASSES_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `CLASES` (" +
            "  `idClase` INTEGER," +
            "  `CodigoClase` TEXT," +
            "  `FechaInicio` TEXT,"+
            "  `Nombre` TEXT,"+
            "  `Tema` TEXT," +
            "  `Materias_idMateria` INTEGER," +
            "  `Profesor_idProfesor` INTEGER" +
            ")";

    private static final String SUBMISSION_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `ENTREGAS` (" +
           /* "  `idEntrega` INTEGER," +
            "  `Ejercicios_idEjercicios` INTEGER," +
            "  `Tarea_idTarea` INTEGER," +
            "  `Evaluacion_idEvaluacion` INTEGER," +
            "  `Subida_idSubida` INTEGER," +
            "  `Upp` INTEGER," +
            "  `Creado` DATETIME)";*/

            "  `idEntrega` INTEGER constraint `Entregas_PK`" +
            "primary key autoincrement,"+
            "  `CodigoEntrega` TEXT, "+
            "  `CodigoTarea` TEXT,"+
            "  `Creado` DATETIME,"+
            "  `RTentrega` INTEGER,"+
            "  `Upp` INTEGER," +
            "  `Estudiante_IdEstudiante` INTEGER,"+
            "  `Subida_idSubida` INTEGER," +
            "  `Tarea_idTarea` INTEGER" +
            ")";

    private static final String STUDENT_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `ESTUDIANTE` (" +
            "  `idEstudiante` INTEGER," +
            "  `Apellidos` TEXT," +
            "  `Edad` INTEGER,"+
            "  `Nombres` TEXT," +
            "  `FK_USUARIO` INTEGER," +
            //"  `Cursos_idCursos` INTEGER," +
            "  `CodigoEstudiante` TEXT)";

    private static final String GRADE_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `INFOGRADO` (" +

            "`idInfoGrado` INTEGER constraint `INFOGRADO_PK`" +
            "primary key autoincrement,"+
            "`CodigoGrado` TEXT," +
            "`Institucion` TEXT," +
            "`Nombre` TEXT," +
            "`UbicacionInstitucion` TEXT," +
            "`Estudiante_idEstudiante` INTEGER" +
            ")";

    private static final String STUDY_MATERIAL_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `MATERIALESTUDIO` (" +
            "  `idMaterialEstudio` INTEGER," +
            "  `Descripcion` TEXT," +
            "  `Nombre` TEXT," +
            "  `NombreArchivo` TEXT," +
            "  `Ruta` TEXT," +
            "  `Clase_idClases` INTEGER)" ;


    private static final String SUBJECTS_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `MATERIAS` (" +
            "`idMateria` INTEGER," +
            "`Codigo` TEXT, " +
            "`Descripcion` TEXT, " +
            "`Imagen` TEXT, " +
            "`Subtitulo` TEXT, " +
            "`Titulo` TEXT, " +
            "`idInstitucion` INTEGER," +
            "`Profesor_idProfesor` INTEGER" +
            //"`Cursos_idCursos` INTEGER, " +
            ")";

    private static final String TEACHER_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `PROFESOR` (" +
            "`idProfesor` INTEGER," +
            "`Apellidos` TEXT,"+
            "`Codigo` INTEGER,"+
            "`Nombres` TEXT)";

    private static final String RELATION_STUDENT_SUBJECT = "CREATE TABLE IF NOT EXISTS `REL_ESTUDIANTE_MATERIAS`(" +
            "`FK_ESTUDIANTE` INTEGER, "+
            "`FK_MATERIA` INTEGER) ";

    private static final String UPLOAD_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `SUBIDA` (" +
            "`idSubida` INTEGER constraint `Subida_PK`" +
            "primary key autoincrement,"+
            "`Fecha` TEXT,"+
            "`FechaDescarga` TEXT,"+
            "`SubidaKiosco` INTEGER,"+
            "`Estudiante_idEstudiante` INTEGER)";

    private static final String TASK_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `TAREAS` ("+
            " `ID` INTEGER," +
            " `CODIGO` INTEGER," +
            " `NOMBRETAREA` TEXT," +
            "`REGISTROTAREA` INTEGER," +
            "`TAREAKIOSCO` INTEGER,"+
            "`ESTUDIANTE_IDESTUDIANTE` INTEGER,"+
            " `MATERIA_IDMATERIA` INTEGER," +
            " `SUBIDA_IDSUBIDA` INTEGER)";

    private static final String USERS_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `USUARIOS` ( "+
            "`idUsuario` INTEGER constraint `Usuarios_pk`" +
            "primary key autoincrement,"+
            "`Password`  TEXT,"+
            "`UserName`TEXT )";

    private static final String VERSION_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `VERSION` (" +
            "`idVersion` INTEGER constraint `Version_pk` "+
            " primary key autoincrement,"+
            "`NUMERO` FLOAT)";


    //private static final String GRADE_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `Grado` (" +
          //  "`idGrado` INTEGER," +
          //  "`Grado` TEXT)";


          //  "  `Blob_idBlob` INTEGER)";

    //private static final String IPEND_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `IPEND` (`PORT` TEXT, `IP` TEXT)";

    /*private static final String EVALUATION_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `Evaluacion` (" +
            "  `idEvaluacion` INTEGER," +
            "  `Nombre` TEXT," +
            "  `Materias_idMaterias` INTEGER," +
            "  `Subida_idSubida` INTEGER)";
    */




    /*private static final String EXERCISES_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `Ejercicios` (" +
            "  `idEjercicios` INTEGER," +
            "  `Nombre` TEXT," +
            "  `Clases_idClases` INTEGER," +
            "  `Subida_idSubida` INTEGER)";*/



    /*private static final String BLOB_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS `Blob` (" +
            "  `idBlob` INTEGER," +
            "  `Codigo` INTEGER," +
            "  `Subida_idSubida` INTEGER," +
            "  `Entrega_id` INTEGER," +
            "  `Ruta` TEXT)";
    */




    private static final String DB_NAME = "TOT";
    private static final int DB_VERSION = 1;

    public DbOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crea DB
        db.execSQL(VERSION_TABLE_CREATE);
        db.execSQL(TASK_TABLE_CREATE);
        db.execSQL(UPLOAD_TABLE_CREATE);
        db.execSQL(TEACHER_TABLE_CREATE);
        db.execSQL(SUBJECTS_TABLE_CREATE);
        db.execSQL(GRADE_TABLE_CREATE);
        db.execSQL(STUDY_MATERIAL_TABLE_CREATE);
        // db.execSQL(IPEND_TABLE_CREATE);
        // db.execSQL(EVALUATION_TABLE_CREATE);
        db.execSQL(STUDENT_TABLE_CREATE);
        db.execSQL(SUBMISSION_TABLE_CREATE);
        //db.execSQL(EXERCISES_TABLE_CREATE);
        db.execSQL(CLASSES_TABLE_CREATE);
        //db.execSQL(BLOB_TABLE_CREATE);
        db.execSQL(USERS_TABLE_CREATE);
        db.execSQL(FILE_KIOSCO_CREATE);
        db.execSQL(RELATION_STUDENT_SUBJECT);
        //Asigna Version 0.1 a la tabla
        ContentValues cv = new ContentValues();
        cv.put(VERSION_NUMBER, 1.0);
        db.insert(VERSION_TABLE_NAME, null, cv);

        //cv.clear();
        //cv.put(IPEND_PORT, "8000");
        //cv.put(IPEND_IP, "192.168.0.27");
        //db.insert(IPEND_TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

}
