package com.tsg.tot.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Subjects implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("curso")
    @Expose
    private Grade curso;
    @SerializedName("profesor")
    @Expose
    private Teacher profesor;
    @SerializedName("codigo")
    @Expose
    private String codigo;
    @SerializedName("titulo")
    @Expose
    private String titulo;
    @SerializedName("subtitulo")
    @Expose
    private String subtitulo;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("imagen")
    @Expose
    private String imagen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Grade getCurso() {
        return curso;
    }

    public void setCurso(Grade curso) {
        this.curso = curso;
    }

    public Teacher getProfesor() {
        return profesor;
    }

    public void setProfesor(Teacher profesor) {
        this.profesor = profesor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Subjects(Integer id, Grade curso, Teacher profesor, String codigo, String titulo, String subtitulo, String descripcion, String imagen) {
        this.id = id;
        this.curso = curso;
        this.profesor = profesor;
        this.codigo = codigo;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Subjects() {
    }
}
