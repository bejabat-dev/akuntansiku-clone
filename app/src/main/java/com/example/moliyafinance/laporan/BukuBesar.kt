package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.adapters.AdapterBukuBesar
import com.example.moliyafinance.databinding.ActivityBukuBesarBinding
import com.example.moliyafinance.models.Transaksi
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

        // Get distinct debits and credits
        val distinctDebits: List<String> = transaksiList.map { it.debit }.toSet().toList()
        val distinctKredits: List<String> = transaksiList.map { it.kredit }.toSet().toList()

        // Combine and get unique values
        val combinedUniqueValues: List<String> = (distinctDebits + distinctKredits).toSet().toList()

        // Sort unique values
        val sortedUniqueValues = combinedUniqueValues.sorted()

        // Create the map with unique values as keys
        val mapByUniqueValues: Map<String, List<Transaksi>> = sortedUniqueValues.associateWith { uniqueValue ->
            transaksiList.filter { it.debit == uniqueValue || it.kredit == uniqueValue }
        }

        // Convert mapByUniqueValues to ArrayList<HashMap<String, List<Transaksi>>>
        val arrayListOfHashMaps = ArrayList<HashMap<String, List<Transaksi>>>()
        sortedUniqueValues.forEach { key ->
            val transactions = mapByUniqueValues[key] ?: emptyList()
            val hashMap = hashMapOf(key to transactions)
            arrayListOfHashMaps.add(hashMap)
        }

        // Set up the adapter
        val adapter = AdapterBukuBesar(this, arrayListOfHashMaps, sortedUniqueValues)
        bind.recycer.adapter = adapter
        bind.recycer.layoutManager = LinearLayoutManager(this)
    }
}