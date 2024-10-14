package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityPerubahanModalBinding
import com.example.moliyafinance.databinding.DialogTanggalBinding
import com.example.moliyafinance.models.PerubahanModal
import com.example.moliyafinance.navigation.Dashboard

class PerubahanModal : AppCompatActivity() {
    private lateinit var bind: ActivityPerubahanModalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityPerubahanModalBinding.inflate(layoutInflater)
        setContentView(bind.root)
        initClicks()
        init()
    }


    private fun initClicks() {
        bind.pilihTanggal.setOnClickListener {
            val dialogTanggalBinding = DialogTanggalBinding.inflate(layoutInflater)
        }
        bind.back.setOnClickListener {
            finish()
        }
    }

    private fun init() {
        val listPerubahan = ArrayList<PerubahanModal>()
        for (data in Dashboard.listTransaksi) {
            print(data)
            if (data.jenisTransaksi == "Tanam Modal") {
                val newData = PerubahanModal(data.debit, data.nominal, 0)
                listPerubahan.add(newData)
            }
            if (data.jenisTransaksi == "Tarik Modal") {
                val newData = PerubahanModal(data.kredit, 0, data.nominal)
                listPerubahan.add(newData)
            }
        }
        print(listPerubahan)
        print("loaded")
    }
}