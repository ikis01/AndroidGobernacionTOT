package com.tsg.tot.main.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tsg.tot.R;
import com.tsg.tot.adapter.SubjectsAdapter;
import com.tsg.tot.adapter.TasksAdapter;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.main.mainmvp.MainMVP;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformationFragment} factory method to
 * create an instance of this fragment.
 */
public class InformationFragment extends Fragment implements FragmentsMVP.View{

    private OnFragmentInteractionListener mListener;

    TextView tv_title, tv_subtitle, tv_description;
    ImageView iv_image;

    RecyclerView recyclerViewTask;
    TasksAdapter tasksAdapter;

    MainMVP.Presenter presenter;

    Subjects subjects;

    public InformationFragment(MainMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        recyclerViewTask = view.findViewById(R.id.recyclerTask);
        recyclerViewTask.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewTask.setLayoutManager(linearLayoutManager);

        tasksAdapter = new TasksAdapter();
        recyclerViewTask.setAdapter(tasksAdapter);

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
        setTaskSubjects(presenter.getTaskSubject(getContext(), subjects.getId()), getContext(), presenter);
    }

    @Override
    public void setSubjects(List<Subjects> subjectsList, Context context, MainMVP.Presenter presenter) {

    }

    @Override
    public void setTaskSubjects(List<Task> taskSubjects, Context context, MainMVP.Presenter presenter) {
        tasksAdapter.dataSet(taskSubjects, taskSubjects.size(), context, presenter);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
