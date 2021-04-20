package com.tsg.kot.data.model;

public class SubmissionDisplay {

    private Integer idEntrega;
    private Integer upp;
    private String nombreArchivo;
    private String estatus;
    private String ruta ;

    public Integer getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(Integer idEntrega) {
        this.idEntrega = idEntrega;
    }

    public Integer getUpp() {
        return upp;
    }

    public void setUpp(Integer upp) {
        this.upp = upp;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public SubmissionDisplay(Integer idEntrega, Integer upp, String nombreArchivo, String estatus,String ruta) {
        this.idEntrega = idEntrega;
        this.upp = upp;
        this.nombreArchivo = nombreArchivo;
        this.estatus = estatus;
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public SubmissionDisplay() {

    }
}
