package com.example.moliyafinance

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityLoginBinding
import com.example.moliyafinance.navigation.Dashboard

class Login : AppCompatActivity() {
    private lateinit var bind: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.login.setOnClickListener {
            val i = Intent(this,Dashboard::class.java)
            startActivity(i)
        }
    }
}