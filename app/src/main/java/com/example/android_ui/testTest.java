package com.example.android_ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class testTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_test);
        Log.v("testTest.java","Verbose");
        Log.d("testTest.java","Debug");
        Log.i("testTest.java","Info");
        Log.w("testTest.java","Warning");
        Log.e("testTest.java","Error");
    }
}
