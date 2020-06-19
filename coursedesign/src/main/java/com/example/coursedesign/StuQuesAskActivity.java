package com.example.coursedesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.coursedesign.base.BaseActivity;
import com.example.coursedesign.internet.ApiService;
import com.example.coursedesign.utils.AppConstants;
import com.example.coursedesign.utils.CommonUtil;
import com.example.coursedesign.utils.RetrofitWapper;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.coursedesign.Model.ResponseResult;


public class StuQuesAskActivity extends BaseActivity {

    Button btn;
    EditText edt;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_ques_ask);

        bundle = this.getIntent().getExtras();

        edt = findViewById(R.id.edt);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String StuId = bundle.getString("id");
                String StuContent = edt.getText().toString();

                Call<ResponseResult> putQues = RetrofitWapper.getInstance().create(ApiService.class).putQues(StuId, StuContent);
                putQues.enqueue(new Callback<ResponseResult>() {
                    @Override
                    public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {
                        ResponseResult result = response.body();

                        if (result.getCode() == AppConstants.RESULT_SUCCESS) {
                            CommonUtil.showToast(mContext, "提交问题成功,返回主页面");
                            Intent intent = new Intent(StuQuesAskActivity.this,StuIndexActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else CommonUtil.showToast(mContext, "提交失败");
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
        return R.layout.activity_stu_ques_ask;
    };

}
