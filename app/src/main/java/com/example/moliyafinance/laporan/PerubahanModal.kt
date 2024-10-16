package com.example.moliyafinance.laporan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.Utils
import com.example.moliyafinance.adapters.AdapterPerubahanModal
import com.example.moliyafinance.databinding.ActivityPerubahanModalBinding
import com.example.moliyafinance.databinding.DialogTanggalBinding
import com.example.moliyafinance.models.PerubahanModal
import com.example.moliyafinance.models.Transaksi

import com.example.moliyafinance.navigation.Dashboard

class PerubahanModal : AppCompatActivity() {
    private lateinit var bind: ActivityPerubahanModalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityPerubahanModalBinding.inflate(layoutInflater)
        setContentView(bind.root)
        initClicks()
        init()
    }


    private fun initClicks() {
        bind.pilihTanggal.setOnClickListener {
            val dialogTanggalBinding = DialogTanggalBinding.inflate(layoutInflater)
        }
        bind.back.setOnClickListener {
            finish()
        }
    }

    private fun init() {
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
        bind.recycler.adapter = adapterPerubahanModal
        bind.recycler.layoutManager = LinearLayoutManager(this)
        bind.recycler.post {
            bind.totalPerubahanModal.text = Utils().formatToRupiah(adapterPerubahanModal.getTotal())
        }
    }
}