package com.tsg.kot.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TaskRemote {





    @SerializedName("tareaId")
    @Expose
    private Integer tareaId;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("instrucciones")
    @Expose
    private String instrucciones;

    @SerializedName("idD2L")
    @Expose
    private Integer idD2L;


    @SerializedName("idD2LOther")
    @Expose
    private String idD2LOther;


    @SerializedName("fechaEntrega")
    @Expose
    private String fechaEntrega;

    @SerializedName("fechaDescarga")
    @Expose
    private String fechaDescarga;


    @SerializedName("file")
    @Expose
    private List<FileTaskRemote> file;

    @SerializedName("materia")
    @Expose
    private SubjectsRemote materia;

    private Integer idRegistro;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }





    public String getFechaDescarga() {
        return fechaDescarga;
    }

    public void setFechaDescarga(String fechaDescarga) {
        this.fechaDescarga = fechaDescarga;
    }


    public Integer getIdD2L() {
        return idD2L;
    }

    public void setIdD2L(Integer idD2L) {
        this.idD2L = idD2L;
    }

    public SubjectsRemote getMateria() {
        return materia;
    }

    public void setMateria(SubjectsRemote materia) {
        this.materia = materia;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }



    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getIdD2LOther() {
        return idD2LOther;
    }

    public void setIdD2LOther(String idD2LOther) {
        this.idD2LOther = idD2LOther;
    }

    public List<FileTaskRemote> getFile() {
        return file;
    }

    public void setFile(List<FileTaskRemote> file) {
        this.file = file;
    }
}
