package com.tsg.tot.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lessons {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("tema")
    @Expose
    private String tema;
    @SerializedName("fecha_inicio")
    @Expose
    private String fechaInicio;
    @SerializedName("materias")
    @Expose
    private Integer materias;
    @SerializedName("profesor")
    @Expose
    private Integer profesor;

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

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Integer getMaterias() {
        return materias;
    }

    public void setMaterias(Integer materias) {
        this.materias = materias;
    }

    public Integer getProfesor() {
        return profesor;
    }

    public void setProfesor(Integer profesor) {
        this.profesor = profesor;
    }

    public Lessons(Integer id, String nombre, String tema, String fechaInicio, Integer materias, Integer profesor) {
        this.id = id;
        this.nombre = nombre;
        this.tema = tema;
        this.fechaInicio = fechaInicio;
        this.materias = materias;
        this.profesor = profesor;
    }

    public Lessons() {

    }
}
