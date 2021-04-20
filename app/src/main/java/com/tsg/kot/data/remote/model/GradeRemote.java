package com.tsg.kot.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GradeRemote {

    @SerializedName("id")
    @Expose()
    private Integer id ;

    @SerializedName("codigo")
    @Expose()
    private String codigo;

    @SerializedName("nombre")
    @Expose()
    private String nombre;

    @SerializedName("institucion")
    @Expose()
    private InstitutionRemote institucion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public InstitutionRemote getInstitucion() {
        return institucion;
    }

    public void setInstitucion(InstitutionRemote institucion) {
        this.institucion = institucion;
    }
}


