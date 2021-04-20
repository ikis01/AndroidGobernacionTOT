package com.tsg.kot.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Planning {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fecha")
    @Expose
    private String fecha;
    @SerializedName("tarea")
    @Expose
    private Integer tarea;
    @SerializedName("ejercicios")
    @Expose
    private Integer ejercicios;
    @SerializedName("clase")
    @Expose
    private Integer clase;
    @SerializedName("evaluacion")
    @Expose
    private Integer evaluacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getTarea() {
        return tarea;
    }

    public void setTarea(Integer tarea) {
        this.tarea = tarea;
    }

    public Integer getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(Integer ejercicios) {
        this.ejercicios = ejercicios;
    }

    public Integer getClase() {
        return clase;
    }

    public void setClase(Integer clase) {
        this.clase = clase;
    }

    public Integer getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Integer evaluacion) {
        this.evaluacion = evaluacion;
    }
}
