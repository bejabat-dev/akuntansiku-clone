package com.example.moliyafinance

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.widget.TextView
import java.text.SimpleDateFormat
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
                // Update the calendar with the selected time
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)

                // Format and display the selected time
                val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                textView.text = timeFormat.format(calendar.time)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true // Use 24-hour format
        )
        timePickerDialog.show()
    }
}