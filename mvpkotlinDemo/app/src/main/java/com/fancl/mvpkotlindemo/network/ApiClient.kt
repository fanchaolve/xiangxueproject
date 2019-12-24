package com.fancl.mvpkotlindemo.network

import com.fancl.mvpkotlindemo.BuildConfig
import com.fancl.mvpkotlindemo.mvp.api.WanAndroidApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory



class ApiClient private constructor() {

    lateinit var retrofit: Retrofit
    lateinit var service: WanAndroidApi

    private object Holder {
        val INSTANCE = ApiClient()
    }

    companion object {
        val instance by lazy {
            Holder.INSTANCE
        }
    }

    fun createRetrofit() {
//        val okhttpClient = OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor().setLevel(
//               if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
//                else
//                   HttpLoggingInterceptor.Level.NONE
//            )).build()

        val okHttpClient2 = OkHttpClient.Builder()
            .apply {
                addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                        if (BuildConfig.DEBUG)
                            HttpLoggingInterceptor.Level.BODY
                        else
                            HttpLoggingInterceptor.Level.NONE
                    )
                )

            }.build()

        retrofit = Retrofit.Builder()
            .apply {
                baseUrl("https://www.wanandroid.com/")
                addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                addConverterFactory(GsonConverterFactory.create())
            }.client(okHttpClient2).build()

        service = retrofit.create(WanAndroidApi::class.java)
    }

    fun <T> getService(service: Class<T>): T {
        return retrofit.create(service)
    }
}