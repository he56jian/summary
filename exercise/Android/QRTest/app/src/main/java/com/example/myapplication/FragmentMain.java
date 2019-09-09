package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.myapplication.com.example.myapplication.activity.ChatManager;

public class FragmentMain extends FragmentActivity implements View.OnClickListener{

    Button btn_parcam,btn_connect,btn_other;
    FrameLayout show_des;
    ParamFragment paramFragment;
    ConnectFragment con;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        ParamFragment paramFragment = new ParamFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.show_des, paramFragment).commit();
        ChatManager.getCM().connect(this,"192.168.0.104",22567);
        ChatManager.getCM().send("#02#");
//        ChatManager.getCM().connect("192.168.1.224", 5001);
//        ChatManager.getCM().send("#02#");

        show_des = findViewById(R.id.show_des);
        btn_parcam = findViewById(R.id.btn_parcam);
        btn_connect = findViewById(R.id.btn_connect);
        btn_other = findViewById(R.id.btn_other);
        btn_parcam.setOnClickListener(this);
        btn_connect.setOnClickListener(this);
        btn_other.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_parcam:
                ParamFragment paramFragment = new ParamFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.show_des, paramFragment).commit();
                break;
            case R.id.btn_connect:
                ConnectFragment con = new ConnectFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.show_des, con).commit();
                break;
            case R.id.btn_other:
                break;

        }
    }
}
