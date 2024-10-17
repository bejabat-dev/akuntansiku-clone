package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.Utils
import com.example.moliyafinance.adapters.MAdapterBukuBesar
import com.example.moliyafinance.databinding.ActivityBukuBesarBinding
import com.example.moliyafinance.databinding.DialogTanggalBinding
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
            initClicks()
        }
    }

    private fun initClicks() {
        bind.back.setOnClickListener {
            finish()
        }
        bind.pilihTanggal.setOnClickListener {
            val dialogTanggalBinding = DialogTanggalBinding.inflate(layoutInflater)
            Utils().showDateDialog(this, dialogTanggalBinding)
        }
    }

    private fun init() {
        Utils().fadeIn(bind.recycler)
        if (Dashboard.date.isNotEmpty()) {
            bind.bulan.text = Dashboard.date
        }
        val transaksiList = Dashboard.listTransaksi
        val listStringTransaksi = extractString(transaksiList)
        val innerTransaksi = ArrayList<List<Transaksi>>()

        for (s in listStringTransaksi) {
            val newData = transaksiList.filter { it.debit == s || it.kredit == s }
            innerTransaksi.add(newData)
        }

        val adapter = MAdapterBukuBesar.AdapterBukuBesar(listStringTransaksi, innerTransaksi)
        bind.recycler.adapter = adapter
        bind.recycler.layoutManager = LinearLayoutManager(this)
    }

    private fun extractString(transaksiList: List<Transaksi>): List<String> {
        val debitValues = transaksiList.map { it.debit }
        val kreditValues = transaksiList.map { it.kredit }
        val combinedValues = debitValues + kreditValues
        return combinedValues.distinct()
    }
}