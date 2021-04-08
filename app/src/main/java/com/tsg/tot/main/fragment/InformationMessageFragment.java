package com.tsg.tot.main.fragment;

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

import com.tsg.tot.R;

import com.tsg.tot.adapter.MessagesAdapter;
import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.data.model.Lessons;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.data.model.SubmissionDisplay;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.data.remote.model.FileMessageRemote;
import com.tsg.tot.data.remote.model.MessageRemote;
import com.tsg.tot.data.remote.model.StudyMaterialRemote;
import com.tsg.tot.main.mainmvp.MainMVP;
import com.tsg.tot.main.mainmvp.MainView;
import com.tsg.tot.storage.TOTPreferences;
import com.tsg.tot.subject.DetailSubjectActivity;
import com.tsg.tot.task.taskmvp.TaskMVP;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformationMessageFragment} factory method to
 * create an instance of this fragment.
 */
public class InformationMessageFragment extends Fragment implements FragmentsMVP.View{

    private OnFragmentInteractionListener mListener;



    RecyclerView recyclerViewMessage;
    MessagesAdapter messagesAdapter;

    MainMVP.Presenter presenter;

    Subjects subjects;

    public InformationMessageFragment(MainMVP.Presenter presenter) {

        this.presenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        recyclerViewMessage = view.findViewById(R.id.recyclerMessage);
        recyclerViewMessage.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewMessage.setLayoutManager(linearLayoutManager);

        messagesAdapter = new MessagesAdapter();
        recyclerViewMessage.setAdapter(messagesAdapter);

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
        Integer idEstudianteI = Integer.parseInt(TOTPreferences.getInstance(getContext()).getIdEstudiante()==""?"0":TOTPreferences.getInstance(getContext()).getIdEstudiante());


        List<MessageRemote> messageRemoteList = presenter.getMessagesStudent(getContext(),idEstudianteI);
        setInformationMessage(messageRemoteList,getContext(),presenter);
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
        messagesAdapter.dataSet(messageRemoteList,messageRemoteList.size(),context,presenter);
    }

    @Override
    public void setFilesMessage(List<FileMessageRemote> fileMessageRemoteList, Context context, MainMVP.Presenter presenter) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public InformationMessageFragment() {

    }




    public void setInformation(List<MessageRemote> messagesList) {

    }
}
