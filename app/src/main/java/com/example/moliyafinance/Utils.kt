package com.example.moliyafinance

import android.animation.ObjectAnimator
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.moliyafinance.adapters.AdapterTransaksi
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.models.User
import com.example.moliyafinance.navigation.Dashboard
import com.example.moliyafinance.navigation.Home
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Utils {
    private val calendar = Calendar.getInstance()

    fun showDatePickerDialog(context: Context, textView: TextView) {
        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                textView.text = dateFormat.format(calendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    fun showTimePickerDialog(context: Context, textView: TextView) {
        val timePickerDialog = TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)

                val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                textView.text = timeFormat.format(calendar.time)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.show()
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
            "kategoriDebit" to transaksi.kategoriDebit,
            "kategoriKredit" to transaksi.kategoriKredit,
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
            "nominal" to transaksi.nominal,
            "kategoriDebit" to transaksi.kategoriDebit,
            "kategoriKredit" to transaksi.kategoriKredit,
            "timestamp" to createTimestamp(transaksi.tanggal, transaksi.waktu)
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

    fun getFilteredTransaksi(
        context: Context,
        start: Timestamp, end: Timestamp, adapter: AdapterTransaksi
    ) {

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val db = FirebaseFirestore.getInstance().collection("Transactions")

        db.whereEqualTo("uid", User.userData.uid).whereGreaterThanOrEqualTo("timestamp", start)
            .whereLessThanOrEqualTo("timestamp", end).get()
            .addOnSuccessListener { querySnapshot ->
                val transaksiList = querySnapshot.documents.mapNotNull { document ->
                    val dateString = document.getString("tanggal") ?: ""
                    println(dateString)
                    val date = dateFormat.parse(dateString)
                    document.toObject(Transaksi::class.java)?.apply {
                        this.date = date
                    }
                }
                val sortedList = transaksiList.sortedBy { it.date }
                Dashboard.listTransaksi = sortedList
                Home().setRecyclerAdapter(sortedList)
            }
            .addOnFailureListener { exception ->
                showToast(context, exception.toString())
                println(exception.toString())
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

    fun getMonth(date: String): String {
        return date.substring(3, 5)
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
}