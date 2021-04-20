/*
 * Copyright (c) 2021.
 */

package com.tsg.kot.data.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

public class UploadFile {
    @SerializedName("File")
    @Expose
    private File file ;

    @SerializedName("TareaRegistroId")
    @Expose
    private String tareaRegistroId;

    @SerializedName("Mac")
    @Expose
    private String macAdress;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getTareaRegistroId() {
        return tareaRegistroId;
    }

    public void setTareaRegistroId(String tareaRegistroId) {
        this.tareaRegistroId = tareaRegistroId;
    }

    public String getMacAdress() {
        return macAdress;
    }

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    public UploadFile (File file ,String tareaRegistroId,String macAdress){
        this.file = file;
        this.macAdress = macAdress;
        this.tareaRegistroId = tareaRegistroId;

    }

    public UploadFile(){

    }

    @NonNull
    @Override
    public String toString(){
        return "UploadFile { File = "+ this.file.getName()+" macAddress = " + this.getMacAdress() + " tareaRegistroId = " +this.tareaRegistroId
                ;
    }
}
