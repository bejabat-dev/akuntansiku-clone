package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.adapters.AdapterBebanOperasional
import com.example.moliyafinance.databinding.ActivityBebanOperasionalBinding
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.models.formatToRupiah
import com.example.moliyafinance.navigation.Dashboard

class BebanOperasional : AppCompatActivity() {

    private lateinit var bind: ActivityBebanOperasionalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityBebanOperasionalBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
    }

    private fun init() {
        bind.back.setOnClickListener {
            finish()
        }
        initBebanOperasional()
    }

    private fun initBebanOperasional() {
        val listTransaksi = Dashboard.listTransaksi
        val newList = ArrayList<Transaksi>()
        for (data in listTransaksi) {
            if (data.kategoriDebit == "Beban") {
                newList.add(data)
            }
        }
        val adapter = AdapterBebanOperasional(newList)
        bind.recyclerBebanOperasional.adapter = adapter
        bind.recyclerBebanOperasional.layoutManager = LinearLayoutManager(this)
        bind.recyclerBebanOperasional.post {
            bind.total.text = formatToRupiah(adapter.getTotal())
        }
    }
}