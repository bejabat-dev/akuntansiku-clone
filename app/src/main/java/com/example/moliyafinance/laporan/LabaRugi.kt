package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityLabaRugiBinding

class LabaRugi : AppCompatActivity() {
    private lateinit var bind : ActivityLabaRugiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLabaRugiBinding.inflate(layoutInflater)
        setContentView(bind.root)
    }

    private fun init(){

    }
}