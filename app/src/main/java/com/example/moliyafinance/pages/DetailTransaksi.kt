package com.example.moliyafinance.pages

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moliyafinance.R
import com.example.moliyafinance.databinding.ActivityDetailTransaksiBinding
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.models.TransaksiDetails
import com.example.moliyafinance.navigation.Home
import com.google.firebase.firestore.core.UserData

class DetailTransaksi : AppCompatActivity() {
    private lateinit var bind: ActivityDetailTransaksiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityDetailTransaksiBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
    }

    private fun init() {
        Log.e("Tes","Jenis: ${TransaksiDetails.detailTransaksi.jenisTransaksi}")
    }
}