package com.fancl.mvpkotlindemo.view

import android.app.Dialog
import android.content.Context
import com.fancl.mvpkotlindemo.R


object LoadingDialog {
    private var dialog:Dialog? =null

    fun show(context: Context){
        cancel()
        dialog = Dialog(context)
        dialog?.let {
            it.setContentView(R.layout.activity_login)
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)

        }

    }

    fun cancel(){
        dialog?.cancel()
    }
}