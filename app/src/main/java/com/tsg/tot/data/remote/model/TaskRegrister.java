package com.tsg.tot.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskRegrister {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fechaCreacion")
    @Expose
    private String fechaCreacion;
    @SerializedName("tareaId")
    @Expose
    private Integer tareaId;
    @SerializedName("materiaId")
    @Expose
    private Integer materiaId;
    @SerializedName("estudianteId")
    @Expose
    private Integer estudianteId;
    @SerializedName("kioskoServerId")
    @Expose
    private Integer kioskoServerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getTareaId() {
        return tareaId;
    }

    public void setTareaId(Integer tareaId) {
        this.tareaId = tareaId;
    }

    public Integer getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Integer materiaId) {
        this.materiaId = materiaId;
    }

    public Integer getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Integer estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Integer getKioskoServerId() {
        return kioskoServerId;
    }

    public void setKioskoServerId(Integer kioskoServerId) {
        this.kioskoServerId = kioskoServerId;
    }
}
