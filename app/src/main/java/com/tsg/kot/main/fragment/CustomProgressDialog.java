package com.tsg.kot.main.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.tsg.kot.R;

public class CustomProgressDialog extends ProgressDialog {

    public CustomProgressDialog(Context context,String title ) {
        super(context, R.style.CustomProgressDialog);

        WindowManager.LayoutParams wlmp = getWindow().getAttributes();

        wlmp.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(wlmp);
        setCancelable(false);
        setTitle(title);
        setOnCancelListener(null);
        setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        View view = LayoutInflater.from(context).inflate(
                R.layout.progress_dialog, null);
        setContentView(view);
    }
}