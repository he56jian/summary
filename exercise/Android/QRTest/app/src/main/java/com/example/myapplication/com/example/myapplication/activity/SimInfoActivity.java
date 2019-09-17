package com.example.myapplication.com.example.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.myapplication.DataApplication;
import com.example.myapplication.R;

public class SimInfoActivity extends Activity implements View.OnClickListener {

    String sim_apn, sim_acount, sim_passwd;
    String data_sim_apn, data_sim_acount, data_sim_passwd;
    EditText edit_sim_apn, edit_sim_acount, edit_sim_passwd;
    DataApplication dataApplication = DataApplication.getDataApplication();
    Button btn_save;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sim_info);

        data_sim_apn = dataApplication.getSim_apn();
        data_sim_acount = dataApplication.getSim_acount();
        data_sim_passwd = dataApplication.getSim_passwd();

        edit_sim_apn = findViewById(R.id.edit_sim_apn);
        edit_sim_acount = findViewById(R.id.edit_sim_acount);
        edit_sim_passwd = findViewById(R.id.edit_sim_passwd);

        edit_sim_apn.setText(data_sim_apn);
        edit_sim_acount.setText(data_sim_acount);
        edit_sim_passwd.setText(data_sim_passwd);

        btn_save = findViewById(R.id.btn_sim_save);
        btn_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sim_save:

                sim_apn = edit_sim_apn.getText() + "";
                sim_acount = edit_sim_acount.getText() + "";
                sim_passwd = edit_sim_passwd.getText() + "";

                dataApplication.setSim_apn(sim_apn);
                dataApplication.setSim_acount(sim_acount);
                dataApplication.setSim_passwd(sim_passwd);
                break;
        }
    }

}
