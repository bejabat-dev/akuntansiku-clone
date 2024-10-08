package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityArusKasBinding
import com.example.moliyafinance.databinding.DialogTanggalBinding

class ArusKas : AppCompatActivity() {
    private lateinit var bind : ActivityArusKasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityArusKasBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
    }

    private fun init(){
        bind.back.setOnClickListener {
            finish()
        }
        bind.pilihTanggal.setOnClickListener {
            val dialogTanggalBinding = DialogTanggalBinding.inflate(layoutInflater)
        }
    }
}