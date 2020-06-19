package com.example.coursedesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.coursedesign.base.BaseActivity;

public class RootIndexActivity extends BaseActivity {

    private Button btn_student;
    private Button btn_teacher;
    private Button btn_group;
    private Button btn_group_message;

    private Button root_logout;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_index);

        final Bundle bundle = this.getIntent().getExtras();

        btn_student=(Button) findViewById(R.id.btn_student);
        btn_teacher=(Button) findViewById(R.id.btn_teacher);
        btn_group=(Button) findViewById(R.id.btn_group);
        btn_group_message=(Button) findViewById(R.id.btn_group_message);

        root_logout =(Button) findViewById(R.id.root_logout);

        root_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(RootIndexActivity.this);
            }
        });

        btn_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RootIndexActivity.this,RootStuIndexActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        btn_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RootIndexActivity.this,RootTeaIndexActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RootIndexActivity.this, RootSTActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_group_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RootIndexActivity.this, RootInforActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public int getLayout() {
        return R.layout.activity_root_index;
    }
}
