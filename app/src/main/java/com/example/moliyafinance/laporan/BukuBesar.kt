package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.adapters.AdapterBukuBesar
import com.example.moliyafinance.databinding.ActivityBukuBesarBinding
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.navigation.Dashboard
import com.example.moliyafinance.navigation.Dashboard.Companion.isLoaded

class BukuBesar : AppCompatActivity() {
    private lateinit var bind: ActivityBukuBesarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityBukuBesarBinding.inflate(layoutInflater)
        setContentView(bind.root)
        if (isLoaded) {
            init()
        }
    }

    private fun init() {
        val transaksiList = Dashboard.listTransaksi
        val listStringTransaksi = extractString(transaksiList)
        val innerTransaksi = ArrayList<List<Transaksi>>()

        for (s in listStringTransaksi) {
            val newData = transaksiList.filter { it.debit == s || it.kredit == s }
            innerTransaksi.add(newData)
        }
        val adapter = AdapterBukuBesar(listStringTransaksi,innerTransaksi)
        bind.recycer.adapter = adapter
        bind.recycer.layoutManager = LinearLayoutManager(this)
    }

    private fun extractString(transaksiList: List<Transaksi>): List<String> {
        val debitValues = transaksiList.map { it.debit }
        val kreditValues = transaksiList.map { it.kredit }
        val combinedValues = debitValues + kreditValues
        return combinedValues.distinct()
    }
}