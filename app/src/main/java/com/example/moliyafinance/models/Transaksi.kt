package com.example.moliyafinance.models

import com.google.firebase.Timestamp
import java.util.Date

data class Transaksi(
    val id: Long = 0,
    val uid: String = "",
    val tanggal: String = "",
    val waktu: String = "",
    val kategoriDebit: String = "",
    val kategoriKredit: String = "",
    val jenisTransaksi: String = "",
    val debit: String = "",
    val kredit: String = "",
    val catatan: String = "",
    val nominal: Int = 0,
    val nomorDebit: String = "",
    val nomorKredit: String = "",
    val timestamp: Timestamp? = null,
    var date: Date? = null
)
