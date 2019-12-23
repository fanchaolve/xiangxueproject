package com.fancl.mvpkotlindemo.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fancl.mvpkotlindemo.R

class LoginActivity :AppCompatActivity(){

    companion object{//伴生对象 final static
        val TAG = "fancl"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}