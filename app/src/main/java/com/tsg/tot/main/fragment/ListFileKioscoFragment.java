package com.tsg.tot.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tsg.tot.R;
import com.tsg.tot.adapter.TasksAdapter;
import com.tsg.tot.data.model.FilesKiosco;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.main.mainmvp.MainMVP;
import com.tsg.tot.main.mainmvp.MainPresenter;
import com.tsg.tot.main.mainmvp.MainView;
import com.tsg.tot.subject.DetailSubjectActivity;
import com.tsg.tot.task.taskmvp.TaskMVP;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

/**
 * A fragment representing a list of Items.
 */
public class ListFileKioscoFragment extends Fragment implements FragmentsMVP.View{
    private ListFileKioscoFragment.OnFragmentInteractionListener mListener;

    RecyclerView recyclerList;
    FileKioscoAdapter fileKioscoAdapter;

    //@Inject
   // MainMVP.Presenter presenter;

    @Inject
    TaskMVP.Presenter presenter;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListFileKioscoFragment(TaskMVP.Presenter presenter) {
        this.presenter = presenter;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_fie_kiosco, container, false);

        recyclerList = view.findViewById(R.id.recyclerFileKiosco);
        recyclerList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerList.setLayoutManager(linearLayoutManager);

        fileKioscoAdapter = new FileKioscoAdapter();
        recyclerList.setAdapter(fileKioscoAdapter);

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
        fileKioscoAdapter.dataSet(filesKioscoList,filesKioscoList.size(),context,presenter);
    }



    @Override
    public void onResume() {

        Integer intCode = 0;
        super.onResume();
        String code = "";//((MainView)getContext()).tv_studentCode.getText().toString();
        code = "209";// code.replace("Código:","");
        String materia = "6763";
        if (code != ""){
            intCode= Integer.parseInt(code);
        }else{

        }
        setFileKiosco(presenter.getFileKioscos(getContext(), Integer.parseInt(code),Integer.parseInt(materia),601),  getContext(),  presenter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ListFileKioscoFragment.OnFragmentInteractionListener) {
            mListener = (ListFileKioscoFragment.OnFragmentInteractionListener) context;
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }



}