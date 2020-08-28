package com.tsg.tot.sqlite;

public final class DBConstants {

    /**
     * Table Names
     */
    public static final String VERSION_TABLE_NAME = "VERSION";
    public static final String TASK_TABLE_NAME = "Tarea";
    public static final String UPLOAD_TABLE_NAME = "Subida";
    public static final String TEACHER_TABLE_NAME = "Profesor";
    public static final String SUBJECTS_TABLE_NAME = "Materias";
    public static final String STUDYMATERIAL_TABLE_NAME = "MaterialEstudio";
    public static final String IPEND_TABLE_NAME = "IPEND";
    public static final String EVALUATION_TABLE_NAME = "Evaluacion";
    public static final String STUDENTS_TABLE_NAME = "Estudiante";
    public static final String SUBMISSIONS_TABLE_NAME = "Entrega";
    public static final String EXERCISES_TABLE_NAME = "Ejercicios";
    public static final String LESSONS_TABLE_NAME = "Clases";
    public static final String BLOB_TABLE_NAME = "Blob";

    /**
     * Version table columns
     */
    public static final String VERSION_NUMBER = "NUMERO";

    /**
     * Tarea table columns
     */
    public static final String TASK_ID = "idTarea";
    public static final String TASK_NAME = "NombreTarea";
    public static final String TASK_SUBJECT_ID = "Materias_idMaterias";
    public static final String TASK_UPLOAD_ID = "Subida_idSubida";

    /**
     * Subida table columns
     */
    public static final String UPLOAD_ID = "idSubida";
    public static final String UPLOAD_DATE = "Fecha";

    /**
     * Profesor table columns
     */
    public static final String TEACHER_ID = "idProfesor";
    public static final String TEACHER_NAME = "Nombres";

    /**
     * Materias table columns
     */
    public static final String SUBJECTS_ID = "idMaterias";
    public static final String SUBJECTS_TITLE = "Titulo";
    public static final String SUBJECTS_CURSE_ID = "Cursos_idCursos";
    public static final String SUBJECTS_TEACHER_ID = "Profesor_idProfesor";
    public static final String SUBJECTS_SUBTITLE = "Subtitulo";
    public static final String SUBJECTS_DESCRIPTION = "Descripcion";
    public static final String SUBJECTS_IMAGE = "Imagen";

    /**
     * MaterialEstudio table columns
     */
    public static final String STUDYMATERIAL_ID = "idMaterialEstudio";
    public static final String STUDYMATERIAL_CLASSES_ID = "Clases_id";
    public static final String STUDYMATERIAL_NAME = "Nombre";
    public static final String STUDYMATERIAL_DESCRIPTION = "Descripcion";
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
    public static final String STUDENTS_CURSE_ID = "Cursos_idCursos";
    public static final String STUDENTS_CODE = "CodigoEstudiante";

    /**
     * Entrega table columns
     */
    public static final String SUBMISSIONS_ID = "idEntrega";
    public static final String SUBMISSIONS_EXERCISES_ID = "Ejercicios_idEjercicios";
    public static final String SUBMISSIONS_TASK_ID = "Tarea_idTarea";
    public static final String SUBMISSIONS_EVALUATION_ID = "Evaluacion_idEvaluacion";
    public static final String SUBMISSIONS_UPLOAD_ID = "Subida_idSubida";
    public static final String SUBMISSIONS_UPP = "Upp";
    public static final String SUBMISSIONS_CREATED = "Creado";

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
    public static final String LESSONS_THEME = "Tema";
    public static final String LESSONS_SUBJECT_ID = "Materias_idMaterias";
    public static final String LESSONS_TEACHER_ID = "Profesor_idProfesor";
    public static final String LESSONS_NAME = "NombreClase";
    public static final String LESSONS_INIT_DATE = "FechaInicio";

    /**
     * Blob table columns
     */
    public static final String BLOB_ID = "idBlob";
    public static final String BLOB_CODE = "Codigo";
    public static final String BLOB_UPLOAD_ID = "Subida_idSubida";
    public static final String BLOB_SUBMISSION_ID = "Entrega_id";
    public static final String BLOB_RUTE = "Ruta";

}
