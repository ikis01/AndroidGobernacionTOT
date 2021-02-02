package com.tsg.tot.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Submissions {

    @SerializedName("id")
    @Expose
    private Integer id;

    private String codigoEntrega ;
    private String codigoTarea ;
    private Integer rtEntrega;

    @SerializedName("creado")
    @Expose
    private String creado;
    @SerializedName("upp")
    @Expose
    private Integer upp;

    @SerializedName("tarea")
    @Expose
    private Integer tarea;

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



    public Integer getTarea() {
        return tarea;
    }

    public void setTarea(Integer tarea) {
        this.tarea = tarea;
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

    public Submissions(Integer id, String creado, Integer upp, Integer ejercios, Integer tarea, Integer evaluacion, Integer subida, Integer estudiante) {
        this.id = id;
        this.creado = creado;
        this.upp = upp;
        this.tarea = tarea;
        this.subida = subida;
        this.estudiante = estudiante;
    }

    public Submissions() {
    }

    public String getCodigoEntrega() {
        return codigoEntrega;
    }

    public void setCodigoEntrega(String codigoEntrega) {
        this.codigoEntrega = codigoEntrega;
    }

    public String getCodigoTarea() {
        return codigoTarea;
    }

    public void setCodigoTarea(String codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

    public Integer getRtEntrega() {
        return rtEntrega;
    }

    public void setRtEntrega(Integer rtEntrega) {
        this.rtEntrega = rtEntrega;
    }
}
