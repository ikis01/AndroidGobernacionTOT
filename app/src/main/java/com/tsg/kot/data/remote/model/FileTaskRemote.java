package com.tsg.kot.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FileTaskRemote {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("nombre")
    @Expose
    private String nombre;


    @SerializedName("fechaDescarga")
    @Expose
    private String fechaDescarga;

    @SerializedName("idD2L")
    @Expose
    private Integer idD2L;

    @SerializedName("tareaId")
    @Expose
    private Integer tareaId;


    @SerializedName("url")
    @Expose
    private String url ;

    @SerializedName("size")
    @Expose
    private Integer size ;


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

    public Integer getTareaId() {
        return tareaId;
    }

    public void setTareaId(Integer tareaId) {
        this.tareaId = tareaId;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
