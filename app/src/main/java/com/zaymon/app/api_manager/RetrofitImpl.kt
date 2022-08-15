package com.zaymon.app.api_manager

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitImpl {

    companion object{
        val interceptor = HttpLoggingInterceptor()


        val client =  OkHttpClient
        .Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(interceptor).build();

        val retrofit =   Retrofit.Builder()
        .baseUrl("https://www.google.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    }
}