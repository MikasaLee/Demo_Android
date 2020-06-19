package com.example.android_ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class testTextView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview);

        EditText et = findViewById(R.id.et_1);
        TextView tv = findViewById(R.id.tv_1);
        tv.setText(et.getText());

    }
}
