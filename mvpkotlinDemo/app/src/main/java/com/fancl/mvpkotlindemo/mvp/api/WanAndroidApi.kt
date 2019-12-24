package com.fancl.mvpkotlindemo.mvp.api

import com.fancl.mvpkotlindemo.bean.LoginBean
import com.fancl.mvpkotlindemo.bean.ResponseWapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST



interface WanAndroidApi {


    @POST("user/register")
    @FormUrlEncoded
    fun regist(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): Observable<ResponseWapper<LoginBean>>


    @POST("user/login")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<ResponseWapper<LoginBean>>
}