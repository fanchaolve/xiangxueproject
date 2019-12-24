package com.fancl.mvpkotlindemo.network

import com.fancl.mvpkotlindemo.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient private constructor(){

    private object  Holder{
        val INSTANCE = ApiClient()
    }
    companion object{
        val instance by lazy {
            Holder.INSTANCE
        }
    }

    fun createRetrofit(){
//        val okhttpClient = OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor().setLevel(
//               if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
//                else
//                   HttpLoggingInterceptor.Level.NONE
//            )).build()

        val okHttpClient2 = OkHttpClient.Builder()
            .apply {
                addInterceptor(HttpLoggingInterceptor().setLevel(
                    if(BuildConfig.DEBUG)
                        HttpLoggingInterceptor.Level.BODY
                    else
                        HttpLoggingInterceptor.Level.NONE
                ))

            }.build()

        val retrofit = Retrofit.Builder()
            .apply {
                baseUrl("")
                addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                addConverterFactory(GsonConverterFactory.create())
            }.client(okHttpClient2)
    }
}