/*
 * Copyright (c) 2021.
 */

package com.tsg.tot.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    @SerializedName("filesMensajes")
    @Expose
    private List<FileMessageRemote> filesMensajes;

    @SerializedName("mensajeKioscoId")
    @Expose
    private Integer mensajeKioscoId;

    @SerializedName("materiaTitulo")
    @Expose

    private String materiaTitulo ;

    @SerializedName("registroMensajeKiosco")
    @Expose
    private Integer registroMensajeKiosco;

    @SerializedName("idEstudiante")
    @Expose
    private Integer idEstudiante;


    @SerializedName("idMateria")
    @Expose
    private Integer idMateria;



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

    public List<FileMessageRemote> getFilesMensajes() {
        return filesMensajes;
    }

    public void setFilesMensajes(List<FileMessageRemote> filesMensajes) {
        this.filesMensajes = filesMensajes;
    }

    public Integer getMensajeKioscoId() {
        return mensajeKioscoId;
    }

    public void setMensajeKioscoId(Integer mensajeKioscoId) {
        this.mensajeKioscoId = mensajeKioscoId;
    }

    public String getMateriaTitulo() {
        return materiaTitulo;
    }

    public void setMateriaTitulo(String materiaTitulo) {
        this.materiaTitulo = materiaTitulo;
    }

    public Integer getRegistroMensajeKiosco() {
        return registroMensajeKiosco;
    }

    public void setRegistroMensajeKiosco(Integer registroMensajeKiosco) {
        this.registroMensajeKiosco = registroMensajeKiosco;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }



    public MessageRemote(Integer id, String mensajes, String idD2L, String fechaCreacion, String fechaDescarga, SubjectsRemote materia, List<FileMessageRemote> filesMensajes, Integer mensajeKioscoId, String materiaTitulo, Integer registroMensajeKiosco, Integer idEstudiante, Integer idMateria) {
        this.id = id;
        this.mensajes = mensajes;
        this.idD2L = idD2L;
        this.fechaCreacion = fechaCreacion;
        this.fechaDescarga = fechaDescarga;
        this.materia = materia;
        this.filesMensajes = filesMensajes;
        this.mensajeKioscoId = mensajeKioscoId;
        this.materiaTitulo = materiaTitulo;
        this.registroMensajeKiosco = registroMensajeKiosco;
        this.idEstudiante = idEstudiante;
        this.idMateria = idMateria;
    }

    public MessageRemote (){

    }
}
