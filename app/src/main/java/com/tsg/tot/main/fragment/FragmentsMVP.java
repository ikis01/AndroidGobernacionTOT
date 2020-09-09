package com.tsg.tot.main.fragment;

import android.content.Context;

import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.main.mainmvp.MainMVP;

import java.util.List;

public interface FragmentsMVP {

    interface View {
        void setSubjects(List<Subjects> subjectsList, Context context, MainMVP.Presenter presenter);
    }
}
