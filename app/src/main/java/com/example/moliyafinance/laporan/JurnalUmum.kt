package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.adapters.AdapterJurnalUmum
import com.example.moliyafinance.databinding.ActivityJurnalUmumBinding
import com.example.moliyafinance.models.fadeIn
import com.example.moliyafinance.models.formatToRupiah
import com.example.moliyafinance.navigation.Dashboard

class JurnalUmum : AppCompatActivity() {
    private lateinit var bind: ActivityJurnalUmumBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityJurnalUmumBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
    }

    private fun init() {
        bind.swipe.isRefreshing = true
        val adapter = AdapterJurnalUmum(Dashboard.listTransaksi)
        fadeIn(bind.recycler)
        bind.recycler.adapter = adapter
        bind.recycler.layoutManager = LinearLayoutManager(this)
        bind.swipe.isRefreshing = false

        bind.recycler.post {
            bind.totalDebit.text = formatToRupiah(adapter.getTotalDebit())
            bind.totalKredit.text = formatToRupiah(adapter.getTotalKredit())
        }
    }
}