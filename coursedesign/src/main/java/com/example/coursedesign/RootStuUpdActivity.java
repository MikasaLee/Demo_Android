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

public class RootStuUpdActivity extends BaseActivity {

    EditText edt_stu_number;
    EditText edt_stu_name;
    EditText edt_stu_college;
    EditText edt_stu_class;
    Button btn_stu_update;
    EditText edt_stu_password;

    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_stu_upd);

        bundle = this.getIntent().getExtras();

        edt_stu_number = findViewById(R.id.edt_stu_number1);
        edt_stu_name = findViewById(R.id.edt_stu_name1);
        edt_stu_college = findViewById(R.id.edt_stu_college1);
        edt_stu_class = findViewById(R.id.edt_stu_class1);
        edt_stu_password = findViewById(R.id.edt_stu_password1);

        btn_stu_update = findViewById(R.id.btn_stu_update1);

        btn_stu_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String stu_name = edt_stu_name.getText().toString();
                final String stu_number = edt_stu_number.getText().toString();
                final String stu_college = edt_stu_college.getText().toString();
                final String stu_class = edt_stu_class.getText().toString();
                final String stu_password = edt_stu_password.getText().toString();


                Call<ResponseResult> updStuBean = RetrofitWapper.getInstance().create(ApiService.class).updStu(stu_number,stu_name,stu_college,stu_class,stu_password);
                updStuBean.enqueue(new Callback<ResponseResult>() {
                    @Override
                    public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {

                        System.out.println("response.code():"+response.code());

                        ResponseResult result = response.body();

                        if (result.getCode() == AppConstants.RESULT_SUCCESS) {
                            CommonUtil.showToast(mContext, "修改学生成功,返回主页面");
                            Intent intent = new Intent(RootStuUpdActivity.this,RootStuIndexActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            RootStuUpdActivity.this.finish();

                        }else{
                            CommonUtil.showToast(mContext, "修改学生失败");
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
        return R.layout.activity_root_stu_upd;
    }
}
