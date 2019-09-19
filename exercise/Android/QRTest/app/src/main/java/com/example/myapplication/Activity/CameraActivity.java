package com.example.myapplication.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.myapplication.Database.DataApplication;
import com.example.myapplication.R;
import com.example.myapplication.BaseMethods.Utils;

import java.util.ArrayList;
import java.util.List;

public class CameraActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private String cameraMode, photoSize, photoBurst, burstSpeed, sendingOption, shutterSpeed, flashPower, videoSize, videoLength;
    private List<String> list_photoSize = new ArrayList<String>();
    private List<String> list_photoBurst = new ArrayList<String>();
    private List<String> list_burstSpeed = new ArrayList<String>();
    private List<String> list_sendingOption = new ArrayList<String>();
    private List<String> list_shutterSpeed = new ArrayList<String>();
    private List<String> list_videoSize = new ArrayList<String>();
    private List<String> list_videoLength = new ArrayList<String>();
    private List<String> list_flashPower = new ArrayList<String>();
    private Spinner spinner_photoSize, spinner_photoBurst, spinner_burstSpeed, spinner_sendingOption, spinner_shutterSpeed, spinner_flashPower, spinner_videoSize, spinner_videoLength;
    private Button btnCameraSave;
    private LinearLayout layoutMain;
    private LayoutInflater inflater;
    private DataApplication dataApplication;
    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(); //初始化数据
        getParam();             //获取参数
        chooseCameraMode();         //选择当前时video还是photo;
    }

    /**
     * 初始化数据
     */
    private void init() {
        setContentView(R.layout.camera_mode);
        dataApplication = DataApplication.getDataApplication();
        utils = new Utils(this);
        btnCameraSave = findViewById(R.id.button_camera_save);
    }

    /**
     * 获取参数
     */
    private void getParam() {
        cameraMode = dataApplication.getCameraMode();
        cameraModeIndex = dataApplication.getCameraModeIndex();
        photoBurst = dataApplication.getPhotoBurst();
        photoBurstIndex = dataApplication.getPhotoBurstIndex();
        photoSize = dataApplication.getPhotoSize();
        photoSizeIndex = dataApplication.getPhotoSizeIndex();
        burstSpeed = dataApplication.getBurstSpeed();
        burstSpeedIndex = dataApplication.getBurstSpeedIndex();
        sendingOption = dataApplication.getSendingOption();
        sendingOptionIndex = dataApplication.getSendingOptionIndex();
        shutterSpeed = dataApplication.getShutterSpeed();
        shutterSpeedIndex = dataApplication.getShutterSpeedIndex();
        flashPower = dataApplication.getFlashPower();
        flashPowerIndex = dataApplication.getFlashPowerIndex();
        videoSize = dataApplication.getVideoSize();
        videoSizeIndex = dataApplication.getVideoSizeIndex();
        videoLength = dataApplication.getVideoLength();
        videoLengthIndex = dataApplication.getVideoLengthIndex();

        btnCameraSave.setOnClickListener(this);
    }

    private RadioGroup rg;
    private RadioButton rb_photo, rb_video;

    //在cameraMode里面进行的选择
    private void chooseCameraMode() {
        layoutMain = findViewById(R.id.camera_mode);
        inflater = getLayoutInflater();
        rg =  findViewById(R.id.rg_sex);
        rb_photo = findViewById(R.id.rb_photo);
        rb_video =  findViewById(R.id.rb_video);

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
                        break;
                    case R.id.rb_photo:
                        myFirst = (LinearLayout) inflater.inflate(
                                R.layout.camera_photo, null).findViewById(R.id.camera_photo);
                        layoutMain.removeAllViews();
                        layoutMain.addView(myFirst);
                        dataApplication.setCameraMode("photo");
                        init_photo();
                        break;
                }
            }
        });
        if(cameraMode == "photo"){
            rb_photo.setChecked(true);
        }else{
            rb_video.setChecked(true);
        }
    }

    /**
     * 选择为video时的操作
     */
    private void init_video() {
        spinner_videoSize = (Spinner) findViewById(R.id.spinner_videoSize);
        spinner_videoLength = findViewById(R.id.spinner_videoLength);

//        //设置选择器，并设置监听
//        ArrayAdapter<String> video_SizeAdapter = new ArrayAdapter<>(
//                this, android.R.layout.simple_list_item_1, dataApplication.getVideoSizeList());
        spinner_videoSize.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataApplication.getVideoSizeList()));
        spinner_videoSize.setOnItemSelectedListener(this);

        spinner_videoLength.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataApplication.getVideoLengthList()));
        spinner_videoLength.setOnItemSelectedListener(this);

        spinner_videoSize.setSelection(videoSizeIndex);
        spinner_videoLength.setSelection(videoLengthIndex);
        cameraModeIndex = 1;
    }

    /**
     * 上一个版本
     */
//    private void init_video(){
////        if (list_videoSize.isEmpty()) {
////            list_videoSize.add("D1");
////            list_videoSize.add("720P");
////            list_videoSize.add("1080P");
////            list_videoSize.add("1440P");
////        }
////        //photo size选择器选择监听
////        utils.listSelect(list_videoSize, spinner_videoSize, "videoSize");
//
////        if (list_videoLength.isEmpty()) {
////            list_videoLength.add("5sec");
////            list_videoLength.add("10sec");
////            list_videoLength.add("15sec");
////            list_videoLength.add("20sec");
////            list_videoLength.add("25sec");
////            list_videoLength.add("30sec");
////            list_videoLength.add("35sec");
////            list_videoLength.add("40sec");
////            list_videoLength.add("45sec");
////            list_videoLength.add("50sec");
////            list_videoLength.add("55sec");
////            list_videoLength.add("60sec");
////        }
////        //photo size选择器选择监听
//////        utils.listSelect(list_videoLength, spinner_videoLength, "videoLength");
////        utils.setSpinnerDefaultValue(spinner_videoSize,videoSize);
////        utils.setSpinnerDefaultValue(spinner_videoLength,videoLength);
//    }
//当前选择的项目

    /**
     * 选择为photo时的操作
     */
    private void init_photo() {
        spinner_photoSize = findViewById(R.id.spinner_photoSize);
        spinner_photoSize.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataApplication.getPhotoSizeList()));
        spinner_photoSize.setOnItemSelectedListener(this);
        spinner_photoSize.setSelection(photoSizeIndex);

        spinner_photoBurst = findViewById(R.id.spinner_photoBurst);
        spinner_photoBurst.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataApplication.getPhotoBurstList()));
        spinner_photoBurst.setOnItemSelectedListener(this);
        spinner_photoBurst.setSelection(photoBurstIndex);


        spinner_burstSpeed = findViewById(R.id.spinner_burstSpeed);
        spinner_burstSpeed.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataApplication.getBurstSpeedList()));
        spinner_burstSpeed.setOnItemSelectedListener(this);
        spinner_burstSpeed.setSelection(burstSpeedIndex);


        spinner_shutterSpeed = findViewById(R.id.spinner_shutterSpeed);
        spinner_shutterSpeed.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataApplication.getShutterSpeedList()));
        spinner_shutterSpeed.setOnItemSelectedListener(this);
        spinner_shutterSpeed.setSelection(shutterSpeedIndex);

        spinner_flashPower = findViewById(R.id.spinner_flashPower);
        spinner_flashPower.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataApplication.getFlashPowerList()));
        spinner_flashPower.setOnItemSelectedListener(this);
        spinner_flashPower.setSelection(flashPowerIndex);

        spinner_sendingOption = findViewById(R.id.spinner_sendingOption);
        spinner_sendingOption.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataApplication.getSendingOptionList()));
        spinner_sendingOption.setOnItemSelectedListener(this);
        spinner_sendingOption.setSelection(sendingOptionIndex);

        cameraModeIndex = 0;
    }
    /**
     * 上一个版本
     */
//    private void init_photo() {
//        spinner_photoSize = findViewById(R.id.spinner_photoSize);
//        spinner_photoBurst = findViewById(R.id.spinner_photoBurst);
//        spinner_burstSpeed = findViewById(R.id.spinner_burstSpeed);
//        spinner_shutterSpeed = findViewById(R.id.spinner_shutterSpeed);
//        spinner_flashPower = findViewById(R.id.spinner_flashPower);
//        spinner_sendingOption = findViewById(R.id.spinner_sendingOption);
//
//        if (list_photoSize.isEmpty()) {
//            //往列表中添加选项
//            list_photoSize.add("1080P");
//            list_photoSize.add("3MP");
//            list_photoSize.add("5MP");
////            list_photoSize.add("12MP");
////            list_photoSize.add("16Mp");
////            list_photoSize.add("20MP");
//        }
//        //photo size选择器选择监听
//        utils.listSelect(list_photoSize, spinner_photoSize, "photoSize");
//
//        //往列表中添加选项
//        if (list_photoBurst.isEmpty()) {
//            list_photoBurst.add("1photo");
//            list_photoBurst.add("2photos");
//            list_photoBurst.add("3photos");
//            list_photoBurst.add("4photos");
//            list_photoBurst.add("5photos");
//            list_photoBurst.add("6photos");
//            list_photoBurst.add("7photos");
//            list_photoBurst.add("8photos");
//            list_photoBurst.add("9photos");
//            list_photoBurst.add("10photos");
//        }
//        //photo burst选择器选择监听
//        utils.listSelect(list_photoBurst, spinner_photoBurst, "photoBurst");
//
//        if (list_burstSpeed.isEmpty()) {
//            list_burstSpeed.add("Fast(200ms)");
//            list_burstSpeed.add("Show(500ms)");
//        }
//        utils.listSelect(list_burstSpeed, spinner_burstSpeed, "burstSpeed");
//
//        if (list_sendingOption.isEmpty()) {
//            list_sendingOption.add("1st");
//            list_sendingOption.add("2st");
//            list_sendingOption.add("3st");
//            list_sendingOption.add("4st");
//            list_sendingOption.add("5st");
//            list_sendingOption.add("6st");
//            list_sendingOption.add("7st");
//            list_sendingOption.add("8st");
//            list_sendingOption.add("9st");
//            list_sendingOption.add("10st");
//        }
//        utils.listSelect(list_sendingOption, spinner_sendingOption, "sendingOption");
//        if (list_shutterSpeed.isEmpty()) {
//            list_shutterSpeed.add("Normal");
//            list_shutterSpeed.add("Fast");
//            list_shutterSpeed.add("High");
//        }
//        utils.listSelect(list_shutterSpeed, spinner_shutterSpeed, "shutterSpeed");
//
//        if (list_flashPower.isEmpty()) {
//            list_flashPower.add("Low");
//            list_flashPower.add("Normal");
//            list_flashPower.add("High");
//        }
//        utils.listSelect(list_flashPower, spinner_flashPower, "flashPower");
//
//        utils.setSpinnerDefaultValue(spinner_photoSize,photoSize);
//        utils.setSpinnerDefaultValue(spinner_photoBurst,photoBurst);
//        utils.setSpinnerDefaultValue(spinner_burstSpeed,burstSpeed);
//        utils.setSpinnerDefaultValue(spinner_shutterSpeed,shutterSpeed);
//        utils.setSpinnerDefaultValue(spinner_flashPower,flashPower);
//        utils.setSpinnerDefaultValue(spinner_sendingOption,sendingOption);
//
//    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_camera_save:
                dataApplication.setCameraModeIndex(cameraModeIndex);
                dataApplication.setVideoSize(videoSize);
                dataApplication.setVideoSizeIndex(videoSizeIndex);
                dataApplication.setVideoLength(videoLength);
                dataApplication.setVideoLengthIndex(videoLengthIndex);
                dataApplication.setPhotoSize(photoSize);
                dataApplication.setPhotoSizeIndex(photoSizeIndex);
                dataApplication.setPhotoBurst(photoBurst);
                dataApplication.setPhotoBurstIndex(photoBurstIndex);
                dataApplication.setBurstSpeed(burstSpeed);
                dataApplication.setBurstSpeedIndex(burstSpeedIndex);
                dataApplication.setSendingOption(sendingOption);
                dataApplication.setSendingOptionIndex(sendingOptionIndex);
                dataApplication.setShutterSpeed(shutterSpeed);
                dataApplication.setShutterSpeedIndex(shutterSpeedIndex);
                dataApplication.setFlashPower(flashPower);
                dataApplication.setFlashPowerIndex(flashPowerIndex);
                break;
        }
    }
    private int cameraModeIndex,videoSizeIndex,videoLengthIndex,photoSizeIndex,photoBurstIndex,burstSpeedIndex,sendingOptionIndex,shutterSpeedIndex,flashPowerIndex;
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String content = adapterView.getItemAtPosition(i).toString();
        switch (adapterView.getId()){
            case R.id.spinner_videoSize:
                videoSize = content;
                videoSizeIndex = i;
                break;
            case R.id.spinner_videoLength:
                videoLength = content;
                videoLengthIndex = i;
                break;
            case R.id.spinner_burstSpeed:
                burstSpeed = content;
                burstSpeedIndex = i;
                break;
            case R.id.spinner_shutterSpeed:
                shutterSpeed = content;
                shutterSpeedIndex = i;
                break;
            case R.id.spinner_flashPower:
                flashPower = content;
                flashPowerIndex = i;
                break;
            case R.id.spinner_photoBurst:
                photoBurst = content;
                photoBurstIndex = i;
                break;
            case R.id.spinner_photoSize:
                photoSize = content;
                photoSizeIndex = i;
                break;
            case R.id.spinner_sendingOption:
                sendingOption = content;
                sendingOptionIndex = i;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
