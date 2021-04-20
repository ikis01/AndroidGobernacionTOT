package com.tsg.kot.data.model;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Task  {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("subida")
    @Expose
    private Upload subida;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("codigo")
    @Expose
    private String codigo;
    @SerializedName("materias")
    @Expose
    private Integer materias;
    @SerializedName("estudiante")
    @Expose
    private Integer estudiante;

    @SerializedName("tareakiosco")
    @Expose
    private Integer tareakiosco;

    @SerializedName("registrotarea")
    @Expose
    private Integer registroTarea;

    @SerializedName("descripcionMateria")
    @Expose
    private String descripcionMateria;

    /**
     * No args constructor for use in serialization
     */
    public Task() {
    }

    public Task(Integer id, Upload subida, String nombre, String codigo, Integer materias, Integer estudiante, Integer tareakiosco) {
        this.id = id;
        this.subida = subida;
        this.nombre = nombre;
        this.codigo = codigo;
        this.materias = materias;
        this.estudiante = estudiante;
        this.tareakiosco = tareakiosco;
    }

    public Task(Integer id, Upload subida, String nombre, String codigo, Integer materias, Integer estudiante, Integer tareakiosco,String descripcionMateria) {
        this.id = id;
        this.subida = subida;
        this.nombre = nombre;
        this.codigo = codigo;
        this.materias = materias;
        this.estudiante = estudiante;
        this.tareakiosco = tareakiosco;
        this.descripcionMateria = descripcionMateria;
    }

    public Task(Integer id, Upload subida, String nombre, String codigo, Integer materias, Integer estudiante, Integer tareakiosco,Integer registroTarea) {
        this.id = id;
        this.subida = subida;
        this.nombre = nombre;
        this.codigo = codigo;
        this.materias = materias;
        this.estudiante = estudiante;
        this.tareakiosco = tareakiosco;
        this.registroTarea = registroTarea;
    }

    /**
     * @param estudiante student
     * @param codigo     code
     * @param subida     upload
     * @param id         id
     * @param nombre     name
     * @param materias   last name
     */
    public Task(Integer id, Upload subida, String nombre, String codigo, Integer materias, Integer estudiante) {
        super();
        this.id = id;
        this.subida = subida;
        this.nombre = nombre;
        this.codigo = codigo;
        this.materias = materias;
        this.estudiante = estudiante;
    }

    protected Task(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        nombre = in.readString();
        codigo = in.readString();
        if (in.readByte() == 0) {
            materias = null;
        } else {
            materias = in.readInt();
        }
        if (in.readByte() == 0) {
            estudiante = null;
        } else {
            estudiante = in.readInt();
        }
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Upload getSubida() {
        return subida;
    }

    public void setSubida(Upload subida) {
        this.subida = subida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getMaterias() {
        return materias;
    }

    public void setMaterias(Integer materias) {
        this.materias = materias;
    }

    public Integer getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Integer estudiante) {
        this.estudiante = estudiante;
    }

    public Integer getTareakiosco() {
        return tareakiosco;
    }

    public void setTareakiosco(Integer tareakiosco) {
        this.tareakiosco = tareakiosco;
    }

    public Integer getRegistroTarea() {
        return registroTarea;
    }

    public void setRegistroTarea(Integer registroTarea) {
        this.registroTarea = registroTarea;
    }

    public String getDescripcionMateria() {
        return descripcionMateria;
    }

    public void setDescripcionMateria(String descripcionMateria) {
        this.descripcionMateria = descripcionMateria;
    }
}
