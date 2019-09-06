package com.example.myapplication.com.example.myapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

import com.example.myapplication.DataApplication;
import com.example.myapplication.R;

public class SysActivity extends Activity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    private DataApplication dataApplication;
    private Switch switch_password, switch_rename,switch_overwrite;
    private EditText edit_password, edit_rename;
    private Button button_save;
    private String rename, password;
    private int overWrite,sta_rename,sta_password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sys);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    private void init() {
        switch_password = findViewById(R.id.switch_password);
        switch_rename = findViewById(R.id.switch_rename);
        switch_overwrite = findViewById(R.id.switch_overwrite);

        edit_password = findViewById(R.id.edit_password);
        edit_rename = findViewById(R.id.edit_rename);
        switch_password.setOnCheckedChangeListener(this);
        switch_rename.setOnCheckedChangeListener(this);
        switch_overwrite.setOnCheckedChangeListener(this);

        //设置rename只能输入数字和字母
        dataApplication = DataApplication.getDataApplication();
        button_save = findViewById(R.id.button_save);
        button_save.setOnClickListener(this);


        sta_rename = dataApplication.getStaRename();                //获取记录着的密码开关；
        sta_password = dataApplication.getStaPassword();
        overWrite = dataApplication.getOverWrite();

        if (sta_rename == 1) {                             //如果密码开关是开着的
            rename = dataApplication.getRename();
            edit_rename.setText(rename);
            Toast.makeText(this, "rename：" + rename, Toast.LENGTH_SHORT).show();
            switch_rename.setChecked(true);
        }
        if (sta_password == 1) {
            password = dataApplication.getPassword();
            edit_password.setText(password);
            switch_password.setChecked(true);
        }
        if(overWrite == 1){
            switch_overwrite.setChecked(true);
        }else{
            switch_overwrite.setChecked(false);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.switch_rename:
                dataApplication.setStaName(b);
                sta_rename=(b?1:0);
                //如果当前为on
                if (sta_rename == 1) {
                    Toast.makeText(this, "当前为on", Toast.LENGTH_SHORT).show();
                    edit_rename.setVisibility(View.VISIBLE);

                    dataApplication.setStaName(true);
                    edit_rename.setKeyListener(new DigitsKeyListener() {
                        @Override
                        public int getInputType() {
                            return InputType.TYPE_TEXT_VARIATION_PASSWORD;
                        }

                        @Override
                        protected char[] getAcceptedChars() {
                            char[] data = getStringData(R.string.wordAndNum).toCharArray();
                            return data;
                        }
                    });
                } else {
                    edit_rename.setVisibility(View.INVISIBLE);
                    edit_rename.setText("");
                    dataApplication.setStaName(false);
                }
                break;
            case R.id.switch_password:
                dataApplication.setStaPassword(b);
                sta_password=(b?1:0);
                if (sta_password == 1) {
                    edit_password.setVisibility(View.VISIBLE);
                    dataApplication.setStaPassword(true);               //设置密码开关状态
                } else {
                    edit_password.setVisibility(View.INVISIBLE);            //关掉开关
                    edit_password.setText("");
                    dataApplication.setStaName(false);
                }
                break;
            case R.id.switch_overwrite:
                dataApplication.setOverWrite(b);
                break;
        }
    }

    public String getStringData(int id) {
        return getResources().getString(id);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_save:

//                Log.i("当前rename",dataApplication.getRename());
//                Log.i("当前password",dataApplication.getPassword());
                rename = edit_rename.getText().toString();
                password = edit_password.getText().toString();
                if (sta_rename == 1) {      //如果是on模式
                    if(rename.length()==0){
                        dataApplication.setRename("uovision");
                    }else{
                        dataApplication.setRename(rename);
                    }
                }else{
                    dataApplication.setRename("uovision");
                }
                if (sta_password ==1 ) {
                    if(password.length()==0){
                        dataApplication.setPassword("0000");
                    }else{

                    dataApplication.setPassword(password);
                    }
                }else{
                    dataApplication.setPassword("0000");

                }
                break;
        }
    }
}