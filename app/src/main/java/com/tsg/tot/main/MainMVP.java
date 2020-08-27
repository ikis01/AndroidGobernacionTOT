package com.tsg.tot.main;

import com.tsg.tot.data.model.Version;

import java.util.List;

public interface MainMVP {

    interface View {
        /**
         * Show list of info
         *
         * @param versionList version
         */
        void setContent(List<Version> versionList);
    }

    interface Presenter {
        /**
         * Swet view of presenter
         *
         * @param view view
         */
        void setView(MainMVP.View view);

        /**
         * Notify that the button was pressed
         */
        void mainButtonClicked();

        void sendBlob();
    }

    interface Model {

        interface OnFinishedListener {
            void onFinished(List<Version> versionList);

            void onFailure(Throwable t);
        }

        /**
         * Check API info
         */
        void checkInfo(OnFinishedListener onFinishedListener);

        /**
         * Send Blob info
         */
        void sendBlob(OnFinishedListener onFinishedListener);
    }

}
