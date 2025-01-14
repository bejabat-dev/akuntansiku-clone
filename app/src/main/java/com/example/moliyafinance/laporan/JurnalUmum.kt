package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.Utils
import com.example.moliyafinance.adapters.AdapterJurnalUmum
import com.example.moliyafinance.databinding.ActivityJurnalUmumBinding
import com.example.moliyafinance.databinding.DialogTanggalBinding

import com.example.moliyafinance.navigation.Dashboard
import com.example.moliyafinance.navigation.Dashboard.Companion.isLoaded

class JurnalUmum : AppCompatActivity() {
    private lateinit var bind: ActivityJurnalUmumBinding
    private lateinit var dialogTanggalBinding: DialogTanggalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityJurnalUmumBinding.inflate(layoutInflater)
        setContentView(bind.root)
        if (isLoaded) {
            init()
            initClicks()
        }
    }

    private fun init() {
        if (Dashboard.date.isNotEmpty()) {
            bind.tanggal.text = Dashboard.date
        }
        val adapter = AdapterJurnalUmum(Dashboard.listTransaksi)
        Utils().fadeIn(bind.recycler)
        bind.back.setOnClickListener {
            finish()
        }
        bind.recycler.adapter = adapter
        bind.recycler.layoutManager = LinearLayoutManager(this)

        bind.recycler.post {
            bind.totalDebit.text = Utils().formatToRupiah(adapter.getTotalDebit())
            bind.totalKredit.text = Utils().formatToRupiah(adapter.getTotalKredit())
        }
    }

    private fun initClicks() {
        bind.pilihTanggal.setOnClickListener {
            dialogTanggalBinding = DialogTanggalBinding.inflate(layoutInflater)
            Utils().showDateDialog(this, dialogTanggalBinding)
        }
    }
}