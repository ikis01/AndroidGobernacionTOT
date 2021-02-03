package com.tsg.tot.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
//import org.mx.infonavit.appinfonavit.v4.model.Credits;
//import org.mx.infonavit.appinfonavit.v4.model.ModelNotification;
//import org.mx.infonavit.appinfonavit.v4.model.ModelUserToken;
//import org.mx.infonavit.appinfonavit.v4.model.miuc.ResponsePerfilate;
//import org.mx.infonavit.appinfonavit.v4.model.miuc.TitularMiuc;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by imc
 */

public class TOTPreferences {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context mContext;
    // shared pref mode
    private final int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "TOTPreferences";

    private static com.tsg.tot.storage.TOTPreferences _instance;

    public static synchronized com.tsg.tot.storage.TOTPreferences getInstance(Context context) {
        if (_instance == null) {
            _instance = new com.tsg.tot.storage.TOTPreferences(context);
        }
        return _instance;
    }

    private TOTPreferences(Context context) {
        this.mContext = context;
        pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    //******RunApp
    private static final String FIRSTRUN = "FIRSTRUN";

    public void setRunAppFirst(boolean firstRun) {
        editor.putBoolean(FIRSTRUN, firstRun);
        editor.commit();
    }

    public boolean isRunAppFirst() {
        return pref.getBoolean(FIRSTRUN, false);
    }

    //******Login
    private static final String RFC = "RFC";

    public void setRFC(String rfc) {
        editor.putString(RFC, rfc);
        editor.commit();
    }

    private static final String IDUSUARIO = "IDUSUARIO";

    public void setIdUsuario(String idUsuario) {
        editor.putString(IDUSUARIO, idUsuario);
        editor.commit();
    }

    public String getIdUsuario() {
        return pref.getString(IDUSUARIO,"");
    }


    private static final String IDCLASE = "IDCLASE";

    public void setIdClase(String idClase) {
        editor.putString(IDCLASE, idClase);
        editor.commit();
    }

    public String getIdclase() {
        return pref.getString(IDCLASE,"");
    }

    private static final String TOKEN = "TOKEN";

    public void setToken(String token) {
        editor.putString(TOKEN, token);
        editor.commit();
    }

    public String getToken() {
        return pref.getString(TOKEN,"");
    }

}
