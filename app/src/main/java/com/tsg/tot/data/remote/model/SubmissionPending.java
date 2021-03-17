/*
 * Copyright (c) 2021.
 */

package com.tsg.tot.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmissionPending {

    @SerializedName("tareaRegistroId")
    @Expose
    private Integer tareaRegistroId;

    @SerializedName("archivo")
    @Expose
    private String  archivo ;

    @SerializedName("macAddress")
    @Expose
    private String macAddress;

    public Integer getTareaRegistroId() {
        return tareaRegistroId;
    }

    public void setTareaRegistroId(Integer tareaRegistroId) {
        this.tareaRegistroId = tareaRegistroId;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public SubmissionPending(Integer tareaRegistroId, String archivo, String macAddress) {
        this.tareaRegistroId = tareaRegistroId;
        this.archivo = archivo;
        this.macAddress = macAddress;
    }
}
