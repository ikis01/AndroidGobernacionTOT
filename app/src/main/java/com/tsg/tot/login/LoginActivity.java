package com.tsg.tot.login;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.tsg.tot.R;
import com.tsg.tot.data.model.Users;
import com.tsg.tot.data.remote.Client;
import com.tsg.tot.main.mainmvp.MainView;
import com.tsg.tot.repository.DatabaseRepository;
import com.tsg.tot.sqlite.DbOpenHelper;
import com.tsg.tot.task.TaskActivity;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    Client client = new Client();
    private TextView tv_estatus_kiosko;
    private EditText et_usuario,et_contrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    }


    public void iniciarSesion (View view){
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




            et_usuario.setText("");
            et_contrasena.setText("");
            if (usersList.size()==1){
                Toast.makeText(this,"Inicio Exitoso",Toast.LENGTH_LONG).show();
                Intent intent  = new  Intent (view.getContext(), MainView.class);

                view.getContext().startActivity(intent);

            }else {
                Toast.makeText(this,"Usuario No registrado ",Toast.LENGTH_LONG).show();
            }


        }else{
            Toast.makeText(this,"Favor de llenar todos los campos ",Toast.LENGTH_LONG).show();
        }


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
            dbR.updateUser(registro,this);

            et_usuario.setText("");
            et_contrasena.setText("");
            Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Favor de llenar todos los campos ",Toast.LENGTH_LONG).show();
        }


    }
}