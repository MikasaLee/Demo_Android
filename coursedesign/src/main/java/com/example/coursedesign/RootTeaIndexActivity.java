package com.example.coursedesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.coursedesign.Model.TeacherBean;
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

public class RootTeaIndexActivity extends BaseActivity {

    private Button btn_tea_add;
    private Button btn_tea_update;
    private Button btn_tea_delete;

    private ListView lv_tea_name,lv_tea_number,lv_tea_college,lv_tea_grade;


    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_tea_index);

        bundle = this.getIntent().getExtras();

        btn_tea_add=(Button) findViewById(R.id.btn_tea_add );
        btn_tea_update=(Button) findViewById(R.id.btn_tea_update);
        btn_tea_delete=(Button) findViewById(R.id.btn_tea_delete);

        lv_tea_name = findViewById(R.id.lv_tea_name);
        lv_tea_number = findViewById(R.id.lv_tea_number);
        lv_tea_college = findViewById(R.id.lv_tea_college);
        lv_tea_grade = findViewById(R.id.lv_tea_grade);
        
        
        //连接数据库 导入教师的信息//

        btn_tea_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RootTeaIndexActivity.this, RootTeaAddActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_tea_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RootTeaIndexActivity.this,RootTeaUpdActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_tea_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RootTeaIndexActivity.this,RootTeaDelActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //得到所有教师
        Call<List<TeacherBean>> call = RetrofitWapper.getInstance().create(ApiService.class).getTeas();
        call.enqueue(new Callback<List<TeacherBean>>() {
            @Override
            public void onResponse(Call<List<TeacherBean>> call, Response<List<TeacherBean>> response) {

                List<TeacherBean> TeacherBeans = response.body();
                if(TeacherBeans != null){
                    setActivity(TeacherBeans);
                }else{
                    CommonUtil.showToast(mContext, "教师信息错误");
                }
            }

            @Override
            public void onFailure(Call<List<TeacherBean>> call, Throwable t) {
                CommonUtil.showToast(mContext, "连接不上服务器");
                Logger.i("login error:" + t.getMessage());
            }
        });

    }

    @Override
    public int getLayout() {
        return R.layout.activity_root_tea_index;
    }

    public void setActivity(List<TeacherBean> TeacherBeans) {
        //设置页面

        List<String> Teas_name = new ArrayList<>();
        List<String> Teas_number = new ArrayList<>();
        List<String> Teas_college = new ArrayList<>();
        List<String> Teas_grade = new ArrayList<>();

        for(TeacherBean TeacherBean:TeacherBeans){
            Teas_name.add(TeacherBean.getTeaName());
            Teas_number.add(TeacherBean.getTeaId());
            Teas_college.add(TeacherBean.getCollegeName());
            Teas_grade.add(TeacherBean.getTeaGrade());
        }


        ArrayAdapter<String> adapter_Teas_name = new ArrayAdapter<String>(RootTeaIndexActivity.this, R.layout.list_text, R.id.ListTextView, Teas_name);
        ArrayAdapter<String> adapter_Teas_number = new ArrayAdapter<String>(RootTeaIndexActivity.this, R.layout.list_text, R.id.ListTextView, Teas_number);
        ArrayAdapter<String> adapter_Teas_college = new ArrayAdapter<String>(RootTeaIndexActivity.this, R.layout.list_text, R.id.ListTextView, Teas_college);
        ArrayAdapter<String> adapter_Teas_grade = new ArrayAdapter<String>(RootTeaIndexActivity.this, R.layout.list_text, R.id.ListTextView, Teas_grade);

        lv_tea_name.setAdapter(adapter_Teas_name); //导入
        lv_tea_number.setAdapter(adapter_Teas_number); //导入
        lv_tea_college.setAdapter(adapter_Teas_college); //导入
        lv_tea_grade.setAdapter(adapter_Teas_grade); //导入

    }
}
