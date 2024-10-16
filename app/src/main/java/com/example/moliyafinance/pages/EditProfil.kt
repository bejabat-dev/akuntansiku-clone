package com.example.moliyafinance.pages

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityEditProfilBinding
import com.example.moliyafinance.navigation.Dashboard

class EditProfil : AppCompatActivity() {
    private lateinit var bind: ActivityEditProfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityEditProfilBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
    }

    private fun init() {
        bind.back.setOnClickListener {
            finish()
        }

        val user = Dashboard.userData
        bind.nama.setText(user.nama)
        bind.email.setText(user.email)
        bind.nohp.setText(user.nohp)
    }
}