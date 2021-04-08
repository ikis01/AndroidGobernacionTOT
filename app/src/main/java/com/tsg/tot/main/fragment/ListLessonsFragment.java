package com.tsg.tot.main.fragment;

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

import com.tsg.tot.R;
import com.tsg.tot.adapter.LessonsAdapter;
import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.data.model.Lessons;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.data.model.SubmissionDisplay;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.data.remote.model.FileMessageRemote;
import com.tsg.tot.data.remote.model.MessageRemote;
import com.tsg.tot.data.remote.model.StudyMaterialRemote;
import com.tsg.tot.main.mainmvp.MainMVP;
import com.tsg.tot.storage.TOTPreferences;
import com.tsg.tot.task.taskmvp.TaskMVP;

import java.util.List;

import javax.inject.Inject;

/**
 * A fragment representing a list of Items.
 */
public class ListLessonsFragment extends Fragment implements
        TaskMVP.Presenter ,FragmentsMVP.View {
    private ListLessonsFragment.OnFragmentInteractionListener mListener;

    RecyclerView recyclerList;

    LessonsAdapter lessonsAdapter;
    Integer idMateria = 0;

    //@Inject
    // MainMVP.Presenter presenter;

    @Inject
    TaskMVP.Presenter presenter;



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */


    public ListLessonsFragment(TaskMVP.Presenter presenter, Integer idMateria) {
        this.presenter = presenter;

        this.idMateria = idMateria;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_lessons, container, false);

        recyclerList = view.findViewById(R.id.recyclerLessons);
        recyclerList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerList.setLayoutManager(linearLayoutManager);
        lessonsAdapter = new LessonsAdapter();
        recyclerList.setAdapter(lessonsAdapter);

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
        lessonsAdapter.dataSet(lessonsList,lessonsList.size(),context,presenter);
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
    public void onResume() {

        super.onResume();
        Integer idEstudianteI = Integer.parseInt(TOTPreferences.getInstance(getContext()).getIdEstudiante());
        setLessons(presenter.getLessons(getContext(),idEstudianteI,idMateria),getContext(),presenter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ListLessonsFragment.OnFragmentInteractionListener) {
            mListener = (ListLessonsFragment.OnFragmentInteractionListener) context;
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
    public void setLessons(Lessons lessons) {

    }

    @Override
    public void setFileKiosco(FilesKiosco fileKiosco) {

    }

    @Override
    public void setView(TaskMVP.View view) {

    }

    @Override
    public List<Task> getTaskSubject(Context context, int idSubject, String token, Integer idEstudiante) {
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
    public List<FilesKiosco> getFileKioscos(Context context, int parseInt, int parseInt1, int i) {
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

}