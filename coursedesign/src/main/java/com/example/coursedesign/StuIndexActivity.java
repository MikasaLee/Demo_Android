package com.example.coursedesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.coursedesign.Model.InformationBean;
import com.example.coursedesign.Model.StudentBean;
import com.example.coursedesign.base.BaseActivity;
import com.example.coursedesign.internet.ApiService;
import com.example.coursedesign.utils.CommonUtil;
import com.example.coursedesign.utils.RetrofitWapper;
import com.orhanobut.logger.Logger;
import com.example.coursedesign.Model.GroupMessageBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StuIndexActivity extends BaseActivity {

    private Button myButton_one;
    private Button myButton_two;
    private Button stu_logout;

    Bundle bundle;
    private TextView stu_name,stu_number,stu_college,stu_class,stu_info,stu_groupmessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_index);

        bundle = this.getIntent().getExtras();

        myButton_one=findViewById(R.id.question);
        myButton_two=findViewById(R.id.answer);
        stu_logout = findViewById(R.id.stu_logout);

        stu_name = findViewById(R.id.stu_name);
        stu_number = findViewById(R.id.stu_number);
        stu_college = findViewById(R.id.stu_college);
        stu_class = findViewById(R.id.stu_class);
        stu_groupmessage = findViewById(R.id.stu_groupmessage);
        stu_info = findViewById(R.id.stu_info);

        stu_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               logout(StuIndexActivity.this);
            }
        });

        myButton_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {startQues();}
        });

        myButton_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAns();
            }
        });

        myButton_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAns();
            }
        });


        //得到学生
        Call<StudentBean> call = RetrofitWapper.getInstance().create(ApiService.class).getStu(bundle.getString("id"));

        call.enqueue(new Callback<StudentBean>() {
            @Override
            public void onResponse(Call<StudentBean> call, Response<StudentBean> response) {

                StudentBean studentBean = response.body();
                if(studentBean != null){
                    setActivity(studentBean);
                }else{
                    CommonUtil.showToast(mContext, "学生信息错误");
                }
            }

            @Override
            public void onFailure(Call<StudentBean> call, Throwable t) {
                CommonUtil.showToast(mContext, "连接不上服务器");
                Logger.i("login error:" + t.getMessage());
            }
        });

    }

    public void setActivity(StudentBean studentBean) {
        //设置页面
        //学生信息
        stu_name.setText(studentBean.getStuName());
        stu_number.setText(studentBean.getStuId());
        stu_college.setText(studentBean.getCollegeName());
        stu_class.setText(studentBean.getStuClass());

        //公告
        Call<InformationBean> callinfo = RetrofitWapper.getInstance().create(ApiService.class).getInfo();
        callinfo.enqueue(new Callback<InformationBean>() {
            @Override
            public void onResponse(Call<InformationBean> call, Response<InformationBean> response) {
                InformationBean informationBean = response.body();

                System.out.println("学生信息response.code():" + response.code());
                if (informationBean != null) {
                    stu_info.setText(informationBean.getInforContent());
                } else {
                    CommonUtil.showToast(mContext, "公告信息错误");
                }
            }

            @Override
            public void onFailure(Call<InformationBean> call, Throwable t) {
                CommonUtil.showToast(mContext, "连接不上服务器");
                Logger.i("login error:" + t.getMessage());
            }
        });

        //群消息
        Call<GroupMessageBean> callgm = RetrofitWapper.getInstance().create(ApiService.class).getGM(bundle.getString("id"));
        callgm.enqueue(new Callback<GroupMessageBean>() {
            @Override
            public void onResponse(Call<GroupMessageBean> call, Response<GroupMessageBean> response) {
                GroupMessageBean groupMessageBean = response.body();
                if (groupMessageBean != null) {
                    stu_groupmessage.setText(groupMessageBean.getgMContent());
                } else {
                    CommonUtil.showToast(mContext, "群发信息错误");
                }
            }

            @Override
            public void onFailure(Call<GroupMessageBean> call, Throwable t) {
                CommonUtil.showToast(mContext, "连接不上服务器");
                Logger.i("login error:" + t.getMessage());
            }
        });

    }
    public void startQues(){
        Intent intent_one=new Intent(StuIndexActivity.this, StuQuesAskActivity.class);
        intent_one.putExtras(bundle);
        startActivity(intent_one);
    }

    public void startAns(){
        Intent intent_two=new Intent(StuIndexActivity.this, StuQuesListActivity.class);
        intent_two.putExtras(bundle);
        startActivity(intent_two);
    }

    @Override
    public int getLayout() { return R.layout.activity_stu_index; }
}
