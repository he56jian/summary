package com.example.myapplication.BaseMethods;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.myapplication.Database.DataApplication;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    private Context context;
    public Utils(Context context) {
        this.context = context;
        dataApplication = new DataApplication().getDataApplication();
    }

    private DataApplication dataApplication;
    private ArrayAdapter<String> adapter;
    private String sInfo;

    /**
     * 下拉列表的监听；
     * @param list_ele 包含下拉列表项的集合
     * @param spinnerEle 进行监听的spinner控件；
     * @param name
     */
    public void listSelect(final List<String> list_ele, Spinner spinnerEle, final String name) {
        //第二步：为下拉列表定义一个适配器
        adapter = new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_item, list_ele);
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
                dataApplication.setValue(name, sInfo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sInfo = adapterView.getItemAtPosition(0).toString();
                dataApplication.setValue(name, sInfo);
            }
        });
    }

    private static final String BIN_SEPARATOR = " ";
    //把字符串转成二进制字符串
    public static String toBinaryString(String str) {
        if (str == null) return null;
        StringBuffer sb = new StringBuffer();           //创建一个StringBuffer类
        byte[] bytes = str.getBytes();                  //把字符串转成一个个字符的ASCII码值
        for (byte aByte : bytes) {
            String a = Integer.toBinaryString(aByte);
            sb.append(Integer.toBinaryString(aByte));
        }
        return sb.toString();
    }

    //把字符串转成字符数组
//    public static char[] stringToCharList(String str) {
//        if (str == null) return null;
//        StringBuffer sb = new StringBuffer();           //创建一个StringBuffer类
//        byte[] bytes = str.getBytes();                  //把字符串转成一个个字符的ASCII码值
//        for (byte aByte : bytes) {
//            String a = Integer.toBinaryString(aByte);
//            sb.append(Integer.toBinaryString(aByte));
//        }
//    }

//    //把传进来的值转成对应字符数组
//    public static char[] strToCharList(Object object){
//        return null;
//    }

    //合并字符数组
    public static char[] method(String now, List<char[]> list) {
        for(int i=0;i<list.size();i++){
            for(int j=0;j<list.get(i).length;j++)
                //获取了每一个char[]
                now += list.get(i)[j];
        }
        char[] result = now.toCharArray();
        return result;
    }
//    两个字符数组合并
    public char[] addChar(char[] arr, char[] arr2){
       String text =  charToString(arr) + charToString(arr2);
        char [] value = text.toCharArray();
        return value;
    }

    //把字符数组转换成字符串
    public String charToString(char[] arr){
        String text ="";
        for(int i = 0; i < arr.length; i++){
            text += arr[i];
        }
        String value = text;
        return value;
    }

//    //把字符串转成ascii之后转成字符数组
//    public static char[] strToCharList(String str,int length){
//        byte[] bytes = str.getBytes();
//        String ret = "";
//        for(int i=0;i< bytes.length;i++){
//            String hex = Integer.toHexString(bytes[i] & 0xFF);
//            if(hex.length()==1){
//                hex = "0" + hex;
//            }
//            ret+=hex;
//        }
//        char[] value = ret.toCharArray();
//        return value;
//    }
    //把整型转成字符数组
    public static char[] intToCharList(int num){
        String hex = num+"";
        if(hex.length() == 1){
            hex = "0"+hex;
        }
        char[] result = hex.toCharArray();
        return result;
    }


    /***
     * 定义char 数组长度，不够补0；
     * * @param oldChar
     * @param length
     * @return
     */
    public char[] addZeorChar(char[] oldChar,int length){
        char[] newChar = new char[length];
        int oldLen = oldChar.length;
        for(int i=0;i<length;i++){
            if(i> oldLen-1){
                newChar[i] = 0;
            }else{
                newChar[i] = oldChar[i];
            }
        }
        return newChar;
    }


    /**
     * ping ip,查看当前是否和ip相连；
     * @param ip
     */
    public void isAvailableByPing(String ip) {
                Toast.makeText(context,"查看IP界面",Toast.LENGTH_SHORT).show();
        //网络操作应在子线程中操作，避免阻塞UI线程，导致ANR
        new Thread(new Runnable() {
            @Override
            public void run() {
                PingNetEntity pingNetEntity = new PingNetEntity(ip, 3, 5, new StringBuffer());
                pingNetEntity = PingNet.ping(pingNetEntity);
                msgPing = "连接时间:"+pingNetEntity.getPingTime()+";"+"连接结果："+pingNetEntity.isResult();
//                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
//                System.out.println("testPing"+ pingNetEntity.getIp());
//                System.out.println("testPing"+ "time=" + pingNetEntity.getPingTime());
//                System.out.println("ping:"+ pingNetEntity.isResult() + "");
                pingResult = pingNetEntity.isResult();
                if(pingResult){
                    subHandler.sendEmptyMessage(4);
                }else{
                    subHandler.sendEmptyMessage(4);
                }
            }
        }).start();
    }
    String msgPing;
    Boolean pingResult;
    private String status;
    private String message;

    /**
     * 用于在多线程用提醒用户
     */
    public Handler subHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    message = "成功连接到了"+dataApplication.getIP() + ":"+ dataApplication.getPORT();
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    break;
                case 0:
                    status = "连接失败";
                    message = "连接"+dataApplication.getIP() + ":"+ dataApplication.getPORT() +"状态："+ status;
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    status = "正在连接";
//                    message = "连接"+dataApplication.getIP() + ":"+ dataApplication.getPORT() +"状态："+ status;
                    Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    status = "重新连接两次失败,ping:"+dataApplication.getIP() + ":"+dataApplication.getPORT() +",情况为："+pingResult;
                    Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(context, msgPing, Toast.LENGTH_SHORT).show();
                    break;
                case 99:
                    Toast.makeText(context,"测试专用",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    /**
     * 生成固定大小的二维码(不需网络权限)
     *
     * @param content 需要生成的内容
     * @param width   二维码宽度
     * @param height  二维码高度
     * @return
     */
    public Bitmap generateBitmap(String content, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将时间戳转换为时间
     */
    public String dateToStamp(long s) {
        String res;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(s);
            res = simpleDateFormat.format(date);
        } catch (Exception e) {
            return "";
        }
        return res;
    }


    /**
     * 设置下拉选项框的默认值
     * @param spinner
     * @param value
     */
    public void setSpinnerDefaultValue(Spinner spinner, String value) {
        SpinnerAdapter apsAdapter = spinner.getAdapter();
        int size = apsAdapter.getCount();
        for (int i = 0; i < size; i++) {
            if (value.equals(apsAdapter.getItem(i).toString())) {
                System.out.println(i);
                spinner.setSelection(i,true);
                break;
            }
        }
    }

    //获取固定长度的字符，不足后面补0
    public static String formatStr(String str, int length) {
        int strLen;
        if (str == null) {
            strLen = 0;
        }else{
            strLen= str.length();
        }

        if (strLen == length) {
            return str;
        } else if (strLen < length) {
            int temp = length - strLen;
            String tem = "";
            for (int i = 0; i < temp; i++) {
                tem = tem + '0';
            }
            return str + tem;
        }else{
            return str.substring(0,length);
        }
    }

    /**
     * 字节数组的拼接
     * @param bt1 拼接在前面的数组
     * @param bt2 拼接在后面的数组
     * @return  拼接后的数组
     */
    public static byte[] byteMerger(byte[] bt1, byte[] bt2){
        byte[] bt3 = new byte[bt1.length+bt2.length];
        System.arraycopy(bt1, 0, bt3, 0, bt1.length);
        System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
        return bt3;
    }

    /**
     * 字节数组的打印
     * @param byteArray 需要打印的字节数组
     * @return
     */
    public static String toHexString(byte[] byteArray) {
        if (byteArray == null || byteArray.length < 1)
            throw new IllegalArgumentException("this byteArray must not be null or empty");
        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) < 0x10)//0~F前面不零
                hexString.append("0");
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString().toLowerCase();
    }

    /**
     * 把字符串转成固定长度字节数组，不足的前面补零
     */
    public static byte[] createByte(String oldString,int len){
        byte[] result = new byte[len];
        for(int i =0;i<len;i++){
            if(i<oldString.length()){
                result[i] = oldString.getBytes()[i];
            }else{
                result[i]=0;
            }
        }
        return result;
    }


}