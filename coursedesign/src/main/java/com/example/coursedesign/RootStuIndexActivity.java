package com.example.coursedesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.coursedesign.Model.GroupMessageBean;
import com.example.coursedesign.Model.InformationBean;
import com.example.coursedesign.Model.StudentBean;
import com.example.coursedesign.base.BaseActivity;
import com.example.coursedesign.internet.ApiService;
import com.example.coursedesign.utils.CommonUtil;
import com.example.coursedesign.utils.RetrofitWapper;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RootStuIndexActivity extends BaseActivity {

    private Button btn_stu_add;
    private Button btn_stu_update;
    private Button btn_stu_delete;

    private ListView lv_stu_name,lv_stu_number,lv_stu_college,lv_stu_class;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_stu_index);

        bundle = this.getIntent().getExtras();

        btn_stu_add = (Button) findViewById(R.id.btn_stu_add);
        btn_stu_update = (Button) findViewById(R.id.btn_stu_update);
        btn_stu_delete = (Button) findViewById(R.id.btn_stu_delete);

        lv_stu_name = findViewById(R.id.lv_stu_name);
        lv_stu_number = findViewById(R.id.lv_stu_number);
        lv_stu_college = findViewById(R.id.lv_stu_college);
        lv_stu_class = findViewById(R.id.lv_stu_class);


        //连接数据库 导入学生的信息//

        btn_stu_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RootStuIndexActivity.this, RootStuAddActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_stu_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RootStuIndexActivity.this, RootStuUpdActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_stu_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RootStuIndexActivity.this, RootStuDelActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



        //得到所有学生
        Call<List<StudentBean>> call = RetrofitWapper.getInstance().create(ApiService.class).getStus();
        call.enqueue(new Callback<List<StudentBean>>() {
            @Override
            public void onResponse(Call<List<StudentBean>> call, Response<List<StudentBean>> response) {

                List<StudentBean> studentBeans = response.body();
                if(studentBeans != null){
                    setActivity(studentBeans);
                }else{
                    CommonUtil.showToast(mContext, "学生信息错误");
                }
            }

            @Override
            public void onFailure(Call<List<StudentBean>> call, Throwable t) {
                CommonUtil.showToast(mContext, "连接不上服务器");
                Logger.i("login error:" + t.getMessage());
            }
        });
    }

    public void setActivity(List<StudentBean> studentBeans) {
        //设置页面

        List<String> stus_name = new ArrayList<>();
        List<String> stus_number = new ArrayList<>();
        List<String> stus_college = new ArrayList<>();
        List<String> stus_class = new ArrayList<>();

        for(StudentBean studentBean:studentBeans){
            stus_name.add(studentBean.getStuName());
            stus_number.add(studentBean.getStuId());
            stus_college.add(studentBean.getCollegeName());
            stus_class.add(studentBean.getStuClass());
        }


        ArrayAdapter<String> adapter_stus_name = new ArrayAdapter<String>(RootStuIndexActivity.this, R.layout.list_text, R.id.ListTextView, stus_name);
        ArrayAdapter<String> adapter_stus_number = new ArrayAdapter<String>(RootStuIndexActivity.this, R.layout.list_text, R.id.ListTextView, stus_number);
        ArrayAdapter<String> adapter_stus_college = new ArrayAdapter<String>(RootStuIndexActivity.this, R.layout.list_text, R.id.ListTextView, stus_college);
        ArrayAdapter<String> adapter_stus_class = new ArrayAdapter<String>(RootStuIndexActivity.this, R.layout.list_text, R.id.ListTextView, stus_class);

        lv_stu_name.setAdapter(adapter_stus_name); //导入
        lv_stu_number.setAdapter(adapter_stus_number); //导入
        lv_stu_college.setAdapter(adapter_stus_college); //导入
        lv_stu_class.setAdapter(adapter_stus_class); //导入

    }

    @Override
    public int getLayout() { return R.layout.activity_stu_index; }
}
