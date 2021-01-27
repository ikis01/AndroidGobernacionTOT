package com.tsg.tot.login;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.gson.JsonObject;
import com.tsg.tot.R;
import com.tsg.tot.data.model.TokenCustom;
import com.tsg.tot.data.model.Users;
import com.tsg.tot.data.remote.ApiUtils;
import com.tsg.tot.data.remote.Client;
import com.tsg.tot.main.mainmvp.MainView;
import com.tsg.tot.repository.ApiRepository;
import com.tsg.tot.repository.DatabaseRepository;
import com.tsg.tot.root.App;
import com.tsg.tot.utils.UtilsTot;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.MODIFY_PHONE_STATE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class LoginActivity extends AppCompatActivity {
    /// prueba por modular
    private ApiRepository apiRepository;
    Response <TokenCustom> token ;
    TokenCustom tokenCustom = new TokenCustom();
    Client client = new Client();
    private TextView tv_estatus_kiosko;
    private EditText et_usuario,et_contrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_login);



        tv_estatus_kiosko = (TextView) findViewById(R.id.tv_estatus_kiosko);

        if (client.isClientOnline()){
            tv_estatus_kiosko.setText("ONLINE");
            //Toast.makeText(this,"Kiosko Online ",Toast.LENGTH_LONG).show();
        }
        else {
            tv_estatus_kiosko.setText("OFFLINE");
            Toast.makeText(this,"Kiosko Offline ",Toast.LENGTH_LONG).show();
        }

        //Permissions
        requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, MODIFY_PHONE_STATE}, 1);



    }


    public void iniciarSesion (View view){

        requestPermissions(new String[]{READ_EXTERNAL_STORAGE, READ_PHONE_STATE}, 1);


        et_usuario = (EditText) findViewById(R.id.et_usuario);
        et_contrasena = (EditText) findViewById(R.id.et_contrasena);
        String usuario = et_usuario.getText().toString();
        String password = et_contrasena.getText().toString();

        if (!usuario.isEmpty()&& !password.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("UserName",usuario);
            registro.put("Password",password);

            DatabaseRepository dbR = new DatabaseRepository();
            List<Users> usersList = dbR.getUsers(registro,this);


            File storageDir = null;
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                //RUNTIME PERMISSION Android M
                if(PackageManager.PERMISSION_GRANTED==ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Data");
                    if (!storageDir.exists()){
                        storageDir.mkdir();
                    }

                }else{
                    requestPermissions(new String[]{READ_EXTERNAL_STORAGE, READ_PHONE_STATE}, 1);
                }

            }


            //  UtilsTot utilsTot = new UtilsTot();

          //  utilsTot.createFile(false,"RUTA","666","BLOB");

            //et_usuario.setText("");
            //et_contrasena.setText("");
            if (usersList.size()==1){
               // Toast.makeText(this,"Inicio Exitoso",Toast.LENGTH_LONG).show();
                Integer idUsuario = usersList.get(0).getIdUsuario();
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("username",usuario);
                jsonObject.addProperty("password",password);

                ///Se obtiene Login remoto  token  en caso de no obtenerlo no se procede a sincronizar
                Call<TokenCustom> tokenCustomCall =  ApiUtils.getAPIService().postLogin(jsonObject);
                tokenCustomCall.enqueue(new Callback<TokenCustom>() {
                    @Override
                    public void onResponse(Call<TokenCustom> call, Response<TokenCustom> response) {
                        if(response.code()==200){
                            token = response;


                            //tokenCustom.setToken(token.body().getToken());
                            //tokenCustom.setExpiration(token.body().getExpiration());
                            Intent intent  = new  Intent (view.getContext(), MainView.class);

                           // intent.putExtra("token", tokenCustom.getToken());
                            intent.putExtra("token", "Bearer "+ token.body().getToken());
                            intent.putExtra("idUsuario",idUsuario.toString());

                            view.getContext().startActivity(intent);

                            //// token de authenticacion para todos los servicios
                        }else {
                            Intent intent  = new  Intent (view.getContext(), MainView.class);

                            view.getContext().startActivity(intent);

                        }
                    }

                    @Override
                    public void onFailure(Call<TokenCustom> call, Throwable t) {
                        Intent intent  = new  Intent (view.getContext(), MainView.class);

                        view.getContext().startActivity(intent);

                    }
                });

                /// se hace la sincronizacion



            }else {
                Toast.makeText(this,"Usuario No registrado en el dispositivo",Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(this,"Favor de llenar todos los campos ",Toast.LENGTH_LONG).show();
        }

    }



    public void sincronizar (View view ){
        DatabaseRepository dbR = new DatabaseRepository();
        dbR.updateVersion(0.111f,this);
        this.iniciarSesion(view);
    }

    public void registrar (View view){

        et_usuario = (EditText) findViewById(R.id.et_usuario);
        et_contrasena = (EditText) findViewById(R.id.et_contrasena);
        String usuario = et_usuario.getText().toString();
        String password = et_contrasena.getText().toString();

        if (!usuario.isEmpty()&& !password.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("UserName",usuario);
            registro.put("Password",password);


            DatabaseRepository dbR = new DatabaseRepository();
            List<Users> usersList = dbR.getUsers(registro,this);

            //// verificar registro previo
            if(usersList.size()>0){
                Toast.makeText(this, "El Usuario ya fue registrado previamente", Toast.LENGTH_SHORT).show();
            }else{

                dbR.updateUser(registro,this);
                dbR.updateVersion(1.0f,this);
            }

            //et_usuario.setText("");
            //et_contrasena.setText("");
            Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Favor de llenar todos los campos ",Toast.LENGTH_LONG).show();
        }


    }

}