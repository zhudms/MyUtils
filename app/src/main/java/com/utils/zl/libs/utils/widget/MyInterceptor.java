package com.utils.zl.libs.utils.widget;

import com.zxyilian.nurse.MyApplaction;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * retroift的拦截器
 * @param
 * @return
 * @time 2016/10/28
 * @auth TANG
 */
public     class MyInterceptor implements Interceptor{

        private String orgCode;
        private String wardCode;

        public MyInterceptor(String orgCode, String wardCode){
            this.orgCode=orgCode;
            this.wardCode=wardCode;

        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (MyApplaction.getmUser()== null) {
                return chain.proceed(request);
            }

            HttpUrl httpUrl = request.url().newBuilder()
                    .addQueryParameter("orgCode",orgCode)
                    .addQueryParameter("wardCode",wardCode)
                    .build();
            request = request.newBuilder().url(httpUrl).build();
            return chain.proceed(request);
        }
    }