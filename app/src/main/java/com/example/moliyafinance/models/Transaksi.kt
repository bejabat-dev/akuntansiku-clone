package com.example.moliyafinance.models

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import com.example.moliyafinance.LoadingDialog
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Transaksi(
    val id: Long = 0,
    val uid: String = "",
    val tanggal: String = "",
    val waktu: String = "",
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

object TransaksiDetails {
    lateinit var detailTransaksi: Transaksi
}

fun createTimestamp(dateString: String, timeString: String): Timestamp {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val dateTimeString = "$dateString $timeString"
    val date: Date? = dateFormat.parse(dateTimeString)
    return if (date != null) {
        Timestamp(date)
    } else {
        throw IllegalArgumentException("Invalid date or time format")
    }
}

fun hapusTransaksi(context: Context, id: Long) {
    LoadingDialog.showDialog(context, "Menghapus transaksi")
    val db = FirebaseFirestore.getInstance().collection("Transactions")
    db.document(id.toString()).delete().addOnSuccessListener {
        if (context is Activity) {
            LoadingDialog.dialog.dismiss()
            context.finish()
        }
    }.addOnFailureListener {
        LoadingDialog.dialog.dismiss()
        showToast(context, "Terjadi kesalahan")
    }
}

fun tambahTransaksi(context: Context, transaksi: Transaksi) {
    LoadingDialog.showDialog(context, null)
    val db = FirebaseFirestore.getInstance().collection("Transactions")
    val transaksiMap = mapOf(
        "id" to transaksi.id,
        "uid" to transaksi.uid,
        "tanggal" to transaksi.tanggal,
        "waktu" to transaksi.waktu,
        "jenisTransaksi" to transaksi.jenisTransaksi,
        "debit" to transaksi.debit,
        "kredit" to transaksi.kredit,
        "catatan" to transaksi.catatan,
        "nomorDebit" to transaksi.nomorDebit,
        "nomorKredit" to transaksi.nomorKredit,
        "nominal" to transaksi.nominal,
        "timestamp" to createTimestamp(transaksi.tanggal, transaksi.waktu)
    )
    db.document(transaksi.id.toString()).set(transaksiMap).addOnSuccessListener {
        LoadingDialog.dialog.dismiss()
        if (context is Activity) {
            LoadingDialog.dialog.dismiss()
            context.finish()
        }
    }.addOnFailureListener { e ->
        showToast(context, e.toString())
        LoadingDialog.dialog.dismiss()
    }
}

fun updateTransaksi(context: Context, transaksi: Transaksi) {
    LoadingDialog.showDialog(context, null)
    val db = FirebaseFirestore.getInstance().collection("Transactions")
    val transaksiMap = mapOf(
        "id" to transaksi.id,
        "uid" to transaksi.uid,
        "tanggal" to transaksi.tanggal,
        "waktu" to transaksi.waktu,
        "jenisTransaksi" to transaksi.jenisTransaksi,
        "debit" to transaksi.debit,
        "kredit" to transaksi.kredit,
        "catatan" to transaksi.catatan,
        "nomorDebit" to transaksi.nomorDebit,
        "nomorKredit" to transaksi.nomorKredit,
        "nominal" to transaksi.nominal
    )
    db.document(transaksi.id.toString()).update(transaksiMap).addOnSuccessListener {
        LoadingDialog.dialog.dismiss()
        if (context is Activity) {
            LoadingDialog.dialog.dismiss()
            context.finish()
        }
    }.addOnFailureListener { e ->
        showToast(context, e.toString())
        LoadingDialog.dialog.dismiss()
    }
}

fun getTransaksi(
    context: Context,
    onResult: (List<Transaksi>) -> Unit,
    onError: (Exception) -> Unit
) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val db = FirebaseFirestore.getInstance().collection("Transactions")
    db.whereEqualTo("uid", User.userData.uid).get()
        .addOnSuccessListener { querySnapshot ->
            val transaksiList = querySnapshot.documents.mapNotNull { document ->
                val dateString = document.getString("tanggal") ?: ""
                val date = dateFormat.parse(dateString)
                document.toObject(Transaksi::class.java)?.apply {
                    this.date = date
                }
            }
            val sortedList = transaksiList.sortedBy { it.date }
            onResult(sortedList)
        }
        .addOnFailureListener { exception ->
            showToast(context, exception.toString())
            onError(exception)
        }
}

fun formatToRupiah(amount: Int): String {
    val localeID = Locale("in", "ID") // Indonesian locale
    val numberFormat =
        NumberFormat.getCurrencyInstance(localeID) // Currency formatter for Indonesia
    numberFormat.maximumFractionDigits = 0 // Remove decimal places
    return numberFormat.format(amount).replace("Rp ", "Rp")
}

fun formatTanggalBukuBesar(s: String): String? {
    val dateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
    val date = dateFormat.parse(s)
    return date?.let { dateFormat.format(it) }
}

fun formatNominal(amount: Int): String {
    val localeID = Locale("in", "ID") // Indonesian locale
    val numberFormat =
        NumberFormat.getCurrencyInstance(localeID) // Currency formatter for Indonesia
    numberFormat.maximumFractionDigits = 0 // Remove decimal places
    return numberFormat.format(amount).replace("Rp", "")
}

fun showToast(context: Context, s: String) {
    Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
}

fun fadeIn(view: View) {
    view.alpha = 0f
    view.visibility = View.VISIBLE
    ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).apply {
        duration = 600 // Duration of the animation in milliseconds
        start()
    }
}