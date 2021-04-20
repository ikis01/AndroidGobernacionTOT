package com.tsg.kot.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Uploads {

    @SerializedName("idSubida")
    @Expose
    private Integer idSubida;

    @SerializedName("Fecha")
    @Expose
    private String fecha;

    @SerializedName("FechaDescarga")
    @Expose
    private String fechaDescarga;

    @SerializedName("SubidaKiosco")
    @Expose
    private Integer subidaKiosco;

    @SerializedName("Estudiante_idEstudiante")
    @Expose
    private Integer estudiante_idEstudiante;

    public Integer getIdSubida() {
        return idSubida;
    }

    public void setIdSubida(Integer idSubida) {
        this.idSubida = idSubida;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaDescarga() {
        return fechaDescarga;
    }

    public void setFechaDescarga(String fechaDescarga) {
        this.fechaDescarga = fechaDescarga;
    }

    public Integer getSubidaKiosco() {
        return subidaKiosco;
    }

    public void setSubidaKiosco(Integer subidaKiosco) {
        this.subidaKiosco = subidaKiosco;
    }

    public Integer getEstudiante_idEstudiante() {
        return estudiante_idEstudiante;
    }

    public void setEstudiante_idEstudiante(Integer estudiante_idEstudiante) {
        this.estudiante_idEstudiante = estudiante_idEstudiante;
    }

    public Uploads() {

    }

    public Uploads(Integer idSubida, String fecha, String fechaDescarga, Integer subidaKiosco, Integer estudiante_idEstudiante) {
        this.idSubida = idSubida;
        this.fecha = fecha;
        this.fechaDescarga = fechaDescarga;
        this.subidaKiosco = subidaKiosco;
        this.estudiante_idEstudiante = estudiante_idEstudiante;
    }
}
