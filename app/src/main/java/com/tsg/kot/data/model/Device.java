package com.tsg.kot.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Device {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("estudiante")
    @Expose
    private Object estudiante;
    @SerializedName("MAC")
    @Expose
    private String mAC;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Object estudiante) {
        this.estudiante = estudiante;
    }

    public String getMAC() {
        return mAC;
    }

    public void setMAC(String mAC) {
        this.mAC = mAC;
    }

}
