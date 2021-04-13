package com.tsg.tot.sqlite;

/**
 * Clase que contiene todas las Constantes utilizadas en la
 * creación y consulta de tablas de la Base de Datos.
 */
public final class DBConstants {

    /**
     * Constants to identify from which repository
     * the information will be extracted
     */
    public static final int API_REPOSITORY = 0;
    public static final int DATABASE_REPOSITORY = 1;

    /**
     * Table Names
     */




    public static final String KIOSCO_TABLE_NAME = "ARCHIVOSKIOSCO";



    public static final String VERSION_TABLE_NAME = "VERSION";
    public static final String TASK_TABLE_NAME = "Tareas";
    public static final String UPLOAD_TABLE_NAME = "Subida";

    public static final String SUBJECTS_TABLE_NAME = "MATERIAS";
    public static final String GRADE_TABLE_NAME = "Grado";
    public static final String STUDYMATERIAL_TABLE_NAME = "MaterialEstudio";
    public static final String IPEND_TABLE_NAME = "IPEND";
    public static final String EVALUATION_TABLE_NAME = "Evaluacion";
    public static final String STUDENTS_TABLE_NAME = "Estudiante";
    public static final String SUBMISSIONS_TABLE_NAME = "ENTREGAS";
    public static final String EXERCISES_TABLE_NAME = "Ejercicios";
    public static final String LESSONS_TABLE_NAME = "Clases";
    public static final String BLOB_TABLE_NAME = "Blob";
    public static final String USERS_TABLE_NAME = "Usuarios";

    public static final String INFO_GRADE_TABLE_NAME = "INFOGRADO";
    public static final String STUDENT_TABLE_NAME = "ESTUDIANTE";
    public static final String TEACHER_TABLE_NAME = "PROFESOR";
    public static final String REL_STUDENT_SUBJECT_TABLE_NAME = "REL_ESTUDIANTE_MATERIAS";
    public static final String UPLOADS_TABLE_NAME = "SUBIDA";

    public static final String MESSAGE_KIOSCO_TABLE_NAME = "MENSAJE_KIOSCO";
    public static final String MESSAGE_ANSWER_KIOSCO_TABLE_NAME = "RESPUESTA_MENSAJE" ;
    public static final String FILE_MESSAGE_KIOSCO_TABLE_NAME = "ARCHIVO_MENSAJE_KIOSCO";


    /****
     * RESPUESTA_MENSAJE
     * */
    public static final String MESSAGE_ANSWER_KIOSCO_ID = "ID";
    public static final String MESSAGE_ANSWER_KIOSCO_BODY = "BODY";
    public  static  final String MESSAGE_ANSWER_KIOSCO_ESTADO = "ESTADO";
    public static final String MESSAGE_ANSWER_KIOSCO_ID_MENSAJE_KIOSCO = "ID_MENSAJE_KIOSCO";

    /****
     * MENSAJE_KIOSCO
     * */

    public static final String  MESSAGE_KIOSCO_ID = "ID";
    public static final String  MESSAGE_KIOSCO_FECHA_DESCARGA = "FECHA_DESCARGA";
    public static final String  MESSAGE_KIOSCO_IDD2L= "IDD2L";
    public static final String  MESSAGE_KIOSCO_ID_MENSAJE_KIOSCO = "ID_MENSAJE_KIOSCO";
    public static final String  MESSAGE_KIOSCO_NOMBRE = "NOMBRE";
    public static final String  MESSAGE_KIOSCO_REGISTRO_MENSAJE_KIOSCO = "REGISTRO_MENSAJE_KIOSCO";
    public static final String  MESSAGE_KIOSCO_ID_ESTUDIANTE = "ID_ESTUDIANTE";
    public static final String  MESSAGE_KIOSCO_ID_MATERIA = "ID_MATERIA";


    /******
     * ARCHIVO_MENSAJE_KIOSCO
     * */

    public static final String  FILE_MESSAGE_KIOSCO_ID = "ID";
    public static final String  FILE_MESSAGE_FECHA_DESCARGA = "FECHA_DESCARGA";
    public static final String  FILE_MESSAGE_ID_ARCHIVO_MENSAJE = "ID_ARCHIVO_MENSAJE";
    public static final String  FILE_MESSAGE_ID_ARCHIVO_KIOSCO = "ID_ARCHIVO_KIOSCO";
    public static final String  FILE_MESSAGE_IDD2L = "IDD2L";
    public static final String  FILE_MESSAGE_NOMBRE = "NOMBRE";
    public static final String  FILE_MESSAGE_URL = "URL";
    public static final String  FILE_MESSAGE_ID_MENSAJE_KIOSCO = "ID_MENSAJE_KIOSCO";

    /****
     * ARCHIVOSKIOSCO columns
     */
    public static final String KIOSCO_ID_ARCHIVOS = "idArchivosTOT";
    public static final String KIOSCO_ARCHIVO_KIOSCO = "archivoKiosco";
    public static final String KIOSCO_CODIGO ="codigo";
    public static final String KIOSCO_RUTA ="ruta" ;
    public static final String KIOSCO_NOMBRE ="nombreArchivo";
    public static final String KIOSCO_ID_ENTREGA ="idEntrega";
    public static final String KIOSCO_ID_SUBIDA ="subida_idsubida";


    /**
     *
     * UPLOADS COLUMNS
     *
     * */
    public static final String UPLOADS_ID = "idSubida";
    public static final String UPLOADS_FECHA = "Fecha";
    public static final String UPLOADS_FECHA_DESCARGA ="FechaDescarga";
    public static final String UPLOADS_SUBIDA_KIOSCO ="SubidaKiosco";
    public static final String UPLOADS_ID_ESTUDIANTE ="Estudiante_idEstudiante";


    /**
     *
     * REL_ESTUDIANTE_MATERIA  COLUMNS
     * */

    public static final String REL_STUDENT_SUBJECT_FK_STUDENT = "FK_ESTUDIANTE";
    public static final String REL_STUDENT_SUBECT_FK_SUBJECT = "FK_MATERIA";

    /**
     * Version table columns
     */
    public static final String VERSION_NUMBER = "NUMERO";

    /**
     * Tarea table columns
     */
    public static final String TASK_ID = "ID";
    public static final String TASK_CODE = "CODIGO";
    public static final String TASK_NAME = "NOMBRETAREA";
    public static final String TASK_REGISTER = "REGISTROTAREA";
    public static final String TASK_KIOSCO = "TAREAKIOSCO";
    public static final String TASK_STUDENT_ID = "ESTUDIANTE_IDESTUDIANTE";
    public static final String TASK_SUBJECT_ID = "MATERIA_IDMATERIA";
    public static final String TASK_UPLOAD_ID = "SUBIDA_IDSUBIDA";


    /**
     * Subida table columns
     */
    public static final String UPLOAD_ID = "idSubida";
    public static final String UPLOAD_DATE = "Fecha";

    /**
     * Profesor table columns
     */
    public static final String TEACHER_ID = "idProfesor";
    public static final String TEACHER_LAST_NAME = "Apellidos";
    public static final String TEACHER_NAME = "Nombres";
    public static final String TEACHER_CODE = "Codigo";

    /**
     * Materias table columns
     */
    public static final String SUBJECTS_ID = "idMateria";
    public static final String SUBJECTS_CODE = "Codigo";
    public static final String SUBJECTS_DESCRIPTION = "Descripcion";
    public static final String SUBJECTS_IMAGE = "Imagen";
    public static final String SUBJECTS_SUBTITLE = "Subtitulo";
    public static final String SUBJECTS_TITLE = "Titulo";
    public static final String SUBJECTS_TEACHER_ID = "Profesor_idProfesor";
    public static final String SUBJECTS_INSTITUTE = "idInstitucion";





    /**
     * Grado table columns
     */
    public static final String GRADE_ID = "idGrado";
    public static final String GRADE_TITLE = "Grado";


    /**
     * Studenr table columns
     * <p>
     * STUDENT TABLE COLUMNS
     */
    public static final String STUDENT_IDESTUDIANTE = "IDESTUDIANTE";
    public static final String STUDENT_APELLIDOS = "APELLIDOS";
    public static final String STUDENT_EDAD = "EDAD";
    public static final String STUDENT_NOMBRES = "NOMBRES";
    public static final String STUDENT_FK_USUARIO = "FK_USUARIO";


    /**
     * Grado table columns
     * <p>
     * INFOGRADO TABLE COLUMNS
     */
    public static final String INFO_GRADE_IDINFOGRADO = "idInfoGrado";
    public static final String INFO_GRADE_CODIGOGRADO = "CodigoGrado";
    public static final String INFO_GRADE_INSTITUCION = "Institucion";
    public static final String INFO_GRADE_NOMBRE = "Nombre";
    public static final String INFO_GRADE_UBICACIONINSTITUCION = "UbicacionInstitucion";
    public static final String INFO_GRADE_ESTUDIANTE_IDESTUDIANTE = "Estudiante_idEstudiante";


    /**
     * MaterialEstudio table columns
     */
    public static final String STUDYMATERIAL_ID = "idMaterialEstudio";
    public static final String STUDYMATERIAL_DESCRIPTION = "Descripcion";
    public static final String STUDYMATERIAL_NAME = "Nombre";
    public static final String STUDYMATERIAL_NAME_FILE = "NombreArchivo";
    public static final String STUDYMATERIAL_PATH = "Ruta";
    public static final String STUDYMATERIAL_CLASSES_ID = "Clase_idClases";

    public static final String STUDYMATERIAL_BLOB_ID = "Blob_idBlob";

    /**
     * IPEND table columns
     */
    public static final String IPEND_PORT = "PORT";
    public static final String IPEND_IP = "IP";

    /**
     * Evaluacion table columns
     */
    public static final String EVALUATION_ID = "idEvaluacion";
    public static final String EVALUATION_NAME = "Nombre";
    public static final String EVALUATION_SUBJECT_ID = "Materias_idMaterias";
    public static final String EVALUATION_UPLOAD_ID = "Subida_idSubida";

    /**
     * Estudiante table columns
     */
    public static final String STUDENTS_ID = "idEstudiante";
    public static final String STUDENTS_NAME = "Nombres";
    public static final String STUDENTS_LAST_NAME = "Apellidos";
    public static final String STUDENTS_CURSE_ID = "Cursos_idCursos";
    public static final String STUDENTS_CODE = "CodigoEstudiante";

    /**
     * Entrega table columns
     */
    public static final String SUBMISSIONS_ID = "idEntrega";
    public static final String SUBMISSIONS_CODE ="CodigoEntrega";
    public static final String SUBMISSIONS_CODE_TASK ="CodigoTarea";
    public static final String SUBMISSIONS_CREATED ="Creado";
    public static final String SUBMISSIONS_RT_SUBMISSION ="RTentrega";
    public static final String SUBMISSIONS_UPP ="Upp";
    public static final String SUBMISSIONS_STUDENT_ID ="Estudiante_IdEstudiante";
    public static final String SUBMISSIONS_UPLOAD_ID = "Subida_idSubida";
    public static final String SUBMISSIONS_TASK_ID = "Tarea_idTarea";

    /**
     * Ejercicios table columns
     */
    public static final String EXERCISES_ID = "idEjercicios";
    public static final String EXERCISES_NAME = "Nombre";
    public static final String EXERCISES_CLASSES_ID = "Clases_idClases";
    public static final String EXERCISES_UPLOAD_ID = "Subida_idSubida";

    /**
     * Clases table columns
     */
    public static final String LESSONS_ID = "idClase";
    public static final String LESSONS_CODIGO ="CodigoClase";
    public static final String LESSONS_THEME = "Tema";
    public static final String LESSONS_SUBJECT_ID = "Materias_idMateria";
    public static final String LESSONS_TEACHER_ID = "Profesor_idProfesor";
    public static final String LESSONS_NAME = "Nombre";
    public static final String LESSONS_INIT_DATE = "FechaInicio";

    /**
     * Blob table columns
     */
    public static final String BLOB_ID = "idBlob";
    public static final String BLOB_CODE = "Codigo";
    public static final String BLOB_UPLOAD_ID = "Subida_idSubida";
    public static final String BLOB_SUBMISSION_ID = "Entrega_id";
    public static final String BLOB_RUTE = "Ruta";

    /**
     * Users table columns
     */
    public static final String USERS_ID = "idUsuario";
    public static final String USERS_PASSWORD = "Password";
    public static final String USERS_USER_NAME = "UserName";


}
