package com.example.myapplication.com.example.myapplication.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import com.example.myapplication.App_Data;
import com.example.myapplication.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class CameraActivity extends Activity {
    private Switch switcher;
    private String cameraMode,photoSize,photoBurst,burstSpeed,sendingOption,shutterSpeed,flashPower,videoSize,videoLength,sInfo;
    private List<String> list_photoSize = new ArrayList<String>();
    private List<String> list_photoBurst = new ArrayList<String>();
    private List<String> list_burstSpeed = new ArrayList<String>();
    private List<String> list_sendingOption = new ArrayList<String>();
    private List<String> list_shutterSpeed = new ArrayList<String>();
    private List<String> list_videoSize = new ArrayList<String>();
    private List<String> list_videoLength = new ArrayList<String>();
    private List<String> list_flashPower = new ArrayList<String>();
    private Spinner spinner_photoSize, spinner_photoBurst, spinner_burstSpeed, spinner_sendingOption, spinner_shutterSpeed, spinner_flashPower,spinner_videoSize,spinner_videoLength;
    //    private Spinner spinner_photoBurst;
    private ArrayAdapter<String> adapter;
    private  LinearLayout layoutMain;
    private LayoutInflater inflater;
    private App_Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bundle bundle = this.getIntent().getExtras();
//        data = (Data) bundle.getSerializable("data");
        init();
        chooseCameraMode();         //选择当前时video还是photo;
    }
//    private App_Data app;
    private void init() {
//        app = (App_Data) getApplication();
        setContentView(R.layout.camera_mode);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Toast.makeText(this,data.getCameraMode(),Toast.LENGTH_SHORT).show();
    }

    private RadioGroup rg;
    private RadioButton rb_photo, rb_video;
    //在cameraMode里面进行的选择
    private void chooseCameraMode() {
        layoutMain =  findViewById(R.id.camera_mode);
        inflater=getLayoutInflater();
        rg = (RadioGroup) findViewById(R.id.rg_sex);
        rb_photo = findViewById(R.id.rb_photo);
        rb_video = (RadioButton) findViewById(R.id.rb_video);
        //注意是给RadioGroup绑定监视器
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 选中状态改变时被触发
                switch (checkedId) {
                    case R.id.rb_video:
                        LinearLayout  myFirst = (LinearLayout) inflater.inflate(
                                R.layout.camera_video, null).findViewById(R.id.camera_video);
                        layoutMain.removeAllViews();
                        layoutMain.addView(myFirst);
//                        app.setCameraMode("video");
                        init_video();
                        cameraMode = "video";
                        break;
                    case R.id.rb_photo:
                        myFirst = (LinearLayout) inflater.inflate(
                                R.layout.camera_photo, null).findViewById(R.id.camera_photo);
                        layoutMain.removeAllViews();
                        layoutMain.addView(myFirst);
                        init_photo();
//                        app.setCameraMode("photo");
                        cameraMode="photo";
                        break;
                }
            }
        });
    }

    ////选择video的时候的初始化操作
    private void init_video() {
        spinner_videoSize = findViewById(R.id.spinner_videoSize);
        spinner_videoLength = findViewById(R.id.spinner_videoLength);
        if(list_videoSize.isEmpty()){
            list_videoSize.add("1080P");
            list_videoSize.add("720P");
            list_videoSize.add("WVGA");
        }
        //photo size选择器选择监听
        listSelect(list_videoSize, spinner_videoSize,"videoSize");

        if(list_videoLength.isEmpty()){
            list_videoLength.add("5sec");
            list_videoLength.add("10sec");
            list_videoLength.add("15sec");
            list_videoLength.add("20sec");
            list_videoLength.add("25sec");
            list_videoLength.add("30sec");
            list_videoLength.add("35sec");
            list_videoLength.add("40sec");
            list_videoLength.add("45sec");
            list_videoLength.add("50sec");
            list_videoLength.add("55sec");
            list_videoLength.add("60sec");
        }

        //photo size选择器选择监听
        listSelect(list_videoLength, spinner_videoLength,"videoLength");

    }

    //选择图片的时候的初始化操作
    private void init_photo() {
//        list_photoSize = null;
//        list_sendingOption = null;
//        list_shutterSpeed = null;
//        list_flashPower = null;
//        list_photoBurst = null;
//        list_burstSpeed = null;


        spinner_photoSize = findViewById(R.id.spinner_photoSize);
        spinner_photoBurst = findViewById(R.id.spinner_photoBurst);
        spinner_burstSpeed = findViewById(R.id.spinner_burstSpeed);

        if(list_photoSize.isEmpty()){
            //往列表中添加选项
            list_photoSize.add("3MP");
            list_photoSize.add("5MP");
            list_photoSize.add("8MP");
            list_photoSize.add("12MP");
            list_photoSize.add("16Mp");
            list_photoSize.add("20MP");
        }

        //photo size选择器选择监听
        listSelect(list_photoSize, spinner_photoSize,"photoSize");

        //往列表中添加选项
        if(list_photoBurst.isEmpty()){
            list_photoBurst.add("1photo");
            list_photoBurst.add("2photos");
            list_photoBurst.add("3photos");
            list_photoBurst.add("4photos");
            list_photoBurst.add("5photos");
            list_photoBurst.add("6photos");
            list_photoBurst.add("7photos");
            list_photoBurst.add("8photos");
            list_photoBurst.add("9photos");
            list_photoBurst.add("10photos");
        }
        //photo burst选择器选择监听
        listSelect(list_photoBurst, spinner_photoBurst,"photoBurst");


        if(list_burstSpeed.isEmpty()){
            list_burstSpeed.add("Fast(200ms)");
            list_burstSpeed.add("Show(500ms)");}
        listSelect(list_burstSpeed, spinner_burstSpeed,"burstSpeed");

        spinner_sendingOption = findViewById(R.id.spinner_sendingOption);

        if(list_sendingOption.isEmpty()){
            list_sendingOption.add("1photo");
            list_sendingOption.add("2photos");
            list_sendingOption.add("3photos");
            list_sendingOption.add("4photos");
            list_sendingOption.add("5photos");
            list_sendingOption.add("6photos");
            list_sendingOption.add("7photos");
            list_sendingOption.add("8photos");
            list_sendingOption.add("9photos");
            list_sendingOption.add("10photos");}
        listSelect(list_sendingOption, spinner_sendingOption,"sendingOption");

        spinner_shutterSpeed = findViewById(R.id.spinner_shutterSpeed);
        if(list_shutterSpeed.isEmpty()){
            list_shutterSpeed.add("Normal");
            list_shutterSpeed.add("Fast");}
        listSelect(list_shutterSpeed, spinner_shutterSpeed,"shutterSpeed");

        spinner_flashPower = findViewById(R.id.spinner_flashPower);
        if(list_flashPower.isEmpty()){
            list_flashPower.add("Low");
            list_flashPower.add("Normal");
            list_flashPower.add("High");}
        listSelect(list_flashPower, spinner_flashPower,"flashPower");
    }

    //下拉列表的选择
    public void listSelect(final List<String> list_ele, Spinner spinnerEle, final String name) {
        //第二步：为下拉列表定义一个适配器
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_ele);
        //第三步：设置下拉列表下拉时的菜单样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        spinnerEle.setAdapter(adapter);
        //第五步：添加监听器，为下拉列表设置事件的响应
        spinnerEle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //获取选择的项的值
                sInfo = adapterView.getItemAtPosition(i).toString();
//                    app.setData(name,sInfo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //获取选择的项的值
                sInfo = adapterView.getItemAtPosition(0).toString();
//                    data.setData(name,sInfo);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                if(cameraMode == "video"){
//                    Toast.makeText(this,,Toast.LENGTH_SHORT).show();
                    //当前选择为video
                    System.out.println(data.getData("videoLength"));
                }else{
                    //当前选择为photo
                    Toast.makeText(this,data.toString(),Toast.LENGTH_SHORT).show();
                }
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
