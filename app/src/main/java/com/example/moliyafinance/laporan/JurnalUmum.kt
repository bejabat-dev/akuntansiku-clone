package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.adapters.AdapterTransaksi
import com.example.moliyafinance.databinding.ActivityJurnalUmumBinding
import com.example.moliyafinance.models.fadeIn
import com.example.moliyafinance.models.getTransaksi
import com.example.moliyafinance.models.showToast

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
        getTransaksi(this, onResult = { list ->
            run {
                    val adapter = AdapterTransaksi(this, list)
                    fadeIn(bind.recycler)
                    bind.recycler.adapter = adapter
                    bind.recycler.layoutManager = LinearLayoutManager(this)
                    bind.swipe.isRefreshing = false

            }
        }, onError = {
            bind.swipe.isRefreshing = false
            showToast(this, "Terjadi kesalahan")
        })
    }
}