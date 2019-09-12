package com.example.myapplication.com.example.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DataApplication;
import com.example.myapplication.R;
import com.example.myapplication.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TriggerModeActivity extends Activity {
    private Spinner spinner_triggerPir,spinner_triggerSen,spinner_triggerTimeLapse;
    private List<String> list_triggerPir = new ArrayList<String>();
    private List<String> list_triggerSen = new ArrayList<String>();
    private List<String> list_triggerTimeLapse = new ArrayList<String>();
    private Utils utils;
    private String triggerPir,triggetSen,triggerTimelapse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trigger);
        init();
    }

    private void init() {
//        getActionBar().setDisplayHomeAsUpEnabled(true);
        utils = new Utils(this);
        DataApplication dataApplication = DataApplication.getDataApplication();

        triggerPir = dataApplication.getTriggerPir();
        triggetSen = dataApplication.getTriggerSen();
        triggerTimelapse = dataApplication.getTriggerTimelapse();

        spinner_triggerPir = findViewById(R.id.spinner_triggerPir);
        spinner_triggerSen = findViewById(R.id.spinner_triggerSen);
        spinner_triggerTimeLapse = findViewById(R.id.spinner_triggerTimeLapse);




        if(list_triggerSen.isEmpty()){
            //往列表中添加选项
            list_triggerSen.add("off");
            list_triggerSen.add("Low");
            list_triggerSen.add("Auto");
            list_triggerSen.add("High");
        }
        //photo size选择器选择监听
        utils.listSelect(list_triggerSen, spinner_triggerSen,"spinner_triggerSen");

        if(list_triggerPir.isEmpty()){

            //往列表中添加选项
            list_triggerPir.add("0Sec");
            list_triggerPir.add("1Sec");
            list_triggerPir.add("2Sec");
            list_triggerPir.add("3Sec");
            list_triggerPir.add("4Sec");
            list_triggerPir.add("5Sec");
            list_triggerPir.add("10Sec");
            list_triggerPir.add("15Sec");
            list_triggerPir.add("20Sec");
            list_triggerPir.add("25Sec");
            list_triggerPir.add("30Sec");
            list_triggerPir.add("35Sec");
            list_triggerPir.add("40Sec");
            list_triggerPir.add("45Sec");
            list_triggerPir.add("50Sec");
            list_triggerPir.add("55Sec");
            list_triggerPir.add("1min");
            list_triggerPir.add("2min");
            list_triggerPir.add("3min");
            list_triggerPir.add("4min");
            list_triggerPir.add("5min");
            list_triggerPir.add("10min");
            list_triggerPir.add("15min");
            list_triggerPir.add("20min");
            list_triggerPir.add("25min");
            list_triggerPir.add("30min");
            list_triggerPir.add("35min");
            list_triggerPir.add("40min");
            list_triggerPir.add("45min");
            list_triggerPir.add("50min");
            list_triggerPir.add("55min");
            list_triggerPir.add("60min");
        }
        //photo size选择器选择监听
        utils.listSelect(list_triggerPir, spinner_triggerPir,"triggerPir");

        if(list_triggerTimeLapse.isEmpty()){
            //往列表中添加选项
//            list_triggerTimeLapse.add("off");
//            list_triggerTimeLapse.add("0Sec");
//            list_triggerTimeLapse.add("1Sec");
//            list_triggerTimeLapse.add("2Sec");
//            list_triggerTimeLapse.add("3Sec");
//            list_triggerTimeLapse.add("4Sec");
            list_triggerTimeLapse.add("5Sec");
            list_triggerTimeLapse.add("10Sec");
            list_triggerTimeLapse.add("15Sec");
            list_triggerTimeLapse.add("20Sec");
            list_triggerTimeLapse.add("25Sec");
            list_triggerTimeLapse.add("30Sec");
            list_triggerTimeLapse.add("35Sec");
            list_triggerTimeLapse.add("40Sec");
            list_triggerTimeLapse.add("45Sec");
            list_triggerTimeLapse.add("50Sec");
            list_triggerTimeLapse.add("55Sec");
            list_triggerTimeLapse.add("1min");
            list_triggerTimeLapse.add("2min");
            list_triggerTimeLapse.add("3min");
            list_triggerTimeLapse.add("4min");
            list_triggerTimeLapse.add("5min");
            list_triggerTimeLapse.add("10min");
            list_triggerTimeLapse.add("15min");
            list_triggerTimeLapse.add("20min");
            list_triggerTimeLapse.add("25min");
            list_triggerTimeLapse.add("30min");
            list_triggerTimeLapse.add("35min");
            list_triggerTimeLapse.add("40min");
            list_triggerTimeLapse.add("45min");
            list_triggerTimeLapse.add("50min");
            list_triggerTimeLapse.add("55min");
            list_triggerTimeLapse.add("1Hour");
            list_triggerTimeLapse.add("2Hour");
            list_triggerTimeLapse.add("3Hour");
            list_triggerTimeLapse.add("4Hour");
            list_triggerTimeLapse.add("5Hour");
            list_triggerTimeLapse.add("6Hour");
            list_triggerTimeLapse.add("7Hour");
            list_triggerTimeLapse.add("8Hour");
            list_triggerTimeLapse.add("12Hour");
            list_triggerTimeLapse.add("16Hour");
            list_triggerTimeLapse.add("20Hour");
            list_triggerTimeLapse.add("24Hour");
        }
        //photo size选择器选择监听
        utils.listSelect(list_triggerTimeLapse, spinner_triggerTimeLapse,"spinner_triggerTimeLapse");

        utils.setSpinnerDefaultValue(spinner_triggerPir,triggerPir);
        utils.setSpinnerDefaultValue(spinner_triggerSen,triggetSen);
        utils.setSpinnerDefaultValue(spinner_triggerTimeLapse,triggerTimelapse);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                DataApplication dataApplication = DataApplication.getDataApplication();
                Toast.makeText(this,dataApplication.getTriggerPir(),Toast.LENGTH_SHORT).show();
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
