package com.example.moliyafinance.pages

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.Utils
import com.example.moliyafinance.Variables
import com.example.moliyafinance.adapters.AdapterDataAkun
import com.example.moliyafinance.databinding.ActivityTambahTransaksiBinding
import com.example.moliyafinance.databinding.DialogTransaksiBinding
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.models.TransaksiDetails
import com.example.moliyafinance.models.createTimestamp
import com.example.moliyafinance.models.tambahTransaksi
import com.example.moliyafinance.navigation.Dashboard
import com.google.firebase.auth.FirebaseAuth

class TambahTransaksi : AppCompatActivity(), AdapterDataAkun.OnItemClickListener {
    private lateinit var selectedJenisTransaksi: String
    private lateinit var bind: ActivityTambahTransaksiBinding
    private lateinit var dialogBinding: DialogTransaksiBinding
    private lateinit var dialog: AlertDialog
    private lateinit var type: String
    private lateinit var selectedDebit: String
    private lateinit var selectedKredit: String
    private lateinit var selectedNomorDebit: String
    private lateinit var selectedNomorKredit: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityTambahTransaksiBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init()
        initListeners()
        initDialog()
        initClicks()
        checkEditing()
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
                    selectedJenisTransaksi = s
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
    }

    @SuppressLint("SetTextI18n")
    private fun checkEditing() {
        if (Dashboard.editing) {
            bind.judul.text = "Edit Transaksi"
            val ts = TransaksiDetails.detailTransaksi
            bind.tanggal.setText(ts.tanggal)
            bind.waktu.setText(ts.waktu)
            val jenisTransaksi = ts.jenisTransaksi
            val index = Variables.jenis_transaksi.indexOf(jenisTransaksi)
            bind.spinnerJenisTransaksi.setSelection(index)
            bind.debit.setText(ts.debit)
            bind.kredit.setText(ts.kredit)
            bind.catatan.setText(ts.catatan)
            bind.nominal.setText(ts.nominal.toString())
        }
    }

    private fun initDialog() {
        dialogBinding = DialogTransaksiBinding.inflate(layoutInflater)
        val b = AlertDialog.Builder(this)
        b.setView(dialogBinding.root)
        val adapter = AdapterDataAkun(Variables.daftar_akun, this)
        dialogBinding.recycler.adapter = adapter
        dialogBinding.recycler.layoutManager = LinearLayoutManager(this)
        dialog = b.create()
    }

    private fun initClicks() {
        val auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        bind.back.setOnClickListener {
            finish()
        }
        bind.debit.setOnClickListener {
            type = "debit"
            dialog.show()
        }
        bind.kredit.setOnClickListener {
            type = "kredit"
            dialog.show()
        }
        bind.tanggal.setOnClickListener {
            Utils().showDatePickerDialog(this, bind.tanggal)
        }
        bind.waktu.setOnClickListener {
            Utils().showTimePickerDialog(this, bind.waktu)
        }
        bind.simpan.setOnClickListener {
            val tanggal = bind.tanggal.text.toString().trim()
            val waktu = bind.waktu.text.toString().trim()
            val debit = bind.debit.text.toString().trim()
            val kredit = bind.kredit.text.toString().trim()
            val catatan = bind.catatan.text.toString().trim()
            val nominalStr = bind.nominal.text.toString().trim()

            if (tanggal.isEmpty() || waktu.isEmpty() || debit.isEmpty() || kredit.isEmpty() || catatan.isEmpty() || nominalStr.isEmpty()) {
                Toast.makeText(this, "Harap penuhi semua kolom", Toast.LENGTH_SHORT).show()
            } else {
                val nominal = nominalStr.toIntOrNull()
                if (nominal == null) {
                    Toast.makeText(this, "Harap penuhi semua kolom", Toast.LENGTH_SHORT).show()
                } else {
                    val id = System.currentTimeMillis()
                    val data = Transaksi(
                        id,
                        uid!!,
                        tanggal,
                        waktu,
                        selectedJenisTransaksi,
                        selectedDebit,
                        selectedKredit,
                        catatan,
                        nominal,
                        selectedNomorDebit,
                        selectedNomorKredit,
                        createTimestamp(tanggal, waktu)
                    )
                    tambahTransaksi(this, data)
                }
            }
        }
    }

    override fun onItemClick(item: Variables.DataAkun) {
        if (type == "debit") {
            selectedNomorDebit = item.nomor
            selectedDebit = item.jenis
            bind.debit.setText(item.jenis)
            dialog.dismiss()
        } else if (type == "kredit") {
            selectedNomorKredit = item.nomor
            selectedKredit = item.jenis
            bind.kredit.setText(item.jenis)
            dialog.dismiss()
        }
    }
}