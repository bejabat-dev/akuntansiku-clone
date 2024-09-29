package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.adapters.AdapterPeriode
import com.example.moliyafinance.databinding.ActivityPeriodeBinding
import com.example.moliyafinance.databinding.DialogTanggalBinding
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.navigation.Dashboard

class Periode : AppCompatActivity() {
    private lateinit var bind: ActivityPeriodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityPeriodeBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
    }

    data class Periode(val kategori: String, val innerAdapter: List<Transaksi>)

    private fun init() {
        bind.pilihTanggal.setOnClickListener {
            val dialogTanggalBinding = DialogTanggalBinding.inflate(layoutInflater)
        }
        bind.back.setOnClickListener {
            finish()
        }

        val kategori = ArrayList<String>()
        val periodeList = ArrayList<Periode>()

        for (data in Dashboard.listTransaksi) {
            kategori.add(data.debit)
            kategori.add(data.kredit)
        }
        kategori.sort()
        for (data in kategori) {
            val innerList = ArrayList<Transaksi>()
            for (newData in Dashboard.listTransaksi) {
                if (newData.debit == data || newData.kredit == data) {
                    innerList.add(newData)
                }
            }
            val periode = Periode(data, innerList)
            periodeList.add(periode)
        }
        val adapter = AdapterPeriode(this, periodeList)
        bind.recycler.adapter = adapter
        bind.recycler.layoutManager = LinearLayoutManager(this)

    }
}