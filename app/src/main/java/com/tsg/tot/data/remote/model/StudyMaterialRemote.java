package com.tsg.tot.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudyMaterialRemote {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("tema")
    @Expose
    private String tema ;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("nombreArchivo")
    @Expose
    private String nombreArchivo;

    @SerializedName("idD2L")
    @Expose
    private String idD2L;

    private String claseId;

    @SerializedName("url")
    @Expose
    private String url;


    private String ruta ;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getIdD2L() {
        return idD2L;
    }

    public void setIdD2L(String idD2L) {
        this.idD2L = idD2L;
    }

    public String getClaseId() {
        return claseId;
    }

    public void setClaseId(String claseId) {
        this.claseId = claseId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }


    public StudyMaterialRemote(Integer id, String tema, String descripcion, String nombreArchivo, String claseId, String ruta) {
        this.id = id;
        this.tema = tema;
        this.descripcion = descripcion;
        this.nombreArchivo = nombreArchivo;
        this.claseId = claseId;
        this.ruta = ruta;
    }
}
