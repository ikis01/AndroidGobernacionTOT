package com.tsg.tot.main;

import android.content.Context;

import com.tsg.tot.data.model.Device;
import com.tsg.tot.data.model.Evaluations;
import com.tsg.tot.data.model.Exercises;
import com.tsg.tot.data.model.Grade;
import com.tsg.tot.data.model.Lessons;
import com.tsg.tot.data.model.Planning;
import com.tsg.tot.data.model.Student;
import com.tsg.tot.data.model.StudyMaterial;
import com.tsg.tot.data.model.Subjects;
import com.tsg.tot.data.model.Submissions;
import com.tsg.tot.data.model.Task;
import com.tsg.tot.data.model.Teacher;
import com.tsg.tot.data.model.Upload;

import java.util.List;

public interface MainMVP {

    interface View {

        void setTextVersion(float version);

    }

    interface Presenter {

        void setView(MainMVP.View view);

        void createDB(Context context);

        void checkVersions(Context context);

        void testPOST(Context context);

    }

    interface Model {

        interface OnFinishedListener {
            void onCheckVersionFinished(float version, Context context);

            void onFailure(Throwable t);
        }

        void createDb(Context context);

        float checkAPIVersion(OnFinishedListener onFinishedListener, Context context);

        float checkDbVersion(OnFinishedListener onFinishedListener, Context context);

        void updateAllDb(float versionList,
                         List<Task> taskList,
                         List<Upload> uploadList,
                         List<Teacher> teacherList,
                         List<Subjects> subjectsList,
                         List<StudyMaterial> studyMaterialList,
                         List<Evaluations> evaluationsList,
                         List<Student> studentList,
                         List<Submissions> submissionsList,
                         List<Exercises> exercisesList,
                         List<Lessons> lessonsList,
                         OnFinishedListener onFinishedListener,
                         Context context);

        //Methods for downloading data from Endpoints

        List<Device> checkDevices(OnFinishedListener onFinishedListener, Context context);

        List<Lessons> checkLessons(OnFinishedListener onFinishedListener, Context context);

        List<Grade> checkGrades(OnFinishedListener onFinishedListener, Context context);

        List<Exercises> checkExercises(OnFinishedListener onFinishedListener, Context context);

        List<Submissions> checkSubmissions(OnFinishedListener onFinishedListener, Context context);

        List<Student> checkStudents(OnFinishedListener onFinishedListener, Context context);

        List<Evaluations> checkEvaluations(OnFinishedListener onFinishedListener, Context context);

        List<StudyMaterial> checkStudyMaterials(OnFinishedListener onFinishedListener, Context context);

        List<Subjects> checkSubjects(OnFinishedListener onFinishedListener, Context context);

        List<Planning> checkPlanning(OnFinishedListener onFinishedListener, Context context);

        List<Teacher> checkTeachers(OnFinishedListener onFinishedListener, Context context);

        List<Upload> checkUploads(OnFinishedListener onFinishedListener, Context context);

        List<Task> checkTasks(OnFinishedListener onFinishedListener, Context context);

        //Methods for POST data on Endpoints

        void postTask(OnFinishedListener onFinishedListener, String uploadId, String subjectId, String name, String code);

        void postEvaluations(OnFinishedListener onFinishedListener, String uploadId, String name, String subjectId);

        void postExercises(OnFinishedListener onFinishedListener, String uploadId, String name, String lessonsId);

        void postSubmissions(OnFinishedListener onFinishedListener, String upp, String exercisesId, String taskId, String evaluationId, String uploadId, String studentId);

        void postBlob(OnFinishedListener onFinishedListener, String code, String file, String upload, String submissionId);
    }

}
