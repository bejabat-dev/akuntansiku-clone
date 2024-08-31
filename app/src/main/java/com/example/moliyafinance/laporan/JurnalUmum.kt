package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityLaporanBinding

class JurnalUmum : AppCompatActivity() {
    private lateinit var bind : ActivityLaporanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLaporanBinding.inflate(layoutInflater)
        setContentView(bind.root)

    }

    private fun init(){

    }
}