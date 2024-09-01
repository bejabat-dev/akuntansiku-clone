package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moliyafinance.adapters.CombinedAdapter
import com.example.moliyafinance.adapters.TransactionGroup
import com.example.moliyafinance.databinding.ActivityBukuBesarBinding
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.navigation.Dashboard

class BukuBesar : AppCompatActivity() {
    private lateinit var bind : ActivityBukuBesarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityBukuBesarBinding.inflate(layoutInflater)
        setContentView(bind.root)
        if(Dashboard.listTransaksi.isNotEmpty()){
            init()
        }
    }

    private fun init(){
        val recyclerView: RecyclerView = bind.recycler
        recyclerView.layoutManager = LinearLayoutManager(this)

        val transaksiList: List<Transaksi> = Dashboard.listTransaksi
        val debitGroups = transaksiList.filter { it.debit.isNotEmpty() }
            .groupBy { it.debit }
            .map { (header, transactions) -> TransactionGroup(header, transactions) }

        val creditGroups = transaksiList.filter { it.kredit.isNotEmpty() }
            .groupBy { it.kredit }
            .map { (header, transactions) -> TransactionGroup(header, transactions) }

        val adapter = CombinedAdapter(debitGroups, creditGroups)
        recyclerView.adapter = adapter

    }
}