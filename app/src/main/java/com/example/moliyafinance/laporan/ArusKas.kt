package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.Utils
import com.example.moliyafinance.databinding.ActivityArusKasBinding
import com.example.moliyafinance.databinding.DialogTanggalBinding
import com.example.moliyafinance.navigation.Dashboard

class ArusKas : AppCompatActivity() {
    private lateinit var bind: ActivityArusKasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityArusKasBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
    }

    private fun init() {
        bind.back.setOnClickListener {
            finish()
        }
        bind.pilihTanggal.setOnClickListener {
            val dialogTanggalBinding = DialogTanggalBinding.inflate(layoutInflater)
            Utils().showDateDialog(this, dialogTanggalBinding)
        }
        if (Dashboard.date.isNotEmpty()) {
            bind.hari.text = Dashboard.date
        }
    }
}