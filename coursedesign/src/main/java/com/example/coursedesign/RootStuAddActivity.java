package com.example.coursedesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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

public class RootStuAddActivity extends BaseActivity {

    Button btn_stu_add;
    EditText edt_stu_name;
    EditText edt_stu_college;
    EditText edt_stu_class;
    EditText edt_stu_number;
    EditText edt_stu_password;

    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_stu_add);

        bundle = this.getIntent().getExtras();

        edt_stu_name = findViewById(R.id.edt_stu_name);
        edt_stu_number = findViewById(R.id.edt_stu_number);
        edt_stu_college = findViewById(R.id.edt_stu_college);
        edt_stu_class = findViewById(R.id.edt_stu_class);
        edt_stu_password = findViewById(R.id.edt_stu_password);


        btn_stu_add = findViewById(R.id.btn_stu_add);

        btn_stu_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //数据库添加//

                final String stu_name = edt_stu_name.getText().toString();
                final String stu_number = edt_stu_number.getText().toString();
                final String stu_college = edt_stu_college.getText().toString();
                final String stu_class = edt_stu_class.getText().toString();
                final String stu_password = edt_stu_password.getText().toString();

                Call<ResponseResult> addStuBean = RetrofitWapper.getInstance().create(ApiService.class).addStu(stu_number,stu_name,stu_college,stu_class,stu_password);
                addStuBean.enqueue(new Callback<ResponseResult>() {
                    @Override
                    public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {

                        System.out.println("response.code():"+response.code());

                        ResponseResult result = response.body();

                        if (result.getCode() == AppConstants.RESULT_SUCCESS) {
                            CommonUtil.showToast(mContext, "添加学生成功,返回主页面");
                            Intent intent = new Intent(RootStuAddActivity.this,RootStuIndexActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            RootStuAddActivity.this.finish();

                        }else{
                            CommonUtil.showToast(mContext, "添加学生失败");
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
        return R.layout.activity_root_stu_add;
    }
}
