package com.example.moliyafinance.pages


import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.R
import com.example.moliyafinance.Variables
import com.example.moliyafinance.databinding.ActivityTambahAsetBinding

class TambahAset : AppCompatActivity() {
    private lateinit var bind: ActivityTambahAsetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityTambahAsetBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
    }

    private fun init() {
        bind.back.setOnClickListener {
            finish()
        }
        val arraySpinnerAset = ArrayList<String>()
        for (data in Variables.akunList) {
            if (data.kategori == "Harta Tetap") {
                arraySpinnerAset.add(data.nama)
            }
        }
        val akunList = ArrayList<String>()
        for (data in Variables.akunList) {
            akunList.add(data.nama)
        }

        val spinnerAsetAdapter =
            ArrayAdapter(this, R.layout.spinner_jenis_transaksi, arraySpinnerAset)

        val akunAdapter = ArrayAdapter(this, R.layout.spinner_jenis_transaksi, Variables.akunList)
        spinnerAsetAdapter.setDropDownViewResource(R.layout.spinner_dropdown_jenis_transaksi)
        akunAdapter.setDropDownViewResource(R.layout.spinner_dropdown_jenis_transaksi)
        bind.spinnerAkunAset.adapter = spinnerAsetAdapter
        bind.akunDikreditkan.adapter = akunAdapter
    }
}