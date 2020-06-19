package com.example.coursedesign.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.coursedesign.MainActivity;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayout());
        ButterKnife.bind(this);
        mContext = this;
        Logger.init().hideThreadInfo().setLogLevel(LogLevel.FULL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * 加载布局的方法
     *
     * @return
     */
    public abstract int getLayout();

    public void logout(Context context){
        startActivity(new Intent(context, MainActivity.class));
    }
}
