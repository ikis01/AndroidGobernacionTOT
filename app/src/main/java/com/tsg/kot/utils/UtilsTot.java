package com.tsg.kot.utils;

import android.util.Log;

import java.io.File;

public class UtilsTot {

    public Boolean createFile(Boolean existe, String pathFolder, String idD2L, String folderName) {


        Boolean fileCreated = false;
        if (existe == false) {
            File Direccion = new File("Data/" + idD2L + folderName);
            //File Direccion = new File(nombreDirectorio);

            fileCreated = true;
            if (Direccion.exists()) {

                if (Direccion.isDirectory()) {
                    Log.d("Es una Carpeta ","");

                }
            } else {
                if (Direccion.mkdirs()) {

                    Log.d("Directorio creado ","");
                } else {
                    Direccion.mkdir();
                    fileCreated = false;
                    Log.d("Error al crear directorio ","");

                }
            }
        }

        return fileCreated;

    }


}

