package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityNeracaBinding
import com.example.moliyafinance.databinding.DialogTanggalBinding

class Neraca : AppCompatActivity() {
    private lateinit var bind: ActivityNeracaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityNeracaBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
    }

    private fun init() {
        bind.pilihTanggal.setOnClickListener {
            val dialogTanggalBinding = DialogTanggalBinding.inflate(layoutInflater)
        }
        bind.back.setOnClickListener {
            finish()
        }
    }
}