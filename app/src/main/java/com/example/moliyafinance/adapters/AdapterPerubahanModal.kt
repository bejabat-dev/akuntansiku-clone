package com.example.moliyafinance.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moliyafinance.R

class AdapterPerubahanModal(private val dataSet: List<String>) :
    RecyclerView.Adapter<AdapterPerubahanModal.ViewHolder>() {

    private var total = 0

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val namaAkun: TextView = v.findViewById(R.id.nama_akun)
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
        v.namaAkun.text = dataSet[pos]
    }
}
