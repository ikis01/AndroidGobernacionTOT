package com.tsg.tot.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Task {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("subida")
    @Expose
    private Upload subida;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("codigo")
    @Expose
    private String codigo;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getMaterias() {
        return materias;
    }

    public void setMaterias(Integer materias) {
        this.materias = materias;
    }
}
