package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.adapters.AdapterJurnalUmum
import com.example.moliyafinance.databinding.ActivityJurnalUmumBinding
import com.example.moliyafinance.models.fadeIn
import com.example.moliyafinance.models.formatToRupiah
import com.example.moliyafinance.navigation.Dashboard
import com.example.moliyafinance.navigation.Dashboard.Companion.isLoaded
import com.example.moliyafinance.navigation.Home

class JurnalUmum : AppCompatActivity() {
    private lateinit var bind: ActivityJurnalUmumBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityJurnalUmumBinding.inflate(layoutInflater)
        setContentView(bind.root)
        if (isLoaded) {
            init()
            initClicks()
        }
    }

    private fun init() {
        val adapter = AdapterJurnalUmum(Dashboard.listTransaksi)
        fadeIn(bind.recycler)
        bind.back.setOnClickListener {
            finish()
        }
        bind.recycler.adapter = adapter
        bind.recycler.layoutManager = LinearLayoutManager(this)

        bind.recycler.post {
            bind.totalDebit.text = formatToRupiah(adapter.getTotalDebit())
            bind.totalKredit.text = formatToRupiah(adapter.getTotalKredit())
        }
    }

    private fun initClicks(){
        val dialog = Home.dialogCompanion
        bind.pilihTanggal.setOnClickListener {
            dialog.show()
        }
    }
}