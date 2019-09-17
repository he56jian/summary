package com.example.myapplication;


import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.Utils.intToCharList;
import static com.example.myapplication.Utils.method;
import static com.example.myapplication.Utils.strToCharList;

public class DataApplication {
    public Boolean protecte = false;
    public String cameraMode;
    private String photoSize;
    private String photoBurst;
    private String burstSpeed;
    private String sendingOption;
    private String shutterSpeed;
    private String flashPower;
    private String videoSize;
    private String videoLength;
    private String triggerPir;
    private String triggerTimelapse;
    private String triggerSen;
    private String wortTime1;
    private String workTime2;
    private String workTime3;
    private String workTime4;
    private String sendMode;
    private String remoteControl;
    private String rename;
    private String password;

    private String sim_apn, sim_acount, sim_passwd;
    private Boolean staName = false, staPassword = false, overWrite = false;
    private static DataApplication dataApplication = new DataApplication();
    private String status = "default";
    private String IP;
    private int PORT;
    Context context;
    int sta_connect = 0;

    public String getSim_apn() {
        return sim_apn;
    }

    public void setSim_apn(String sim_apn) {
        this.sim_apn = sim_apn;
        System.out.println("传进来的：" + sim_apn);
    }

    public String getSim_acount() {
        return sim_acount;
    }

    public void setSim_acount(String sim_acount) {
        this.sim_acount = sim_acount;
    }

    public String getSim_passwd() {
        return sim_passwd;
    }

    public void setSim_passwd(String sim_passwd) {
        this.sim_passwd = sim_passwd;
    }

    List<String> serverMegList;
    private String retServer = "";

    public void defaultSetting() {
        this.cameraMode = "photo";
        this.photoSize = "3MP";
        this.photoBurst = "1photo";
        this.burstSpeed = "Fast(200ms)";
        this.sendingOption = "1st";
        this.shutterSpeed = "normal";
        this.flashPower = "normal";
        this.videoSize = "wvga";
        this.videoLength = "5Sec";
        this.triggerPir = "30Sec";
        this.triggerTimelapse = "5min";
        this.wortTime1 = "off";
        this.workTime2 = "off";
        this.workTime3 = "off";
        this.workTime4 = "off";
        this.sendMode = "00";
        this.remoteControl = "Delay 0.5H";
        this.rename = "uovision";
        this.overWrite = false;
        this.triggerSen = "Auto";
        this.password = "0000";
        this.PORT = 5001;
        this.IP = "192.168.0.1";
        this.sim_apn = "";
        this.sim_acount = "";
        this.sim_passwd = "";
    }

    public DataApplication(Context context) {
        this.context = context;
    }

    public DataApplication() {

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
        if (staName) {
            if (rename.equals("")) {
                rename = "";
            }
            return rename;
        } else {
            return "";
        }

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
            case "retServer":
                dataApplication.setServerMeg(value);
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
            case "rename":
                dataApplication.setRename(value);
                break;
            case "password":
                dataApplication.setPassword(value);
                break;
            case "sendMode":
                dataApplication.setSendMode(value);
                break;
            case "remoteControl":
                dataApplication.setRemoteControl(value);
                break;
        }
    }


    public String getValue(String key) {
        String value = "";
        switch (key) {
            case "retServer":
                value = dataApplication.getServerMeg();
                break;
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
            case "rename":
                value = dataApplication.getRename();
                break;
            case "password":
                value = dataApplication.getPassword();
                break;
            case "sendMode":
                value = dataApplication.getSendMode();
                break;
            case "remoteControl":
                value = dataApplication.getRemoteControl();
                break;
            case "sim_apn":
                value = dataApplication.getSim_apn();
                break;
            case "sim_acount":
                value = dataApplication.getSim_acount();
                break;
            case "sim_passwd":
                value = dataApplication.getSim_passwd();
                break;

        }
        return value;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        if (password.equals("")) {
            password = "";
        }
        return password;
    }

    public void setStaName(Boolean value) {
        staName = value;
    }

    public void setStaPassword(Boolean value) {
        staPassword = value;
    }

    public int getStaRename() {
        return staName ? 1 : 0;
    }

    public int getStaPassword() {
        return staPassword ? 1 : 0;
    }

    public void setOverWrite(Boolean value) {
        overWrite = value;
    }

    public int getOverWrite() {
        if (overWrite) {          //如果overWrite = on时为1；
            return 1;
        } else {
            return 0;
        }
    }

    List<char[]> list;

    //获取设置的参数的字节数组
    public char[] getCharCam() {
        list = new ArrayList<>();
        Utils utils = new Utils(context);
        int charCam = getCharWithPar("cameraMode", cameraMode);
        int charPhotoSize = getCharWithPar("photoSize", photoSize);
        int charPhotoBurst = getCharWithPar("photoBurst", photoBurst);
        int charBurstSpeed = getCharWithPar("burstSpeed", burstSpeed);
        int charSendingOption = getCharWithPar("sendingOption", sendingOption);
        int charShutterSpeed = getCharWithPar("shutterSpeed", shutterSpeed);
        int charFlashPower = getCharWithPar("flashPower", flashPower);
        int charVideoSize = getCharWithPar("videoSize", videoSize);
        int charVideoLength = getCharWithPar("videoLength", videoLength);

        int charTriggerSen = getCharWithPar("triggerSen", triggerSen);
        int charTriggerPir = getCharWithPar("triggerPir", triggerPir);
        int charTriggerTimelapse = getCharWithPar("triggerTimelapse", triggerTimelapse);
        int charSendMode = getCharWithPar("sendMode", sendMode);
        int charRemoteControl = getCharWithPar("remoteControl", remoteControl);
        int charStaName = getCharWithPar("staName", staName + "");
//        int charRename = getCharWithPar("rename", rename);
        int charStaPassword = getCharWithPar("staPassword", staPassword + "");
//        int charPassword = getCharWithPar("staName", password);


        System.out.println("charCam:" + charCam + "   cameraMode：" + cameraMode);
        System.out.println("charPhotoSize:" + charPhotoSize + "   photoSize：" + photoSize);
        System.out.println("charPhotoBurst:" + charPhotoBurst + "   photoBurst：" + photoBurst);
        System.out.println("charBurstSpeed:" + charBurstSpeed + "   burstSpeed：" + burstSpeed);
        System.out.println("charSendingOption:" + charSendingOption + "   sendingOption：" + sendingOption);
        System.out.println("charShutterSpeed:" + charShutterSpeed + "   shutterSpeed：" + shutterSpeed);
        System.out.println("charFlashPower:" + charFlashPower + "   flashPower：" + flashPower);
        System.out.println("charVideoSize:" + charVideoSize + "   videoSize：" + videoSize);
        System.out.println("charVideoLength:" + charVideoLength + "   videoLength：" + videoLength);
        System.out.println("charTriggerSen:" + charTriggerSen + "   triggerSen：" + triggerSen);
        System.out.println("charTriggerPir:" + charTriggerPir + "   triggerPir：" + triggerPir);
        System.out.println("charTriggerTimelapse:" + charTriggerTimelapse + "   triggerTimelapse：" + triggerTimelapse);
        System.out.println("charSendMode:" + charSendMode + "   sendMode：" + sendMode);
        System.out.println("charRemoteControl:" + charRemoteControl + "   remoteControl：" + remoteControl);
        System.out.println("charStaName:" + charStaName + "   staName：" + staName);
        System.out.println("rename:" + rename + "   rename：" + rename);
        System.out.println("charStaPassword:" + charStaPassword + "   staPassword：" + staPassword);
        System.out.println("password:" + password + "   password：" + password);
        System.out.println("sim_apn:" + sim_apn + "   sim_apn：" + sim_apn);
        System.out.println("sim_acount:" + sim_acount + "   sim_acount：" + sim_acount);
        System.out.println("sim_passwd:" + sim_passwd + "   sim_passwd：" + sim_passwd);

        list.add(intToCharList(charCam));
        list.add(intToCharList(charPhotoSize));
        list.add(intToCharList(charPhotoBurst));
        list.add(intToCharList(charBurstSpeed));
        list.add(intToCharList(charSendingOption));
        list.add(intToCharList(charShutterSpeed));
        list.add(intToCharList(charFlashPower));
        list.add(intToCharList(charVideoSize));
        list.add(intToCharList(charVideoLength));
        list.add(intToCharList(charTriggerSen));
        list.add(intToCharList(charTriggerPir));
        list.add(intToCharList(charTriggerTimelapse));
        list.add(intToCharList(charSendMode));
        list.add(intToCharList(charRemoteControl));
        list.add(intToCharList(charStaName));
        utils = new Utils(context);
        char[] arr = new char[]{'#', '#'};
        char[] charRename,charPasswd;
        if (!staName) {
            rename = "uovision";
        }
        charRename = utils.addChar(strToCharList(rename, 16), arr);
        list.add(charRename);
        list.add(intToCharList(charStaPassword));

        if (!staPassword) {
            password = "0000";
        }
        charPasswd = utils.addChar(strToCharList(password, 8),arr);
        list.add(charPasswd);
//        list.add(strToCharList(sim_apn,64));
//        list.add(strToCharList(sim_acount,64));
//        list.add(strToCharList(sim_passwd,64));

        char[] charsim_apn = utils.addChar(strToCharList(sim_apn, 64), arr);
        char[] charsim_acount = utils.addChar(strToCharList(sim_acount, 64), arr);
        char[] charsim_passwd = utils.addChar(strToCharList(sim_passwd, 64), arr);

        list.add(charsim_apn);
        list.add(charsim_acount);
        list.add(charsim_passwd);

        char[] value = method("", list);
        System.out.println(value);
        return value;
    }


    //把相机设置转成二进制；
    public int getCharWithPar(String key, String value) {
        int result = 0;
        switch (key) {
            case "cameraMode":
                switch (value) {
                    case "photo":
                        result = 0;
                        break;
                    case "video":
                        result = 1;
                        break;
                }
                break;
            case "photoSize":
                switch (value) {
                    case "3MP":
                        result = 1;
                        break;
                    case "5MP":
                        result = 2;
                        break;
                    case "1080P":
                        result = 0;
                        break;
//                    case "12MP":
//                        result = 3;
//                    break;
//                    case "16Mp":
//                        result = 4;
//                    break;
//                    case "20MP":
//                        result = 5;
//                    break;
                }
                break;
            case "photoBurst":
                switch (value) {
                    case "1photo":
                        result = 0;
                        break;
                    case "2photos":
                        result = 1;
                        break;
                    case "3photos":
                        result = 2;
                        break;
                    case "4photos":
                        result = 3;
                        break;
                    case "5photos":
                        result = 4;
                        break;
                    case "6photos":
                        result = 5;
                        break;
                    case "7photos":
                        result = 6;
                        break;
                    case "8photos":
                        result = 7;
                        break;
                    case "9photos":
                        result = 8;
                        break;
                    case "10photos":
                        result = 9;
                        break;
                }
                break;
            case "burstSpeed":
                switch (value) {
                    case "Fast(200ms)":
                        result = 0;
                        break;
                    case "Show(500ms)":
                        result = 1;
                        break;
                }
                break;
            case "sendingOption":
                switch (value) {
                    case "1st":
                        result = 0;
                        break;
                    case "2st":
                        result = 1;
                        break;
                    case "3st":
                        result = 2;
                        break;
                    case "4st":
                        result = 0;
                        break;
                    case "5st":
                        result = 1;
                        break;
                    case "6st":
                        result = 2;
                        break;
                    case "7st":
                        result = 0;
                        break;
                    case "8st":
                        result = 1;
                        break;
                    case "9st":
                        result = 2;
                        break;
                    case "10st":
                        result = 2;
                        break;
                }
                break;
            case "shutterSpeed":
                switch (value) {
                    case "Normal":
                        result = 0;
                        break;
                    case "Fast":
                        result = 1;
                        break;
                    case "High":
                        result = 2;
                        break;
                }
                break;
            case "flashPower":
                switch (value) {
                    case "Low":
                        result = 0;
                        break;
                    case "Normal":
                        result = 1;
                        break;
                    case "High":
                        result = 2;
                        break;
                }
                break;
            case "videoSize":
                switch (value) {
                    case "D1":
                        result = 0;
                    case "720P":
                        result = 1;
                        break;
                    case "1080P":
                        result = 2;
                        break;
                    case "1440P":
                        result = 3;
                        break;
                }
                break;
            case "videoLength":
                switch (value) {
                    case "5sec":
                        result = 0;
                        break;
                    case "10sec":
                        result = 1;
                        break;
                    case "15sec":
                        result = 2;
                        break;
                    case "20sec":
                        result = 3;
                        break;
                    case "25sec":
                        result = 4;
                        break;
                    case "30sec":
                        result = 5;
                        break;
                    case "35sec":
                        result = 6;
                        break;
                    case "40sec":
                        result = 7;
                        break;
                    case "45sec":
                        result = 8;
                        break;
                    case "50sec":
                        result = 9;
                        break;
                    case "55sec":
                        result = 10;
                        break;
                    case "60sec":
                        result = 11;
                        break;
                }
                break;
            case "triggerSen":
                switch (value) {
                    case "off":
                        result = 0;
                        break;
                    case "Low":
                        result = 1;
                        break;
                    case "Auto":
                        result = 2;
                        break;
                    case "High":
                        result = 3;
                        break;
                }
                break;
            case "triggerPir":
                System.out.println(value);
                switch (value) {
                    case "0Sec":
                        result = 0;
                        break;
                    case "1Sec":
                        result = 1;
                        break;
                    case "2Sec":
                        result = 2;
                        break;
                    case "3Sec":
                        result = 3;
                        break;
                    case "4Sec":
                        result = 4;
                        break;
                    case "5Sec":
                        result = 5;
                        break;
                    case "10Sec":
                        result = 6;
                        break;
                    case "15Sec":
                        result = 7;
                        break;
                    case "20Sec":
                        result = 8;
                        break;
                    case "25Sec":
                        result = 9;
                        break;
                    case "30Sec":
                        result = 10;
                        break;
                    case "35Sec":
                        result = 11;
                        break;
                    case "40Sec":
                        result = 12;
                        break;
                    case "45Sec":
                        result = 13;
                        break;
                    case "50Sec":
                        result = 14;
                        break;
                    case "55Sec":
                        result = 15;
                        break;
                    case "1min":
                        result = 16;
                        break;
                    case "2min":
                        result = 17;
                        break;
                    case "3min":
                        result = 18;
                        break;
                    case "4min":
                        result = 19;
                        break;
                    case "5min":
                        result = 20;
                        break;
                    case "10min":
                        result = 21;
                        break;
                    case "15min":
                        result = 22;
                        break;
                    case "20min":
                        result = 23;
                        break;
                    case "25min":
                        result = 24;
                        break;
                    case "30min":
                        result = 25;
                        break;
                    case "35min":
                        result = 26;
                        break;
                    case "40min":
                        result = 27;
                        break;
                    case "45min":
                        result = 28;
                        break;
                    case "50min":
                        result = 29;
                        break;
                    case "55min":
                        result = 30;
                        break;
                    case "60min":
                        result = 31;
                        break;
                }
                break;
            case "triggerTimelapse":
                switch (value) {
//                    case "0Sec":
//                        return 0;
//                    break;
//                    case "1Sec":
//                        return 1;
//                    break;
//                    case "2Sec":
//                        return 2;
//                    break;
//                    case "3Sec":
//                        return 3;
//                    break;
//                    case "4Sec":
//                        return 4;
//                    break;
                    case "5Sec":
                        result = 1;
                        break;
                    case "10Sec":
                        result = 2;
                        break;
                    case "15Sec":
                        result = 3;
                        break;
                    case "20Sec":
                        result = 4;
                        break;
                    case "25Sec":
                        result = 5;
                        break;
                    case "30Sec":
                        result = 60;
                        break;
                    case "35Sec":
                        result = 7;
                        break;
                    case "40Sec":
                        result = 8;
                        break;
                    case "45Sec":
                        result = 9;
                        break;
                    case "50Sec":
                        result = 10;
                        break;
                    case "55Sec":
                        result = 11;
                        break;
                    case "1min":
                        result = 12;
                        break;
                    case "2min":
                        result = 13;
                        break;
                    case "3min":
                        result = 14;
                        break;
                    case "4min":
                        result = 15;
                        break;
                    case "5min":
                        result = 16;
                        break;
                    case "10min":
                        result = 17;
                        break;
                    case "15min":
                        result = 18;
                        break;
                    case "20min":
                        result = 19;
                        break;
                    case "25min":
                        result = 20;
                        break;
                    case "30min":
                        result = 21;
                        break;
                    case "35min":
                        result = 22;
                        break;
                    case "40min":
                        result = 23;
                        break;
                    case "45min":
                        result = 24;
                        break;
                    case "50min":
                        result = 25;
                        break;
                    case "55min":
                        result = 26;
                        break;
                    case "1Hour":
                        result = 27;
                        break;
                    case "2Hour":
                        result = 28;
                        break;
                    case "3Hour":
                        result = 29;
                        break;
                    case "4Hour":
                        result = 30;
                        break;
                    case "5Hour":
                        result = 31;
                        break;
                    case "6Hour":
                        result = 32;
                        break;
                    case "7Hour":
                        result = 33;
                    case "8Hour":
                        result = 34;
                        break;
                    case "12Hour":
                        result = 35;
                        break;
                    case "20Hour":
                        result = 36;
                        break;
                    case "24Hour":
                        result = 37;
                        break;
                }
                break;
            case "sendMode":
                int sendMode2 = Integer.parseInt(value);
                result = sendMode2;
                break;
            case "remoteControl":
                switch (value) {
                    case "Realtime":
                        result = 0;
                        break;
                    case "Delay 0.5H":
                        result = 1;
                        break;
                    case "Delay 1H":
                        result = 2;
                        break;
                    case "Delay 2H":
                        result = 3;
                        break;
                    case "Delay 3H":
                        result = 4;
                        break;
                    case "Delay 4H":
                        result = 5;
                        break;
                    case "Delay 6H":
                        result = 6;
                        break;
                    case "Delay 12H":
                        result = 7;
                        break;
                    case "Delay 24H":
                        result = 8;
                        break;
                }
                break;
            case "staName":
                switch (value) {
                    case "false":
                        result = 0;
                        break;
                    case "true":
                        result = 1;
                        break;
                }
                break;
            case "staPassword":
                switch (value) {
                    case "false":
                        result = 0;
                        break;
                    case "true":
                        result = 1;
                        break;
                }
                break;
        }
        return result;
    }

    //显示二维码数
    public String getQRCode() {
        char[] showValue = getCharCam();
//        System.out.println(showValue);
//        char[] showValue = getValue();
//        System.out.println(showValue);
        String count = "";
        for (int i = 0; i < showValue.length; i++) {
            count += showValue[i];
        }
        return count;
    }


    //保存服务器返回的数据
    public void setServerMeg(String value) {
        addServerMegList(value);
        this.retServer = value;
    }

    //获取服务器返回的数据
    public String getServerMeg() {
        return retServer;
    }

    public void addServerMegList(String value) {
        serverMegList.add(value);
    }

    //设置链接状态
    public void setStaConnect(int status) {
        this.sta_connect = status;
    }

    //获取当前链接状态
    public int getStaConnect() {
        return sta_connect;
    }

    public void setIP(String value) {
        this.IP = value;
    }

    public String getIP() {
        return IP;
    }

    public void setPORT(int value) {
        this.PORT = value;
    }

    public int getPORT() {
        return PORT;
    }

}
