package com.example.moliyafinance.pages

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityAsetBinding

class Aset : AppCompatActivity() {
    private lateinit var bind : ActivityAsetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityAsetBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
    }
    private fun init(){
        bind.back.setOnClickListener {
            finish()
        }
    }
}