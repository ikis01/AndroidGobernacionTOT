package com.tsg.tot.main.fragment;

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

import com.tsg.tot.R;
import com.tsg.tot.data.model.Subjects;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformationFragment} factory method to
 * create an instance of this fragment.
 */
public class InformationFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    TextView tv_title, tv_subtitle, tv_description;
    ImageView iv_image;

    public InformationFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        tv_title = (TextView) view.findViewById(R.id.title_subject);
        tv_subtitle = (TextView) view.findViewById(R.id.subtitle_subject);
        tv_description = (TextView) view.findViewById(R.id.description_subject);

        iv_image = (ImageView) view.findViewById(R.id.image_subject);

        Bundle objectSubject = getArguments();
        Subjects subjects;

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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}