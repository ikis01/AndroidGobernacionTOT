package com.tsg.tot.main.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tsg.tot.R;
import com.tsg.tot.adapter.SubjectsAdapter;
import com.tsg.tot.adapter.TasksAdapter;
import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.data.model.Lessons;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.data.model.SubmissionDisplay;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.data.remote.model.StudyMaterialRemote;
import com.tsg.tot.main.mainmvp.MainMVP;
import com.tsg.tot.storage.TOTPreferences;
import com.tsg.tot.task.taskmvp.TaskMVP;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListSubjectFragment} factory method to
 * create an instance of this fragment.
 */
public class  ListSubjectFragment extends Fragment implements FragmentsMVP.View {

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerList;
    TasksAdapter tasksAdapter;
    SubjectsAdapter subjectsAdapter;
    int tareasPendientes = 0;
    MainMVP.Presenter presenter;

    public ListSubjectFragment(MainMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_subject, container, false);
        recyclerList = view.findViewById(R.id.recyclerView);
        recyclerList.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerList.setLayoutManager(linearLayoutManager);

        subjectsAdapter = new SubjectsAdapter();
        recyclerList.setAdapter(subjectsAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
//        Integer intCode = 0;

//        String code = ((MainView)getContext()).tv_studentCode.getText().toString();
//        code = code.replace("Código:","");
//
//        if (code != ""){
//            intCode= Integer.parseInt(code);
//        }else{
//
//        }

        //Integer idEstudianteI = Integer.parseInt(TOTPreferences.getInstance(getContext()).getIdUsuario());

        Integer idEstudianteI = Integer.parseInt(TOTPreferences.getInstance(getContext()).getIdEstudiante()==""?"0":TOTPreferences.getInstance(getContext()).getIdEstudiante());


        setSubjects(presenter.getSubjects(getContext(),"",idEstudianteI), getContext(), presenter );
        tareasPendientes =  TOTPreferences.getInstance(getContext()).getTareasPendientes();

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void setSubjects(List<Subjects> subjectsList, Context context, MainMVP.Presenter presenter) {
        subjectsAdapter.dataSet(subjectsList, subjectsList.size(), context, presenter);
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

    public ListSubjectFragment() {

    }
}