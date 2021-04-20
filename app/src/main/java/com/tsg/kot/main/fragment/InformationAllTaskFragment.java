package com.tsg.kot.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tsg.kot.R;
import com.tsg.kot.adapter.TasksAdapter;
import com.tsg.kot.data.model.FilesKiosco;
import com.tsg.kot.data.model.Lessons;
import com.tsg.kot.data.model.MessageAnswer;
import com.tsg.kot.data.model.Subjects;
import com.tsg.kot.data.model.SubmissionDisplay;
import com.tsg.kot.data.model.Task;
import com.tsg.kot.data.remote.model.FileMessageRemote;
import com.tsg.kot.data.remote.model.MessageRemote;
import com.tsg.kot.data.remote.model.StudyMaterialRemote;
import com.tsg.kot.main.mainmvp.MainMVP;
import com.tsg.kot.main.mainmvp.MainView;
import com.tsg.kot.storage.TOTPreferences;
import com.tsg.kot.subject.DetailSubjectActivity;
import com.tsg.kot.task.taskmvp.TaskMVP;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformationAllTaskFragment} factory method to
 * create an instance of this fragment.
 */
public class InformationAllTaskFragment extends Fragment implements FragmentsMVP.View{

    private OnFragmentInteractionListener mListener;

    TextView tv_title, tv_subtitle, tv_description;
    ImageView iv_image;
    ImageButton ib_subject;
    int tareasPendientes = 0;

    RecyclerView recyclerViewAllTask;
    TasksAdapter tasksAdapter;


    MainMVP.Presenter presenter;

    Subjects subjects;

    public InformationAllTaskFragment(MainMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_all_task, container, false);
        ib_subject =  view.findViewById(R.id.ib_subject_all_task);
        recyclerViewAllTask = view.findViewById(R.id.recyclerTaskAll);
        recyclerViewAllTask.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewAllTask.setLayoutManager(linearLayoutManager);

        tasksAdapter = new TasksAdapter();
        recyclerViewAllTask.setAdapter(tasksAdapter);

        tv_title = view.findViewById(R.id.title_subject);
        tv_subtitle = view.findViewById(R.id.subtitle_subject);
        tv_description = view.findViewById(R.id.description_subject);

        iv_image = view.findViewById(R.id.image_subject);

        Bundle objectSubject = getArguments();

        if (objectSubject != null) {
            subjects = (Subjects) objectSubject.getSerializable("subject");

            if (subjects != null) {
                setInformation(subjects);
            }

        }
        return view;
    }

    public void setInformation(Subjects subjects) {
        tv_title.setText(subjects.getTitulo());
        tv_subtitle.setText(subjects.getSubtitulo());
        tv_description.setText(subjects.getDescripcion());
        ib_subject.setOnClickListener(view -> {

            //Toast.makeText(context,"click en material: "+taskList.get(position).getNombre(),Toast.LENGTH_SHORT).show();
            Intent intent  = new  Intent (getContext(), DetailSubjectActivity.class);
            intent.putExtra( "idSubject",subjects.getId());
            intent.putExtra("titleSubject",subjects.getTitulo());
            intent.putExtra("student_name", ((MainView)getContext()).tv_studentName.getText());
            tareasPendientes =  TOTPreferences.getInstance(getContext()).getTareasPendientes();

            getContext().startActivity(intent);


        });



        try {
            InputStream stream = new ByteArrayInputStream(Base64.decode(subjects.getImagen().getBytes(), Base64.DEFAULT));
            Bitmap bitmap = BitmapFactory.decodeStream(stream);

            iv_image.setImageBitmap(bitmap);
        } catch (Exception ex) {
            Log.d("setInformation", ex.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        Integer idEstudianteI = Integer.parseInt(TOTPreferences.getInstance(getContext()).getIdEstudiante()==""?"0":TOTPreferences.getInstance(getContext()).getIdEstudiante());


        setTaskSubjects(presenter.getAllPendingTaskSubject(getContext(), idEstudianteI), getContext(), presenter);
        tareasPendientes =  TOTPreferences.getInstance(getContext()).getTareasPendientes();
    }

    @Override
    public void setSubjects(List<Subjects> subjectsList, Context context, MainMVP.Presenter presenter) {

    }

    @Override
    public void setTaskSubjects(List<Task> taskSubjects, Context context, MainMVP.Presenter presenter) {
        tasksAdapter.dataSet(taskSubjects, taskSubjects.size(), context, presenter);
        tareasPendientes =  TOTPreferences.getInstance(getContext()).getTareasPendientes();

    }

    @Override
    public void setFileKiosco(List<FilesKiosco> filesKioscoList, Context context, TaskMVP.Presenter presenter) {

    }

    @Override
    public void setLessons(List<Lessons> lessonsList, Context context, TaskMVP.Presenter presenter) {

    }

    @Override
    public void setStudyMaterials(List<StudyMaterialRemote> studyMaterialRemoteList, Context context, TaskMVP.Presenter presenter) {

    }

    @Override
    public void setSubmissionDisplay(List<SubmissionDisplay> submissionDisplayList, Context context, TaskMVP.Presenter presenter) {

    }

    @Override
    public void setInformationMessage(List<MessageRemote> messageRemoteList, Context context, MainMVP.Presenter presenter) {

    }

    @Override
    public void setFilesMessage(List<FileMessageRemote> fileMessageRemoteList, Context context, MainMVP.Presenter presenter) {

    }

    @Override
    public void setAnswerMessage(List<MessageAnswer> fileMessageRemoteList, Context context, MainMVP.Presenter presenter) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public InformationAllTaskFragment() {

    }
}
