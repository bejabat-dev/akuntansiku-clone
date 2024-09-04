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
import com.example.moliyafinance.Variables.akunList
import com.example.moliyafinance.adapters.AdapterDataAkun
import com.example.moliyafinance.databinding.ActivityTambahTransaksiBinding
import com.example.moliyafinance.databinding.DialogTransaksiBinding
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.models.TransaksiDetails
import com.example.moliyafinance.models.createTimestamp
import com.example.moliyafinance.models.tambahTransaksi
import com.example.moliyafinance.models.updateTransaksi
import com.example.moliyafinance.navigation.Dashboard
import com.google.firebase.auth.FirebaseAuth

class TambahTransaksi : AppCompatActivity(), AdapterDataAkun.OnItemClickListener {
    private lateinit var selectedJenisTransaksi: String
    private lateinit var bind: ActivityTambahTransaksiBinding
    private lateinit var bindingDebit: DialogTransaksiBinding
    private lateinit var bindingKredit: DialogTransaksiBinding
    private lateinit var dialogDebit: AlertDialog
    private lateinit var dialogKredit: AlertDialog
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
            selectedDebit = ts.debit
            selectedKredit = ts.kredit
            selectedNomorDebit = ts.nomorDebit
            selectedNomorKredit = ts.nomorKredit
            bind.debit.setText(ts.debit)
            bind.kredit.setText(ts.kredit)
            bind.catatan.setText(ts.catatan)
            bind.nominal.setText(ts.nominal.toString())
        }
    }

    private fun initDialog() {
        //  val debitAccounts = akunList.filter { it.jenis == "Debit" }
        //   val creditAccounts = akunList.filter { it.jenis == "Kredit" }
        bindingDebit = DialogTransaksiBinding.inflate(layoutInflater)
        bindingKredit = DialogTransaksiBinding.inflate(layoutInflater)
        val debit = AlertDialog.Builder(this)
        val kredit = AlertDialog.Builder(this)
        debit.setView(bindingDebit.root)
        kredit.setView(bindingKredit.root)
        val adapterDebit = AdapterDataAkun(akunList, this)
        val adapterKredit = AdapterDataAkun(akunList, this)
        bindingDebit.recycler.adapter = adapterDebit
        bindingDebit.recycler.layoutManager = LinearLayoutManager(this)
        bindingKredit.recycler.adapter = adapterKredit
        bindingKredit.recycler.layoutManager = LinearLayoutManager(this)
        dialogDebit = debit.create()
        dialogKredit = kredit.create()
    }

    private fun initClicks() {
        val auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        bind.back.setOnClickListener {
            finish()
        }
        bind.debit.setOnClickListener {
            type = "debit"
            dialogDebit.show()
        }
        bind.kredit.setOnClickListener {
            type = "kredit"
            dialogKredit.show()
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

                    if (Dashboard.editing) {
                        val id = TransaksiDetails.detailTransaksi.id
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
                        updateTransaksi(this, data)
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
    }

    override fun onItemClick(item: Variables.DataAkun) {
        if (type == "debit") {
            selectedNomorDebit = item.nomor
            selectedDebit = item.nama
            bind.debit.setText(item.nama)
            dialogDebit.dismiss()
        } else if (type == "kredit") {
            selectedNomorKredit = item.nomor
            selectedKredit = item.nama
            bind.kredit.setText(item.nama)
            dialogKredit.dismiss()
        }
    }
}