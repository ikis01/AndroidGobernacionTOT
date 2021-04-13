package com.tsg.tot.data.remote;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class  ApiUtils {

    private ApiUtils() {
    }


    //Servidor Ofinica TSG
    // public  static final String BASE_URL = "http://192.168.0.120:6104/";

    //Local Pruebas Israel
     public static final String BASE_URL = "http://192.168.1.101:6104";

   // Local Pruebas Milena
    // public static final String BASE_URL ="http://192.168.20.34:6104";


    public static final String PORT_URL = "6104";

    public static ApiService getAPIServiceGetTasks( ) {
        return Client.getClientGetTasks().create(ApiService.class);
    }


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

    public static String getMacAddress() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "LOCALHOST";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(Integer.toHexString(b & 0xFF) + ":");
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            Log.d("Error", ex.getMessage());
        }
        return "LOCALHOST";
    }
}
