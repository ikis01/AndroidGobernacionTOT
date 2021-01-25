package com.tsg.tot.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeacherRemote {

    @SerializedName("id")
    @Expose
    private Integer id ;

    @SerializedName("nombres")
    @Expose
    private String nombres;

    @SerializedName("apellidos")
    @Expose
    private String apellidos;

    @SerializedName("institucion")
    @Expose
    private InstitutionRemote institucion;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public InstitutionRemote getInstitucion() {
        return institucion;
    }

    public void setInstitucion(InstitutionRemote institucion) {
        this.institucion = institucion;
    }
}
