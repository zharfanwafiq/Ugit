package com.orainjistudio.ugit.data.api

import com.orainjistudio.ugit.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {
    private const val timeOut = 50L
    fun getApiService():ApiService{
        val interceptor= if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }else{
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

     val okHttpClient = OkHttpClient.Builder()
         .addInterceptor(interceptor)
         .addInterceptor { chain ->
             val request = chain.request()
                 .newBuilder()
                 .addHeader("GITHUB_PAT", BuildConfig.GITHUB_TOKEN_PAT)
                 .build()
             return@addInterceptor chain.proceed(request)
         }
         .connectTimeout(timeOut, TimeUnit.SECONDS)
         .writeTimeout(timeOut,TimeUnit.SECONDS)
         .writeTimeout(timeOut, TimeUnit.SECONDS)
         .build()

        val retrofit:Retrofit = Retrofit.Builder()
            .baseUrl(UrlEndPoint.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}