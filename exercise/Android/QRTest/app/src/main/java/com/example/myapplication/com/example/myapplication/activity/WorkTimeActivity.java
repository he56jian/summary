package com.example.myapplication.com.example.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import com.example.myapplication.R;

public class WorkTimeActivity extends Activity implements CompoundButton.OnCheckedChangeListener{

    private Switch workTimeSwitch1, workTimeSwitch2, workTimeSwitch3, workTimeSwitch4;
    private LinearLayout message_workTime1, message_workTime2, message_workTime3, message_workTime4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worktime);
        getEle();
    }

    private void getEle() {
//        getActionBar().setDisplayHomeAsUpEnabled(true);
        workTimeSwitch1 = findViewById(R.id.switch1);
        workTimeSwitch2 = findViewById(R.id.switch2);
        workTimeSwitch3 = findViewById(R.id.switch3);
        workTimeSwitch4 = findViewById(R.id.switch4);
        message_workTime1 = findViewById(R.id.message_workTime1);
        message_workTime2 = findViewById(R.id.message_workTime2);
        message_workTime3 = findViewById(R.id.message_workTime3);
        message_workTime4 = findViewById(R.id.message_workTime4);

        workTimeSwitch1.setOnCheckedChangeListener(this);
        workTimeSwitch2.setOnCheckedChangeListener(this);
        workTimeSwitch3.setOnCheckedChangeListener(this);
        workTimeSwitch4.setOnCheckedChangeListener(this);

        workTimeSwitch1.setChecked(true);
        workTimeSwitch2.setChecked(true);
        workTimeSwitch3.setChecked(true);
        workTimeSwitch4.setChecked(true);

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.switch1:
                if(!b){
                    message_workTime1.setVisibility(View.INVISIBLE);
                }else{
                    message_workTime1.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.switch2:
                if(!b){
                    message_workTime2.setVisibility(View.INVISIBLE);
                }else{
                    message_workTime2.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.switch3:
                if(!b){
                    message_workTime3.setVisibility(View.INVISIBLE);
                }else{
                    message_workTime3.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.switch4:
                if(!b){
                    message_workTime4.setVisibility(View.INVISIBLE);
                }else{
                    message_workTime4.setVisibility(View.VISIBLE);
                }
                break;

        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}


