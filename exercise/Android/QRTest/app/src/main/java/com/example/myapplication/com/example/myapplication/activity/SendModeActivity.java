package com.example.myapplication.com.example.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DataApplication;
import com.example.myapplication.R;

public class SendModeActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendmode);
        DataApplication dataApplication = new DataApplication().getDataApplication();
        Toast.makeText(this,dataApplication.getCameraMode(),Toast.LENGTH_SHORT).show();
    }
}
