package com.tsg.tot.login;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.gson.JsonObject;
import com.tsg.tot.R;
import com.tsg.tot.data.model.TokenCustom;
import com.tsg.tot.data.model.Users;
import com.tsg.tot.data.remote.ApiUtils;
import com.tsg.tot.data.remote.Client;
import com.tsg.tot.main.fragment.CustomProgressDialog;
import com.tsg.tot.main.mainmvp.MainView;
import com.tsg.tot.repository.ApiRepository;
import com.tsg.tot.repository.DatabaseRepository;
import com.tsg.tot.storage.TOTPreferences;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.MODIFY_PHONE_STATE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class LoginActivity extends AppCompatActivity {
    /// prueba por modular
    boolean isConnected  = false;
    private ApiRepository apiRepository;
    Response<TokenCustom> token;
    TokenCustom tokenCustom = new TokenCustom();
    Client client = new Client();
    private TextView tv_estatus_kiosko;
    private EditText et_usuario, et_contrasena;
    CustomProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitNetwork().build());


        setContentView(R.layout.activity_login);


        tv_estatus_kiosko = (TextView) findViewById(R.id.tv_estatus_kiosko);



        //Permissions
        requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, MODIFY_PHONE_STATE}, 1);


    }

    @Override
    protected void onResume() {
        super.onResume();
        isConnected = comprobarConexion();
        if (isConnected) {
            tv_estatus_kiosko.setText("ONLINE");
            //Toast.makeText(this,"Kiosko Online ",Toast.LENGTH_LONG).show();
        } else {
            tv_estatus_kiosko.setText("OFFLINE");
            //Toast.makeText(this, "Kiosko Offline ", Toast.LENGTH_LONG).show();
        }

    }




    public void iniciarSesion(View view,Boolean Sync) {

        dialog = new CustomProgressDialog(LoginActivity.this,
                getResources().getString(R.string.message_load_db));
        dialog.setIcon(R.drawable.tot_icon);
        showLoadingDialog();
        dialog.setProgress(dialog.getProgress() + 5);
        requestPermissions(new String[]{READ_EXTERNAL_STORAGE, READ_PHONE_STATE}, 1);

        dialog.setProgress(dialog.getProgress() + 5);
        et_usuario = (EditText) findViewById(R.id.et_usuario);
        et_contrasena = (EditText) findViewById(R.id.et_contrasena);
        String usuario = et_usuario.getText().toString();
        String password = et_contrasena.getText().toString();

        if (!usuario.isEmpty() && !password.isEmpty()) {
            dialog.setProgress(dialog.getProgress() + 5);
            ContentValues registro = new ContentValues();
            registro.put("UserName", usuario);
            registro.put("Password", password);

            DatabaseRepository dbR = new DatabaseRepository();
            List<Users> usersList = dbR.getUsers(registro, this);


            File storageDir = null;
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                //RUNTIME PERMISSION Android M
                if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    if (! Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).exists()){
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).mkdir();
                    }

                    storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Data");
                    if (!storageDir.exists()) {
                        storageDir.mkdir();
                    }

                    File storageHomework = null;

                    storageHomework = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Data", "Tareas");
                    if (!storageHomework.exists()) {
                        storageHomework.mkdir();
                    }

                } else {
                    requestPermissions(new String[]{READ_EXTERNAL_STORAGE, READ_PHONE_STATE}, 1);
                }

            }
            dialog.setProgress(dialog.getProgress() + 5);

            if (usersList.size() == 1) {
                // Toast.makeText(this,"Inicio Exitoso",Toast.LENGTH_LONG).show();
                dialog.setProgress(dialog.getProgress() + 5);
                Integer idUsuario = usersList.get(0).getIdUsuario();
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("username", usuario);
                jsonObject.addProperty("password", password);
            try{dialog.setProgress(dialog.getProgress() + 5);
                ///Se obtiene Login remoto  token  en caso de no obtenerlo no se procede a sincronizar
                Call<TokenCustom> tokenCustomCall = ApiUtils.getAPIServiceLogin().postLogin(jsonObject);
                tokenCustomCall.enqueue(new Callback<TokenCustom>() {
                    @Override
                    public void onResponse(Call<TokenCustom> call, Response<TokenCustom> response) {
                        if (response.code() == 200) {
                            dialog.setProgress(dialog.getProgress() + 25);
                            token = response;
                            //tokenCustom.setToken(token.body().getToken());
                            //tokenCustom.setExpiration(token.body().getExpiration());
                            dialog.setProgress(dialog.getProgress() + 5);
                            Intent intent = new Intent(view.getContext(), MainView.class);

                            // intent.putExtra("token", tokenCustom.getToken());
                            intent.putExtra("token", "Bearer " + token.body().getToken());
                            intent.putExtra("idUsuario", idUsuario.toString());
                            TOTPreferences.getInstance(view.getContext()).setIdUsuario(idUsuario.toString());
                            dialog.setProgress(dialog.getProgress() + 5);
                            if (Sync) {
                                //dismissLoadingDialog();
                                intent.putExtra("actionSync",true);
                            }

                            view.getContext().startActivity(intent);

                            //// token de authenticacion para todos los servicios
                        } else {
                            if (response.code() == 404) {
                                AlertDialog.Builder dialog404 = new AlertDialog.Builder(LoginActivity.this);
                                // dialogoConfirmarSubida.setTitle("");
                                response.errorBody();
                                dialog404.setMessage("Usuario No encontrado en Kiosco");
                                dialog404.setCancelable(false);
                                dialog404.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogo1, int id) {
                                        // finish();
                                        //getIntent();
                                    }
                                });
                                dialog.setProgress(dialog.getProgress() + 25);
                                /// por definir 10 marzo 2021
                                dismissLoadingDialog();
                                dialog404.show();
                            } else {
                                 if (response.code() == 400) {
                                    AlertDialog.Builder dialog400 = new AlertDialog.Builder(LoginActivity.this);
                                    // dialogoConfirmarSubida.setTitle("");
                                    response.errorBody();
                                    dialog400.setMessage("Login Incorrecto");
                                    dialog400.setCancelable(false);
                                    dialog400.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialogo1, int id) {
                                            // finish();
                                            //getIntent();
                                        }
                                    });
                                    dialog.setProgress(dialog.getProgress() + 25);
                                   // 19 de marzo 2021
                                     dismissLoadingDialog();
                                    dialog400.show();
                                } else {
                                    Intent intent = new Intent(view.getContext(), MainView.class);
                                    intent.putExtra("token", "sinConexion");
                                    intent.putExtra("idUsuario", idUsuario.toString());
                                    // 19 de marzo 2021
                                     dismissLoadingDialog();
                                    view.getContext().startActivity(intent);
                                }

                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<TokenCustom> call, Throwable t) {
                        dialog.setProgress(dialog.getProgress() + 5);
                        Intent intent = new Intent(view.getContext(), MainView.class);
                        intent.putExtra("token", "sinConexion");
                        intent.putExtra("idUsuario", idUsuario.toString());
                        TOTPreferences.getInstance(view.getContext()).setIdUsuario(idUsuario.toString());
                        dismissLoadingDialog();
                        view.getContext().startActivity(intent);

                    }
                });

                /// se hace la sincronizacion

            } catch (Exception e) {
                Intent intent = new Intent(view.getContext(), MainView.class);
                intent.putExtra("token", "sinConexion");
                intent.putExtra("idUsuario", idUsuario.toString());
                dismissLoadingDialog();
                view.getContext().startActivity(intent);
            }


            } else {
                //  Toast.makeText(this,"Usuario No registrado en el dispositivo",Toast.LENGTH_LONG).show();
                dismissLoadingDialog();
                AlertDialog.Builder dialogoUsuarioNoRegistrado = new AlertDialog.Builder(LoginActivity.this);
                // dialogoConfirmarSubida.setTitle("");
                dialogoUsuarioNoRegistrado.setMessage("Usuario No registrado en el dispositivo");
                dialogoUsuarioNoRegistrado.setCancelable(false);
                dialogoUsuarioNoRegistrado.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                    }
                });
                dialogoUsuarioNoRegistrado.show();

            }

        } else {
            //Toast.makeText(this,"Favor de llenar todos los campos ",Toast.LENGTH_LONG).show();

            AlertDialog.Builder dialogoUsuarioNoRegistrado = new AlertDialog.Builder(LoginActivity.this);
            // dialogoConfirmarSubida.setTitle("");
            dialogoUsuarioNoRegistrado.setMessage("Favor de llenar todos los campos");
            dialogoUsuarioNoRegistrado.setCancelable(false);
            dialogoUsuarioNoRegistrado.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {

                }
            });
            dialogoUsuarioNoRegistrado.show();

        }

    }

    public void iniciarSesion(View view) {

        dialog = new CustomProgressDialog(LoginActivity.this,
                getResources().getString(R.string.message_load_db));
        dialog.setIcon(R.drawable.tot_icon);
        showLoadingDialog();
        dialog.setProgress(dialog.getProgress() + 5);
        requestPermissions(new String[]{READ_EXTERNAL_STORAGE, READ_PHONE_STATE}, 1);

        dialog.setProgress(dialog.getProgress() + 5);
        et_usuario = (EditText) findViewById(R.id.et_usuario);
        et_contrasena = (EditText) findViewById(R.id.et_contrasena);
        String usuario = et_usuario.getText().toString();
        String password = et_contrasena.getText().toString();

        if (!usuario.isEmpty() && !password.isEmpty()) {
            dialog.setProgress(dialog.getProgress() + 5);
            ContentValues registro = new ContentValues();
            registro.put("UserName", usuario);
            registro.put("Password", password);

            DatabaseRepository dbR = new DatabaseRepository();
            List<Users> usersList = dbR.getUsers(registro, this);


            File storageDir = null;
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                //RUNTIME PERMISSION Android M
                if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                   if (! Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).exists()){
                       Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).mkdir();
                   }

                    storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Data");
                    if (!storageDir.exists()) {
                        storageDir.mkdir();
                    }

                    File storageHomework = null;

                    storageHomework = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Data", "Tareas");
                    if (!storageHomework.exists()) {
                        storageHomework.mkdir();
                    }

                } else {
                    requestPermissions(new String[]{READ_EXTERNAL_STORAGE, READ_PHONE_STATE}, 1);
                }

            }
            dialog.setProgress(dialog.getProgress() + 5);

            if (usersList.size() == 1) {
                // Toast.makeText(this,"Inicio Exitoso",Toast.LENGTH_LONG).show();
                dialog.setProgress(dialog.getProgress() + 5);
                Integer idUsuario = usersList.get(0).getIdUsuario();
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("username", usuario);
                jsonObject.addProperty("password", password);
                try{dialog.setProgress(dialog.getProgress() + 5);
                    ///Se obtiene Login remoto  token  en caso de no obtenerlo no se procede a sincronizar
                    Call<TokenCustom> tokenCustomCall = ApiUtils.getAPIServiceLogin().postLogin(jsonObject);
                    tokenCustomCall.enqueue(new Callback<TokenCustom>() {
                        @Override
                        public void onResponse(Call<TokenCustom> call, Response<TokenCustom> response) {
                            if (response.code() == 200) {
                                dialog.setProgress(dialog.getProgress() + 25);
                                token = response;
                                //tokenCustom.setToken(token.body().getToken());
                                //tokenCustom.setExpiration(token.body().getExpiration());
                                dialog.setProgress(dialog.getProgress() + 5);
                                Intent intent = new Intent(view.getContext(), MainView.class);

                                // intent.putExtra("token", tokenCustom.getToken());
                                intent.putExtra("token", "Bearer " + token.body().getToken());
                                intent.putExtra("idUsuario", idUsuario.toString());
                                TOTPreferences.getInstance(view.getContext()).setIdUsuario(idUsuario.toString());

                                Boolean sync = TOTPreferences.getInstance(view.getContext()).getActionSync()==null?false:TOTPreferences.getInstance(view.getContext()).getActionSync();
                                Log.d("Sync","Sync : "+sync.toString());
                                dialog.setProgress(dialog.getProgress() + 5);
                                if (sync) {
                                    //dismissLoadingDialog();
                                    intent.putExtra("actionSync",true);
                                }else {
                                    dismissLoadingDialog();
                                   // intent.putExtra("actionSync",false);
                                }


                                view.getContext().startActivity(intent);

                                //// token de authenticacion para todos los servicios
                            } else {
                                if (response.code() == 404) {
                                    AlertDialog.Builder dialog404 = new AlertDialog.Builder(LoginActivity.this);
                                    // dialogoConfirmarSubida.setTitle("");
                                    response.errorBody();
                                    dialog404.setMessage("Usuario No encontrado en Kiosco");
                                    dialog404.setCancelable(false);
                                    dialog404.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialogo1, int id) {
                                            // finish();
                                            //getIntent();
                                        }
                                    });
                                    dialog.setProgress(dialog.getProgress() + 25);
                                    dismissLoadingDialog();
                                    dialog404.show();
                                } else {
                                    if (response.code() == 400) {
                                        AlertDialog.Builder dialog400 = new AlertDialog.Builder(LoginActivity.this);
                                        // dialogoConfirmarSubida.setTitle("");
                                        response.errorBody();
                                        dialog400.setMessage("Login Incorrecto");
                                        dialog400.setCancelable(false);
                                        dialog400.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialogo1, int id) {
                                                // finish();
                                                //getIntent();
                                            }
                                        });
                                        dialog.setProgress(dialog.getProgress() + 25);
                                        dismissLoadingDialog();
                                        dialog400.show();
                                    } else {
                                        Intent intent = new Intent(view.getContext(), MainView.class);
                                        intent.putExtra("token", "sinConexion");
                                        intent.putExtra("idUsuario", idUsuario.toString());
                                        dismissLoadingDialog();
                                        view.getContext().startActivity(intent);
                                    }

                                }


                            }
                        }

                        @Override
                        public void onFailure(Call<TokenCustom> call, Throwable t) {
                            dialog.setProgress(dialog.getProgress() + 5);
                            Intent intent = new Intent(view.getContext(), MainView.class);
                            intent.putExtra("token", "sinConexion");
                            intent.putExtra("idUsuario", idUsuario.toString());
                            TOTPreferences.getInstance(view.getContext()).setIdUsuario(idUsuario.toString());
                            dismissLoadingDialog();
                            view.getContext().startActivity(intent);

                        }
                    });

                    /// se hace la sincronizacion

                } catch (Exception e) {
                    Intent intent = new Intent(view.getContext(), MainView.class);
                    intent.putExtra("token", "sinConexion");
                    intent.putExtra("idUsuario", idUsuario.toString());
                    dismissLoadingDialog();
                    view.getContext().startActivity(intent);
                }


            } else {
                //  Toast.makeText(this,"Usuario No registrado en el dispositivo",Toast.LENGTH_LONG).show();
                dismissLoadingDialog();
                AlertDialog.Builder dialogoUsuarioNoRegistrado = new AlertDialog.Builder(LoginActivity.this);
                // dialogoConfirmarSubida.setTitle("");
                dialogoUsuarioNoRegistrado.setMessage("Usuario No registrado en el dispositivo");
                dialogoUsuarioNoRegistrado.setCancelable(false);
                dialogoUsuarioNoRegistrado.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                    }
                });
                dialogoUsuarioNoRegistrado.show();

            }

        } else {
            //Toast.makeText(this,"Favor de llenar todos los campos ",Toast.LENGTH_LONG).show();

            AlertDialog.Builder dialogoUsuarioNoRegistrado = new AlertDialog.Builder(LoginActivity.this);
            // dialogoConfirmarSubida.setTitle("");
            dialogoUsuarioNoRegistrado.setMessage("Favor de llenar todos los campos");
            dialogoUsuarioNoRegistrado.setCancelable(false);
            dialogoUsuarioNoRegistrado.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {

                }
            });
            dialogoUsuarioNoRegistrado.show();

        }

    }


    public void sincronizar(View view) {
        DatabaseRepository dbR = new DatabaseRepository();
        dbR.updateVersion(0.111F, this);
        TOTPreferences.getInstance(view.getContext()).setActionSync(true);
        this.iniciarSesion(view,true);
    }

    public void registrar(View view) {

        et_usuario = (EditText) findViewById(R.id.et_usuario);
        et_contrasena = (EditText) findViewById(R.id.et_contrasena);
        String usuario = et_usuario.getText().toString();
        String password = et_contrasena.getText().toString();

        if (!usuario.isEmpty() && !password.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("UserName", usuario);
            registro.put("Password", password);


            DatabaseRepository dbR = new DatabaseRepository();
            List<Users> usersList = dbR.getUsers(registro, this);

            /// prueba de obtencion de datos

 /*           List<LessonsRemote> lessonsRemotesList = dbR.getLessons(this, 209);
            List<StudyMaterialRemote> studyMaterialRemotes = dbR.getStudyMaterial(this, 1);
            List<Task> taskList = dbR.getTasks(this, 209);
            List<FilesKiosco> filesKioscoList = dbR.getFileKioscos(this, 209, 6763, 601);

            List<SubmissionDisplay> submissionDisplayList = dbR.getSubmissionsDisplay(this, 1, 1, 1);
*/            /// prueba de obtencion de datos

            //// verificar registro previo
            if (usersList.size() > 0) {
                // Toast.makeText(this, "El Usuario ya fue registrado previamente", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                // dialogoConfirmarSubida.setTitle("");
                dialog.setMessage("El Usuario ya fue registrado previamente");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                    }
                });
                dialog.show();

            } else {

                dbR.updateUser(registro, this);
                dbR.updateVersion(1.0f, this);
                //Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_LONG).show();

                AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                // dialogoConfirmarSubida.setTitle("");
                dialog.setMessage("Estudiante Registrado Exitosamente");

                // Se agrega bandera Sync por que se acaba de registrar y se deseara sincronizar
                TOTPreferences.getInstance(view.getContext()).setActionSync(true);

                dialog.setCancelable(false);
                dialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                    }
                });
                dialog.show();
            }

            //et_usuario.setText("");
            //et_contrasena.setText("");

        } else {
            //Toast.makeText(this,"Favor de llenar todos los campos ",Toast.LENGTH_LONG).show();
            AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
            // dialogoConfirmarSubida.setTitle("");
            dialog.setMessage("Favor de llenar todos los campos");
            dialog.setCancelable(false);
            dialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {

                }
            });
            dialog.show();
        }


    }


    private boolean ejecutaCliente() {
        String ip = ApiUtils.BASE_URL;
        String puerto = ApiUtils.PORT_URL;
        //log(" socket " + ip + " " + puerto);
        try {
            Socket sk = new Socket(ip, Integer.parseInt(puerto));

            BufferedReader entrada = new BufferedReader(new
                    InputStreamReader(sk.getInputStream()));
            PrintWriter salida = new PrintWriter(
                    new OutputStreamWriter(sk.getOutputStream()), true);
            //log("enviando ... Hola Mundo!");
            salida.println("Hola mundo");
            // log("recibiendo ... " + entrada.readLine());
            sk.close();
            return true;
        } catch (Exception e) {
            // log("error: " + e.toString());
            return false;
        }
    }

    private boolean comprobarConexion(){

       ApiRepository apiRepository = new ApiRepository();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", "probando");
        jsonObject.addProperty("password", "probando");
        try{
            ///Se obtiene Login remoto  token  en caso de no obtenerlo no se procede a sincronizar
            Call<TokenCustom> tokenCustomCall = ApiUtils.getAPIServiceCheckConnection().postLogin(jsonObject);

            tokenCustomCall.enqueue(new Callback<TokenCustom>() {
                @Override
                public void onResponse(Call<TokenCustom> call, Response<TokenCustom> response) {
                    if (response.code() == 200||response.code() == 404||response.code() == 400) {
                        isConnected = true ;

                     } else {

                        isConnected = false ;
                    }
                }

                @Override
                public void onFailure(Call<TokenCustom> call, Throwable t) {

                    isConnected = false ;
                    return;
                }
            });


        } catch (Exception e) {

        }


        return isConnected;
    }


    public void showLoadingDialog() {
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }


    public void dismissLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}