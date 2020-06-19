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

public class RootSTActivity extends BaseActivity {

    Button btn_stu_add_group;
    EditText edt_group_stu_number;
    EditText edt_group_tea_number;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_st);

        bundle = this.getIntent().getExtras();

        edt_group_tea_number = findViewById(R.id.edt_group_tea_number);
        edt_group_stu_number = findViewById(R.id.edt_group_stu_number);

        btn_stu_add_group = findViewById(R.id.btn_stu_add_group);

        btn_stu_add_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //数据库添加//
                Call<ResponseResult> bindST = RetrofitWapper.getInstance().create(ApiService.class).bindST(edt_group_stu_number.getText().toString(),edt_group_tea_number.getText().toString());
                bindST.enqueue(new Callback<ResponseResult>() {
                    @Override
                    public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {
                        ResponseResult result = response.body();

                        if (result.getCode() == AppConstants.RESULT_SUCCESS) {
                            CommonUtil.showToast(mContext, "绑定成功,返回主页面");
                            Intent intent = new Intent(RootSTActivity.this,RootIndexActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else CommonUtil.showToast(mContext, "绑定失败");
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
        return R.layout.activity_root_st;
    }
}
