package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.adapters.AdapterLabaRugi
import com.example.moliyafinance.databinding.ActivityLabaRugiBinding
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.models.formatToRupiah
import com.example.moliyafinance.navigation.Dashboard

class LabaRugi : AppCompatActivity() {
    private lateinit var bind: ActivityLabaRugiBinding
    private var totalLabaKotor = 0
    private var totalBebanOperasional = 0
    private var totalPendapatanLainnya = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLabaRugiBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
    }

    private fun init() {
        initPendapatanDariPenjualan()
        initHargaPokokPenjualan()
        initBebanOperasional()
        initPendapatanLainnya()
    }

    private fun initPendapatanDariPenjualan() {
        val transaksiList = Dashboard.listTransaksi
        val newList = ArrayList<Transaksi>()
        for (data in transaksiList) {
            if (data.kategoriKredit == "Pendapatan") {
                newList.add(data)
            }
        }
        val adapter = AdapterLabaRugi.AdapterPendapatan("Pemasukan", newList)
        bind.innerPendapatanPenjualan.adapter = adapter
        bind.innerPendapatanPenjualan.layoutManager = LinearLayoutManager(this)
        bind.innerPendapatanPenjualan.post {
            totalLabaKotor += adapter.getTotal()
            val totalPendapatan = formatToRupiah(adapter.getTotal())
            bind.totalPendapatanPenjualan.text = totalPendapatan
            bind.pendapatanDariPenjualan.text = totalPendapatan
        }
    }

    private fun initHargaPokokPenjualan() {
        val transaksiList = Dashboard.listTransaksi
        val newList = ArrayList<Transaksi>()
        for (data in transaksiList) {
            if (data.kategoriDebit == "Harga Pokok Penjualan") {
                newList.add(data)
            }
        }
        val adapter = AdapterLabaRugi.AdapterPendapatan("Pengeluaran", newList)
        bind.innerHargaPokok.adapter = adapter
        bind.innerHargaPokok.layoutManager = LinearLayoutManager(this)
        bind.innerHargaPokok.post {
            totalLabaKotor -= adapter.getTotal()
            val totalPengeluaran = formatToRupiah(adapter.getTotal())
            bind.totalHargaPokokPenjualan.text = totalPengeluaran
            bind.hargaPokokPenjualan.text = totalPengeluaran
            bind.labaKotor.text = formatToRupiah(totalLabaKotor)
        }
    }

    private fun initBebanOperasional() {
        val transaksiList = Dashboard.listTransaksi
        val newList = ArrayList<Transaksi>()
        for (data in transaksiList) {
            if (data.kategoriDebit == "Beban") {
                newList.add(data)
            }
        }
        val adapter = AdapterLabaRugi.AdapterPendapatan("Pengeluaran", newList)
        bind.innerBebanOperasional.adapter = adapter
        bind.innerBebanOperasional.layoutManager = LinearLayoutManager(this)
        bind.innerBebanOperasional.post {
            val totalPengeluaran = formatToRupiah(adapter.getTotal())
            bind.totalBebanOperasional.text = totalPengeluaran
            bind.bebanOperasional.text = totalPengeluaran
            totalBebanOperasional = totalLabaKotor - adapter.getTotal()
            if (totalBebanOperasional < 0) {
                val total = "(${formatToRupiah(totalBebanOperasional)})"
                bind.labaBebanOperasional.text = total.replace("-", "")
            } else {
                bind.labaBebanOperasional.text = formatToRupiah(totalBebanOperasional)
            }
        }
    }

    private fun initPendapatanLainnya() {
        val transaksiList = Dashboard.listTransaksi
        val newList = ArrayList<Transaksi>()
        for (data in transaksiList) {
            if (data.kategoriKredit == "Pendapatan Lainnya") {
                newList.add(data)
            }
        }
        val adapter = AdapterLabaRugi.AdapterPendapatan("Pemasukan", newList)
        bind.innerPendapatanLainnya.adapter = adapter
        bind.innerPendapatanLainnya.layoutManager = LinearLayoutManager(this)
        bind.innerPendapatanLainnya.post {
            totalPendapatanLainnya = adapter.getTotal()
            bind.totalPendapatanLainnya.text = formatToRupiah(adapter.getTotal())
            bind.pendapatanLainnya.text = formatToRupiah(adapter.getTotal())
        }
    }

    private fun initBebanLainnya(){

    }
}