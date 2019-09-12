package com.example.myapplication.com.example.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
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

import com.example.myapplication.DataApplication;
import com.example.myapplication.R;
import com.example.myapplication.Utils;

import java.util.ArrayList;
import java.util.List;

public class CameraActivity extends Activity {
    private Switch switcher;
    private String cameraMode, photoSize, photoBurst, burstSpeed, sendingOption, shutterSpeed, flashPower, videoSize, videoLength, sInfo;
    private List<String> list_photoSize = new ArrayList<String>();
    private List<String> list_photoBurst = new ArrayList<String>();
    private List<String> list_burstSpeed = new ArrayList<String>();
    private List<String> list_sendingOption = new ArrayList<String>();
    private List<String> list_shutterSpeed = new ArrayList<String>();
    private List<String> list_videoSize = new ArrayList<String>();
    private List<String> list_videoLength = new ArrayList<String>();
    private List<String> list_flashPower = new ArrayList<String>();
    private Spinner spinner_photoSize, spinner_photoBurst, spinner_burstSpeed, spinner_sendingOption, spinner_shutterSpeed, spinner_flashPower, spinner_videoSize, spinner_videoLength;
    //    private Spinner spinner_photoBurst;

    private LinearLayout layoutMain;
    private LayoutInflater inflater;
    private DataApplication dataApplication;
    private Utils utils;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        chooseCameraMode();         //选择当前时video还是photo;
    }

    //    private App_Data app;
    private void init() {
        setContentView(R.layout.camera_mode);
        dataApplication = DataApplication.getDataApplication();
        utils = new Utils(this);

        cameraMode = dataApplication.getCameraMode();
        photoBurst = dataApplication.getPhotoBurst();
        photoSize = dataApplication.getPhotoSize();
        burstSpeed = dataApplication.getBurstSpeed();
        sendingOption = dataApplication.getSendingOption();
        shutterSpeed = burstSpeed = dataApplication.getShutterSpeed();
        flashPower = dataApplication.getFlashPower();
        videoSize = dataApplication.getVideoSize();
        videoLength = dataApplication.getVideoLength();

    }

    private RadioGroup rg;
    private RadioButton rb_photo, rb_video;

    //在cameraMode里面进行的选择
    private void chooseCameraMode() {
        layoutMain = findViewById(R.id.camera_mode);
        inflater = getLayoutInflater();
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
                        LinearLayout myFirst = inflater.inflate(
                                R.layout.camera_video, null).findViewById(R.id.camera_video);
                        layoutMain.removeAllViews();
                        layoutMain.addView(myFirst);
                        dataApplication.setCameraMode("video");
                        init_video();
//                        cameraMode = "video";
                        break;
                    case R.id.rb_photo:
                        myFirst = (LinearLayout) inflater.inflate(
                                R.layout.camera_photo, null).findViewById(R.id.camera_photo);
                        layoutMain.removeAllViews();
                        layoutMain.addView(myFirst);
                        dataApplication.setCameraMode("photo");
                        init_photo();
//                        cameraMode="photo";
                        break;
                }
            }
        });
        if(dataApplication.getCameraMode() == "photo"){
            rb_photo.setChecked(true);
        }else{
            rb_video.setChecked(true);
        }

    }

    ////选择video的时候的初始化操作
    private void init_video() {
        spinner_videoSize = findViewById(R.id.spinner_videoSize);
        spinner_videoLength = findViewById(R.id.spinner_videoLength);
        if (list_videoSize.isEmpty()) {
            list_videoSize.add("1440P");
            list_videoSize.add("1080P");
            list_videoSize.add("720P");
            list_videoSize.add("WVGA");
        }
        //photo size选择器选择监听
        utils.listSelect(list_videoSize, spinner_videoSize, "videoSize");

        if (list_videoLength.isEmpty()) {
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
        utils.listSelect(list_videoLength, spinner_videoLength, "videoLength");

        utils.setSpinnerDefaultValue(spinner_videoSize,videoSize);
        utils.setSpinnerDefaultValue(spinner_videoLength,videoLength);

    }

    //选择图片的时候的初始化操作
    private void init_photo() {
        spinner_photoSize = findViewById(R.id.spinner_photoSize);
        spinner_photoBurst = findViewById(R.id.spinner_photoBurst);
        spinner_burstSpeed = findViewById(R.id.spinner_burstSpeed);
        spinner_shutterSpeed = findViewById(R.id.spinner_shutterSpeed);
        spinner_flashPower = findViewById(R.id.spinner_flashPower);
        spinner_sendingOption = findViewById(R.id.spinner_sendingOption);

        if (list_photoSize.isEmpty()) {
            //往列表中添加选项
            list_photoSize.add("3MP");
            list_photoSize.add("5MP");
            list_photoSize.add("8MP");
//            list_photoSize.add("12MP");
//            list_photoSize.add("16Mp");
//            list_photoSize.add("20MP");
        }

        //photo size选择器选择监听
        utils.listSelect(list_photoSize, spinner_photoSize, "photoSize");

        //往列表中添加选项
        if (list_photoBurst.isEmpty()) {
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
        utils.listSelect(list_photoBurst, spinner_photoBurst, "photoBurst");

        if (list_burstSpeed.isEmpty()) {
            list_burstSpeed.add("Fast(200ms)");
            list_burstSpeed.add("Show(500ms)");
        }
        utils.listSelect(list_burstSpeed, spinner_burstSpeed, "burstSpeed");

        if (list_sendingOption.isEmpty()) {
            list_sendingOption.add("1st");
            list_sendingOption.add("2st");
            list_sendingOption.add("3st");
            list_sendingOption.add("4st");
            list_sendingOption.add("5st");
            list_sendingOption.add("6st");
            list_sendingOption.add("7st");
            list_sendingOption.add("8st");
            list_sendingOption.add("9st");
            list_sendingOption.add("10st");
        }
        utils.listSelect(list_sendingOption, spinner_sendingOption, "sendingOption");
        if (list_shutterSpeed.isEmpty()) {
            list_shutterSpeed.add("Normal");
            list_shutterSpeed.add("Fast");
            list_shutterSpeed.add("High");
        }
        utils.listSelect(list_shutterSpeed, spinner_shutterSpeed, "shutterSpeed");

        if (list_flashPower.isEmpty()) {
            list_flashPower.add("Low");
            list_flashPower.add("Normal");
            list_flashPower.add("High");
        }
        utils.listSelect(list_flashPower, spinner_flashPower, "flashPower");

        utils.setSpinnerDefaultValue(spinner_photoSize,photoSize);
        utils.setSpinnerDefaultValue(spinner_photoBurst,photoBurst);
        utils.setSpinnerDefaultValue(spinner_burstSpeed,burstSpeed);
        utils.setSpinnerDefaultValue(spinner_shutterSpeed,shutterSpeed);
        utils.setSpinnerDefaultValue(spinner_flashPower,flashPower);
        utils.setSpinnerDefaultValue(spinner_sendingOption,sendingOption);

    }




}
