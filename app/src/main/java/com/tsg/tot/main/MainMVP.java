package com.tsg.tot.main;

import android.content.Context;

import com.tsg.tot.data.model.Version;

import java.util.List;

public interface MainMVP {

    interface View {
        /**
         * Show list of info
         *
         * @param version version
         */
        void setContent(float version);
    }

    interface Presenter {

        void setView(MainMVP.View view);

        void checkVersions(Context context);

        void sendBlob();

        void createDB(Context context);
    }

    interface Model {

        interface OnFinishedListener {
            void onFinished(float version, Context context);

            void onFailure(Throwable t);
        }

        /**
         * Check API version
         */
        float checkAPIVersion(OnFinishedListener onFinishedListener, Context context);

        /**
         * Check DB version
         */
        float checkDbVersion(OnFinishedListener onFinishedListener, Context context);

        /**
         * Send Blob info
         */
        void sendBlob(OnFinishedListener onFinishedListener);

        /**
         * Create db on start aplication
         */
        void createDb(Context context);

        void setDbVersion(List<Version> versionList, Context context);
    }

}
