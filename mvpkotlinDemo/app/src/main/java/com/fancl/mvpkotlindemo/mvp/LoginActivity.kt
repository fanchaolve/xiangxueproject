package com.fancl.mvpkotlindemo.mvp

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.fancl.mvpkotlindemo.R
import com.fancl.mvpkotlindemo.bean.LoginBean
import com.fancl.mvpkotlindemo.mvp.api.WanAndroidApi
import com.fancl.mvpkotlindemo.network.ApiClient
import com.fancl.mvpkotlindemo.network.ApiError
import com.fancl.mvpkotlindemo.network.ApiResponse
import com.fancl.mvpkotlindemo.network.NetworkScheduler
import io.reactivex.Observer

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    companion object {
        //伴生对象 final static
        val TAG = "fancl"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.setOnClickListener(onClickListener)
    }

    private val onClickListener = OnClickListener {
        when (it.id) {
            R.id.login -> {
                ApiClient.instance.getService(WanAndroidApi::class.java)
                    .login(username.text.toString(), password.text.toString()).compose(
                        NetworkScheduler.compose()
                    )
                    .subscribe(object : ApiResponse<LoginBean>(context = this) {
                        override fun success(data: LoginBean?) {

                        }

                        override fun failure(statusCode: Int, apiError: ApiError) {
                        }

                    })
            }
        }
    }
}


