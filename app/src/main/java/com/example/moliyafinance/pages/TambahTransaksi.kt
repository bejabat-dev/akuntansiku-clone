package com.example.moliyafinance.pages

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.Variables
import com.example.moliyafinance.databinding.ActivityTambahTransaksiBinding

class TambahTransaksi : AppCompatActivity() {
    private lateinit var bind : ActivityTambahTransaksiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityTambahTransaksiBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
        initListeners()
    }

    private fun init(){
        val adapterJenisTransaki = ArrayAdapter(this,android.R.layout.simple_spinner_item,Variables.jenis_transaksi)
        adapterJenisTransaki.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bind.spinnerJenisTransaksi.adapter = adapterJenisTransaki
    }

    private fun initListeners(){
        bind.spinnerJenisTransaksi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val s = p0?.getItemAtPosition(p2).toString()
                Variables.selected_jenis_transaksi = s
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
}