package com.example.moliyafinance

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.moliyafinance.databinding.DialogLoadingBinding

object LoadingDialog {
    lateinit var dialog: AlertDialog
    fun showDialog(context: Context,text:String?) {
        val bind = DialogLoadingBinding.inflate(LayoutInflater.from(context))
        val b = AlertDialog.Builder(context)
        b.setView(bind.root)
        if(text!=null){
            bind.text.text = text
        }
        dialog = b.setCancelable(false).create()
        dialog.show()
    }
}