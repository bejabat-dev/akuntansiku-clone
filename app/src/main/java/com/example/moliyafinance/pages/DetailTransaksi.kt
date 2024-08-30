package com.example.moliyafinance.pages

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityDetailTransaksiBinding
import com.example.moliyafinance.models.TransaksiDetails
import com.example.moliyafinance.models.User
import com.example.moliyafinance.models.formatToRupiah

class DetailTransaksi : AppCompatActivity() {
    private lateinit var bind: ActivityDetailTransaksiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityDetailTransaksiBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
        initClicks()
    }

    private fun init() {
        val ts = TransaksiDetails.detailTransaksi
        bind.catatan.text = ts.catatan
        bind.tanggal.text = ts.tanggal
        bind.transaksi.text = ts.jenisTransaksi
        bind.pembuat.text = User.userData.nama
        bind.judulDebit.text = ts.debit
        bind.judulKredit.text = ts.kredit
        bind.debit.text = formatToRupiah(ts.nominal)
        bind.kredit.text = formatToRupiah(ts.nominal)
    }

    private fun initClicks(){
        bind.hapus.setOnClickListener {

        }

        bind.edit.setOnClickListener {

        }

        bind.back.setOnClickListener {
            finish()
        }
    }
}