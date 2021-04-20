package com.tsg.kot.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Submissions {

    @SerializedName("idEntrega")
    @Expose
    private Integer idEntrega;

    @SerializedName("codigoEntrega")
    @Expose
    private String codigoEntrega ;

    @SerializedName("codigoTarea")
    @Expose
    private String codigoTarea ;

    @SerializedName("rtEntrega")
    @Expose
    private Integer rtEntrega;

    @SerializedName("creado")
    @Expose
    private String creado;

    @SerializedName("upp")
    @Expose
    private Integer upp;

    @SerializedName("idTarea")
    @Expose
    private Integer idTarea;

    @SerializedName("idSubida")
    @Expose
    private Integer idSubida;

    @SerializedName("idEstudiante")
    @Expose
    private Integer idEstudiante;

    public Integer getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(Integer idEntregas) {
        this.idEntrega = idEntregas;
    }

    public String getCreado() {
        return creado;
    }

    public void setCreado(String creado) {
        this.creado = creado;
    }

    public Integer getUpp() {
        return upp;
    }

    public void setUpp(Integer upp) {
        this.upp = upp;
    }



    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }



    public Integer getIdSubida() {
        return idSubida;
    }

    public void setIdSubida(Integer idSubida) {
        this.idSubida = idSubida;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }


    public Submissions(Integer idEntrega, String codigoEntrega, String codigoTarea, Integer rtEntrega, String creado, Integer upp, Integer idTarea, Integer idSubida, Integer idEstudiante) {
        this.idEntrega = idEntrega;
        this.codigoEntrega = codigoEntrega;
        this.codigoTarea = codigoTarea;
        this.rtEntrega = rtEntrega;
        this.creado = creado;
        this.upp = upp;
        this.idTarea = idTarea;
        this.idSubida = idSubida;
        this.idEstudiante = idEstudiante;
    }

    public Submissions(Integer id, String creado, Integer upp, Integer ejercios, Integer idTarea, Integer evaluacion, Integer idSubida, Integer idEstudiante) {
        this.idEntrega = id;
        this.creado = creado;
        this.upp = upp;
        this.idTarea = idTarea;
        this.idSubida = idSubida;
        this.idEstudiante = idEstudiante;
    }

    public Submissions() {
    }

    public String getCodigoEntrega() {
        return codigoEntrega;
    }

    public void setCodigoEntrega(String codigoEntrega) {
        this.codigoEntrega = codigoEntrega;
    }

    public String getCodigoTarea() {
        return codigoTarea;
    }

    public void setCodigoTarea(String codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

    public Integer getRtEntrega() {
        return rtEntrega;
    }

    public void setRtEntrega(Integer rtEntrega) {
        this.rtEntrega = rtEntrega;
    }
}
