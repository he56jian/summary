package com.example.myapplication;

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

    //下拉列表的选择
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

    //把字符串转成ascii之后转成字符数组
    public static char[] strToCharList(String str,int length){
        byte[] bytes = str.getBytes();
        String ret = "";
        for(int i=0;i< bytes.length;i++){
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if(hex.length()==1){
                hex = "0" + hex;
            }
            ret+=hex;
        }
//        System.out.println(ret);
        char[] value = ret.toCharArray();
//        char[] result = new char[length];
//        if(value.length != 0 ){
//            for(int i=0;i<value.length;i++){
//                result[i] = value[i];
//            }
//        }
        return value;
    }
    //把整型转成字符数组
    public static char[] intToCharList(int num){
        String hex = num+"";
        if(hex.length() == 1){
            hex = "0"+hex;
        }
        char[] result = hex.toCharArray();
        return result;
    }


        //把字符串转成二进制数
    public static byte[] hex2byte(String str) {
        if (str == null){
            return null;
        }
        str = str.trim();
        int len = str.length();

        if (len == 0 || len % 2 == 1){
            return null;
        }
        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < str.length(); i += 2) {
                b[i / 2] = (byte) Integer.decode("0X" + str.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }


    // 二进制转字符串
    public static String byte2hex(byte[] b)
    {
        StringBuffer sb = new StringBuffer();
        String stmp = "";
        for (int i = 0; i < b.length; i++) {
            stmp = Integer.toHexString(b[i] & 0XFF);
            if (stmp.length() == 1){
                sb.append("0" + stmp);
            }else{
                sb.append(stmp);
            }

        }
        return sb.toString();
    }

    /**
     * byte数组转换为二进制字符串,每个字节以","隔开
     **/
    public static String byteArrToBinStr(byte[] b) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            result.append(Long.toString(b[i] & 0xff, 2) + ",");
        }
        return result.toString().substring(0, result.length() - 1);
    }

    //ping ip查看情况
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


    public void writeFile(View view){

    }

}