package com.tsg.tot.data.remote;

import java.io.IOException;

public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "No hay conexion con el servicio";
    }

}