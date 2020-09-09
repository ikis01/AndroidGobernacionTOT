package com.tsg.tot.main.mainmvp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;
import com.tsg.tot.R;
import com.tsg.tot.data.model.Student;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.main.fragment.InformationFragment;
import com.tsg.tot.main.fragment.ListSubjectFragment;
import com.tsg.tot.root.App;

import java.util.List;

import javax.inject.Inject;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;

public class MainView extends AppCompatActivity implements MainMVP.View, ListSubjectFragment.OnFragmentInteractionListener {

    @Inject
    MainMVP.Presenter presenter;

    ListSubjectFragment listSubjectFragment;
    InformationFragment informationFragment;
    FragmentTransaction fragmentTransaction;

    TextView tv_studentCode, tv_studentName, tv_institutionName,
            tv_location, tv_pendingEvaluations, tv_pendingTask;

    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initViewElements();
        ((App) getApplication()).getComponent().inject(this);

        //Permissions
        requestPermissions(new String[]{READ_EXTERNAL_STORAGE, READ_PHONE_STATE}, 1);

        presenter.createDB(this);

        //Init Fragment
        listSubjectFragment = new ListSubjectFragment(presenter);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentList, listSubjectFragment);
        fragmentTransaction.commit();
    }

    private void initViewElements() {
        mainLayout = findViewById(R.id.mainLayout);
        tv_studentCode = findViewById(R.id.tv_studentCode);
        tv_studentName = findViewById(R.id.tv_studentName);
        tv_institutionName = findViewById(R.id.tv_institutionName);
        tv_location = findViewById(R.id.tv_location);

        tv_pendingTask = findViewById(R.id.tv_pendingTask);
        tv_pendingEvaluations = findViewById(R.id.tv_pendingEvaluations);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.checkVersions(this);
        presenter.setInfoStudent(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void setInfoStudent(List<Student> studentList) {
        if (studentList.size() > 0) {
            String code = getResources().getString(R.string.main_view_StudentCode)
                    + studentList.get(0).getCodigo();
            tv_studentCode.setText(code);
            tv_studentName.setText(studentList.get(0).getNombres());
        }

        tv_pendingTask.setText(getResources().getString(R.string.main_view_PendingTasks));
        tv_pendingEvaluations.setText(getResources().getString(R.string.main_view_PendingEvaluations));
    }

    @Override
    public void setInfoSubject(Subjects subjects) {
        informationFragment = (InformationFragment) this.getSupportFragmentManager().
                findFragmentById(R.id.contentFragment);

        if (informationFragment != null && findViewById(R.id.contentFragment) == null) {
            informationFragment.setInformation(subjects);
        } else {
            informationFragment = new InformationFragment();
            Bundle bundleEnvio = new Bundle();
            bundleEnvio.putSerializable("subject", subjects);
            informationFragment.setArguments(bundleEnvio);

            //Carga el fragment en el Activity
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.contentFragment, informationFragment).
                    addToBackStack(null).commit();
        }
    }

    @Override
    public void notifyRefresh() {
        Snackbar.make(mainLayout, getResources().getString(R.string.message_snackbar_refresh), Snackbar.LENGTH_INDEFINITE)
                .setBackgroundTint(getResources().getColor(R.color.colorAccent))
                .setAction("RECARGAR", view -> {
                    finish();
                    startActivity(getIntent());
                })
                .setActionTextColor(getResources().getColor(R.color.colorWhite))
                .setDuration(10000)
                .show();
    }
}
