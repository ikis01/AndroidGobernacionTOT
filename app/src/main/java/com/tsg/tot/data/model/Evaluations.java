package com.tsg.tot.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Evaluations {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("subida")
    @Expose
    private Upload subida;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("materias")
    @Expose
    private Integer materias;

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

    public Integer getMaterias() {
        return materias;
    }

    public void setMaterias(Integer materias) {
        this.materias = materias;
    }

    public Evaluations(Integer id, Upload subida, String nombre, Integer materias) {
        this.id = id;
        this.subida = subida;
        this.nombre = nombre;
        this.materias = materias;
    }
}
