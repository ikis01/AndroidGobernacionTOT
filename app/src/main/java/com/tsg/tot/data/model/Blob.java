package com.tsg.tot.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Blob {

    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("file")
    @Expose
    private String file;
    @SerializedName("subida")
    @Expose
    private Integer subida;
    @SerializedName("entrega")
    @Expose
    private Integer entrega;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Integer getSubida() {
        return subida;
    }

    public void setSubida(Integer subida) {
        this.subida = subida;
    }

    public Integer getEntrega() {
        return entrega;
    }

    public void setEntrega(Integer entrega) {
        this.entrega = entrega;
    }
}
