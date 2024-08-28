package com.example.moliyafinance.pages

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.AdapterDataAkun
import com.example.moliyafinance.Variables
import com.example.moliyafinance.databinding.ActivityTambahTransaksiBinding
import com.example.moliyafinance.databinding.DialogTransaksiBinding

class TambahTransaksi : AppCompatActivity() {
    private lateinit var selectedJenisTransaksi: String
    private lateinit var bind: ActivityTambahTransaksiBinding
    private lateinit var dialogBinding : DialogTransaksiBinding
    private lateinit var dialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityTambahTransaksiBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
        initListeners()
        initDialog()
        initClicks()
    }

    private fun init() {
        val adapterJenisTransaki =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, Variables.jenis_transaksi)
        adapterJenisTransaki.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bind.spinnerJenisTransaksi.adapter = adapterJenisTransaki
    }

    private fun initListeners() {
        bind.spinnerJenisTransaksi.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val s = p0?.getItemAtPosition(p2).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
    }

    private fun initDialog(){
        dialogBinding = DialogTransaksiBinding.inflate(layoutInflater)
        val b = AlertDialog.Builder(this)
        b.setView(dialogBinding.root)
        val adapter = AdapterDataAkun(Variables.daftar_akun)
        dialogBinding.recycler.adapter = adapter
        dialogBinding.recycler.layoutManager = LinearLayoutManager(this)
        dialog = b.create()
    }

    private fun initClicks(){
        bind.debit.setOnClickListener {
            dialog.show()
        }
        bind.kredit.setOnClickListener {
            dialog.show()
        }
    }
}