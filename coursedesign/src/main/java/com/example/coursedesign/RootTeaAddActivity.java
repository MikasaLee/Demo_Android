package com.example.coursedesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.coursedesign.Model.ResponseResult;
import com.example.coursedesign.base.BaseActivity;
import com.example.coursedesign.internet.ApiService;
import com.example.coursedesign.utils.AppConstants;
import com.example.coursedesign.utils.CommonUtil;
import com.example.coursedesign.utils.RetrofitWapper;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RootTeaAddActivity extends BaseActivity {

    Button btn_tea_add;
    EditText edt_tea_name;
    EditText edt_tea_college;
    EditText edt_tea_grade;
    EditText edt_tea_number;
    EditText edt_tea_password;

    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_tea_add);

        bundle = this.getIntent().getExtras();

        edt_tea_name = findViewById(R.id.edt_tea_name);
        edt_tea_number = findViewById(R.id.edt_tea_number);
        edt_tea_college = findViewById(R.id.edt_tea_college);
        edt_tea_grade = findViewById(R.id.edt_tea_grade);
        edt_tea_password = findViewById(R.id.edt_tea_password);


        btn_tea_add = findViewById(R.id.btn_tea_add);

        btn_tea_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //数据库添加//

                final String tea_name = edt_tea_name.getText().toString();
                final String tea_number = edt_tea_number.getText().toString();
                final String tea_college = edt_tea_college.getText().toString();
                final String tea_grade = edt_tea_grade.getText().toString();
                final String tea_password = edt_tea_password.getText().toString();

                Call<ResponseResult> addteaBean = RetrofitWapper.getInstance().create(ApiService.class).addTea(tea_number,tea_name,tea_college,tea_grade,tea_password);
                addteaBean.enqueue(new Callback<ResponseResult>() {
                    @Override
                    public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {

                        System.out.println("response.code():"+response.code());

                        ResponseResult result = response.body();

                        if (result.getCode() == AppConstants.RESULT_SUCCESS) {
                            CommonUtil.showToast(mContext, "添加老师成功,返回主页面");
                            Intent intent = new Intent(RootTeaAddActivity.this,RootTeaIndexActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            RootTeaAddActivity.this.finish();

                        }else{
                            CommonUtil.showToast(mContext, "添加老师失败");
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
    }

    @Override
    public int getLayout() {
        return R.layout.activity_root_tea_add;
    }
}
