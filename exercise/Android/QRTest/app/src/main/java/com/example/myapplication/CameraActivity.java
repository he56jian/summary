package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {
    private String content = "我是传回来的值";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_mode);
        btn1 = (Button) findViewById(R.id.button1);
    }


    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        Intent data = new Intent();
        data.putExtra("data", content);
        setResult(2, data);
        finish();
    }
}
