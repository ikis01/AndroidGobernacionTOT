/*
 * Copyright (c) 2021.
 */

package com.tsg.tot.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageRegisterRemote {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("fechaCreacion")
    @Expose
    private String fechaCreacion;

    @SerializedName("mac")
    @Expose
    private String mac;

    @SerializedName("mensajeId")
    @Expose
    private Integer mensajeId;

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

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Integer getMensajeId() {
        return mensajeId;
    }

    public void setMensajeId(Integer mensajeId) {
        this.mensajeId = mensajeId;
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
