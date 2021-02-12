package com.tsg.tot.main.mainmvp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.JsonObject;
import com.tsg.tot.R;
import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.data.model.Student;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.data.model.TokenCustom;
import com.tsg.tot.data.remote.ApiUtils;
import com.tsg.tot.data.remote.model.TaskRegristerRemote;
import com.tsg.tot.login.LoginActivity;
import com.tsg.tot.main.fragment.CustomProgressDialog;
import com.tsg.tot.main.fragment.InformationFragment;

import com.tsg.tot.main.fragment.ListSubjectFragment;
import com.tsg.tot.repository.ApiRepository;
import com.tsg.tot.repository.DatabaseRepository;
import com.tsg.tot.root.App;
import com.tsg.tot.storage.TOTPreferences;
import com.tsg.tot.task.taskmvp.TaskMVP;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;

public class MainView extends AppCompatActivity
        implements MainMVP.View, ListSubjectFragment.OnFragmentInteractionListener, View.OnClickListener {

    @Inject
    MainMVP.Presenter presenter;
    TaskRegristerRemote taskRegristerRemote;
    //TaskMVP.Presenter presenterTask;
    ListSubjectFragment listSubjectFragment;
    InformationFragment informationFragment;
    FragmentTransaction fragmentTransaction;

    public TextView tv_studentCode, tv_studentName, tv_institutionName,
            tv_location, tv_pendingTask;

    LinearLayout mainLayout;

    //ProgressDialog dialog;
    CustomProgressDialog dialog;
    String token, idUsuario = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initViewElements();
        ((App) getApplication()).getComponent().inject(this);

        //Permissions
        //        requestPermissions(new String[]{READ_EXTERNAL_STORAGE, READ_PHONE_STATE}, 1);

        presenter.createDB(this);

        if (getIntent().getExtras() != null) {
            token = getIntent().getExtras().getString("token");
            idUsuario = getIntent().getExtras().getString("idUsuario");
        }
        // Toast.makeText(this, "el token es : \n" + token, Toast.LENGTH_SHORT).show();
        DatabaseRepository dbR = new DatabaseRepository();

        List<Student> studentList = dbR.getStudent(MainView.this, Integer.parseInt(idUsuario));

        if (studentList.size() > 0) {
            TOTPreferences.getInstance(MainView.this).setIdEstudiante(studentList.get(0).getId().toString());
        } else {
            TOTPreferences.getInstance(MainView.this).setIdEstudiante("0");
        }


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

        dialog = new CustomProgressDialog(MainView.this,
                getResources().getString(R.string.message_load_db));
        dialog.setIcon(R.drawable.tot_icon);
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        showLoadingDialog();
        if (!token.equals("sinConexion")){
            presenter.checkVersions(this, dialog, token, idUsuario);
        }else{
            dialog.setProgress(dialog.getProgress() + 35);
        }
        dialog.setProgress(dialog.getProgress() + 5);
        presenter.setInfoStudent(this, Integer.parseInt(idUsuario));
        dialog.setProgress(dialog.getProgress() + 5);
        if (!token.equals("sinConexion")){
            registrarTareasDescargadas();
        }else{
            dismissLoadingDialog();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
        startActivity(getIntent());
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
                    + studentList.get(0).getId();
            tv_studentCode.setText(code);
            tv_studentName.setText(studentList.get(0).getNombres() + " " + studentList.get(0).getApellidos());
        }

        tv_pendingTask.setText(getResources().getString(R.string.main_view_PendingTasks) + ": ");
    }


    @Override
    public void setInfoSubject(Subjects subjects) {
        informationFragment = (InformationFragment) this.getSupportFragmentManager().
                findFragmentById(R.id.contentFragment);

        if (informationFragment != null && findViewById(R.id.contentFragment) == null) {
            informationFragment.setInformation(subjects);
            Integer idEstudianteI = Integer.parseInt(TOTPreferences.getInstance(MainView.this).getIdEstudiante()==""?"0":TOTPreferences.getInstance(MainView.this).getIdEstudiante());

            informationFragment.setTaskSubjects(presenter.getTaskSubject(this, subjects.getId(), "",idEstudianteI), this, presenter);
        } else {
            informationFragment = new InformationFragment(presenter);
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
        /*Snackbar.make(mainLayout, getResources().getString(R.string.message_snackbar_refresh), Snackbar.LENGTH_INDEFINITE)
                .setBackgroundTint(getResources().getColor(R.color.colorAccent))
                .setAction("RECARGAR", view -> {
                    finish();
                    startActivity(getIntent());
                })
                .setActionTextColor(getResources().getColor(R.color.colorWhite))
                .setDuration(10000)
                .show();*/
        finish();
        startActivity(getIntent());
    }

    @Override
    public void showLoadingDialog() {
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void dismissLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void setFileKiosco(FilesKiosco fileKiosco) {

    }

    public void update(View view) {
        dialog = new CustomProgressDialog(MainView.this,
                getResources().getString(R.string.message_load_db));
        dialog.setIcon(R.drawable.tot_icon);
        dialog.show();
        presenter.updateEverything(this, dialog, token);
        dialog.setProgress(dialog.getProgress() + 5);
        presenter.setInfoStudent(this, Integer.parseInt(idUsuario));
        dialog.setProgress(dialog.getProgress() + 5);
    }

    public void logout(View view) {
        this.finish();
        Intent intent = new Intent();
        intent.setAction("com.package.ACTION_LOGOUT");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        sendBroadcast(intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        TOTPreferences.getInstance(view.getContext()).setIdUsuario("0");
        TOTPreferences.getInstance(view.getContext()).setIdEstudiante("0");



    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.statusTaskImage: //id de ImageView.
                dialog = new CustomProgressDialog(MainView.this,
                        getResources().getString(R.string.message_load_db));
                dialog.setIcon(R.drawable.tot_icon);
                dialog.show();
                presenter.updateEverything(this, dialog, token);
                dialog.setProgress(dialog.getProgress() + 5);
                presenter.setInfoStudent(this, Integer.parseInt(idUsuario));
                dialog.setProgress(dialog.getProgress() + 5);
                //realiza operación al dar clic al imageView.
                Intent intent = new Intent(this, MainView.class);
                startActivity(intent);

                break;
            default:
                break;
        }
    }

    public void registrarTareasDescargadas(){
        DatabaseRepository dbR = new DatabaseRepository();

        List<Task> taskList = dbR.getTasksToRegister(MainView.this,TOTPreferences.getInstance(MainView.this).getIdEstudiante()==""?0:Integer.parseInt(TOTPreferences.getInstance(MainView.this).getIdEstudiante()));
        if (taskList.size()>0){
            for(Task taskAux : taskList){
                Integer idTask = taskAux.getTareakiosco();
                registrarTarea(token,taskAux.getId());
            }

        }

    }

    private void registrarTarea(String token , Integer idTarea){

        ApiRepository apiRepository = new ApiRepository();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("tareaId", idTarea);

        try{
            ///Se obtiene Login remoto  token  en caso de no obtenerlo no se procede a sincronizar
            Call<TaskRegristerRemote> taskRegisterCall = ApiUtils.getAPIServiceTaskRegister().postRegisterTask(token,jsonObject);

            taskRegisterCall.enqueue(new Callback<TaskRegristerRemote>() {
                @Override
                public void onResponse(Call<TaskRegristerRemote> call, Response<TaskRegristerRemote> response) {
                    taskRegristerRemote = response.body();
                    if (response.code() == 201) {
                        Task task = new Task ();
                        DatabaseRepository dbR = new DatabaseRepository();
                        task.setEstudiante(taskRegristerRemote.getEstudianteId());
                        task.setTareakiosco(taskRegristerRemote.getTareaId());
                        task.setRegistroTarea(taskRegristerRemote.getId());
                        dbR.updateTaskRegister(task,MainView.this);
                    }
                    else {
                        return;
                    }


                }

                @Override
                public void onFailure(Call<TaskRegristerRemote> call, Throwable t) {

                }
            });


        } catch (Exception e) {

        }

    }
}
