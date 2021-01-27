package com.tsg.tot.task;

import android.net.Uri;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tsg.tot.R;
import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.data.model.Student;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.main.fragment.ListFileKioscoFragment;
import com.tsg.tot.main.fragment.ListSubjectFragment;
import com.tsg.tot.main.mainmvp.MainMVP;
import com.tsg.tot.task.taskmvp.TaskMVP;

import java.util.List;

import javax.inject.Inject;

public class TaskDetailActivity extends AppCompatActivity implements MainMVP.View, ListFileKioscoFragment.OnFragmentInteractionListener,View.OnClickListener {
    @Inject
    TaskMVP.Presenter presenter;

    FragmentTransaction fragmentTransaction;

    ListFileKioscoFragment listFileKioscoFragment;

    TextView tv_studentCode, tv_studentName, tv_institutionName,
            tv_location, tv_pendingTask , tv_taskName;

    Button btn_volver;


    @Override
    protected void onResume(){

        super.onResume();

       // if (presenter!=null){}
       // presenter.setView((TaskMVP.View) this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String studentName = getIntent().getExtras().getString("student_name");
        String taskName = getIntent().getExtras().getString("task_name");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        btn_volver = (Button) findViewById(R.id.btn_volver);

        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_studentName = findViewById(R.id.tv_studentName);
        tv_studentName.setText(studentName);

        tv_taskName = findViewById(R.id.tv_taskName);
        tv_taskName.setText(taskName);


        //Init Fragment
        listFileKioscoFragment = new ListFileKioscoFragment(presenter);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentMaterialTareaList, listFileKioscoFragment);
        fragmentTransaction.commit();

       /// Toast.makeText(this, " entrando a tarea Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void setInfoStudent(List<Student> studentList) {

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
}