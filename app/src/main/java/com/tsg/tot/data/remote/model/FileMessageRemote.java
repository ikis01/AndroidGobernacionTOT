/*
 * Copyright (c) 2021.
 */

package com.tsg.tot.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FileMessageRemote {
    @SerializedName("id")
    @Expose
    private Integer id ;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("mensajeId")
    @Expose
    private Integer mensajeId ;

    @SerializedName("fechaDescarga")
    @Expose
    private String fechaDescarga;

    @SerializedName("idD2L")
    @Expose
    private String idD2L;

    @SerializedName("url")
    @Expose
    private String url ;

    @SerializedName("idMensajeKiosco")
    @Expose
    private Integer idMensajeKiosco;



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

    public Integer getMensajeId() {
        return mensajeId;
    }

    public void setMensajeId(Integer mensajeId) {
        this.mensajeId = mensajeId;
    }

    public String getFechaDescarga() {
        return fechaDescarga;
    }

    public void setFechaDescarga(String fechaDescarga) {
        this.fechaDescarga = fechaDescarga;
    }

    public String getIdD2L() {
        return idD2L;
    }

    public void setIdD2L(String idD2L) {
        this.idD2L = idD2L;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIdMensajeKiosco() {
        return idMensajeKiosco;
    }

    public void setIdMensajeKiosco(Integer idMensajeKiosco) {
        this.idMensajeKiosco = idMensajeKiosco;
    }

    public FileMessageRemote(Integer id, String nombre, Integer mensajeId, String fechaDescarga, String idD2L, String url, Integer idMensajeKiosco) {
        this.id = id;
        this.nombre = nombre;
        this.mensajeId = mensajeId;
        this.fechaDescarga = fechaDescarga;
        this.idD2L = idD2L;
        this.url = url;
        this.idMensajeKiosco = idMensajeKiosco;
    }

    public FileMessageRemote() {

    }
}
