package com.tsg.tot.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskRemote {

    @SerializedName("tareaId")
    @Expose
    private Integer tareaId;
    @SerializedName("nombreActividad")
    @Expose
    private String nombreActividad;
    @SerializedName("nombreArchivo")
    @Expose
    private String nombreArchivo;
    @SerializedName("idArchivoD2L")
    @Expose
    private Integer idArchivoD2L;
    @SerializedName("fechaDescarga")
    @Expose
    private String fechaDescarga;
    @SerializedName("materiaId")

    @Expose
    private Integer materiaId;

    @SerializedName("file")
    @Expose
    private FileTaskRemote file;

    private Long idSubida ;

    public Long getIdSubida() {
        return idSubida;
    }

    public void setIdSubida(Long idSubida) {
        this.idSubida = idSubida;
    }

    public Integer getTareaId() {
        return tareaId;
    }

    public void setTareaId(Integer tareaId) {
        this.tareaId = tareaId;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Integer getIdArchivoD2L() {
        return idArchivoD2L;
    }

    public void setIdArchivoD2L(Integer idArchivoD2L) {
        this.idArchivoD2L = idArchivoD2L;
    }

    public String getFechaDescarga() {
        return fechaDescarga;
    }

    public void setFechaDescarga(String fechaDescarga) {
        this.fechaDescarga = fechaDescarga;
    }

    public Integer getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Integer materiaId) {
        this.materiaId = materiaId;
    }

    public FileTaskRemote getFile() {
        return file;
    }

    public void setFile(FileTaskRemote file) {
        this.file = file;
    }
}
