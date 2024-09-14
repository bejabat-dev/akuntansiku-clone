package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityPerubahanModalBinding

class PerubahanModal : AppCompatActivity() {
    private lateinit var bind : ActivityPerubahanModalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityPerubahanModalBinding.inflate(layoutInflater)
        setContentView(bind.root)
    }
}