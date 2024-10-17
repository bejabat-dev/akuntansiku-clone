package com.example.moliyafinance

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.moliyafinance.databinding.DialogTanggalBinding
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.navigation.Dashboard
import com.example.moliyafinance.objects.LoadingDialog
import com.example.moliyafinance.objects.User
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Utils {
    private val calendar = Calendar.getInstance()

    fun showDateDialog(
        context: Context,
        dialogBinding: DialogTanggalBinding
    ) {
        val calendar = java.util.Calendar.getInstance()

        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        var startDateTimestamp = Timestamp(calendar.time)

        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)

        var endDateTimestamp = Timestamp(calendar.time)

        val b = AlertDialog.Builder(context)
        b.setView(dialogBinding.root)

        dialogBinding.start.init(
            dialogBinding.start.year, dialogBinding.start.month, dialogBinding.start.dayOfMonth
        ) { _, year, month, day ->
            calendar.set(year, month, day, 0, 0, 0) // Set time to 00:00:00 for start of day
            startDateTimestamp = Timestamp(calendar.time)
        }

        dialogBinding.end.init(
            dialogBinding.end.year, dialogBinding.end.month, dialogBinding.end.dayOfMonth
        ) { _, year, month, day ->
            calendar.set(year, month, day, 23, 59, 59) // Set time to 23:59:59 for end of day
            endDateTimestamp = Timestamp(calendar.time)
        }

        val dialog = b.create()
        dialog.show()

        dialogBinding.simpan.setOnClickListener {
            Utils().getFilteredTransaksi(
                context,
                startDateTimestamp,
                endDateTimestamp
            )
            println("Start: $startDateTimestamp End: $endDateTimestamp")
            dialog.dismiss()
        }
    }

    fun showDatePickerDialog(context: Context, textView: TextView) {
        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
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
        val dateFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
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
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val startOfMonth = Timestamp(calendar.time)
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))

        val endOfMonth = Timestamp(calendar.time)
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val db = FirebaseFirestore.getInstance().collection("Transactions")

        db.whereEqualTo("uid", User.userData.uid)
            .whereGreaterThanOrEqualTo("timestamp", startOfMonth)
            .whereLessThanOrEqualTo("timestamp", endOfMonth).get()
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

    @SuppressLint("NotifyDataSetChanged")
    fun getFilteredTransaksi(
        context: Context,
        start: Timestamp, end: Timestamp
    ) {
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val db = FirebaseFirestore.getInstance().collection("Transactions")

        db.whereEqualTo("uid", User.userData.uid).whereGreaterThanOrEqualTo("timestamp", start)
            .whereLessThanOrEqualTo("timestamp", end).get()
            .addOnSuccessListener { querySnapshot ->
                val transaksiList = querySnapshot.documents.mapNotNull { document ->
                    val dateString = document.getString("tanggal") ?: ""
                    val date = dateFormat.parse(dateString)
                    document.toObject(Transaksi::class.java)?.apply {
                        this.date = date
                    }
                }
                Dashboard.listTransaksi = transaksiList
                val activity = (context as? Activity)
                activity?.recreate()
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