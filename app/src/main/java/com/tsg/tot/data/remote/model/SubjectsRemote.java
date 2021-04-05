package com.tsg.tot.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubjectsRemote {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("titulo")
    @Expose
    private String titulo;

    @SerializedName("subtitulo")
    @Expose
    private String subtitulo;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("profesorId")
    @Expose
    private Integer profesorId;

    @SerializedName("idD2L")
    @Expose
    private Integer idD2L;

    @SerializedName("institucionId")
    @Expose
    private String institucionId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getInstitucionId() {
        return institucionId;
    }

    public void setInstitucionId(String institucionId) {
        this.institucionId = institucionId;
    }

   public Integer getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Integer profesorId) {
        this.profesorId = profesorId;
    }

    public Integer getIdD2L() {
        return idD2L;
    }

    public void setIdD2L(Integer idD2L) {
        this.idD2L = idD2L;
    }
}
