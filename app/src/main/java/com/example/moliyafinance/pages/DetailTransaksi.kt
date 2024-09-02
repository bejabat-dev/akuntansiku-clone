package com.example.moliyafinance.pages

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.moliyafinance.databinding.ActivityDetailTransaksiBinding
import com.example.moliyafinance.models.TransaksiDetails
import com.example.moliyafinance.models.User
import com.example.moliyafinance.models.formatToRupiah
import com.example.moliyafinance.navigation.Dashboard

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
        bind.waktu.text = ts.waktu
        bind.debit.text = formatToRupiah(ts.nominal)
        bind.kredit.text = formatToRupiah(ts.nominal)
    }

    private fun initClicks() {
        bind.hapus.setOnClickListener {
            val b = AlertDialog.Builder(this)
            b.setTitle("Peringatan")
            b.setMessage("Hapus transaksi ?")

            b.setPositiveButton("Ya") { dialog, _ ->
                run {
                    dialog.dismiss()
                }
            }
            b.setNegativeButton("Batal") { dialog, _ ->
                run {
                    dialog.dismiss()
                }
            }

            val dialog = b.create()
            dialog.show()
        }

        bind.edit.setOnClickListener {
            Dashboard.editing = true
            val i = Intent(this, TambahTransaksi::class.java)
            startActivity(i)
            finish()
        }

        bind.back.setOnClickListener {
            finish()
        }
    }
}