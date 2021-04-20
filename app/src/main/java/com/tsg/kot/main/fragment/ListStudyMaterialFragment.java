package com.tsg.kot.main.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tsg.kot.R;
import com.tsg.kot.adapter.StudyMaterialAdapter;
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
import com.tsg.kot.storage.TOTPreferences;
import com.tsg.kot.task.taskmvp.TaskMVP;

import java.util.List;

import javax.inject.Inject;

/**
 * A fragment representing a list of Items.
 */
public class ListStudyMaterialFragment extends Fragment
implements TaskMVP.Presenter , FragmentsMVP.View {
    private ListStudyMaterialFragment.OnFragmentInteractionListener mListener;

    RecyclerView recyclerList;

    StudyMaterialAdapter studyMaterialAdapter;
    Integer idClase = 0;

    @Inject
    TaskMVP.Presenter presenter;


    public ListStudyMaterialFragment(TaskMVP.Presenter presenter,Integer idClase) {
        this.presenter= presenter;
        this.idClase = idClase;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_study_material_, container, false);

        recyclerList = view.findViewById(R.id.recyclerStudyMaterial);
        recyclerList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerList.setLayoutManager(linearLayoutManager);
        studyMaterialAdapter = new StudyMaterialAdapter();
        recyclerList.setAdapter(studyMaterialAdapter);

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
        studyMaterialAdapter.dataSet(studyMaterialRemoteList,studyMaterialRemoteList.size(),context,presenter);
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {

        super.onResume();
        Integer idClaseI = Integer.parseInt(TOTPreferences.getInstance(getContext()).getIdclase());
        setStudyMaterials(presenter.getStudyMaterials(getContext(),idClaseI),getContext(),presenter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
/*        if (context instanceof ListStudyMaterialFragment.OnFragmentInteractionListener) {
            mListener = (ListStudyMaterialFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public ListStudyMaterialFragment() {

    }

    public ListStudyMaterialFragment(TaskMVP.Presenter presenter) {
        this.presenter = presenter;
    }
}