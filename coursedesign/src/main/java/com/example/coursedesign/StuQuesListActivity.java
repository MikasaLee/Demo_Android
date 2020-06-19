package com.example.coursedesign;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.coursedesign.base.BaseActivity;
import com.example.coursedesign.internet.ApiService;
import com.example.coursedesign.utils.CommonUtil;
import com.example.coursedesign.utils.RetrofitWapper;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import com.example.coursedesign.Model.AnswerBean;
import com.example.coursedesign.Model.QuestionBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StuQuesListActivity extends BaseActivity {

    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_ques_list);

        bundle = this.getIntent().getExtras();

        String id = bundle.getString("id");


        //得到学生提交的所有问题
        Call<List<QuestionBean>> call = RetrofitWapper.getInstance().create(ApiService.class).getAllQues(id);

        call.enqueue(new Callback<List<QuestionBean>>() {
            @Override
            public void onResponse(Call<List<QuestionBean>> call, Response<List<QuestionBean>> response) {
                List<QuestionBean> questionBeans = response.body();
                if(questionBeans!=null){
                    List<String> Contents = new ArrayList<>();
                    for(QuestionBean questionBean:questionBeans){
                        Contents.add(questionBean.getStuContent());
                    }

                    ListView listView_one = (ListView) findViewById(R.id.question_list); //找到ListView布局
                    ArrayAdapter<String> adapter_one=new ArrayAdapter<String>(StuQuesListActivity.this, R.layout.list_text, R.id.ListTextView,Contents);
                    listView_one.setAdapter(adapter_one); //导入
                }else{
                    CommonUtil.showToast(mContext, "学生提交的问题错误");
                }
            }

            @Override
            public void onFailure(Call<List<QuestionBean>> call, Throwable t) {
                CommonUtil.showToast(mContext, "连接不上服务器");
                Logger.i("login error:" + t.getMessage());
            }
        });


        //得到老师关于每个问题的回答
        Call<List<AnswerBean>> call1 = RetrofitWapper.getInstance().create(ApiService.class).getAllAns(id);

        call1.enqueue(new Callback<List<AnswerBean>>() {
            @Override
            public void onResponse(Call<List<AnswerBean>> call, Response<List<AnswerBean>> response) {
                List<AnswerBean> answerBeans = response.body();
                if(answerBeans!=null){
                    List<String> Contents = new ArrayList<>();
                    for(AnswerBean answerBean:answerBeans){
                        Contents.add(answerBean.getTeaContent());
                    }

                    ListView listView_two= (ListView) findViewById(R.id.answer_list); //找到ListView布局
                    ArrayAdapter<String> adapter_two=new ArrayAdapter<String>(StuQuesListActivity.this, R.layout.list_text, R.id.ListTextView, Contents);
                    listView_two.setAdapter(adapter_two); //导入
                }else{
                    CommonUtil.showToast(mContext, "老师的回答错误");
                }
            }

            @Override
            public void onFailure(Call<List<AnswerBean>> call, Throwable t) {
                CommonUtil.showToast(mContext, "连接不上服务器");
                Logger.i("login error:" + t.getMessage());
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_stu_ques_list;
    }
}
