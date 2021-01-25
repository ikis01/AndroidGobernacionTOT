package com.tsg.tot.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Student {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("curso")
    @Expose
    private Grade curso;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("nombre")
    @Expose
    private String nombres;
    @SerializedName("apellidos")
    @Expose
    private String apellidos;
    @SerializedName("fecha_nacimiento")
    @Expose
    private String fechaNacimiento;

    private Integer FK_Usuario;

    public Integer getFK_Usuario() {
        return FK_Usuario;
    }

    public void setFK_Usuario(Integer FK_Usuario) {
        this.FK_Usuario = FK_Usuario;
    }

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

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Student(Integer id, Grade curso, Integer codigo, String nombres, String apellidos, String fechaNacimiento) {
        this.id = id;
        this.curso = curso;
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Student(Integer id, Integer codigo, String nombres) {
        this.id = id;
        this.codigo = codigo;
        this.nombres = nombres;
    }

    public Student(Integer id, Integer codigo, String nombres, String apellidos) {
        this.id = id;
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
}
