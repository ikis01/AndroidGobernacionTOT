package com.tsg.tot.main.fragment;

import android.content.Context;

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
import com.tsg.tot.task.taskmvp.TaskMVP;

import java.util.List;

public interface FragmentsMVP {

    interface View {
        void setSubjects(List<Subjects> subjectsList, Context context, MainMVP.Presenter presenter);

        void setTaskSubjects(List<Task> taskSubjects, Context context, MainMVP.Presenter presenter);
        void setFileKiosco(List<FilesKiosco> filesKioscoList, Context context, TaskMVP.Presenter presenter);
        void setLessons(List<Lessons> lessonsList, Context context, TaskMVP.Presenter presenter);
        void setStudyMaterials(List<StudyMaterialRemote> studyMaterialRemoteList, Context context, TaskMVP.Presenter presenter);
        void setSubmissionDisplay(List<SubmissionDisplay> submissionDisplayList,Context context,TaskMVP.Presenter presenter);
        void setInformationMessage(List<MessageRemote> messageRemoteList,Context context ,MainMVP.Presenter presenter);
        void setFilesMessage (List<FileMessageRemote> fileMessageRemoteList,Context context,MainMVP.Presenter presenter);
        void setAnswerMessage  (List<MessageAnswer> fileMessageRemoteList, Context context, MainMVP.Presenter presenter);
    }
}
