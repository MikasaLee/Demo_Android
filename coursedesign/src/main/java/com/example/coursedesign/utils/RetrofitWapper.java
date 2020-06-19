package com.example.coursedesign.utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitWapper {

    private Retrofit mRetrofit;
    private static RetrofitWapper mRetrofitWapper;

    private RetrofitWapper() {
        mRetrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(AppConstants.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    public static RetrofitWapper getInstance() {
        if (mRetrofitWapper == null) {
            synchronized (RetrofitWapper.class) {
                if (mRetrofitWapper == null) {
                    mRetrofitWapper = new RetrofitWapper();
                }
            }
        }
        return mRetrofitWapper;
    }

    /**
     * 生成service对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}
