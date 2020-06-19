package com.example.coursedesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button stu_btn,tea_btn,root_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stu_btn = findViewById(R.id.btn_student_enter);
        tea_btn = findViewById(R.id.btn_teacher_enter);
        root_btn = findViewById(R.id.btn_root_enter);
        addListener();
    }

    public void addListener(){
        stu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stu_btn != null){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("role","student");
                    intent.putExtras(bundle);

                    startActivity(intent);
                }
            }
        });
        tea_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tea_btn != null){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("role","teacher");
                    intent.putExtras(bundle);

                    startActivity(intent);
                }
            }
        });
        root_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(root_btn != null){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("role","root");
                    intent.putExtras(bundle);

                    startActivity(intent);
                }
            }
        });



    }
}
