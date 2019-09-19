package com.example.myapplication.Database;


import android.content.Context;

import com.example.myapplication.BaseMethods.Utils;

import java.util.ArrayList;
import java.util.List;


public class DataApplication {
    private String cameraMode, photoSize, photoBurst, burstSpeed, sendingOption, shutterSpeed, flashPower, videoSize, videoLength;
    private String triggerPir, triggerTimelapse, triggerSen, wortTime1, workTime2, workTime3, workTime4, sendMode, remoteControl, rename;
    private String password, sim_apn, sim_acount, sim_passwd;
    private Boolean staName, staPassword , overWrite, protecte;
    private static DataApplication dataApplication = new DataApplication();
    private String IP;
    private int PORT;
    private Context context;
    private int sta_connect = 0;
    private byte[] Rename = new byte[8];
    private byte[] Passwd = new byte[4];
    private byte[] Sim_apn = new byte[32];
    private byte[] Sim_acount = new byte[32];
    private byte[] Sim_passwd = new byte[32];
    private byte[] resultByte;
    private int videoLengthIndex, videoSizeIndex, cameraModeIndex, photoSizeIndex, photoBurstIndex, burstSpeedIndex, sendingOptionIndex,
            shutterSpeedIndex, flashPowerIndex, triggerSenIndex, triggerPirIndex, triggerTimelapseIndex, remoteControlIndex,sendModeIndex,staNameIndex,staPasswdIndex;
    private String[] camerModeList = new String[]{"photo", "video"};
    private String[] photoSizeList = new String[]{"1080P", "3MP", "5MP"};
    private String[] photoBurstList = new String[]{"1photo", "2photos", "3photos", "4photos", "5photos", "6photos", "7photos", "8photos", "9photos", "10photos"};
    private String[] burstSpeedList = new String[]{"Fast(200ms)", "Slow(500ms)"};
    private String[] sendingOptionList = new String[]{"1st", "2st", "3st", "4st", "5st", "6st", "7st", "8st", "9st", "10st"};
    private String[] shutterSpeedList = new String[]{"Normal", "Fast", "High"};
    private String[] flashPowerList = new String[]{"Low", "Normal", "High"};
    private String[] videoSizeList = new String[]{"D1", "720P", "1080P", "1440P"};
    private String[] videoLengthList = new String[]{
            "5sec", "10sec", "15sec", "20sec",
            "25sec", "30sec", "35sec", "40sec",
            "45sec", "50sec", "55sec", "60sec"
    };

    private String[] triggerSenList = new String[]{"off", "Low", "Auto", "High"};
    private String[] triggerPirList = new String[]{
            "0Sec", "1Sec", "2Sec", "3Sec", "4Sec", "5Sec", "10Sec", "15Sec",
            "20Sec", "25Sec", "30Sec", "35Sec", "40Sec", "45Sec", "50Sec", "55Sec",
            "1min", "2min", "3min", "4min", "5min", "10min", "15min", "20min",
            "25min", "30min", "35min", "40min", "45min", "50min", "55min", "60min"
    };
    private String[] triggerTimelapseList = new String[]{
            "5Sec", "10Sec", "15Sec",
            "20Sec", "25Sec", "30Sec", "35Sec", "40Sec", "45Sec", "50Sec", "55Sec",
            "1min", "2min", "3min", "4min", "5min", "10min", "15min", "20min",
            "25min", "30min", "35min", "40min", "45min", "50min", "55min", "1Hour",
            "2Hour", "3Hour", "4Hour", "5Hour", "6Hour", "7Hour", "8Hour", "12Hour","16Hour", "20Hour", "24Hour"
    };
    private String[] remoteControlList = new String[]{"Realtime", "Delay 0.5H", "Delay 1H", "Delay 2H", "Delay 3H", "Delay 4H", "Delay 6H", "Delay 12H", "Delay 24H"};


    public String[] getVideoSizeList() {
        return videoSizeList;
    }

    public String[] getCamerModeList() {
        return camerModeList;
    }

    public String[] getPhotoSizeList() {
        return photoSizeList;
    }

    public String[] getPhotoBurstList() {
        return photoBurstList;
    }

    public String[] getBurstSpeedList() {
        return burstSpeedList;
    }

    public String[] getSendingOptionList() {
        return sendingOptionList;
    }

    public String[] getShutterSpeedList() {
        return shutterSpeedList;
    }

    public String[] getFlashPowerList() {
        return flashPowerList;
    }

    public String[] getVideoLengthList() {
        return videoLengthList;
    }

    public String[] getTriggerSenList() {
        return triggerSenList;
    }

    public String[] getTriggerPirList() {
        return triggerPirList;
    }

    public String[] getTriggerTimelapseList() {
        return triggerTimelapseList;
    }

    public String[] getRemoteControlList() {
        return remoteControlList;
    }

    public String getSim_apn() {
        return sim_apn;
    }

    public void setSim_apn(String sim_apn) {
        this.sim_apn = sim_apn;
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
        this.cameraModeIndex = 0;
        this.photoSize = "3MP";
        this.photoSizeIndex = 1;
        this.photoBurst = "1photo";
        this.photoBurstIndex = 0;
        this.burstSpeed = "Fast(200ms)";
        this.burstSpeedIndex = 0;
        this.sendingOption = "1st";
        this.sendingOptionIndex=0;
        this.shutterSpeed = "normal";
        this.shutterSpeedIndex=0;
        this.flashPower = "normal";
        this.flashPowerIndex = 0;
        this.videoSize = "D1";
        this.videoSizeIndex = 0;
        this.videoLength = "5Sec";
        this.videoLengthIndex = 0;
        this.triggerPir = "30Sec";
        this.triggerPirIndex = 0x0a;
        this.triggerTimelapse = "5min";
        this.triggerTimelapseIndex = 0x10;
        this.sendMode = "00";
        this.sendModeIndex = 0;
        this.remoteControl = "Delay 0.5H";
        this.remoteControlIndex = 1;
        this.staName = false;
        this.staNameIndex = 0;
        this.rename = "";
        this.overWrite = false;
        this.triggerSen = "Auto";
        this.triggerSenIndex = 2;
        this.staPassword = false;
        this.staPasswdIndex = 0;
        this.password = "";
        this.PORT = 5001;
        this.IP = "192.168.0.1";
        this.sim_apn = "";
        this.sim_acount = "";
        this.sim_passwd = "";
        this.protecte = false;
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

    /**
     * 传入需要设置的值的键及值；
     *
     * @param key
     * @param value
     */
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

    /**
     * 根据传入的键，获取值
     *
     * @param key
     * @return
     */
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
    Utils utils;

    /**
     * 这个是之前的参数，把数据全部转换成字符数组
     * @return
     */
//    public char[] getCharCam() {
//        list = new ArrayList<>();
//        int charCam = getCharWithPar("cameraMode", cameraMode);
//        int charPhotoSize = getCharWithPar("photoSize", photoSize);
//        int charPhotoBurst = getCharWithPar("photoBurst", photoBurst);
//        int charBurstSpeed = getCharWithPar("burstSpeed", burstSpeed);
//        int charSendingOption = getCharWithPar("sendingOption", sendingOption);
//        int charShutterSpeed = getCharWithPar("shutterSpeed", shutterSpeed);
//        int charFlashPower = getCharWithPar("flashPower", flashPower);
//        int charVideoSize = getCharWithPar("videoSize", videoSize);
//        int charVideoLength = getCharWithPar("videoLength", videoLength);
//
//        int charTriggerSen = getCharWithPar("triggerSen", triggerSen);
//        int charTriggerPir = getCharWithPar("triggerPir", triggerPir);
//        int charTriggerTimelapse = getCharWithPar("triggerTimelapse", triggerTimelapse);
//        int charSendMode = getCharWithPar("sendMode", sendMode);
//        int charRemoteControl = getCharWithPar("remoteControl", remoteControl);
//        int charStaName = getCharWithPar("staName", staName + "");
//        int charStaPassword = getCharWithPar("staPassword", staPassword + "");
//
////       char[] newChar =new char[]{1,charCam};
//
//
//
//
//        System.out.println("charCam:" + charCam + "   cameraMode：" + cameraMode);
//        System.out.println("charPhotoSize:" + charPhotoSize + "   photoSize：" + photoSize);
//        System.out.println("charPhotoBurst:" + charPhotoBurst + "   photoBurst：" + photoBurst);
//        System.out.println("charBurstSpeed:" + charBurstSpeed + "   burstSpeed：" + burstSpeed);
//        System.out.println("charSendingOption:" + charSendingOption + "   sendingOption：" + sendingOption);
//        System.out.println("charShutterSpeed:" + charShutterSpeed + "   shutterSpeed：" + shutterSpeed);
//        System.out.println("charFlashPower:" + charFlashPower + "   flashPower：" + flashPower);
//        System.out.println("charVideoSize:" + charVideoSize + "   videoSize：" + videoSize);
//        System.out.println("charVideoLength:" + charVideoLength + "   videoLength：" + videoLength);
//        System.out.println("charTriggerSen:" + charTriggerSen + "   triggerSen：" + triggerSen);
//        System.out.println("charTriggerPir:" + charTriggerPir + "   triggerPir：" + triggerPir);
//        System.out.println("charTriggerTimelapse:" + charTriggerTimelapse + "   triggerTimelapse：" + triggerTimelapse);
//        System.out.println("charSendMode:" + charSendMode + "   sendMode：" + sendMode);
//        System.out.println("charRemoteControl:" + charRemoteControl + "   remoteControl：" + remoteControl);
//        System.out.println("charStaName:" + charStaName + "   staName：" + staName);
//        System.out.println("rename:" + rename + "   rename：" + rename);
//        System.out.println("charStaPassword:" + charStaPassword + "   staPassword：" + staPassword);
//        System.out.println("password:" + password + "   password：" + password);
//        System.out.println("sim_apn:" + sim_apn + "   sim_apn：" + sim_apn);
//        System.out.println("sim_acount:" + sim_acount + "   sim_acount：" + sim_acount);
//        System.out.println("sim_passwd:" + sim_passwd + "   sim_passwd：" + sim_passwd);
//
//        list.add(intToCharList(charCam));
//        list.add(intToCharList(charPhotoSize));
//        list.add(intToCharList(charPhotoBurst));
//        list.add(intToCharList(charBurstSpeed));
//        list.add(intToCharList(charSendingOption));
//        list.add(intToCharList(charShutterSpeed));
//        list.add(intToCharList(charFlashPower));
//        list.add(intToCharList(charVideoSize));
//        list.add(intToCharList(charVideoLength));
//        list.add(intToCharList(charTriggerSen));
//        list.add(intToCharList(charTriggerPir));
//        list.add(intToCharList(charTriggerTimelapse));
//        list.add(intToCharList(charSendMode));
//        list.add(intToCharList(charRemoteControl));
//        list.add(intToCharList(charStaName));
//
//
//
//        utils = new Utils(context);
//        char[] arr = new char[]{'#', '#'};
//        char[] charRename,charPasswd;
//        if (!staName) {
//            rename = "uovision";
//        }
//        charRename = rename.toCharArray();
//        list.add(utils.addZeorChar(charRename,8));
//        list.add(intToCharList(charStaPassword));
//        if (!staPassword) {
//            password = "0000";
//        }
//        charPasswd = password.toCharArray();
//        list.add(utils.addZeorChar(charPasswd,4));
//
//        char[] charsim_apn=sim_apn.toCharArray();
//        list.add(utils.addZeorChar(charsim_apn,32));
//        char[] charsim_acount=sim_acount.toCharArray();
//        list.add(utils.addZeorChar(charsim_acount,32));
//        char[] charsim_passwd=sim_passwd.toCharArray();
//        list.add(utils.addZeorChar(charsim_passwd,32));
//
//        char[] value = method("", list);
//        System.out.println(value);
//        return value;
//    }


    /**
     * 把参数都转换成字节数组；
     * @return
     */
    public byte[] getCharCam() {
        list = new ArrayList<>();
        byte charCam = getByteWithPar("cameraMode", cameraMode);
        byte charPhotoSize = getByteWithPar("photoSize", photoSize);
        byte charPhotoBurst = getByteWithPar("photoBurst", photoBurst);
        byte charBurstSpeed = getByteWithPar("burstSpeed", burstSpeed);
        byte charSendingOption = getByteWithPar("sendingOption", sendingOption);
        byte charShutterSpeed = getByteWithPar("shutterSpeed", shutterSpeed);
        byte charFlashPower = getByteWithPar("flashPower", flashPower);
        byte charVideoSize = getByteWithPar("videoSize", videoSize);
        byte charVideoLength = getByteWithPar("videoLength", videoLength);
        byte charTriggerSen = getByteWithPar("triggerSen", triggerSen);
        byte charTriggerPir = getByteWithPar("triggerPir", triggerPir);
        byte charTriggerTimelapse = getByteWithPar("triggerTimelapse", triggerTimelapse);
        byte charSendMode = getByteWithPar("sendMode", sendMode);
        byte charRemoteControl = getByteWithPar("remoteControl", remoteControl);
        byte charStaName = getByteWithPar("staName", staName + "");
        byte charStaPassword = getByteWithPar("staPassword", staPassword + "");

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

        byte[] bytes = new byte[]{charCam,charPhotoSize,charPhotoBurst,charBurstSpeed,charSendingOption,charShutterSpeed,charFlashPower,charVideoSize
                                    ,charVideoLength,charTriggerSen,charTriggerPir,charTriggerTimelapse,charSendMode,charRemoteControl,charStaName,
                                };
        utils = new Utils(context);
        int renameLen = rename.getBytes().length;        //实际
        for(int i =0;i<Rename.length;i++){
            if(i<renameLen){
                Rename[i] = rename.getBytes()[i];
            }else{
                Rename[i]=0;
            }
        }
        resultByte= utils.byteMerger(bytes,Rename);

        resultByte= utils.byteMerger(resultByte,new byte[]{charStaPassword});

        int passwdLen = password.getBytes().length;        //实际
        for(int i =0;i<Passwd.length;i++){
            if(i<passwdLen){
                Passwd[i] = password.getBytes()[i];
            }else{
                Passwd[i]=0;
            }
        }
       resultByte= utils.byteMerger(resultByte,Passwd);

        int len = sim_apn.getBytes().length;        //实际
        for(int i =0;i<32;i++){
            if(i<len){
                Sim_apn[i] = sim_apn.getBytes()[i];
            }else{
                Sim_apn[i]=0;
            }
        }
        resultByte= utils.byteMerger(resultByte,Sim_apn);

        len = sim_acount.getBytes().length;        //实际
        for(int i =0;i<32;i++){
            if(i<len){
                Sim_acount[i] = sim_acount.getBytes()[i];
            }else{
                Sim_acount[i]=0;
            }
        }

        resultByte= utils.byteMerger(resultByte,Sim_acount);

        len = sim_passwd.getBytes().length;        //实际
        for(int i =0;i<32;i++){
            if(i<len){
                Sim_passwd[i] = sim_passwd.getBytes()[i];
            }else{
                Sim_passwd[i]=0;
            }
        }

        resultByte= utils.byteMerger(resultByte,Sim_passwd);


        return resultByte;
    }


    /**
     * 用于测试
     *
     * @return
     */
    public byte[] getCharCam2() {
        list = new ArrayList<>();
        sendModeIndex = Integer.parseInt(sendMode);
        staNameIndex = staName ?1:0;
        staPasswdIndex = staName ?1:0;

        System.out.println("cameraModeIndex:" + cameraModeIndex + "   cameraMode：" + cameraMode);
        System.out.println("photoSizeIndex:" + photoSizeIndex + "   photoSize：" + photoSize);
        System.out.println("photoBurstIndex:" + photoBurstIndex + "   photoBurst：" + photoBurst);
        System.out.println("burstSpeedIndex:" + burstSpeedIndex + "   burstSpeed：" + burstSpeed);
        System.out.println("sendingOptionIndex:" + sendingOptionIndex + "   sendingOption：" + sendingOption);
        System.out.println("shutterSpeedIndex:" + shutterSpeedIndex + "   shutterSpeed：" + shutterSpeed);
        System.out.println("flashPowerIndex:" + flashPowerIndex + "   flashPower：" + flashPower);
        System.out.println("videoSizeIndex:" + videoSizeIndex + "   videoSize：" + videoSize);
        System.out.println("videoLengthIndex:" + videoLengthIndex + "   videoLength：" + videoLength);
        System.out.println("triggerSenIndex:" + triggerSenIndex + "   triggerSen：" + triggerSen);
        System.out.println("triggerPirIndex:" + triggerPirIndex + "   triggerPir：" + triggerPir);
        System.out.println("triggerTimelapseIndex:" + triggerTimelapseIndex + "   triggerTimelapse：" + triggerTimelapse);
        System.out.println("sendModeIndex:" + sendModeIndex + "   sendMode：" + sendMode);
        System.out.println("remoteControlIndex:" + remoteControlIndex + "   remoteControl：" + remoteControl);
        System.out.println("staNameIndex:" + staNameIndex + "   staName：" + staName);
        System.out.println("rename:" + rename + "   rename：" + rename);
        System.out.println("staPasswdIndex:" + staPasswdIndex + "   staPassword：" + staPassword);
        System.out.println("password:" + password + "   password：" + password);
        System.out.println("sim_apn:" + sim_apn + "   sim_apn：" + sim_apn);
        System.out.println("sim_acount:" + sim_acount + "   sim_acount：" + sim_acount);
        System.out.println("sim_passwd:" + sim_passwd + "   sim_passwd：" + sim_passwd);

        byte[] bytes = new byte[]{(byte)cameraModeIndex, (byte)photoSizeIndex, (byte)photoBurstIndex,(byte) burstSpeedIndex, (byte)sendingOptionIndex,(byte) shutterSpeedIndex, (byte)flashPowerIndex,  (byte)videoSizeIndex
                , (byte)videoLengthIndex,(byte) triggerSenIndex,(byte) triggerPirIndex,(byte) triggerTimelapseIndex, (byte)sendModeIndex, (byte)remoteControlIndex,(byte)staNameIndex
        };

        utils = new Utils(context);
    //进行补0操作
        Rename =  utils.createByte(rename,Rename.length);
        resultByte = utils.byteMerger(bytes, Rename);

        resultByte = utils.byteMerger(resultByte, new byte[]{(byte)staPasswdIndex});

        Passwd =  utils.createByte(password,Passwd.length);
        resultByte = utils.byteMerger(resultByte, Passwd);

        Sim_apn =  utils.createByte(sim_apn,Sim_apn.length);
        resultByte= utils.byteMerger(resultByte,Sim_apn);

        Sim_acount =  utils.createByte(sim_acount,Sim_acount.length);
        resultByte= utils.byteMerger(resultByte,Sim_acount);

        Sim_passwd =  utils.createByte(sim_passwd,Sim_passwd.length);
        resultByte= utils.byteMerger(resultByte,Sim_passwd);
        return resultByte;
    }


    /**
     * 把相机参数转化成整型；
     * @param key
     * @param value
     * @return
     */
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
                        break;
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
                        result = 6;
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
                        break;
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
        String newresult = Integer.toHexString(result);     //转成十六进制数

        return result;
    }


    /**
     * 把相机参数转换成字节类型；
     *
     * @param key
     * @param value
     * @return
     */
    public Byte getByteWithPar(String key, String value) {
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
                        result = 3;
                        break;
                    case "5st":
                        result = 4;
                        break;
                    case "6st":
                        result = 5;
                        break;
                    case "7st":
                        result = 6;
                        break;
                    case "8st":
                        result = 7;
                        break;
                    case "9st":
                        result = 8;
                        break;
                    case "10st":
                        result = 9;
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
                        break;
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
                        result = 6;
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
                        break;
                    case "8Hour":
                        result = 34;
                        break;
                    case "12Hour":
                        result = 35;
                        break;
                    case "16Hour":
                        result = 36;
                        break;
                    case "20Hour":
                        result = 37;
                        break;
                    case "24Hour":
                        result = 38;
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
        Integer cam = Integer.valueOf(result);
        Byte resultByte = cam.byteValue();
        return resultByte;
    }


    /***
     * 对照参数，把参数转换成对应的十六进制字符数组；
     * @param key
     * @param value
     * @return
     */
//    public char[] getCharWithValue(String key, String value) {
//        int result = 0;
//        switch (key) {
//            case "cameraMode":
//                switch (value) {
//                    case "photo":
//                        result = 0;
//                        break;
//                    case "video":
//                        result = 1;
//                        break;
//                }
//                break;
//            case "photoSize":
//                switch (value) {
//                    case "3MP":
//                        result = 1;
//                        break;
//                    case "5MP":
//                        result = 2;
//                        break;
//                    case "1080P":
//                        result = 0;
//                        break;
////                    case "12MP":
////                        result = 3;
////                    break;
////                    case "16Mp":
////                        result = 4;
////                    break;
////                    case "20MP":
////                        result = 5;
////                    break;
//                }
//                break;
//            case "photoBurst":
//                switch (value) {
//                    case "1photo":
//                        result = 0;
//                        break;
//                    case "2photos":
//                        result = 1;
//                        break;
//                    case "3photos":
//                        result = 2;
//                        break;
//                    case "4photos":
//                        result = 3;
//                        break;
//                    case "5photos":
//                        result = 4;
//                        break;
//                    case "6photos":
//                        result = 5;
//                        break;
//                    case "7photos":
//                        result = 6;
//                        break;
//                    case "8photos":
//                        result = 7;
//                        break;
//                    case "9photos":
//                        result = 8;
//                        break;
//                    case "10photos":
//                        result = 9;
//                        break;
//                }
//                break;
//            case "burstSpeed":
//                switch (value) {
//                    case "Fast(200ms)":
//                        result = 0;
//                        break;
//                    case "Show(500ms)":
//                        result = 1;
//                        break;
//                }
//                break;
//            case "sendingOption":
//                switch (value) {
//                    case "1st":
//                        result = 0;
//                        break;
//                    case "2st":
//                        result = 1;
//                        break;
//                    case "3st":
//                        result = 2;
//                        break;
//                    case "4st":
//                        result = 0;
//                        break;
//                    case "5st":
//                        result = 1;
//                        break;
//                    case "6st":
//                        result = 2;
//                        break;
//                    case "7st":
//                        result = 0;
//                        break;
//                    case "8st":
//                        result = 1;
//                        break;
//                    case "9st":
//                        result = 2;
//                        break;
//                    case "10st":
//                        result = 2;
//                        break;
//                }
//                break;
//            case "shutterSpeed":
//                switch (value) {
//                    case "Normal":
//                        result = 0;
//                        break;
//                    case "Fast":
//                        result = 1;
//                        break;
//                    case "High":
//                        result = 2;
//                        break;
//                }
//                break;
//            case "flashPower":
//                switch (value) {
//                    case "Low":
//                        result = 0;
//                        break;
//                    case "Normal":
//                        result = 1;
//                        break;
//                    case "High":
//                        result = 2;
//                        break;
//                }
//                break;
//            case "videoSize":
//                switch (value) {
//                    case "D1":
//                        result = 0;
//                    case "720P":
//                        result = 1;
//                        break;
//                    case "1080P":
//                        result = 2;
//                        break;
//                    case "1440P":
//                        result = 3;
//                        break;
//                }
//                break;
//            case "videoLength":
//                switch (value) {
//                    case "5sec":
//                        result = 0;
//                        break;
//                    case "10sec":
//                        result = 1;
//                        break;
//                    case "15sec":
//                        result = 2;
//                        break;
//                    case "20sec":
//                        result = 3;
//                        break;
//                    case "25sec":
//                        result = 4;
//                        break;
//                    case "30sec":
//                        result = 5;
//                        break;
//                    case "35sec":
//                        result = 6;
//                        break;
//                    case "40sec":
//                        result = 7;
//                        break;
//                    case "45sec":
//                        result = 8;
//                        break;
//                    case "50sec":
//                        result = 9;
//                        break;
//                    case "55sec":
//                        result = 10;
//                        break;
//                    case "60sec":
//                        result = 11;
//                        break;
//                }
//                break;
//            case "triggerSen":
//                switch (value) {
//                    case "off":
//                        result = 0;
//                        break;
//                    case "Low":
//                        result = 1;
//                        break;
//                    case "Auto":
//                        result = 2;
//                        break;
//                    case "High":
//                        result = 3;
//                        break;
//                }
//                break;
//            case "triggerPir":
//                System.out.println(value);
//                switch (value) {
//                    case "0Sec":
//                        result = 0;
//                        break;
//                    case "1Sec":
//                        result = 1;
//                        break;
//                    case "2Sec":
//                        result = 2;
//                        break;
//                    case "3Sec":
//                        result = 3;
//                        break;
//                    case "4Sec":
//                        result = 4;
//                        break;
//                    case "5Sec":
//                        result = 5;
//                        break;
//                    case "10Sec":
//                        result = 6;
//                        break;
//                    case "15Sec":
//                        result = 7;
//                        break;
//                    case "20Sec":
//                        result = 8;
//                        break;
//                    case "25Sec":
//                        result = 9;
//                        break;
//                    case "30Sec":
//                        result = 10;
//                        break;
//                    case "35Sec":
//                        result = 11;
//                        break;
//                    case "40Sec":
//                        result = 12;
//                        break;
//                    case "45Sec":
//                        result = 13;
//                        break;
//                    case "50Sec":
//                        result = 14;
//                        break;
//                    case "55Sec":
//                        result = 15;
//                        break;
//                    case "1min":
//                        result = 16;
//                        break;
//                    case "2min":
//                        result = 17;
//                        break;
//                    case "3min":
//                        result = 18;
//                        break;
//                    case "4min":
//                        result = 19;
//                        break;
//                    case "5min":
//                        result = 20;
//                        break;
//                    case "10min":
//                        result = 21;
//                        break;
//                    case "15min":
//                        result = 22;
//                        break;
//                    case "20min":
//                        result = 23;
//                        break;
//                    case "25min":
//                        result = 24;
//                        break;
//                    case "30min":
//                        result = 25;
//                        break;
//                    case "35min":
//                        result = 26;
//                        break;
//                    case "40min":
//                        result = 27;
//                        break;
//                    case "45min":
//                        result = 28;
//                        break;
//                    case "50min":
//                        result = 29;
//                        break;
//                    case "55min":
//                        result = 30;
//                        break;
//                    case "60min":
//                        result = 31;
//                        break;
//                }
//                break;
//            case "triggerTimelapse":
//                switch (value) {
////                    case "0Sec":
////                        return 0;
////                    break;
////                    case "1Sec":
////                        return 1;
////                    break;
////                    case "2Sec":
////                        return 2;
////                    break;
////                    case "3Sec":
////                        return 3;
////                    break;
////                    case "4Sec":
////                        return 4;
////                    break;
//                    case "5Sec":
//                        result = 1;
//                        break;
//                    case "10Sec":
//                        result = 2;
//                        break;
//                    case "15Sec":
//                        result = 3;
//                        break;
//                    case "20Sec":
//                        result = 4;
//                        break;
//                    case "25Sec":
//                        result = 5;
//                        break;
//                    case "30Sec":
//                        result = 6;
//                        break;
//                    case "35Sec":
//                        result = 7;
//                        break;
//                    case "40Sec":
//                        result = 8;
//                        break;
//                    case "45Sec":
//                        result = 9;
//                        break;
//                    case "50Sec":
//                        result = 10;
//                        break;
//                    case "55Sec":
//                        result = 11;
//                        break;
//                    case "1min":
//                        result = 12;
//                        break;
//                    case "2min":
//                        result = 13;
//                        break;
//                    case "3min":
//                        result = 14;
//                        break;
//                    case "4min":
//                        result = 15;
//                        break;
//                    case "5min":
//                        result = 16;
//                        break;
//                    case "10min":
//                        result = 17;
//                        break;
//                    case "15min":
//                        result = 18;
//                        break;
//                    case "20min":
//                        result = 19;
//                        break;
//                    case "25min":
//                        result = 20;
//                        break;
//                    case "30min":
//                        result = 21;
//                        break;
//                    case "35min":
//                        result = 22;
//                        break;
//                    case "40min":
//                        result = 23;
//                        break;
//                    case "45min":
//                        result = 24;
//                        break;
//                    case "50min":
//                        result = 25;
//                        break;
//                    case "55min":
//                        result = 26;
//                        break;
//                    case "1Hour":
//                        result = 27;
//                        break;
//                    case "2Hour":
//                        result = 28;
//                        break;
//                    case "3Hour":
//                        result = 29;
//                        break;
//                    case "4Hour":
//                        result = 30;
//                        break;
//                    case "5Hour":
//                        result = 31;
//                        break;
//                    case "6Hour":
//                        result = 32;
//                        break;
//                    case "7Hour":
//                        result = 33;
//                    case "8Hour":
//                        result = 34;
//                        break;
//                    case "12Hour":
//                        result = 35;
//                        break;
//                    case "20Hour":
//                        result = 36;
//                        break;
//                    case "24Hour":
//                        result = 37;
//                        break;
//                }
//                break;
//            case "sendMode":
//                int sendMode2 = Integer.parseInt(value);
//                result = sendMode2;
//                break;
//            case "remoteControl":
//                switch (value) {
//                    case "Realtime":
//                        result = 0;
//                        break;
//                    case "Delay 0.5H":
//                        result = 1;
//                        break;
//                    case "Delay 1H":
//                        result = 2;
//                        break;
//                    case "Delay 2H":
//                        result = 3;
//                        break;
//                    case "Delay 3H":
//                        result = 4;
//                        break;
//                    case "Delay 4H":
//                        result = 5;
//                        break;
//                    case "Delay 6H":
//                        result = 6;
//                        break;
//                    case "Delay 12H":
//                        result = 7;
//                        break;
//                    case "Delay 24H":
//                        result = 8;
//                        break;
//                }
//                break;
//            case "staName":
//                switch (value) {
//                    case "false":
//                        result = 0;
//                        break;
//                    case "true":
//                        result = 1;
//                        break;
//                }
//                break;
//            case "staPassword":
//                switch (value) {
//                    case "false":
//                        result = 0;
//                        break;
//                    case "true":
//                        result = 1;
//                        break;
//                }
//                break;
//        }
//        String text = Integer.toHexString(result);
//        if(text.length() == 1){
//            text = '0'+text;
//        }
//        return  text.toCharArray();
//    }

    //显示二维码数

    /**
     * 获取二维码
     *
     * @return
     */
//    public String getQRCode() {
//        char[] showValue = getCharCam();
//        String count = "";
//        for (int i = 0; i < showValue.length; i++) {
//            count += showValue[i];
//        }
//        return count;
//    }


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

    public void setProtected(boolean b) {
        this.protecte = b;
    }

    public Boolean getProtecte() {
        return protecte;
    }


    public int getVideoLengthIndex() {
        return videoLengthIndex;
    }

    public void setVideoLengthIndex(int videoLengthIndex) {
        this.videoLengthIndex = videoLengthIndex;
    }

    public int getVideoSizeIndex() {
        return videoSizeIndex;
    }

    //videoSize的选项；
    public void setVideoSizeIndex(int videoSizeIndex) {
        this.videoSizeIndex = videoSizeIndex;
    }

    public int getCameraModeIndex() {
        return cameraModeIndex;
    }

    public void setCameraModeIndex(int cameraModeIndex) {
        this.cameraModeIndex = cameraModeIndex;
    }

    public int getPhotoSizeIndex() {
        return photoSizeIndex;
    }

    public void setPhotoSizeIndex(int photoSizeIndex) {
        this.photoSizeIndex = photoSizeIndex;
    }

    public int getPhotoBurstIndex() {
        return photoBurstIndex;
    }

    public void setPhotoBurstIndex(int photoBurstIndex) {
        this.photoBurstIndex = photoBurstIndex;
    }

    public int getBurstSpeedIndex() {
        return burstSpeedIndex;
    }

    public void setBurstSpeedIndex(int burstSpeedIndex) {
        this.burstSpeedIndex = burstSpeedIndex;
    }

    public int getSendingOptionIndex() {
        return sendingOptionIndex;
    }

    public void setSendingOptionIndex(int sendingOptionIndex) {
        this.sendingOptionIndex = sendingOptionIndex;
    }

    public int getShutterSpeedIndex() {
        return shutterSpeedIndex;
    }

    public void setShutterSpeedIndex(int shutterSpeedIndex) {
        this.shutterSpeedIndex = shutterSpeedIndex;
    }

    public int getFlashPowerIndex() {
        return flashPowerIndex;
    }

    public void setFlashPowerIndex(int flashPowerIndex) {
        this.flashPowerIndex = flashPowerIndex;
    }

    public int getTriggerSenIndex() {
        return triggerSenIndex;
    }

    public void setTriggerSenIndex(int triggerSenIndex) {
        this.triggerSenIndex = triggerSenIndex;
    }

    public int getTriggerPirIndex() {
        return triggerPirIndex;
    }

    public void setTriggerPirIndex(int triggerPirIndex) {
        this.triggerPirIndex = triggerPirIndex;
    }

    public int getTriggerTimelapseIndex() {
        return triggerTimelapseIndex;
    }

    public void setTriggerTimelapseIndex(int triggerTimelapseIndex) {
        this.triggerTimelapseIndex = triggerTimelapseIndex;
    }

    public int getRemoteControlIndex() {
        return remoteControlIndex;
    }

    public void setRemoteControlIndex(int remoteControlIndex) {
        this.remoteControlIndex = remoteControlIndex;
    }


}
