/*
 * Copyright (c) 2021.
 */

package com.tsg.tot.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageRemote {
    @SerializedName("id")
    @Expose
    private Integer id ;

    @SerializedName("mensajes")
    @Expose
    private String mensajes;

    @SerializedName("idD2L")
    @Expose
    private String idD2L;

    @SerializedName ("fechaCreacion")
    @Expose
    private String fechaCreacion ;

    @SerializedName("fechaDescarga")
    @Expose
    private String fechaDescarga;

    @SerializedName("materia")
    @Expose
    private SubjectsRemote materia ;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public String getIdD2L() {
        return idD2L;
    }

    public void setIdD2L(String idD2L) {
        this.idD2L = idD2L;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaDescarga() {
        return fechaDescarga;
    }

    public void setFechaDescarga(String fechaDescarga) {
        this.fechaDescarga = fechaDescarga;
    }

    public SubjectsRemote getMateria() {
        return materia;
    }

    public void setMateria(SubjectsRemote materia) {
        this.materia = materia;
    }
}
