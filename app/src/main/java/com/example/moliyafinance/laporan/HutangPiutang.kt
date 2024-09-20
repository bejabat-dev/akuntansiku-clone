package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityHutangPiutangBinding
import com.example.moliyafinance.databinding.DialogTanggalBinding
import com.example.moliyafinance.navigation.Dashboard

class HutangPiutang : AppCompatActivity() {

    private lateinit var bind: ActivityHutangPiutangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityHutangPiutangBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
    }

    private fun init() {
        bind.pilihTanggal.setOnClickListener {
            val dialogTanggalBinding = DialogTanggalBinding.inflate(layoutInflater)
            Dashboard.showDialog(this, dialogTanggalBinding)
        }
        bind.back.setOnClickListener {
            finish()
        }
    }
}