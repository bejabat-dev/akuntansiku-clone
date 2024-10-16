package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.Utils
import com.example.moliyafinance.adapters.AdapterNeracaSaldo
import com.example.moliyafinance.databinding.ActivityNeracaSaldoBinding
import com.example.moliyafinance.databinding.DialogTanggalBinding
import com.example.moliyafinance.models.Transaksi

import com.example.moliyafinance.navigation.Dashboard

class NeracaSaldo : AppCompatActivity() {
    private lateinit var bind: ActivityNeracaSaldoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityNeracaSaldoBinding.inflate(layoutInflater)
        setContentView(bind.getRoot())
        init()
    }

    private fun init() {
        bind.pilihTanggal.setOnClickListener {
            val dialogTanggalBinding = DialogTanggalBinding.inflate(layoutInflater)
        }
        Utils().fadeIn(bind.recycler)
        bind.back.setOnClickListener {
            finish()
        }
        val transaksiList = Dashboard.listTransaksi
        val listStringTransaksi = extractString(transaksiList)
        val innerTransaksi = ArrayList<List<Transaksi>>()

        for (s in listStringTransaksi) {
            val newData = transaksiList.filter { it.debit == s || it.kredit == s }
            innerTransaksi.add(newData)
        }
        val adapter = AdapterNeracaSaldo(listStringTransaksi, innerTransaksi)

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