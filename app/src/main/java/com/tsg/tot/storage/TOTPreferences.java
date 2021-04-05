package com.tsg.tot.storage;

import android.content.Context;
import android.content.SharedPreferences;

//import org.mx.infonavit.appinfonavit.v4.model.Credits;
//import org.mx.infonavit.appinfonavit.v4.model.ModelNotification;
//import org.mx.infonavit.appinfonavit.v4.model.ModelUserToken;
//import org.mx.infonavit.appinfonavit.v4.model.miuc.ResponsePerfilate;
//import org.mx.infonavit.appinfonavit.v4.model.miuc.TitularMiuc;

/**
 * Created by imc
 */

public class TOTPreferences {

    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;
    private final Context mContext;
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

    private static final String IDESTUDIANTE = "IDESTUDIANTE";

    public void setIdEstudiante(String idEstudiante) {
        editor.putString(IDESTUDIANTE, idEstudiante);
        editor.commit();
    }

    public String getIdEstudiante() {
        return pref.getString(IDESTUDIANTE,"");
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

    private static final String ACTION_SYNC = "ACTIONSYNC";

    public void setActionSync(Boolean actionSync){
        editor.putBoolean(ACTION_SYNC,actionSync);
        editor.commit();

    }

    public Boolean getActionSync(){
        return pref.getBoolean(ACTION_SYNC, Boolean.parseBoolean(""));
    }


    private static final String TAREASPENDIENTES ="TAREASPENDIENTES";

    public void setTareaspendientes (Integer tareaspendientes){
        editor.putInt("TAREASPENDIENTES",tareaspendientes);
        editor.commit();
    }

    public Integer getTareasPendientes (){
        return pref.getInt("TAREASPENDIENTES",Integer.parseInt("0"));
    }




}
