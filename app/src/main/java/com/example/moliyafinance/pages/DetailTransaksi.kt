package com.example.moliyafinance.pages

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityDetailTransaksiBinding
import com.example.moliyafinance.models.TransaksiDetails
import com.example.moliyafinance.models.User

class DetailTransaksi : AppCompatActivity() {
    private lateinit var bind: ActivityDetailTransaksiBinding
    private lateinit var transaksi : TransaksiDetails
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityDetailTransaksiBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
    }

    private fun init() {
        val ts = transaksi.detailTransaksi
        bind.catatan.text = ts.catatan
        bind.tanggal.text = ts.tanggal
        bind.transaksi.text = ts.jenisTransaksi
        bind.pembuat.text = User.userData.nama
    }
}