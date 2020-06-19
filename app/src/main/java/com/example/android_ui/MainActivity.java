package com.example.android_ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    Button btn_LinearLayout;
    Button btn_RelativeLayout;
    Button btn_FrameLayout;
    Button btn_TextView;
    Button btn_Button;
    Button btn_qqLogin;
    Button btn_Dialog;
    Button btn_Style;
    Button btn_Test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_LinearLayout = findViewById(R.id.btn_linearLayout);
        btn_RelativeLayout = findViewById(R.id.btn_RelativeLayout);
        btn_FrameLayout = findViewById(R.id.btn_FrameLayout);
        btn_TextView = findViewById(R.id.btn_TextView);
        btn_Button = findViewById(R.id.btn_Button);
        btn_qqLogin = findViewById(R.id.btn_qqLogin);
        btn_Dialog = findViewById(R.id.btn_Dialog);
        btn_Style = findViewById(R.id.btn_Style);
        btn_Test = findViewById(R.id.btn_Test);

        addListener();
    }

    public void addListener(){
        if(btn_LinearLayout !=null) btn_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,linearLayout.class));
            }
        });
        if(btn_RelativeLayout !=null) btn_RelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,relativeLayout.class));
            }
        });
        if(btn_FrameLayout !=null) btn_FrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,frameLayout.class));
            }
        });
        if(btn_TextView !=null) btn_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, testTextView.class));
            }
        });
        if(btn_Button !=null) btn_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, testButton.class));
            }
        });
        if(btn_qqLogin !=null) btn_qqLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, qqLogin.class));
            }
        });
        if(btn_Dialog !=null) btn_Dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, testDialog.class));
            }
        });
        if(btn_Style !=null) btn_Style.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, testStyle.class));
            }
        });
        if(btn_Test !=null) btn_Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, testTest.class));
            }
        });
    }

}
