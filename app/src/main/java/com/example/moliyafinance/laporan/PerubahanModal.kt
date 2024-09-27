package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityPerubahanModalBinding
import com.example.moliyafinance.databinding.DialogTanggalBinding
import com.example.moliyafinance.navigation.Dashboard

class PerubahanModal : AppCompatActivity() {
    private lateinit var bind: ActivityPerubahanModalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityPerubahanModalBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
    }

    data class PerubahanModal(val namaAkun: String, val debit: Int, val kredit: Int)

    private fun init() {
        bind.pilihTanggal.setOnClickListener {
            val dialogTanggalBinding = DialogTanggalBinding.inflate(layoutInflater)
        }
        bind.back.setOnClickListener {
            finish()
        }

        val adapterModal = ArrayList<PerubahanModal>()

        for (data in Dashboard.listTransaksi) {
            if (data.kategoriDebit == "Modal") {
                val modal = PerubahanModal(data.debit, data.nominal, 0)
                adapterModal.add(modal)
            } else if (data.kategoriKredit == "Modal") {
                val modal = PerubahanModal(data.kredit, 0, data.nominal)
                adapterModal.add(modal)
            }
        }
    }
}