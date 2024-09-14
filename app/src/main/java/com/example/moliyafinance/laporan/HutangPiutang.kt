package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityHutangPiutangBinding

class HutangPiutang : AppCompatActivity() {

    private lateinit var bind: ActivityHutangPiutangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityHutangPiutangBinding.inflate(layoutInflater)
        setContentView(bind.root)
    }

    private fun init() {

    }
}