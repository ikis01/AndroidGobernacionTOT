package com.tsg.tot.task;

import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tsg.tot.R;
import com.tsg.tot.task.taskmvp.TaskMVP;

import javax.inject.Inject;

public class TaskDetailActivity extends AppCompatActivity {
    @Inject
    TaskMVP.Presenter presenter;


    TextView tv_studentCode, tv_studentName, tv_institutionName,
            tv_location, tv_pendingTask , tv_taskName;

    Button btn_volver;

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



       /// Toast.makeText(this, " entrando a tarea Activity", Toast.LENGTH_SHORT).show();
    }
}