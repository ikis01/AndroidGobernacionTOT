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
import com.tsg.tot.adapter.MessagesAnswerAdapter;
import com.tsg.tot.adapter.MessagesFileAdapter;
import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.data.model.Lessons;
import com.tsg.tot.data.model.MessageAnswer;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListMessageAnswerFragment} factory method to
 * create an instance of this fragment.
 */
public class ListMessageAnswerFragment extends Fragment implements FragmentsMVP.View {

    private OnFragmentInteractionListener mListener;


    RecyclerView recyclerViewAnswerMessage;
    MessagesAnswerAdapter messagesAnswerAdapter;


    MainMVP.Presenter presenter;


    public ListMessageAnswerFragment(MainMVP.Presenter presenter) {

        this.presenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_answer_messages, container, false);

        recyclerViewAnswerMessage = view.findViewById(R.id.recyclerMessageAnswers);
        recyclerViewAnswerMessage.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewAnswerMessage.setLayoutManager(linearLayoutManager);

        messagesAnswerAdapter = new MessagesAnswerAdapter();
        recyclerViewAnswerMessage.setAdapter(messagesAnswerAdapter);

        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        Integer idMensajeKiosco = TOTPreferences.getInstance(getContext()).getIdMensajeKiosco();


        setAnswerMessage( presenter.getMessageAnswer(getContext(),idMensajeKiosco),getContext(),presenter);

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

    }

    @Override
    public void setInformationMessage(List<MessageRemote> messageRemoteList, Context context, MainMVP.Presenter presenter) {
    }

    @Override
    public void setFilesMessage(List<FileMessageRemote> fileMessageRemoteList, Context context, MainMVP.Presenter presenter) {
    }

    @Override
    public void setAnswerMessage(List<MessageAnswer> fileMessageRemoteList, Context context, MainMVP.Presenter presenter) {
        messagesAnswerAdapter.dataSet(fileMessageRemoteList,fileMessageRemoteList.size(),context,presenter);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public ListMessageAnswerFragment() {

    }
}
