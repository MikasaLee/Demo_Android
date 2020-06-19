package com.example.android_ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MyDialog extends Dialog {

    private String dialogName;
    private TextView tvMsg;
    private Button btnOK;
    private Button btnCancel;

    MyDialog(Context context,String dialogName){
        super(context);
        this.dialogName = dialogName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //去除标题
        setContentView(R.layout.my_dialog);             //引入自定义对话框
        tvMsg = findViewById(R.id.tv_title);
        btnOK = findViewById(R.id.btn_ok);
        btnCancel = findViewById(R.id.btn_cancel);

        tvMsg.setText(dialogName);          //设置自定义对话框显示内容
        //为确定按钮设置点击事件
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //为取消按钮设置点击事件
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();          //关闭当前对话框
            }
        });


    }
}
