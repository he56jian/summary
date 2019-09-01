package com.example.myapplication;


public class DataApplication {
    private String cameraMode, photoSize, photoBurst, burstSpeed, sendingOption, shutterSpeed, flashPower, videoSize, videoLength,
            triggerPir, triggerTimelapse, triggerSen, wortTime1, workTime2, workTime3, workTime4, sendMode, remoteControl, rename, overWrite;
    private static DataApplication dataApplication = new DataApplication();
    private String status = "default";

    public void defaultSetting(){
            this.cameraMode = "photo";
            this.photoSize = "3mp";
            this.photoBurst = "1photo";
            this.burstSpeed = "Fast(200ms)";
            this.sendingOption = "1st";
            this.shutterSpeed = "normal";
            this.flashPower = "normal";
            this.videoSize = "wvga";
            this.videoLength = "5sec";
            this.triggerPir = "30s";
            this.triggerTimelapse = "5min";
            this.wortTime1 = "off";
            this.workTime2 = "off";
            this.workTime3 = "off";
            this.workTime4 = "off";
            this.sendMode = "0";
            this.remoteControl = "Delay 0.5H";
            this.rename = "rename";
            this.overWrite = "off";
            this.triggerSen = "normal";
    }

    //这里提供了一个供外部访问本class的静态方法，可以直接访问
    public static DataApplication getDataApplication() {
        return dataApplication;
    }

    public void setTriggerSen(String value) {
        this.triggerSen = value;
    }

    public String getTriggerSen() {
        return triggerSen;
    }

    public String getPhotoSize() {
        return photoSize;
    }

    public String getPhotoBurst() {
        return photoBurst;
    }

    public String getBurstSpeed() {
        return burstSpeed;
    }

    public String getSendingOption() {
        return sendingOption;
    }

    public String getShutterSpeed() {
        return shutterSpeed;
    }

    public String getFlashPower() {
        return flashPower;
    }

    public String getVideoSize() {
        return videoSize;
    }

    public String getVideoLength() {
        return videoLength;
    }

    public String getTriggerPir() {
        return triggerPir;
    }

    public String getTriggerTimelapse() {
        return triggerTimelapse;
    }

    public String getWortTime1() {
        return wortTime1;
    }

    public String getWorkTime2() {
        return workTime2;
    }

    public String getWorkTime3() {
        return workTime3;
    }

    public String getWorkTime4() {
        return workTime4;
    }

    public String getSendMode() {
        return sendMode;
    }

    public String getRemoteControl() {
        return remoteControl;
    }

    public String getRename() {
        return rename;
    }

    public void setPhotoSize(String photoSize) {
        this.photoSize = photoSize;
    }

    public void setPhotoBurst(String photoBurst) {
        this.photoBurst = photoBurst;
    }

    public void setBurstSpeed(String burstSpeed) {
        this.burstSpeed = burstSpeed;
    }

    public void setSendingOption(String sendingOption) {
        this.sendingOption = sendingOption;
    }

    public void setShutterSpeed(String shutterSpeed) {
        this.shutterSpeed = shutterSpeed;
    }

    public void setFlashPower(String flashPower) {
        this.flashPower = flashPower;
    }

    public void setVideoSize(String videoSize) {
        this.videoSize = videoSize;
    }

    public void setVideoLength(String videoLength) {
        this.videoLength = videoLength;
    }

    public void setTriggerPir(String triggerPir) {
        this.triggerPir = triggerPir;
    }

    public void setTriggerTimelapse(String triggerTimelapse) {
        this.triggerTimelapse = triggerTimelapse;
    }

    public void setWortTime1(String wortTime1) {
        this.wortTime1 = wortTime1;
    }

    public void setWorkTime2(String workTime2) {
        this.workTime2 = workTime2;
    }

    public void setWorkTime3(String workTime3) {
        this.workTime3 = workTime3;
    }

    public void setWorkTime4(String workTime4) {
        this.workTime4 = workTime4;
    }

    public void setSendMode(String sendMode) {
        this.sendMode = sendMode;
    }

    public void setRemoteControl(String remoteControl) {
        this.remoteControl = remoteControl;
    }

    public void setRename(String rename) {
        this.rename = rename;
    }

    public void setCameraMode(String value) {
        this.cameraMode = value;
    }

    public String getCameraMode() {
        return dataApplication.cameraMode;
    }

    public static void setValue(String key, String value) {
        switch (key) {
            case "cameraMode":
                dataApplication.setCameraMode(value);
                break;
            case "videoSize":
                dataApplication.setVideoSize(value);
                break;
            case "videoLength":
                dataApplication.setVideoLength(value);
                break;
            case "photoSize":
                dataApplication.setPhotoSize(value);
                break;
            case "photoBurst":
                dataApplication.setPhotoBurst(value);
                break;
            case "burstSpeed":
                dataApplication.setBurstSpeed(value);
                break;
            case "sendingOption":
                dataApplication.setSendingOption(value);
                break;
            case "shutterSpeed":
                dataApplication.setShutterSpeed(value);
                break;
            case "flashPower":
                dataApplication.setFlashPower(value);
                break;
            case "spinner_triggerSen":
                dataApplication.setTriggerSen(value);
                break;
            case "triggerPir":
                dataApplication.setTriggerPir(value);
                break;
            case "spinner_triggerTimeLapse":
                dataApplication.setTriggerTimelapse(value);
                break;
        }
    }

    public String getValue(String key) {
        String value = "";
        switch (key) {
            case "cameraMode":
                value = dataApplication.getCameraMode();
                break;
            case "videoSize":
                value = dataApplication.getVideoSize();
                break;
            case "videoLength":
                value = dataApplication.getVideoLength();
                break;
            case "photoSize":
                value = dataApplication.getPhotoSize();
                break;
            case "photoBurst":
                value = dataApplication.getPhotoBurst();
                break;
            case "burstSpeed":
                value = dataApplication.getBurstSpeed();
                break;
            case "sendingOption":
                value = dataApplication.getSendingOption();
                break;
            case "shutterSpeed":
                value = dataApplication.getShutterSpeed();
                break;
            case "flashPower":
                value = dataApplication.getFlashPower();
                break;
        }
        return value;
    }


//    public void getCameraMode() {
//
//
//    }
}
