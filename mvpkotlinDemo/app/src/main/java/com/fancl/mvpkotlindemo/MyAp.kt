package com.fancl.mvpkotlindemo

import android.app.Application
import com.fancl.mvpkotlindemo.network.ApiClient


class MyAp : Application() {

    override fun onCreate() {
        super.onCreate()
        ApiClient.instance.createRetrofit()

    }
}