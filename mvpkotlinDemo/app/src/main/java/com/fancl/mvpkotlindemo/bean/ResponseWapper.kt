package com.fancl.mvpkotlindemo.bean


data class ResponseWapper<T>(
    var errorCode: Int?,
    var errorMsg: String?,
    var data: T
)