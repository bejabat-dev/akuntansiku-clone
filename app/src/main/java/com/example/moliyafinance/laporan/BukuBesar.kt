package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.adapters.TransaksiAdapter
import com.example.moliyafinance.adapters.getDistinctValues
import com.example.moliyafinance.adapters.groupTransaksiByValue
import com.example.moliyafinance.databinding.ActivityBukuBesarBinding
import com.example.moliyafinance.navigation.Dashboard

class BukuBesar : AppCompatActivity() {
    private lateinit var bind: ActivityBukuBesarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityBukuBesarBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
    }

    private fun init() {
        val transaksiList = Dashboard.listTransaksi

        val distinctValues = getDistinctValues(transaksiList)
        val groupedData = groupTransaksiByValue(transaksiList, distinctValues)

        bind.recycer.layoutManager = LinearLayoutManager(this)
        bind.recycer.adapter = TransaksiAdapter(groupedData)
    }
}