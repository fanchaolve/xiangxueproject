package com.fancl.mvpkotlindemo.network

import android.content.Context
import com.fancl.mvpkotlindemo.R
import kotlinx.android.synthetic.main.activity_login.view.*

enum class ApiErrorType(val code: Int, private val messageId: Int) {


    INTERNAL_SERVER_ERROR(500, R.string.service_error),
    BAD_GATEWAY(502, R.string.service_error),
    NOT_FOUND(404, R.string.not_found),
    CONNECT_TIMEOUT(408, R.string.network_error),
    CONNECT_NOT_CONNECT(499, R.string.network_error),
    UNKOWN_ERROR(700, R.string.unknown_error);

    fun getApiError(context: Context): ApiError {
        return ApiError(this.code, context.getString(this.messageId))
    }
}