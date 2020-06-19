package com.example.coursedesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.coursedesign.Model.AnswerBean;
import com.example.coursedesign.Model.QuestionBean;
import com.example.coursedesign.Model.ResponseResult;
import com.example.coursedesign.base.BaseActivity;
import com.example.coursedesign.internet.ApiService;
import com.example.coursedesign.utils.AppConstants;
import com.example.coursedesign.utils.CommonUtil;
import com.example.coursedesign.utils.ListAdapter;
import com.example.coursedesign.utils.RetrofitWapper;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeaAnsListActivity extends BaseActivity {


    Button tea_check_answer;
    Bundle bundle;
    ListView listView_one,listView_two;
    List<AnswerBean> answerBeans;
    List<QuestionBean> questionBeans;
    Boolean flag = true;

    String string;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_ans_list);

        bundle = this.getIntent().getExtras();

        String id = bundle.getString("id");

        listView_one = (ListView) findViewById(R.id.tea_question_list); //找到ListView布局
        listView_two = (ListView) findViewById(R.id.tea_answer_list); //找到ListView布局


        //得到自己负责的所有学生提交的所有问题

        Call<List<QuestionBean>> callQuestions = RetrofitWapper.getInstance().create(ApiService.class).getAllQuesByTeaId(id);

        callQuestions.enqueue(new Callback<List<QuestionBean>>() {
            @Override
            public void onResponse(Call<List<QuestionBean>> call, Response<List<QuestionBean>> response) {
                questionBeans = response.body();
                if (questionBeans != null) {
                    List<String> Contents = new ArrayList<>();
                    for (QuestionBean questionBean : questionBeans) {
                        Contents.add(questionBean.getStuContent());
                    }

                    ArrayAdapter<String> adapter_one = new ArrayAdapter<String>(TeaAnsListActivity.this, R.layout.list_text, R.id.ListTextView, Contents);
                    listView_one.setAdapter(adapter_one); //导入
                } else
                    CommonUtil.showToast(mContext, "查看所有问题有误！");
                Logger.i("login success server return code is:" + response.code());
            }

            @Override
            public void onFailure(Call<List<QuestionBean>> call, Throwable t) {
                CommonUtil.showToast(mContext, "连接不上服务器");
                Logger.i("login error:" + t.getMessage());
            }
        });


        //显示所有已回答的问题
        Call<List<AnswerBean>> callAnswers = RetrofitWapper.getInstance().create(ApiService.class).getAllAnsByTeaId(id);
        List<String> Contents = new ArrayList<>();
        callAnswers.enqueue(new Callback<List<AnswerBean>>() {
            @Override
            public void onResponse(Call<List<AnswerBean>> call, Response<List<AnswerBean>> response) {
                answerBeans = response.body();
                if (answerBeans != null) {
                    for (AnswerBean answerBean : answerBeans) {
                        Contents.add(answerBean.getTeaContent());
                    }
                    listView_two.setAdapter(new ListAdapter(TeaAnsListActivity.this,Contents));
                } else
                    CommonUtil.showToast(mContext, "查看所有回答有误！");
                Logger.i("login success server return code is:" + response.code());
            }

            @Override
            public void onFailure(Call<List<AnswerBean>> call, Throwable t) {
                CommonUtil.showToast(mContext, "连接不上服务器");
                Logger.i("login error:" + t.getMessage());
            }
        });


        //提交所有回答
        tea_check_answer = findViewById(R.id.tea_check_answer);
        tea_check_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answerBeans!= null) {

                    for (int i = 0; i < answerBeans.size(); i++) {
                        System.out.println(string);

                        //提交
                        Call<ResponseResult> putAns = RetrofitWapper.getInstance().create(ApiService.class).putAns(answerBeans.get(i).getQuesId(),answerBeans.get(i).getTeaContent());
                        putAns.enqueue(new Callback<ResponseResult>() {
                            @Override
                            public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {

                                System.out.println("response.code():"+response.code());
                                ResponseResult result = response.body();

                                if (result.getCode() != AppConstants.RESULT_SUCCESS) {
                                    flag = false;

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
                    if(flag == true) {
                        CommonUtil.showToast(mContext, "回答问题成功,返回主页面");
                        Intent intent = new Intent(TeaAnsListActivity.this, TeaIndexActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else  CommonUtil.showToast(mContext, "回答问题失败!");
                }

            }
        });

    }

    @Override
    public int getLayout() {
        return R.layout.activity_tea_ans_list;
    }

    public void saveEditData(int position, String str) {
        this.answerBeans.get(position).setTeaContent(str);
    }
}
