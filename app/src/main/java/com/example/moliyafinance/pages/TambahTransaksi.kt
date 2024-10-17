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
import androidx.recyclerview.widget.RecyclerView
import com.example.moliyafinance.R
import com.example.moliyafinance.Utils
import com.example.moliyafinance.objects.Variables
import com.example.moliyafinance.objects.Variables.akunList
import com.example.moliyafinance.adapters.AdapterDataAkun
import com.example.moliyafinance.databinding.ActivityTambahTransaksiBinding
import com.example.moliyafinance.databinding.DialogTransaksiBinding
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.models.TransaksiDetails

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
    private lateinit var kategoriDebit: String
    private lateinit var kategoriKredit: String
    private lateinit var selectedDebit: String
    private lateinit var selectedKredit: String
    private lateinit var selectedNomorDebit: String
    private lateinit var selectedNomorKredit: String

    private fun getDebitAdapter(jenis: String): AdapterDataAkun {
        val list = ArrayList<Variables.DataAkun>()
        when (jenis) {
            "Pemasukan" -> {
                for (data in akunList) {
                    if (data.kategori == "Kas & Bank" || data.kategori == "Persediaan") {
                        list.add(data)
                    }
                }
            }

            "Pengeluaran" -> {
                for (data in akunList) {
                    if (data.kategori == "Harga Pokok Penjualan" || data.kategori == "Beban" || data.kategori == "Beban Lainnya" || data.kategori == "Kas & Bank" || data.kategori == "Akun Piutang" || data.kategori == "Harta Tetap") {
                        list.add(data)
                    }
                }
            }

            "Hutang" -> {
                for (data in akunList) {
                    if (data.kategori == "Kas & Bank" || data.kategori == "Persediaan" || data.kategori == "Harta Lancar Lainnya" || data.kategori == "Harta Tetap" || data.kategori == "Harga Pokok Penjualan" || data.kategori == "Beban" || data.kategori == "Beban Lainnya") {
                        list.add(data)
                    }
                }
            }

            "Piutang" -> {
                for (data in akunList) {
                    if (data.kategori == "Akun Piutang") {
                        list.add(data)
                    }
                }
            }

            "Tanam Modal" -> {
                for (data in akunList) {
                    if (data.kategori == "Kas & Bank") {
                        list.add(data)
                    }
                }
            }

            "Tarik Modal" -> {
                for (data in akunList) {
                    if (data.kategori == "Modal") {
                        list.add(data)
                    }
                }
            }

            "Transfer Uang" -> {
                for (data in akunList) {
                    if (data.kategori == "Kas & Bank") {
                        list.add(data)
                    }
                }
            }
        }
        val adapter = AdapterDataAkun(list, this)
        return adapter
    }

    private fun getKreditAdapter(jenis: String): AdapterDataAkun {
        val list = ArrayList<Variables.DataAkun>()
        when (jenis) {
            "Pemasukan" -> {
                for (data in akunList) {
                    if (data.kategori == "Pendapatan" || data.kategori == "Pendapatan Lainnya") {
                        list.add(data)
                    }
                }
            }

            "Pengeluaran" -> {
                for (data in akunList) {
                    if (data.kategori == "Kas & Bank" || data.kategori == "Persediaan" || data.kategori == "Harta Lancar Lainnya" || data.kategori == "Harta Tetap" || data.kategori == "Harta Lainnya") {
                        list.add(data)
                    }
                }
            }

            "Hutang" -> {
                for (data in akunList) {
                    if (data.kategori == "Akun Hutang" || data.kategori == "Kewajiban Lancar Lainnya" || data.kategori == "Kewajiban Jangka Panjang") {
                        list.add(data)
                    }
                }
            }

            "Piutang" -> {
                for (data in akunList) {
                    if (data.kategori == "Kas & Bank" || data.kategori == "Persediaan" || data.kategori == "Harta Lancar Lainnya" || data.kategori == "Pendapatan" || data.kategori == "Pendapatan Lainnya" || data.kategori == "Modal") {
                        list.add(data)
                    }
                }
            }

            "Tanam Modal" -> {
                for (data in akunList) {
                    if (data.kategori == "Modal") {
                        list.add(data)
                    }
                }
            }

            "Tarik Modal" -> {
                for (data in akunList) {
                    if (data.kategori == "Kas & Bank") {
                        list.add(data)
                    }
                }
            }

            "Transfer Uang" -> {
                for (data in akunList) {
                    if (data.kategori == "Kas & Bank") {
                        list.add(data)
                    }
                }
            }

        }
        val adapter = AdapterDataAkun(list, this)
        return adapter
    }

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
            ArrayAdapter(this, R.layout.spinner_jenis_transaksi, Variables.jenis_transaksi)
        adapterJenisTransaki.setDropDownViewResource(R.layout.spinner_dropdown_jenis_transaksi)
        bind.spinnerJenisTransaksi.adapter = adapterJenisTransaki
    }

    @SuppressLint("SetTextI18n")
    private fun setDebitKredit(s: String) {
        when (s) {
            "Pemasukan" -> {
                bind.debitTeks.text = "Simpan ke (Debit)"
                bind.kreditTeks.text = "Diterima dari (Kredit)"
            }

            "Pengeluaran" -> {
                bind.debitTeks.text = "Untuk biaya (Debit)"
                bind.kreditTeks.text = "Diambil dari (Kredit)"
            }

            "Hutang" -> {
                bind.debitTeks.text = "Simpan ke (Debit)"
                bind.kreditTeks.text = "Hutang dari (Kredit)"
            }

            "Piutang" -> {
                bind.debitTeks.text = "Simpan ke (Debit)"
                bind.kreditTeks.text = "Dari (Kredit)"
            }

            "Tanam Modal" -> {
                bind.debitTeks.text = "Simpan ke (Debit)"
                bind.kreditTeks.text = "Modal (Kredit)"
            }

            "Tarik Modal" -> {
                bind.debitTeks.text = "Modal (Debit)"
                bind.kreditTeks.text = "Diambil dari (Kredit)"
            }

            "Transfer Uang" -> {
                bind.debitTeks.text = "Ke (Debit)"
                bind.kreditTeks.text = "Dari (Kredit)"
            }
        }
    }

    private fun initListeners() {
        setDebitKredit("Pemasukan")
        bind.spinnerJenisTransaksi.setSelection(0)
        bind.spinnerJenisTransaksi.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val s = p0?.getItemAtPosition(p2).toString()
                    setDebitKredit(s)
                    setAdapter(bindingDebit.recycler, getDebitAdapter(s))
                    setAdapter(bindingKredit.recycler, getKreditAdapter(s))
                    selectedJenisTransaksi = s
                    bind.debit.text.clear()
                    bind.kredit.text.clear()
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

    private fun setAdapter(view: RecyclerView, adapterDataAkun: AdapterDataAkun) {
        view.adapter = adapterDataAkun
    }

    private fun initDialog() {
        bindingDebit = DialogTransaksiBinding.inflate(layoutInflater)
        bindingKredit = DialogTransaksiBinding.inflate(layoutInflater)
        val debit = AlertDialog.Builder(this)
        val kredit = AlertDialog.Builder(this)
        debit.setView(bindingDebit.root)
        kredit.setView(bindingKredit.root)
        val adapterDebit = getDebitAdapter("Pemasukan")
        val adapterKredit = getKreditAdapter("Pemasukan")
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
                            kategoriDebit, kategoriKredit,
                            selectedJenisTransaksi,
                            selectedDebit,
                            selectedKredit,
                            catatan,
                            nominal,
                            selectedNomorDebit,
                            selectedNomorKredit,
                            Utils().createTimestamp(tanggal, waktu)
                        )
                        Utils().updateTransaksi(this, data)
                    } else {
                        val id = System.currentTimeMillis()
                        val data = Transaksi(
                            id,
                            uid!!,
                            tanggal,
                            waktu,
                            kategoriDebit, kategoriKredit,
                            selectedJenisTransaksi,
                            selectedDebit,
                            selectedKredit,
                            catatan,
                            nominal,
                            selectedNomorDebit,
                            selectedNomorKredit,
                            Utils().createTimestamp(tanggal, waktu)
                        )
                        Utils().tambahTransaksi(this, data)
                    }
                }
            }
        }
    }

    override fun onItemClick(item: Variables.DataAkun) {
        if (type == "debit") {
            selectedNomorDebit = item.nomor
            selectedDebit = item.nama
            kategoriDebit = item.kategori
            bind.debit.setText(item.nama)
            dialogDebit.dismiss()
        } else if (type == "kredit") {
            selectedNomorKredit = item.nomor
            selectedKredit = item.nama
            kategoriKredit = item.kategori
            bind.kredit.setText(item.nama)
            dialogKredit.dismiss()
        }
    }
}