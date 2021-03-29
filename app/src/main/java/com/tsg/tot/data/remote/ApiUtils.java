package com.tsg.tot.data.remote;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class  ApiUtils {

    private ApiUtils() {
    }

    //static final String BASE_URL = "http://192.168.0.27:8000/";

    // servidor Linux "https://192.168.0.120:6104/";
    //  telmex  "https://192.168.1.134:6104/";
    //  izzi  "http://10.0.0.5:6104/"

    //Servidor Ofinica TSG
    // public  static final String BASE_URL = "http://192.168.0.120:6104/";

    // Pruebas Calidad Casa Milena Nuevo Servidor de pruebas 26 Febrero 2021 ******

    //public static final String BASE_URL = "http://192.168.20.24:6104";

    // telmex 2 repetidor
    //
    public static final String BASE_URL = "http://192.168.1.95:6104";

   // Milena 2 actual
    //public static final String BASE_URL ="http://192.168.20.34:6104";

    //static final String BASE_URL = "http://192.168.1.134:6104/";
    //static final String BASE_URL = "http://192.168.1.134";
    //public static final String BASE_URL = "http://192.168.0.120:6104";


    public static final String PORT_URL = "6104";
    public static ApiService getAPIService( ) {
        return Client.getClient().create(ApiService.class);
    }

    public static ApiService getAPIServiceLogin( ) {
        return Client.getClientLogin().create(ApiService.class);
    }

    public static ApiService getAPIServiceTaskRegister( ) {
        return Client.getClientTaskRegister().create(ApiService.class);
    }

    public static ApiService getAPIServiceCheckConnection(){
        return Client.getClientIsConnected().create(ApiService.class);
    }

    
    public static void openFile(Uri uri, Context context){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
