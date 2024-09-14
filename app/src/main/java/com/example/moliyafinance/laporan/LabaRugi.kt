package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.adapters.AdapterLabaRugi
import com.example.moliyafinance.databinding.ActivityLabaRugiBinding
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.models.formatToRupiah
import com.example.moliyafinance.navigation.Dashboard

class LabaRugi : AppCompatActivity() {
    private lateinit var bind: ActivityLabaRugiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLabaRugiBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
    }

    private fun init() {
        val transaksiList = Dashboard.listTransaksi
        val newList = ArrayList<Transaksi>()
        for (data in transaksiList) {
            if (data.kategoriKredit == "Pendapatan") {
                newList.add(data)
            }
        }
        val adapter = AdapterLabaRugi.AdapterPendapatan(newList)
        bind.innerPendapatanPenjualan.adapter = adapter
        bind.innerPendapatanPenjualan.layoutManager = LinearLayoutManager(this)
        bind.innerPendapatanPenjualan.post {
            val totalPendapatan = formatToRupiah(adapter.getTotal())
            bind.totalPendapatanPenjualan.text = totalPendapatan
            bind.pendapatanDariPenjualan.text = totalPendapatan
        }
    }
}