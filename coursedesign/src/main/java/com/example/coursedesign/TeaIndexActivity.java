package com.example.coursedesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.coursedesign.Model.GroupMessageBean;
import com.example.coursedesign.Model.InformationBean;
import com.example.coursedesign.Model.ResponseResult;
import com.example.coursedesign.Model.StudentBean;
import com.example.coursedesign.Model.TeacherBean;
import com.example.coursedesign.base.BaseActivity;
import com.example.coursedesign.internet.ApiService;
import com.example.coursedesign.utils.AppConstants;
import com.example.coursedesign.utils.CommonUtil;
import com.example.coursedesign.utils.RetrofitWapper;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeaIndexActivity extends BaseActivity {

    private Button myButton_one;
    private Button myButton_two;
    private Button tea_logout;

    Bundle bundle;

    private TextView tea_name,tea_number,tea_college,tea_grade,tea_info;
    EditText tea_groupmessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_index);

        bundle = this.getIntent().getExtras();

        myButton_one=(Button) findViewById(R.id.tea_message);
        myButton_two=(Button) findViewById(R.id.tea_question);
        tea_logout=(Button)findViewById(R.id.tea_logout);

        tea_name = findViewById(R.id.tea_name);
        tea_number = findViewById(R.id.tea_number);
        tea_college = findViewById(R.id.tea_college);
        tea_grade = findViewById(R.id.tea_grade);
        tea_groupmessage = findViewById(R.id.tea_groupmessage);
        tea_info = findViewById(R.id.tea_info);


        //发布消息
        myButton_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gMContent = tea_groupmessage.getText().toString();

                Call<ResponseResult> putGroupMessage = RetrofitWapper.getInstance().create(ApiService.class).putGroupMessage(bundle.getString("id"),gMContent);
                putGroupMessage.enqueue(new Callback<ResponseResult>() {
                    @Override
                    public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {

                        System.out.println("response.code():"+response.code());


                        ResponseResult result = response.body();

                        if (result.getCode() == AppConstants.RESULT_SUCCESS) {
                            CommonUtil.showToast(mContext, "发布群消息成功!");
                        }else{
                            CommonUtil.showToast(mContext, "发布群消息失败!");
                        }
                        Logger.i("login success server return code is:" + response.code());
                    }

                    @Override
                    public void onFailure(Call<ResponseResult> call, Throwable t) {
                        CommonUtil.showToast(mContext, "连接不上服务器");
                        Logger.i("login error:" + t.getMessage());
                    }
                });

            }
        });

        //跳转到问题列表
        myButton_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQues();
            }
        });


        //logout
        tea_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(TeaIndexActivity.this);
            }
        });


        //得到老师
        Call<TeacherBean> call = RetrofitWapper.getInstance().create(ApiService.class).getTea(bundle.getString("id"));

        call.enqueue(new Callback<TeacherBean>() {
            @Override
            public void onResponse(Call<TeacherBean> call, Response<TeacherBean> response) {

                TeacherBean teacherBean = response.body();
                if(teacherBean != null){
                    setActivity(teacherBean);
                }else{
                    CommonUtil.showToast(mContext, "学生信息错误");
                }
            }

            @Override
            public void onFailure(Call<TeacherBean> call, Throwable t) {
                CommonUtil.showToast(mContext, "连接不上服务器");
                Logger.i("login error:" + t.getMessage());
            }
        });
    }

    public void setActivity(TeacherBean teacherBean){
        //设置页面
        //学生信息
        tea_name.setText(teacherBean.getTeaName());
        tea_number.setText(teacherBean.getTeaId());
        tea_college.setText(teacherBean.getCollegeName());
        tea_grade.setText(teacherBean.getTeaGrade());

        //得到公告
        //公告
        Call<InformationBean> callinfo = RetrofitWapper.getInstance().create(ApiService.class).getInfo();
        callinfo.enqueue(new Callback<InformationBean>() {
            @Override
            public void onResponse(Call<InformationBean> call, Response<InformationBean> response) {
                InformationBean informationBean = response.body();

                System.out.println("学生信息response.code():"+response.code());
                if(informationBean!=null){
                    tea_info.setText(informationBean.getInforContent());
                }else{
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
        Call<GroupMessageBean> callgm = RetrofitWapper.getInstance().create(ApiService.class).getGMByTea(bundle.getString("id"));
        callgm.enqueue(new Callback<GroupMessageBean>() {
            @Override
            public void onResponse(Call<GroupMessageBean> call, Response<GroupMessageBean> response) {
                GroupMessageBean groupMessageBean = response.body();
                if(groupMessageBean!=null){
                    tea_groupmessage.setText(groupMessageBean.getgMContent());
                }else{
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
    @Override
    public int getLayout() {
        return R.layout.activity_tea_index;
    }

    public void startQues(){
        Intent intent_two=new Intent(TeaIndexActivity.this, TeaAnsListActivity.class);
        intent_two.putExtras(bundle);
        startActivity(intent_two);
    }
}
