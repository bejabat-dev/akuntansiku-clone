package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.adapters.AdapterPendapatan
import com.example.moliyafinance.databinding.ActivityLabaRugiBinding
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.models.formatToRupiah
import com.example.moliyafinance.navigation.Dashboard

class LabaRugi : AppCompatActivity() {
    private lateinit var bind: ActivityLabaRugiBinding
    private var totalLabaKotor = 0
    private var totalLabaBebanOperasional = 0
    private var totalPendapatanLainnya = 0
    private var totalBebanLainnya = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLabaRugiBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
    }

    private fun init() {
        bind.back.setOnClickListener {
            finish()
        }
        initPendapatanDariPenjualan()
        initHargaPokokPenjualan()
        initBebanOperasional()
        initPendapatanLainnya()
        initBebanLainnya()
    }

    private fun initPendapatanDariPenjualan() {
        val transaksiList = Dashboard.listTransaksi
        val newList = ArrayList<Transaksi>()
        for (data in transaksiList) {
            if (data.kategoriKredit == "Pendapatan") {
                newList.add(data)
            }
        }
        val adapter = AdapterPendapatan("Pemasukan", newList)
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
        val adapter = AdapterPendapatan("Pengeluaran", newList)
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
        val adapter = AdapterPendapatan("Pengeluaran", newList)
        bind.innerBebanOperasional.adapter = adapter
        bind.innerBebanOperasional.layoutManager = LinearLayoutManager(this)
        bind.innerBebanOperasional.post {
            val totalPengeluaran = formatToRupiah(adapter.getTotal())
            bind.totalBebanOperasional.text = totalPengeluaran
            bind.bebanOperasional.text = totalPengeluaran
            totalLabaBebanOperasional = totalLabaKotor - adapter.getTotal()
            if (totalLabaBebanOperasional < 0) {
                val total = "(${formatToRupiah(totalLabaBebanOperasional)})"
                bind.labaBebanOperasional.text = total
            } else {
                bind.labaBebanOperasional.text = formatToRupiah(totalLabaBebanOperasional)
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
        val adapter = AdapterPendapatan("Pemasukan", newList)
        bind.innerPendapatanLainnya.adapter = adapter
        bind.innerPendapatanLainnya.layoutManager = LinearLayoutManager(this)
        bind.innerPendapatanLainnya.post {
            totalPendapatanLainnya = adapter.getTotal()
            bind.totalPendapatanLainnya.text = formatToRupiah(adapter.getTotal())
            bind.pendapatanLainnya.text = formatToRupiah(adapter.getTotal())
        }
    }

    private fun initBebanLainnya() {
        val transaksiList = Dashboard.listTransaksi
        val newList = ArrayList<Transaksi>()
        for (data in transaksiList) {
            if (data.kategoriDebit == "Beban Lainnya") {
                newList.add(data)
            }
        }
        val adapter = AdapterPendapatan("Pengeluaran", newList)
        bind.innerBebanLainnya.adapter = adapter
        bind.innerBebanLainnya.layoutManager = LinearLayoutManager(this)
        bind.innerBebanLainnya.post {
            totalBebanLainnya = adapter.getTotal()
            bind.bebanLainnya.text = formatToRupiah(totalBebanLainnya)
            bind.totalBebanLainnya.text = formatToRupiah(totalBebanLainnya)

            val labaBersih = totalLabaBebanOperasional + totalPendapatanLainnya - totalBebanLainnya
            if (labaBersih < 0) {
                val total = "(${formatToRupiah(labaBersih)})"
                bind.labaBersih.text = total
            } else {
                bind.labaBersih.text = formatToRupiah(labaBersih)
            }
        }
    }
}