package com.tsg.tot.subject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tsg.tot.R;
import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.data.model.Student;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.main.fragment.ListLessonsFragment;
import com.tsg.tot.root.App;
import com.tsg.tot.task.taskmvp.TaskMVP;

import java.util.List;

import javax.inject.Inject;

public class DetailSubjectActivity extends AppCompatActivity
    implements TaskMVP.View , ListLessonsFragment.OnFragmentInteractionListener,View.OnClickListener{



    @Inject
    TaskMVP.Presenter presenter;

    FragmentTransaction fragmentTransaction;

    ListLessonsFragment listLessonsFragment;



    @Override
    protected void onResume(){

        super.onResume();

       presenter.setView(this);

        if (presenter!=null){}
         presenter.setView((TaskMVP.View) this);
    }

    TextView tv_studentCode, tv_studentName, tv_institutionName,
            tv_location, tv_pendingTask , tv_titleSubject;
    Button btn_volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Integer idMateria,idEstudiante = 0;
        idMateria = getIntent().getExtras().getInt("idSubject");

        ((App) getApplication()).getComponent().injectDetailActivity(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        String titleSubject = getIntent().getExtras().getString("titleSubject");
        String studentName = getIntent().getExtras().getString("student_name");

        tv_studentName = findViewById(R.id.tv_studentName);
        tv_studentName.setText(studentName);

        tv_titleSubject = findViewById(R.id.tv_titleSubject);
        tv_titleSubject.setText(titleSubject);



        btn_volver = (Button) findViewById(R.id.btn_volver);
        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Init Fragment
        listLessonsFragment = new ListLessonsFragment(presenter,idMateria);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentClaseList, listLessonsFragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void notifyRefresh() {

    }

    @Override
    public void setInfoStudent(List<Student> studentList) {

    }

    @Override
    public void setInfoSubject(Subjects subject) {

    }

    @Override
    public void notifyRefreh() {

    }

    @Override
    public void setFileKiosco(FilesKiosco fileKiosco) {

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }


}