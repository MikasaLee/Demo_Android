package com.example.android_ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class testDialog extends AppCompatActivity {

    Button btn_dialog1;
    Button btn_dialog2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        btn_dialog1 = findViewById(R.id.btn_dialog1);
        btn_dialog2 = findViewById(R.id.btn_dialog2);
        btn_dialog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog1();
            }
        });

        btn_dialog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog2();
            }
        });
    }
    public void Dialog1(){
        //为什么先定义的对话框后显示??
        //普通对话框
        new AlertDialog.Builder(this).setTitle("Dialog对话框")
                .setMessage("是否确定退出")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("确定",null)
                .setNegativeButton("取消",null)
                .show();

        //单选对话框
        new AlertDialog.Builder(this)
                .setTitle("请选择性别")
                .setSingleChoiceItems(new String[]{"男","女"},0,new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setPositiveButton("确定",null).show();

        //多选对话框
        new AlertDialog.Builder(this)
                .setTitle("请添加兴趣爱好")
                .setIcon(R.mipmap.ic_launcher)
                .setMultiChoiceItems(new String[]{"旅游","游泳","美食"},new boolean[]{false,true,false},null)
                .setPositiveButton("确定",null)
                .show();

        //进度条对话框
        ProgressDialog pro = new ProgressDialog(this);
        pro.setTitle("进度条对话框");
        pro.setIcon(R.mipmap.ic_launcher);
        pro.setMessage("正在下载请稍后。。");
        pro.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  //水平进度条
        pro.show();

        //消息对话框
        Toast.makeText(this,"Hello,Toast",Toast.LENGTH_SHORT).show();

    }
    //自定义对话框
    public void Dialog2(){
        MyDialog myDialog = new MyDialog(this,"我是自定义的dialog");
        myDialog.show();
    }
}
