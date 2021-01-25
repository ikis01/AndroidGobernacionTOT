package com.tsg.tot.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LessonsRemote {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("tema")
    @Expose
    private String tema;


    @SerializedName("idD2L")
    @Expose
    private String idD2L;

    @SerializedName("fecha_inicio")
    @Expose
    private String fecha_inicio;

    @SerializedName("materiaId")
    @Expose
    private Integer materiaId;

    @SerializedName("profesorId")
    @Expose
    private Integer profesorId;

    @SerializedName("materialEstudio")
    private List<StudyMaterialRemote> materialEstudio;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getIdD2L() {
        return idD2L;
    }

    public void setIdD2L(String idD2L) {
        this.idD2L = idD2L;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Integer getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Integer materiaId) {
        this.materiaId = materiaId;
    }

    public Integer getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Integer profesorId) {
        this.profesorId = profesorId;
    }

    public List<StudyMaterialRemote> getMaterialEstudio() {
        return materialEstudio;
    }

    public void setMaterialEstudio(List<StudyMaterialRemote> materialEstudio) {
        this.materialEstudio = materialEstudio;
    }
}
