package com.tsg.tot.subject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tsg.tot.R;

public class DetailSubjectActivity extends AppCompatActivity {

    TextView tv_studentCode, tv_studentName, tv_institutionName,
            tv_location, tv_pendingTask , tv_titleSubject;
    Button btn_volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        String titleSubject = getIntent().getExtras().getString("titleSubject");
        //String idSubject = getIntent().getExtras().getString("idSubject");
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
    }
}