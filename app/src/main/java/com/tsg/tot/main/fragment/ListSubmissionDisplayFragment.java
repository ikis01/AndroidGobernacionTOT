package com.tsg.tot.main.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tsg.tot.R;

import com.tsg.tot.adapter.SubmissionsDisplayAdapter;
import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.data.model.Lessons;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.data.model.SubmissionDisplay;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.data.remote.model.StudyMaterialRemote;
import com.tsg.tot.main.mainmvp.MainMVP;
import com.tsg.tot.task.taskmvp.TaskMVP;

import java.util.List;

import javax.inject.Inject;


/**
 * A fragment representing a list of Items.
 */
public class ListSubmissionDisplayFragment extends Fragment implements
        TaskMVP.Presenter ,FragmentsMVP.View{
    private ListFileKioscoFragment.OnFragmentInteractionListener mListener;

    RecyclerView recyclerList;
    SubmissionsDisplayAdapter submissionsDisplayAdapter;
    Integer idMateria = 0;
    Integer idTarea = 0;
    Integer idEstudiante = 0;

    @Inject
    TaskMVP.Presenter presenter;

    public ListSubmissionDisplayFragment (TaskMVP.Presenter presenter,Integer idMateria,Integer idTarea,Integer idEstudiante) {
        this.presenter = presenter;
        this.idEstudiante = idEstudiante;
        this.idTarea= idTarea;
        this.idMateria = idMateria;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_submissions_display, container, false);
        recyclerList = view.findViewById(R.id.recyclerSubmissionDisplay);
        recyclerList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerList.setLayoutManager(linearLayoutManager);
        submissionsDisplayAdapter = new SubmissionsDisplayAdapter();
        recyclerList.setAdapter(submissionsDisplayAdapter);


        return view;
    }

    @Override
    public void setSubjects(List<Subjects> subjectsList, Context context, MainMVP.Presenter presenter) {

    }

    @Override
    public void setTaskSubjects(List<Task> taskSubjects, Context context, MainMVP.Presenter presenter) {

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
        submissionsDisplayAdapter.dataSet(submissionDisplayList,submissionDisplayList.size(),context,presenter);
    }

    @Override
    public void setLessons(Lessons lessons) {

    }

    @Override
    public void setFileKiosco(FilesKiosco fileKiosco) {

    }

    @Override
    public void setView(TaskMVP.View view) {

    }

    @Override
    public List<Task> getTaskSubject(Context context, int idSubject, String token,Integer idEstudiante) {
        return null;
    }

    @Override
    public void setInfoStudent(Context context) {

    }

    @Override
    public void setInfoSubject(Subjects subjects) {

    }

    @Override
    public void notifyRefresh() {

    }

    @Override
    public List<FilesKiosco> getFileKioscos(Context context, int idEstudiante, int idMateria, int idTarea) {
        return null;
    }

    @Override
    public List<Lessons> getLessons(Context context, int idEstudiante, int idMateria) {
        return null;
    }

    @Override
    public List<StudyMaterialRemote> getStudyMaterials(Context context, int idClase) {
        return null;
    }

    @Override
    public List<SubmissionDisplay> getSubmissionDisplay(Context context, int idEstudiante, int idMateria, int idTarea) {
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onResume() {

        super.onResume();
        setSubmissionDisplay(presenter.getSubmissionDisplay(getContext(), idEstudiante,idMateria,idTarea),  getContext(),  presenter);
    }
}