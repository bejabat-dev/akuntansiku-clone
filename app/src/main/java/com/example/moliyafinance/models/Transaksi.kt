package com.example.moliyafinance.models

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

data class Transaksi(
    val uid: String,
    val tanggal: String,
    val waktu: String,
    val jenisTransaksi: String,
    val debit: String,
    val kredit: String,
    val catatan: String,
    val nominal: Int
)

fun updateTransaksi(context: Context, transaksi: Transaksi,path:String?) {
    val db = FirebaseFirestore.getInstance()
    val collection =
        db.collection("Transactions") // Replace w
    val transaksiMap = mapOf(
        "uid" to transaksi.uid,
        "tanggal" to transaksi.tanggal,
        "waktu" to transaksi.waktu,
        "jenisTransaksi" to transaksi.jenisTransaksi,
        "debit" to transaksi.debit,
        "kredit" to transaksi.kredit,
        "catatan" to transaksi.catatan,
        "nominal" to transaksi.nominal
    )
    collection.document("asd").get().addOnSuccessListener {
        if (it.exists()) {
            collection.document("asd").update(transaksiMap)
                .addOnSuccessListener {
                    showToast(context, "Berhasil simpan")
                    if (context is Activity) {
                        context.finish()
                    }
                }
                .addOnFailureListener { e ->
                    println("Error updating transaction: ${e.message}")
                }
        } else {
            collection.document().set(transaksiMap)
                .addOnSuccessListener {
                    showToast(context, "Berhasil simpan")
                    if (context is Activity) {
                        context.finish()
                    }
                    println("Transaction updated successfully!")
                }
                .addOnFailureListener { e ->
                    println("Error updating transaction: ${e.message}")
                }
        }
    }.addOnFailureListener { e -> println("Gagal") }
}

fun showToast(context: Context, s: String) {
    Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
}