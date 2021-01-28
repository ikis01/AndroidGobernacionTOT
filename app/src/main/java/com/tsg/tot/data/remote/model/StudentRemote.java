package com.tsg.tot.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentRemote {

    @SerializedName("id")
    @Expose
    private Integer id;


    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("apellidos")
    @Expose
    private String apellidos;

    @SerializedName("fecha_nacimiento")
    @Expose
    private String fecha_nacimiento;

    @SerializedName("idD2L")
    @Expose
    private Integer idD2L;

    @SerializedName("gradoId")
    @Expose
    private Integer gradoId;

    @SerializedName("institucionId")
    @Expose
    private Integer institucionId;


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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Integer getGradoId() {
        return gradoId;
    }

    public void setGradoId(Integer gradoId) {
        this.gradoId = gradoId;
    }

    public Integer getInstitucionId() {
        return institucionId;
    }

    public void setInstitucionId(Integer institucionId) {
        this.institucionId = institucionId;
    }

    public Integer getIdD2L() {
        return idD2L;
    }

    public void setIdD2L(Integer idD2L) {
        this.idD2L = idD2L;
    }
}
