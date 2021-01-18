package com.tsg.tot.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilesKiosco {

    @SerializedName("idArchivosTOT")
    @Expose
    private Integer idArchivosTOT;

    @SerializedName("archivoKiosco")
    @Expose
    private Integer archivoKiosco;

    @SerializedName("codigo")
    @Expose
    private String codigo;

    @SerializedName("ruta")
    @Expose
    private String ruta;

    @SerializedName("idEntrega")
    @Expose
    private Integer idEntrega;

    @SerializedName("subida_idsubida")
    @Expose
    private Integer subida_idsubida;

    public Integer getIdArchivosTOT() {
        return idArchivosTOT;
    }

    public void setIdArchivosTOT(Integer idArchivosTOT) {
        this.idArchivosTOT = idArchivosTOT;
    }

    public Integer getArchivoKiosco() {
        return archivoKiosco;
    }

    public void setArchivoKiosco(Integer archivoKiosco) {
        this.archivoKiosco = archivoKiosco;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Integer getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(Integer idEntrega) {
        this.idEntrega = idEntrega;
    }

    public Integer getSubida_idsubida() {
        return subida_idsubida;
    }

    public void setSubida_idsubida(Integer subida_idsubida) {
        this.subida_idsubida = subida_idsubida;
    }
}
