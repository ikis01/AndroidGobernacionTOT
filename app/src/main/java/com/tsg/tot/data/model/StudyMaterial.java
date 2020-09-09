package com.tsg.tot.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudyMaterial {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("clases")
    @Expose
    private Integer clases;
    @SerializedName("blob")
    @Expose
    private Integer blob;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getClases() {
        return clases;
    }

    public void setClases(Integer clases) {
        this.clases = clases;
    }

    public Integer getBlob() {
        return blob;
    }

    public void setBlob(Integer blob) {
        this.blob = blob;
    }

    public StudyMaterial(Integer id, String nombre, String descripcion, Integer clases, Integer blob) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.clases = clases;
        this.blob = blob;
    }
}
