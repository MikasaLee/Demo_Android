package com.example.coursedesign;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.coursedesign.Model.AnswerBean;
import com.example.coursedesign.Model.GroupMessageBean;
import com.example.coursedesign.Model.InformationBean;
import com.example.coursedesign.Model.QuestionBean;
import com.example.coursedesign.Model.ResponseResult;
import com.example.coursedesign.Model.StudentBean;

import com.example.coursedesign.base.BaseActivity;
import com.example.coursedesign.internet.ApiService;
import com.example.coursedesign.utils.AppConstants;
import com.example.coursedesign.utils.CommonUtil;
import com.example.coursedesign.utils.RetrofitWapper;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends BaseActivity {

    EditText et_id,et_password;
    Button btn_login;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);

        bundle = this.getIntent().getExtras();
        addListener();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    public void addListener() {



        //确认身份

        if(bundle != null) {
            String role = bundle.getString("role");
            if (role !=null){
                /**
                 * 6.24 ： 只做了学生的绑定用作测试，老师和管理员需要数据库建表齐全后再一次性做好
                 */

                 switch (role){
                    case "student":
                        //对不同的身份进行绑定
                        //登录绑定
                        btn_login.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (btn_login != null) {
                                    //验证学生登录

                                    final String id = et_id.getText().toString();
                                    final String password = et_password.getText().toString();
                                    System.out.println("id:"+id+" password:"+password);

                                    Call<ResponseResult> loginStuBean = RetrofitWapper.getInstance().create(ApiService.class).loginStu(id, password);
                                    loginStuBean.enqueue(new Callback<ResponseResult>() {
                                        @Override
                                        public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {

                                            System.out.println("response.code():"+response.code());


                                            ResponseResult result = response.body();

                                            if (result.getCode() == AppConstants.RESULT_SUCCESS) {
                                                startStuMain(id);
                                            }else{
                                                CommonUtil.showToast(mContext, "用户名或密码错误");
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
                            }
                        });


                        break;
                    case "teacher":

                        //对不同的身份进行绑定
                        //登录绑定
                        btn_login.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (btn_login != null) {
                                    //验证老师登录

                                    final String id = et_id.getText().toString();
                                    final String password = et_password.getText().toString();
                                    System.out.println("id:"+id+" password:"+password);
                                    Call<ResponseResult> loginTeaBean = RetrofitWapper.getInstance().create(ApiService.class).loginTea(id,password);
                                    loginTeaBean.enqueue(new Callback<ResponseResult>() {
                                        @Override
                                        public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {

                                            System.out.println("response.code():"+response.code());


                                            ResponseResult result = response.body();

                                            if (result.getCode() == AppConstants.RESULT_SUCCESS) {
                                                startTeaMain(id);
                                            }else{
                                                CommonUtil.showToast(mContext, "用户名或密码错误");
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
                            }
                        });

                        break;
                    case "root":
                        //对不同的身份进行绑定
                        //登录绑定
                        btn_login.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (btn_login != null) {
                                    //验证管理员登录

                                    final String id = et_id.getText().toString();
                                    final String password = et_password.getText().toString();
                                    System.out.println("id:"+id+" password:"+password);
                                    Call<ResponseResult> loginRoot = RetrofitWapper.getInstance().create(ApiService.class).loginRoot(id,password);
                                    loginRoot.enqueue(new Callback<ResponseResult>() {
                                        @Override
                                        public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {

                                            System.out.println("response.code():"+response.code());


                                            ResponseResult result = response.body();

                                            if (result.getCode() == AppConstants.RESULT_SUCCESS) {
                                                startRootMain(id);
                                            }else{
                                                CommonUtil.showToast(mContext, "用户名或密码错误");
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
                            }
                        });

                        break;

                    default:
                        Log.e("LoginActivity","login user not found!");
                }
            }
        }


    }
    private void startStuMain(String stuId) {
        //OK

        Intent intent = new Intent(LoginActivity.this, StuIndexActivity.class);
        //多加一个id用来识别不同学生
        bundle.putString("id", stuId);
        intent.putExtras(bundle);

        startActivity(intent);
        this.finish();
    }

    private void startTeaMain(String stuId) {
        //OK

        Intent intent = new Intent(LoginActivity.this, TeaIndexActivity.class);
        //多加一个id用来识别不同教师
        bundle.putString("id", stuId);
        intent.putExtras(bundle);

        startActivity(intent);
        this.finish();
    }

    private void startRootMain(String stuId) {
        //OK

        Intent intent = new Intent(LoginActivity.this, RootIndexActivity.class);
        //多加一个id用来识别不同管理员
        bundle.putString("id", stuId);
        intent.putExtras(bundle);

        startActivity(intent);
        this.finish();
    }
}
