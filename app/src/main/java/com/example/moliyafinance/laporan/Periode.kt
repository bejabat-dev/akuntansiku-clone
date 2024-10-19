package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.Utils
import com.example.moliyafinance.adapters.MAdapterPeriode
import com.example.moliyafinance.databinding.ActivityPeriodeBinding
import com.example.moliyafinance.databinding.DialogTanggalBinding
import com.example.moliyafinance.models.Periode
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.navigation.Dashboard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Periode : AppCompatActivity() {
    private lateinit var bind: ActivityPeriodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityPeriodeBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
    }

    private fun init() {

        bind.pilihTanggal.setOnClickListener {
            val dialogTanggalBinding = DialogTanggalBinding.inflate(layoutInflater)
            Utils().showDateDialog(this,
                dialogTanggalBinding
            )
        }
        if(Dashboard.date.isNotEmpty()) {
            bind.hari.text = Dashboard.date
        }
        bind.back.setOnClickListener {
            finish()
        }

        lifecycleScope.launch {
            val adapterPeriode = withContext(Dispatchers.IO) {
                getAdapterPeriode()
            }
            bind.recycler.adapter = adapterPeriode
            bind.recycler.layoutManager = LinearLayoutManager(this@Periode)

            Utils().fadeIn(bind.recycler)
        }
    }

    private fun getAdapterPeriode(): MAdapterPeriode.AdapterPeriode {
        val listPeriode = ArrayList<String>()
        for (data in Dashboard.listTransaksi) {
            listPeriode.add(data.kategoriDebit)
            listPeriode.add(data.kategoriKredit)
        }
        val listPeriodeDistict = listPeriode.distinct()
        val arrayPeriode = ArrayList<Periode>()
        for (data in listPeriodeDistict) {
            val innerTransaksi = ArrayList<Transaksi>()
            for (newData in Dashboard.listTransaksi) {
                if(newData.kategoriDebit == data) {
                    innerTransaksi.add(newData)
                }
                if(newData.kategoriKredit == data) {
                    innerTransaksi.add(newData)
                }
            }
            val periode = Periode(data,innerTransaksi)
            arrayPeriode.add(periode)
        }
        val adapter = MAdapterPeriode.AdapterPeriode(this,arrayPeriode)
        return adapter
    }
}