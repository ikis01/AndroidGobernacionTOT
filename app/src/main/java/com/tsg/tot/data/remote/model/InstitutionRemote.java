package com.tsg.tot.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InstitutionRemote {

    @SerializedName("id")
    @Expose
    private Integer id ;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("idD2L")
    @Expose
    private String idD2L;



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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIdD2L() {
        return idD2L;
    }

    public void setIdD2L(String idD2L) {
        this.idD2L = idD2L;
    }
}
