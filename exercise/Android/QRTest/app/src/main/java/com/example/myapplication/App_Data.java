package com.example.myapplication;

import android.app.Application;

import org.json.JSONException;
import org.json.JSONObject;


public class App_Data extends Application {
    private String data = "";
    private String status = "default";
    private static String cameraMode, photoSize, photoBurst, burstSpeed, sendingOption, shutterSpeed, flashPower,
            videoSize, videoLength, triggerPir, triggerTimelapse, workTime1, workTime2, workTime3,
            workTime4, sendMode, remoteControl, rename, overwrite;

    @Override
    public void onCreate() {
        super.onCreate();
//        init();
    }

//    public void init() {
//        if (status == "default") {
//            this.cameraMode = "photo";
//            this.photoBurst = "1photo";
//            this.photoSize = "3mp";
//            this.burstSpeed = "Fast(200ms)";
//            this.sendingOption = "1 photo";
//            this.shutterSpeed = "Normal";
//            this.flashPower = "low";
//            this.videoSize = "wvga";
//            this.videoLength = "5sec";
//            this.triggerPir = "30sec";
//            this.triggerTimelapse = "5min";
//            this.workTime1 = "off";
//            this.workTime3 = "off";
//            this.workTime4 = "off";
//            this.workTime2 = "off";
//            this.sendMode = "3mp";
//            this.remoteControl = "0.5H";
//            this.rename = "rename";
//            this.overwrite = "off";
//        }
//    }

    public static String getCameraMode() {
        return cameraMode;
    }

    public static String getPhotoSize() {
        return photoSize;
    }

    public static String getPhotoBurst() {
        return photoBurst;
    }

    public static String getBurstSpeed() {
        return burstSpeed;
    }

    public static String getSendingOption() {
        return sendingOption;
    }

    public static String getShutterSpeed() {
        return shutterSpeed;
    }

    public static String getFlashPower() {
        return flashPower;
    }

    public static String getVideoSize() {
        return videoSize;
    }

    public static String getVideoLength() {
        return videoLength;
    }

    public static String getTriggerPir() {
        return triggerPir;
    }

    public static String getTriggerTimelapse() {
        return triggerTimelapse;
    }

    public static String getWorkTime1() {
        return workTime1;
    }

    public static String getWorkTime2() {
        return workTime2;
    }

    public static String getWorkTime3() {
        return workTime3;
    }

    public static String getWorkTime4() {
        return workTime4;
    }

    public static String getSendMode() {
        return sendMode;
    }

    public static String getRemoteControl() {
        return remoteControl;
    }

    public static String getRename() {
        return rename;
    }

    public static String getOverwrite() {
        return overwrite;
    }

    public static void setCameraMode(String cameraMode) {
        App_Data.cameraMode = cameraMode;
    }

    public static void setPhotoSize(String photoSize) {
        App_Data.photoSize = photoSize;
    }

    public static void setPhotoBurst(String photoBurst) {
        App_Data.photoBurst = photoBurst;
    }

    public static void setBurstSpeed(String burstSpeed) {
        App_Data.burstSpeed = burstSpeed;
    }

    public static void setSendingOption(String sendingOption) {
        App_Data.sendingOption = sendingOption;
    }

    public static void setShutterSpeed(String shutterSpeed) {
        App_Data.shutterSpeed = shutterSpeed;
    }

    public static void setFlashPower(String flashPower) {
        App_Data.flashPower = flashPower;
    }

    public static void setVideoSize(String videoSize) {
        App_Data.videoSize = videoSize;
    }

    public static void setVideoLength(String videoLength) {
        App_Data.videoLength = videoLength;
    }

    public static void setTriggerPir(String triggerPir) {
        App_Data.triggerPir = triggerPir;
    }

    public static void setTriggerTimelapse(String triggerTimelapse) {
        App_Data.triggerTimelapse = triggerTimelapse;
    }

    public static void setWorkTime1(String workTime1) {
        App_Data.workTime1 = workTime1;
    }

    public static void setWorkTime2(String workTime2) {
        App_Data.workTime2 = workTime2;
    }

    public static void setWorkTime3(String workTime3) {
        App_Data.workTime3 = workTime3;
    }

    public static void setWorkTime4(String workTime4) {
        App_Data.workTime4 = workTime4;
    }

    public static void setSendMode(String sendMode) {
        App_Data.sendMode = sendMode;
    }

    public static void setRemoteControl(String remoteControl) {
        App_Data.remoteControl = remoteControl;
    }

    public static void setRename(String rename) {
        App_Data.rename = rename;
    }

    public static void setOverwrite(String overwrite) {
        App_Data.overwrite = overwrite;
    }

    private JSONObject jsonObject = new JSONObject();

    public void setData(String key, String value) {
        switch (key) {
            case "photoSize":
                this.setPhotoSize(value);
                break;
            case "photoBurst":
                this.setPhotoBurst(value);
                break;
            case "burstSpeed":
                this.setBurstSpeed(value);
                break;
            case "sendingOption":
                this.setSendingOption(value);
                break;
            case "shutterSpeed":
                this.setShutterSpeed(value);
                break;
            case "flashPower":
                this.setFlashPower(value);
                break;
            case "videoSize":
                this.setVideoSize(value);
                break;
            case "videoLength":
                this.setVideoLength(value);
                break;
        }
    }

    public String getData(String key) {
        try {
            data = jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

}

