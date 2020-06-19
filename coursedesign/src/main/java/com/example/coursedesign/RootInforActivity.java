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

public class RootInforActivity extends BaseActivity {

    EditText edt_notice;
    Button btn_realease_notice;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_infor);


        bundle = this.getIntent().getExtras();

        edt_notice = findViewById(R.id.edt_edt);
        btn_realease_notice = findViewById(R.id.btn_btn);

        btn_realease_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inforContent = edt_notice.getText().toString();

                Call<ResponseResult> setInfor = RetrofitWapper.getInstance().create(ApiService.class).setInfo(inforContent);
                setInfor.enqueue(new Callback<ResponseResult>() {
                    @Override
                    public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {

                        System.out.println("response.code():"+response.code());


                        ResponseResult result = response.body();

                        if (result.getCode() == AppConstants.RESULT_SUCCESS) {
                            CommonUtil.showToast(mContext, "发布公告成功,返回主页面");
                            Intent intent = new Intent(RootInforActivity.this,RootIndexActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            RootInforActivity.this.finish();
                        }else{
                            CommonUtil.showToast(mContext, "发布公告失败");
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
        return R.layout.activity_root_infor;
    }
}
