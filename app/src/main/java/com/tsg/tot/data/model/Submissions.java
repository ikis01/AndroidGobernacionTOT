package com.tsg.tot.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Submissions {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("creado")
    @Expose
    private String creado;
    @SerializedName("upp")
    @Expose
    private Integer upp;
    @SerializedName("ejercios")
    @Expose
    private Object ejercios;
    @SerializedName("tarea")
    @Expose
    private Integer tarea;
    @SerializedName("evaluacion")
    @Expose
    private Object evaluacion;
    @SerializedName("subida")
    @Expose
    private Integer subida;
    @SerializedName("estudiante")
    @Expose
    private Integer estudiante;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreado() {
        return creado;
    }

    public void setCreado(String creado) {
        this.creado = creado;
    }

    public Integer getUpp() {
        return upp;
    }

    public void setUpp(Integer upp) {
        this.upp = upp;
    }

    public Object getEjercios() {
        return ejercios;
    }

    public void setEjercios(Object ejercios) {
        this.ejercios = ejercios;
    }

    public Integer getTarea() {
        return tarea;
    }

    public void setTarea(Integer tarea) {
        this.tarea = tarea;
    }

    public Object getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Object evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Integer getSubida() {
        return subida;
    }

    public void setSubida(Integer subida) {
        this.subida = subida;
    }

    public Integer getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Integer estudiante) {
        this.estudiante = estudiante;
    }

}
