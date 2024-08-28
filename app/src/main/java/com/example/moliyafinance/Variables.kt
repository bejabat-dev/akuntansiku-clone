package com.example.moliyafinance

import kotlin.collections.ArrayList

object Variables {
    val jenis_transaksi = listOf(
        "Pemasukan",
        "Pengeluaran",
        "Hutang",
        "Piutang",
        "Tanam Modal",
        "Tarik Modal",
        "Transfer Uang"
    )

    const val kategori1 = "Kas & Bank"
    const val kategori2 = "Akun Piutang"
    const val kategori3 = "Persediaan"
    const val kategori4 = "Harta Lancar Lainnya"
    const val kategori5 = "Harta tetap"
    const val kategori6 = "Depresiasi & Armotisasi"
    const val kategori8 = "Harta Lainnya"
    const val kategori9 = "Akun Hutang"
    const val kategori10 = "Kewajiban Lancar Lainnya"
    const val kategori11 = "Modal"
    const val kategori12 = "Pendapatan"
    const val kategori13 = "Harga Pokok Penjualan"
    const val kategori14 = "Beban"
    const val kategori15 = "Pendapatan lainnya"
    const val kategori16 = "Beban lainnya"


    val daftar_akun: ArrayList<DataAkun> = arrayListOf(
        DataAkun("1-10001", "Kas", kategori1),
        DataAkun("1-10002", "Rekening Bank 1", kategori1),
        DataAkun("1-10003", "Rekening Bank 2", kategori1),
        DataAkun("1-10004", "Gopay", kategori1),
        DataAkun("1-10005", "OVO", kategori1),
        DataAkun("1-10006", "Dana", kategori1),
        DataAkun("1-10007", "Link Aja", kategori1),
        DataAkun("1-10008", "Cashlez", kategori1),
        DataAkun("1-10010","Piutang Usaha",kategori2),
        DataAkun("1-10011","Piutang Belum Ditagih",kategori2),
        DataAkun("1-10020","Persediaan Barang",kategori3),
        DataAkun("1-10031","Piutang Lainnya",kategori4),
        DataAkun("1-10032","Piutang Karyawan",kategori4),
        DataAkun("1-10033","Dana Belum Disetor",kategori4),
        DataAkun("1-10034","Aset Lancar Lainnya",kategori4),
        DataAkun("1-10035","Biaya Dibayar Di Muka",kategori4),
        DataAkun("1-10036","Uang Muka",kategori4),
        DataAkun("1-10037","PPN Masukan",kategori4),
        DataAkun("1-10038","Pajak Penghasilan",kategori4),
        DataAkun("1-10040","Aktiva Tetap - Tanah",kategori5),
        DataAkun("1-10041","Aset Tetap - Bangunan",kategori5),
        DataAkun("1-10042","Aset Tetap - Pengembangan Bangunan",kategori5),
        DataAkun("1-10043","Aset Tetap - Kendaraan",kategori5),
        DataAkun("1-10044","Aset Tetap - Mesin & Peralatan",kategori5),
        DataAkun("1-10045","Aset Tetap - Peralatan Kantor",kategori5),
        DataAkun("1-10046","Aset Tetap - Aset Sewaan",kategori5),
        DataAkun("1-10047","Aset Tetap - Aset Tidak Berwujud",kategori5),
    )

    data class DataAkun(
        val nomor: String, val jenis: String, val kategori: String
    )
}