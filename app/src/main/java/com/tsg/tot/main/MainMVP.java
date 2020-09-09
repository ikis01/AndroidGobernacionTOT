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

        void setInfoStudent(List<Student> studentList);

        void setInfoSubject(Subjects subjects);

        void notifyRefresh();
    }

    interface Presenter {

        void setView(MainMVP.View view);

        void createDB(Context context);

        void checkVersions(Context context);

        List<Subjects> getSubjects(Context context);

        void setInfoStudent(Context context);

        void setInfoSubject(Subjects subjects);

        void notifyRefresh();

        void testPOST(Context context);

    }

    interface Model {

        interface OnFinishedListener {
            void onFailure(Throwable t);
        }

        void createDb(Context context);

        float checkAPIVersion(Context context);

        float checkDbVersion(Context context);

        void updateAllDb(float versionList,
                         List<Task> taskList,
                         List<Upload> uploadList,
                         List<Teacher> teacherList,
                         List<Subjects> subjectsList,
                         List<Grade> gradeList,
                         List<StudyMaterial> studyMaterialList,
                         List<Evaluations> evaluationsList,
                         List<Student> studentList,
                         List<Submissions> submissionsList,
                         List<Exercises> exercisesList,
                         List<Lessons> lessonsList,
                         Context context);

        //Methods for downloading data from Endpoints

        List<Device> checkDevices(Context context, int from);

        List<Lessons> checkLessons(Context context, int from);

        List<Grade> checkGrades(Context context, int from);

        List<Exercises> checkExercises(Context context, int from);

        List<Submissions> checkSubmissions(Context context, int from);

        List<Student> checkStudents(Context context, int from);

        List<Evaluations> checkEvaluations(Context context, int from);

        List<StudyMaterial> checkStudyMaterials(Context context, int from);

        List<Subjects> checkSubjects(Context context, int from);

        List<Planning> checkPlanning(Context context, int from);

        List<Teacher> checkTeachers(Context context, int from);

        List<Upload> checkUploads(Context context, int from);

        List<Task> checkTasks(Context context, int from);

        //Methods for POST data on Endpoints

        void postTask(String uploadId, String subjectId, String name, String code);

        void postEvaluations(String uploadId, String name, String subjectId);

        void postExercises(String uploadId, String name, String lessonsId);

        void postSubmissions(String upp, String exercisesId, String taskId, String evaluationId, String uploadId, String studentId);

        void postBlob(String code, String file, String upload, String submissionId);
    }

}
