package com.tsg.tot.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exercises {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("subida")
    @Expose
    private Upload subida;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("clases")
    @Expose
    private Integer clases;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Upload getSubida() {
        return subida;
    }

    public void setSubida(Upload subida) {
        this.subida = subida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getClases() {
        return clases;
    }

    public void setClases(Integer clases) {
        this.clases = clases;
    }

    public Exercises(Integer id, Upload subida, String nombre, Integer clases) {
        this.id = id;
        this.subida = subida;
        this.nombre = nombre;
        this.clases = clases;
    }
}
