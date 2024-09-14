package com.example.moliyafinance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moliyafinance.R
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.models.formatToRupiah

class AdapterLabaRugi {
    class AdapterPendapatan(
        private val dataSet: List<Transaksi>
    ) : RecyclerView.Adapter<AdapterPendapatan.ViewHolder>() {
        private var total = 0

        inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val akun: TextView = v.findViewById(R.id.akun)
            val nominal: TextView = v.findViewById(R.id.nominal)
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recycler_laba_rugi, viewGroup, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(v: ViewHolder, position: Int) {
            val data = dataSet[position]
            val nama = data.nomorKredit + " " + data.kredit
            val rp = formatToRupiah(data.nominal)
            v.akun.text = nama
            v.nominal.text = rp

            total += data.nominal
        }

        fun getTotal(): Int {
            return total
        }

        override fun getItemCount() = dataSet.size
    }
}