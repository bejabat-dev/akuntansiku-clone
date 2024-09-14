package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityBebanOperasionalBinding

class BebanOperasional : AppCompatActivity() {

    private lateinit var bind : ActivityBebanOperasionalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityBebanOperasionalBinding.inflate(layoutInflater)
        setContentView(bind.root)
    }

    private fun init(){

    }
}