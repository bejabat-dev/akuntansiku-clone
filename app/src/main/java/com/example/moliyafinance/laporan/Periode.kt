package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityPeriodeBinding
import com.example.moliyafinance.databinding.DialogTanggalBinding

class Periode : AppCompatActivity() {
    private lateinit var bind: ActivityPeriodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityPeriodeBinding.inflate(layoutInflater)
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