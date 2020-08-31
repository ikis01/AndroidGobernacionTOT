package com.tsg.tot.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tsg.tot.R;
import com.tsg.tot.root.App;

import javax.inject.Inject;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;

public class MainView extends AppCompatActivity implements MainMVP.View {

    @Inject
    MainMVP.Presenter presenter;

    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getComponent().inject(this);

        tv_content = findViewById(R.id.tv_content);

        requestPermissions(new String[]{READ_EXTERNAL_STORAGE, READ_PHONE_STATE}, 1);
        presenter.createDB(this);

        //Test POST methods
        //presenter.testPOST(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.checkVersions(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void setTextVersion(float version) {
        String db_version;
        if (version == 0) {
            db_version = "Verificando versión...";
        } else {
            db_version = getResources().getText(R.string.db_version) + String.valueOf(version);
        }

        this.runOnUiThread(() -> tv_content.setText(db_version));
    }
}
