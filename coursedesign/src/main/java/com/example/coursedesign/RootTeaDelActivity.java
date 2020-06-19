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

public class RootTeaDelActivity extends BaseActivity {

    Button btn_tea_delete;
    EditText edt_tea_number;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_tea_del);

        bundle = this.getIntent().getExtras();

        edt_tea_number = findViewById(R.id.edt_tea_number2);
        btn_tea_delete = findViewById(R.id.btn_tea_delete);

        btn_tea_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String tea_number = edt_tea_number.getText().toString();
                Call<ResponseResult> delteaBean = RetrofitWapper.getInstance().create(ApiService.class).delTea(tea_number);
                delteaBean.enqueue(new Callback<ResponseResult>() {
                    @Override
                    public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {

                        System.out.println("response.code():"+response.code());

                        ResponseResult result = response.body();

                        if (result.getCode() == AppConstants.RESULT_SUCCESS) {
                            CommonUtil.showToast(mContext, "删除老师成功,返回主页面");
                            Intent intent = new Intent(RootTeaDelActivity.this,RootTeaIndexActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            RootTeaDelActivity.this.finish();

                        }else{
                            CommonUtil.showToast(mContext, "删除老师失败");
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
        return R.layout.activity_root_tea_del;
    }
}
