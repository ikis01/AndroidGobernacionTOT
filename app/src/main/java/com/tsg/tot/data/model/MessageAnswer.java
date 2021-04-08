/*
 * Copyright (c) 2021.
 */

package com.tsg.tot.data.model;

import androidx.annotation.StringRes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageAnswer {
    @SerializedName("id")
    @Expose
    private Integer id ;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("estado")
    @Expose
    private Integer estado;

    @SerializedName("mensajekiosco_id")
    @Expose
    private Integer idMensajeKiosco;

    @SerializedName("estadoDescripcion")
    @Expose
    private String estadoDescripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getIdMensajeKiosco() {
        return idMensajeKiosco;
    }

    public void setIdMensajeKiosco(Integer idMensajeKiosco) {
        this.idMensajeKiosco = idMensajeKiosco;
    }

    public String getEstadoDescripcion() {
        return estadoDescripcion;
    }

    public void setEstadoDescripcion(String estadoDescripcion) {
        this.estadoDescripcion = estadoDescripcion;
    }

    public MessageAnswer(Integer id, String body, String estadoDescripcion, Integer idMensajeKiosco) {
        this.id = id;
        this.body = body;
        this.estadoDescripcion = estadoDescripcion;
        this.idMensajeKiosco = idMensajeKiosco;
    }

    public MessageAnswer() {

    }
}
