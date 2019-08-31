package com.example.myapplication;

import android.app.Application;

public class DataApplication extends Application {
    private static final String VALUE = "hello";
    private String value;
    @Override
    public void onCreate() {
        super.onCreate();
        setValue(VALUE);

    }

    private void setValue(String value) {
        this.value = value;

    }


}
