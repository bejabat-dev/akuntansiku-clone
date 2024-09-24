package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityPerubahanModalBinding
import com.example.moliyafinance.databinding.DialogTanggalBinding

class PerubahanModal : AppCompatActivity() {
    private lateinit var bind : ActivityPerubahanModalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityPerubahanModalBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
    }

    private fun init(){
        bind.pilihTanggal.setOnClickListener {
            val dialogTanggalBinding = DialogTanggalBinding.inflate(layoutInflater)
        }
        bind.back.setOnClickListener {
            finish()
        }
    }
}