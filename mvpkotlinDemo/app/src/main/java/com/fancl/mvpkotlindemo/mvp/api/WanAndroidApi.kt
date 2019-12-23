package com.fancl.mvpkotlindemo.mvp.api

import com.fancl.mvpkotlindemo.bean.LoginBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable


interface WanAndroidApi {


    @POST("user/register")
    @FormUrlEncoded
    fun regist(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): Observable<LoginBean>


    @POST("user/login")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<LoginBean>
}