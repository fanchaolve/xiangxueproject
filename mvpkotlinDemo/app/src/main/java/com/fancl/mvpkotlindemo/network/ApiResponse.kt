package com.fancl.mvpkotlindemo.network

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.fancl.mvpkotlindemo.bean.ResponseWapper
import com.fancl.mvpkotlindemo.view.LoadingDialog
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException


import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


abstract class ApiResponse<T>(private val context: Context) : Observer<
        ResponseWapper<T>> {

    abstract fun success(data: T?)
    abstract fun failure(statusCode: Int, apiError: ApiError)

    private var isShowing: Boolean = true

    constructor(isShowing: Boolean = false, context: Context)
            : this(context) {
        this.isShowing = isShowing
    }

    override fun onSubscribe(d: Disposable) {
        if (!isShowing) {
            LoadingDialog.show(context)
        }
    }

    override fun onNext(t: ResponseWapper<T>) {

        success(t.data)
    }

    override fun onError(e: Throwable) {
        LoadingDialog.cancel()
        if (e is HttpException) {
            val apiError = when (e.code()) {
                ApiErrorType.INTERNAL_SERVER_ERROR.code -> {
                    ApiErrorType.INTERNAL_SERVER_ERROR.getApiError(context)
                }
                ApiErrorType.BAD_GATEWAY.code -> {
                    ApiErrorType.BAD_GATEWAY.getApiError(context)
                }
                ApiErrorType.NOT_FOUND.code -> {
                    ApiErrorType.NOT_FOUND.getApiError(context)
                }
                else -> otherError(e)
            }
            failure(e.code(), apiError)
            return
        }

        val apiErrorType = when (e) {
            is UnknownHostException -> ApiErrorType.UNKOWN_ERROR
            is ConnectException -> ApiErrorType.CONNECT_NOT_CONNECT
            is SocketTimeoutException -> ApiErrorType.CONNECT_TIMEOUT
            else -> ApiErrorType.UNKOWN_ERROR
        }
        failure(apiErrorType.code, apiErrorType.getApiError(context))
    }

    private fun otherError(e: HttpException): ApiError {
        return Gson().fromJson(e.response()?.errorBody()?.charStream(), ApiError::class.java)

    }

    override fun onComplete() {
        LoadingDialog.cancel()
    }


}