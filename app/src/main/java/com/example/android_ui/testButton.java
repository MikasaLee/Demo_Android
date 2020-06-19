package com.example.android_ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class testButton extends AppCompatActivity{

    Button btn_one;
    Button btn_two;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        btn_one = findViewById(R.id.btn_one);
        btn_two = findViewById(R.id.btn_two);
        btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_two.setText("Button2 has been clicked");
            }
        });
        rg = findViewById(R.id.rg1);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rb1){
                    ((TextView)findViewById(R.id.tv_1)).setText("your sex:Man");
                }else{
                    ((TextView)findViewById(R.id.tv_1)).setText("your sex:Woman");
                }
            }
        });


    }
    //通过实现onClick()方法，实现按钮1的点击事件
    public void click(View v){        //方法名必须与layout中的onCLick值相同
        btn_one.setText("Button1 has been clicked");
    }
}
