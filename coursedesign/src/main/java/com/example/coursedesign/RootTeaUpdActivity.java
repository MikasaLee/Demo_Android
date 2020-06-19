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

public class RootTeaUpdActivity extends BaseActivity {

    EditText edt_tea_number;
    EditText edt_tea_name;
    EditText edt_tea_college;
    EditText edt_tea_grade;
    Button btn_tea_update;
    EditText edt_tea_password;

    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_tea_upd);

        bundle = this.getIntent().getExtras();

        edt_tea_number = findViewById(R.id.edt_tea_number1);
        edt_tea_name = findViewById(R.id.edt_tea_name1);
        edt_tea_college = findViewById(R.id.edt_tea_college1);
        edt_tea_grade = findViewById(R.id.edt_tea_grade1);
        edt_tea_password = findViewById(R.id.edt_tea_password1);

        btn_tea_update = findViewById(R.id.btn_tea_update1);

        btn_tea_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String tea_name = edt_tea_name.getText().toString();
                final String tea_number = edt_tea_number.getText().toString();
                final String tea_college = edt_tea_college.getText().toString();
                final String tea_grade = edt_tea_grade.getText().toString();
                final String tea_password = edt_tea_password.getText().toString();


                Call<ResponseResult> updteaBean = RetrofitWapper.getInstance().create(ApiService.class).updTea(tea_number,tea_name,tea_college,tea_grade,tea_password);
                updteaBean.enqueue(new Callback<ResponseResult>() {
                    @Override
                    public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {

                        System.out.println("response.code():"+response.code());

                        ResponseResult result = response.body();

                        if (result.getCode() == AppConstants.RESULT_SUCCESS) {
                            CommonUtil.showToast(mContext, "修改老师成功,返回主页面");
                            Intent intent = new Intent(RootTeaUpdActivity.this,RootTeaIndexActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            RootTeaUpdActivity.this.finish();

                        }else{
                            CommonUtil.showToast(mContext, "修改老师失败");
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
        return R.layout.activity_root_tea_upd;
    }
}
