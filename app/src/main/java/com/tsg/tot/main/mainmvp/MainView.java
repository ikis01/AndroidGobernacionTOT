package com.tsg.tot.main.mainmvp;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.tsg.tot.data.remote.ApiUtils;
import com.tsg.tot.data.remote.model.SubmissionPending;
import com.tsg.tot.data.remote.model.TaskRegristerRemote;
import com.tsg.tot.main.fragment.CustomProgressDialog;
import com.tsg.tot.main.fragment.InformationFragment;
import com.tsg.tot.main.fragment.ListSubjectFragment;
import com.tsg.tot.repository.ApiRepository;
import com.tsg.tot.repository.DatabaseRepository;
import com.tsg.tot.root.App;
import com.tsg.tot.storage.TOTPreferences;

import java.io.File;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    int tareasPendientes = 0;

    LinearLayout mainLayout;

    //ProgressDialog dialog;
    CustomProgressDialog dialog;
    String token, idUsuario = "";
    Boolean actionSync = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initViewElements();
        ((App) getApplication()).getComponent().inject(this);
        tareasPendientes =  TOTPreferences.getInstance(this).getTareasPendientes();
        //Permissions
        //        requestPermissions(new String[]{READ_EXTERNAL_STORAGE, READ_PHONE_STATE}, 1);

        presenter.createDB(this);

        if (getIntent().getExtras() != null) {
            token = getIntent().getExtras().getString("token");
            idUsuario = getIntent().getExtras().getString("idUsuario");
            actionSync = getIntent().getExtras().getBoolean("actionSync");
        }
        // Toast.makeText(this, "el token es : \n" + token, Toast.LENGTH_SHORT).show();
        DatabaseRepository dbR = new DatabaseRepository();

        List<Student> studentList = dbR.getStudent(MainView.this, Integer.parseInt(idUsuario==null?"0":idUsuario.equals("")?"0":idUsuario));

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

        if (actionSync){
            TOTPreferences.getInstance(this).setActionSync(true);
            showLoadingDialog();
        }


        if (!token.equals("sinConexion")&& !actionSync) {
            presenter.checkVersions(this, dialog, token, idUsuario);
        } else {
                dialog.setProgress(dialog.getProgress() + 35);
        }


        if (!token.equals("sinConexion")&& actionSync) {
            showLoadingDialog();
            presenter.checkVersionsSync(this,dialog,token,idUsuario);
            //presenter.checkVersions(this, dialog, token, idUsuario);
        }




        dialog.setProgress(dialog.getProgress() + 5);
        presenter.setInfoStudent(this, Integer.parseInt(idUsuario));
        dialog.setProgress(dialog.getProgress() + 5);
        if (!token.equals("sinConexion")) {
            registrarTareasDescargadas();
            subirEntregasPendientes();
        } else {
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
            Integer idEstudianteI = Integer.parseInt(TOTPreferences.getInstance(MainView.this).getIdEstudiante() == "" ? "0" : TOTPreferences.getInstance(MainView.this).getIdEstudiante());

            informationFragment.setTaskSubjects(presenter.getTaskSubject(this, subjects.getId(), "", idEstudianteI), this, presenter);
            tareasPendientes =  TOTPreferences.getInstance(this).getTareasPendientes().equals("")?0:TOTPreferences.getInstance(this).getTareasPendientes();
            tv_pendingTask.setText(getResources().getString(R.string.main_view_PendingTasks) + ": " +tareasPendientes);
        } else {
            informationFragment = new InformationFragment(presenter);
            Bundle bundleEnvio = new Bundle();
            bundleEnvio.putSerializable("subject", subjects);
            informationFragment.setArguments(bundleEnvio);
            tareasPendientes =  TOTPreferences.getInstance(this).getTareasPendientes();
            tv_pendingTask.setText(getResources().getString(R.string.main_view_PendingTasks) + ": " +tareasPendientes);


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
        presenter.updateEverything(this, dialog, token,Integer.parseInt(idUsuario.equals("")?"0":idUsuario));
        dialog.setProgress(dialog.getProgress() + 5);
        presenter.setInfoStudent(this, Integer.parseInt(idUsuario));
        dialog.setProgress(dialog.getProgress() + 5);
    }

    public void logout(View view) {
     /*   this.finish();
        Intent intent = new Intent();
        intent.setAction("com.package.ACTION_LOGOUT");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        sendBroadcast(intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        TOTPreferences.getInstance(view.getContext()).setIdUsuario("0");
        TOTPreferences.getInstance(view.getContext()).setIdEstudiante("0");

        TOTPreferences.getInstance(view.getContext()).setIdUsuario("0");
        TOTPreferences.getInstance(view.getContext()).setIdEstudiante("0");
        TOTPreferences.getInstance(view.getContext()).setIdClase("0");


        Thread[] threads = new Thread[Thread.activeCount()];
        Thread.enumerate(threads);
        for (Thread t : threads) {
            if (t.isAlive()) {
                t.interrupt();
            }
        }

         finishAffinity();
*/


        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Cerrar Sesión");
        dialogo1.setMessage("¿ Esta Seguro de que desea cerrar la sesión ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptarCerrarSesion(view);
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelarCerrarSesion(view);
            }
        });
        dialogo1.show();


    }

    public void aceptarCerrarSesion(View view) {
        Intent mStartActivity = new Intent(this, MainView.class);
        int mPendingIntentId = 1;
        PendingIntent mPendingIntent = PendingIntent.getActivity(this, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager)this.getSystemService(ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, //System.currentTimeMillis() +
                 60, mPendingIntent);
        TOTPreferences.getInstance(view.getContext()).setIdUsuario("0");
        TOTPreferences.getInstance(view.getContext()).setIdEstudiante("0");
        TOTPreferences.getInstance(view.getContext()).setIdClase("0");
        TOTPreferences.getInstance(view.getContext()).setTareaspendientes(0);
        //TOTPreferences.getInstance(view.getContext()).setActionSync(false);
        Thread[] threads = new Thread[Thread.activeCount()];
        Thread.enumerate(threads);
        for (Thread t : threads) {
            if (t.isAlive()) {

                t.interrupt();
            }
        }

        finishAffinity();
        super.finish();


        android.os.Process.killProcess(android.os.Process.myPid());

    }

    public void cancelarCerrarSesion(View view) {
        finish();
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.statusTaskImage: //id de ImageView.
                dialog = new CustomProgressDialog(MainView.this,
                        getResources().getString(R.string.message_load_db));
                dialog.setIcon(R.drawable.tot_icon);
                dialog.show();
                presenter.updateEverything(this, dialog, token,Integer.parseInt(idUsuario.equals("")?"0":idUsuario));
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

    public void registrarTareasDescargadas() {
        DatabaseRepository dbR = new DatabaseRepository();

        List<Task> taskList = dbR.getTasksToRegister(MainView.this, TOTPreferences.getInstance(MainView.this).getIdEstudiante() == "" ? 0 : Integer.parseInt(TOTPreferences.getInstance(MainView.this).getIdEstudiante()));
        if (taskList.size() > 0) {
            for (Task taskAux : taskList) {
                Integer idTask = taskAux.getTareakiosco();
                registrarTarea(token, taskAux.getId());
            }

        }

    }

    public void subirEntregasPendientes() {
        DatabaseRepository dbR = new DatabaseRepository();

        List<SubmissionPending> submissionsList = dbR.getSubmissionsToUpload(MainView.this, TOTPreferences.getInstance(MainView.this).getIdEstudiante() == "" ? 0 : Integer.parseInt(TOTPreferences.getInstance(MainView.this).getIdEstudiante()));
        if (submissionsList.size() > 0) {
            for (SubmissionPending submissionsAux : submissionsList) {
                    subirEntrega(token,submissionsAux);
            }
        }
    }

    private void subirEntrega (String token, SubmissionPending submissionPending){

        File file = new File(submissionPending.getArchivo());
        RequestBody requestBody= RequestBody.create(MediaType.parse("application/octet-stream"),file);
        MultipartBody.Part body =MultipartBody.Part.createFormData("File",file.getName(), requestBody);

        ApiRepository apiRepository = new ApiRepository();
        String macAddress = ApiUtils.getMacAddress();

        Integer registroId = submissionPending.getTareaRegistroId();
        RequestBody tareaRegistroId = RequestBody.create(MultipartBody.FORM,registroId.toString());
        RequestBody mac = RequestBody.create(MultipartBody.FORM, macAddress);


      /*  JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("TareaRegistroId",submissionPending.getTareaRegistroId());
        jsonObject.addProperty("Mac",submissionPending.getMacAddress());
        jsonObject.addProperty("File", String.valueOf(file));
*/
//        Uri uri = Uri.fromFile(file);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
//        MultipartBody.Part body = MultipartBody.Part.createFormData("myfile", file.getName(), requestBody);



        try {
            Call<TaskRegristerRemote> submissionUploadTask = ApiUtils.getAPIServiceTaskRegister().postUploadTask(token,body,tareaRegistroId, mac);

            submissionUploadTask.enqueue(new Callback<TaskRegristerRemote>() {
                @Override
                public void onResponse(Call<TaskRegristerRemote> call, Response<TaskRegristerRemote> response) {
                    taskRegristerRemote = response.body();
                    if (response.code() == 201) {

                        DatabaseRepository dbR = new DatabaseRepository();
                        //Task task = new Task();
                        /*task.setEstudiante(taskRegristerRemote.getEstudianteId());
                        task.setTareakiosco(taskRegristerRemote.getTareaId());
                        task.setRegistroTarea(taskRegristerRemote.getId());*/
                        taskRegristerRemote.getTareaRegistroId();
                        dbR.updateSubmissionUpp(taskRegristerRemote.getTareaRegistroId(),MainView.this);
                    } else {
                        return;
                    }


                }

                @Override
                public void onFailure(Call<TaskRegristerRemote> call, Throwable t) {

                }
            });


        } catch (Exception e) {
                Log.d("Exception --- ", "Exception --- " + e.getMessage());
        }

    }

    private void registrarTarea(String token, Integer idTarea) {
        String macAddress = ApiUtils.getMacAddress();
        ApiRepository apiRepository = new ApiRepository();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("tareaId", idTarea);
        jsonObject.addProperty("mac",macAddress);



        try {
            ///Se obtiene Login remoto  token  en caso de no obtenerlo no se procede a sincronizar
            Call<TaskRegristerRemote> taskRegisterCall = ApiUtils.getAPIServiceTaskRegister().postRegisterTask(token, jsonObject);

            taskRegisterCall.enqueue(new Callback<TaskRegristerRemote>() {
                @Override
                public void onResponse(Call<TaskRegristerRemote> call, Response<TaskRegristerRemote> response) {
                    taskRegristerRemote = response.body();
                    if (response.code() == 201) {
                        Task task = new Task();
                        DatabaseRepository dbR = new DatabaseRepository();
                        task.setEstudiante(taskRegristerRemote.getEstudianteId());
                        task.setTareakiosco(taskRegristerRemote.getTareaId());
                        task.setRegistroTarea(taskRegristerRemote.getId());
                        dbR.updateTaskRegister(task, MainView.this);
                    } else {
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

    public static String getMacAddress() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "LOCALHOST";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(Integer.toHexString(b & 0xFF) + ":");
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            Log.d("Error", ex.getMessage());
        }
        return "LOCALHOST";
    }
}
