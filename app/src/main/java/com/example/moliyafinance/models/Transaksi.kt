package com.example.moliyafinance.models

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

data class Transaksi(
    val tanggal: String,
    val waktu: String,
    val jenisTransaksi: String,
    val debit: String,
    val kredit: String,
    val catatan: String,
    val nominal: Int
)

fun updateTransaksi(context: Context, transaksi: Transaksi, documentPath: String) {
    val db = FirebaseFirestore.getInstance()
    val documentRef =
        db.collection("Transactions").document(documentPath) // Replace w
    val transaksiMap = mapOf(
        "tanggal" to transaksi.tanggal,
        "waktu" to transaksi.waktu,
        "jenisTransaksi" to transaksi.jenisTransaksi,
        "debit" to transaksi.debit,
        "kredit" to transaksi.kredit,
        "catatan" to transaksi.catatan,
        "nominal" to transaksi.nominal
    )
    documentRef.get().addOnSuccessListener {
        if (it.exists()) {
            documentRef.update(transaksiMap)
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
            documentRef.set(transaksiMap)
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