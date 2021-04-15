/*
 * Copyright (c) 2021.
 */

package com.tsg.tot.messages;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsg.tot.R;
import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.data.model.Student;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.data.remote.model.MessageRemote;
import com.tsg.tot.data.remote.model.TaskRegristerRemote;
import com.tsg.tot.main.fragment.CustomProgressDialog;
import com.tsg.tot.main.fragment.InformationFragment;
import com.tsg.tot.main.fragment.InformationMessageFragment;
import com.tsg.tot.main.fragment.ListSubjectFragment;
import com.tsg.tot.main.mainmvp.MainMVP;
import com.tsg.tot.main.mainmvp.MainView;
import com.tsg.tot.repository.DatabaseRepository;
import com.tsg.tot.root.App;
import com.tsg.tot.storage.TOTPreferences;

import java.util.List;

import javax.inject.Inject;

public class MessageMainActivity extends AppCompatActivity
        implements MainMVP.View,InformationMessageFragment.OnFragmentInteractionListener ,View.OnClickListener {


    @Inject
    MainMVP.Presenter presenter;

    FragmentTransaction fragmentTransaction;

    InformationMessageFragment informationMessageFragment;

    public TextView tv_studentCode, tv_studentName, tv_institutionName,
            tv_location, tv_pendingTask;

    public Button btn_volver;



    LinearLayout mainLayout;
    String token, idUsuario = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ((App) getApplication()).getComponent().injectMessageActivity(this);

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_message_main);
        initViewElements();
        btn_volver = findViewById(R.id.btn_volver_message_main);
        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (getIntent().getExtras() != null) {
            token = getIntent().getExtras().getString("token");
            idUsuario = getIntent().getExtras().getString("idUsuario");
         }

        DatabaseRepository dbR = new DatabaseRepository();

        List<Student> studentList = dbR.getStudent(MessageMainActivity.this, Integer.parseInt(idUsuario==null?"0":idUsuario.equals("")?"0":idUsuario));
        this.setInfoStudent(studentList);


        informationMessageFragment = new InformationMessageFragment(presenter);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentFragmentMessage, informationMessageFragment);
        fragmentTransaction.commit();

    }

    private void initViewElements() {
        mainLayout = findViewById(R.id.mainLayout);
        tv_studentCode = findViewById(R.id.tv_studentCode);
        tv_studentName = findViewById(R.id.tv_studentName);
        tv_institutionName = findViewById(R.id.tv_institutionName);
        tv_location = findViewById(R.id.tv_location);

        tv_pendingTask = findViewById(R.id.tv_pendingTask);

    }

    @Override
    public void setInfoStudent(List<Student> studentList) {
        if (studentList.size() > 0) {
            String code =  getResources().getString(R.string.main_view_StudentCode)
                    + studentList.get(0).getId();
            tv_studentCode.setText(code);
            tv_studentName.setText(studentList.get(0).getNombres() + " " + studentList.get(0).getApellidos());
        }

        //  tv_pendingTask.setText(getResources().getString(R.string.main_view_PendingTasks) + ": ");
    }

    @Override
    public void setInfoSubject(Subjects subjects) {

    }

    @Override
    public void notifyRefresh() {

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void setFileKiosco(FilesKiosco fileKiosco) {

    }

    @Override
    public void setMessages(List<MessageRemote> messagesList) {
        /*informationMessageFragment = (InformationMessageFragment) this.getSupportFragmentManager().
                findFragmentById(R.id.contentFragmentMessage);

        getSupportFragmentManager().beginTransaction().
                replace(R.id.contentFragmentMessage, informationMessageFragment).
                addToBackStack(null).commit();*/

    }

    @Override
    protected  void onResume(){
        super.onResume();
        presenter.setView(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}