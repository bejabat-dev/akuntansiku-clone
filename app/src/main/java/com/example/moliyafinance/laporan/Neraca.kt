package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.Utils
import com.example.moliyafinance.adapters.AdapterHartaLancar
import com.example.moliyafinance.adapters.AdapterHartaTetap
import com.example.moliyafinance.adapters.AdapterKewajiban
import com.example.moliyafinance.adapters.AdapterPerubahanModal
import com.example.moliyafinance.databinding.ActivityNeracaBinding
import com.example.moliyafinance.databinding.DialogTanggalBinding
import com.example.moliyafinance.models.HartaLancar
import com.example.moliyafinance.models.HartaTetap
import com.example.moliyafinance.models.Kewajiban
import com.example.moliyafinance.models.PerubahanModal
import com.example.moliyafinance.models.Transaksi

import com.example.moliyafinance.navigation.Dashboard

class Neraca : AppCompatActivity() {
    private lateinit var bind: ActivityNeracaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityNeracaBinding.inflate(layoutInflater)
        setContentView(bind.root)
        initClicks()
        init()
    }

    private fun initClicks() {
        bind.pilihTanggal.setOnClickListener {
            val dialogTanggalBinding = DialogTanggalBinding.inflate(layoutInflater)
            Utils().showDateDialog(this, dialogTanggalBinding)
        }
        if (Dashboard.date.isNotEmpty()) {
            bind.hari.text = Dashboard.date
        }
        bind.back.setOnClickListener {
            finish()
        }
    }

    private fun init() {
        val adapterHartaLancar = getAdapterHartaLancar()
        val adapterHartaTetap = getAdapterHartaTetap()
        val adapterKewajiban = getAdapterKewajiban()
        val adapterModal = getAdapterModal()

        var totalHarta = 0
        var totalKewajibanDanModal = 0

        bind.recyclerHartaLancar.adapter = adapterHartaLancar
        bind.recyclerHartaLancar.layoutManager = LinearLayoutManager(this)
        bind.recyclerHartaLancar.post {
            bind.totalHartaLancar.text = Utils().formatToRupiah(adapterHartaLancar.getTotal())
            totalHarta += adapterHartaLancar.getTotal()
        }

        bind.recyclerHartaTetap.adapter = adapterHartaTetap
        bind.recyclerHartaTetap.layoutManager = LinearLayoutManager(this)
        bind.recyclerHartaTetap.post {
            bind.totalHartaTetap.text = Utils().formatToRupiah(adapterHartaTetap.getTotal())
            totalHarta += adapterHartaTetap.getTotal()
            bind.totalHarta.text = Utils().formatToRupiah(totalHarta)
        }

        bind.recyclerKewajiban.adapter = adapterKewajiban
        bind.recyclerKewajiban.layoutManager = LinearLayoutManager(this)
        bind.recyclerKewajiban.post {
            bind.totalKewajiban.text = Utils().formatToRupiah(adapterKewajiban.getTotal())
            totalKewajibanDanModal += adapterKewajiban.getTotal()
        }

        bind.recyclerModal.adapter = adapterModal
        bind.recyclerModal.layoutManager = LinearLayoutManager(this)
        bind.recyclerModal.post {
            bind.totalModal.text = Utils().formatToRupiah(adapterModal.getTotal())
            totalKewajibanDanModal += adapterModal.getTotal()
            bind.totalKewajibanDanModal.text = Utils().formatToRupiah(totalKewajibanDanModal)
        }

    }

    private fun getAdapterHartaLancar(): AdapterHartaLancar {
        val listHartaLancar = ArrayList<String>()
        val arrayHartaLancar = ArrayList<Transaksi>()
        val arrayListHartaLancar = ArrayList<HartaLancar>()

        for (data in Dashboard.listTransaksi) {
            if (data.kategoriDebit == "Kas & Bank") {
                listHartaLancar.add(data.debit)
                arrayHartaLancar.add(data)
            }
            if (data.kategoriKredit == "Kas & Bank") {
                listHartaLancar.add(data.kredit)
                arrayHartaLancar.add(data)
            }
        }
        for (data in listHartaLancar.distinct()) {
            var total = 0
            var nomor = ""
            for (newData in arrayHartaLancar) {
                if (newData.debit == data) {
                    nomor = newData.nomorDebit
                    total += newData.nominal
                }
                if (newData.kredit == data) {
                    nomor = newData.nomorKredit
                    total -= newData.nominal
                }
            }
            val hartaLancar = HartaLancar("$nomor | $data", total)
            arrayListHartaLancar.add(hartaLancar)
        }
        val adapter = AdapterHartaLancar(arrayListHartaLancar)
        return adapter
    }

    private fun getAdapterHartaTetap(): AdapterHartaTetap {
        val listHartaTetap = ArrayList<String>()
        val arrayHartaTetap = ArrayList<Transaksi>()
        val arrayListHartaTetap = ArrayList<HartaTetap>()
        for (data in Dashboard.listTransaksi) {
            if (data.kategoriDebit == "Harta Tetap") {
                listHartaTetap.add(data.debit)
                arrayHartaTetap.add(data)
            }
        }
        for (data in listHartaTetap.distinct()) {
            var total = 0
            var nomor = ""
            for (newData in arrayHartaTetap) {
                if (newData.debit == data) {
                    total += newData.nominal
                    nomor = newData.nomorDebit
                }
            }
            val hartaTetap = HartaTetap("$nomor | $data", total)
            arrayListHartaTetap.add(hartaTetap)
        }
        val adapter = AdapterHartaTetap(arrayListHartaTetap)
        return adapter
    }

    private fun getAdapterKewajiban(): AdapterKewajiban {
        val listKewajiban = ArrayList<String>()
        val arrayKewajiban = ArrayList<Transaksi>()
        val arrayListKewajiban = ArrayList<Kewajiban>()
        for (data in Dashboard.listTransaksi) {
            if (data.jenisTransaksi == "Hutang") {
                listKewajiban.add(data.kredit)
                arrayKewajiban.add(data)
            }
        }
        for (data in listKewajiban.distinct()) {
            var total = 0
            var nomor = ""
            for (newData in arrayKewajiban) {
                if (newData.kredit == data) {
                    total += newData.nominal
                    nomor = newData.nomorKredit
                }
            }
            val kewajiban = Kewajiban("$nomor | $data", total)
            arrayListKewajiban.add(kewajiban)
        }
        val adapter = AdapterKewajiban(arrayListKewajiban)
        return adapter
    }

    private fun getAdapterModal(): AdapterPerubahanModal {
        val listPerubahanModal = ArrayList<String>()
        val arrayPerubahanModal = ArrayList<Transaksi>()
        for (data in Dashboard.listTransaksi) {
            if (data.jenisTransaksi == "Tanam Modal") {
                listPerubahanModal.add(data.kredit)
                arrayPerubahanModal.add(data)
            }
            if (data.jenisTransaksi == "Tarik Modal") {
                listPerubahanModal.add(data.debit)
                arrayPerubahanModal.add(data)
            }
        }

        val arrayListPerubahanModal = ArrayList<PerubahanModal>()
        for (data in listPerubahanModal.distinct()) {
            var total = 0
            var nomor = ""
            for (perubahan in arrayPerubahanModal) {
                if (perubahan.kredit == data) {
                    nomor = perubahan.nomorKredit
                    total += perubahan.nominal
                }
                if (perubahan.debit == data) {
                    nomor = perubahan.nomorDebit
                    total -= perubahan.nominal
                }
            }
            val newData = PerubahanModal("$nomor | $data", total)
            arrayListPerubahanModal.add(newData)
        }
        val adapterPerubahanModal = AdapterPerubahanModal(arrayListPerubahanModal)
        return adapterPerubahanModal
    }

}