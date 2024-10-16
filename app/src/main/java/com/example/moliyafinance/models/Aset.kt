package com.example.moliyafinance.models

data class Aset(
    val nama: String,
    val akunAsetTetap: String,
    val deskripsi: String,
    val tanggal: String,
    val biayaAkusisi: Int,
    val akunDikreditkan: String,
)