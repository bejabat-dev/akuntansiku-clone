package com.example.moliyafinance.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moliyafinance.R
import com.example.moliyafinance.models.PerubahanModal
import com.example.moliyafinance.models.formatToRupiah

class AdapterPerubahanModal(private val dataSet: List<PerubahanModal>) :
    RecyclerView.Adapter<AdapterPerubahanModal.ViewHolder>() {

    private var total = 0

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val namaAkun: TextView = v.findViewById(R.id.nama_akun)
        val nominal: TextView = v.findViewById(R.id.nominal)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_perubahan_modal, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun getTotal():Int{
        return total
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(v: ViewHolder, pos: Int) {
        val data = dataSet[pos]
        v.namaAkun.text = data.namaAkun
        if (data.debit == 0) {
            total += data.kredit
            v.nominal.text = formatToRupiah(data.kredit)
        } else {
            total -= data.debit
            val s = formatToRupiah(data.debit)
            v.nominal.text = "($s)"
        }
    }
}
