package com.example.moliyafinance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moliyafinance.R
import com.example.moliyafinance.Utils
import com.example.moliyafinance.models.Kewajiban

class AdapterKewajiban(private val dataSet: List<Kewajiban>) :
    RecyclerView.Adapter<AdapterKewajiban.ViewHolder>() {

    private var total = 0

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val namaAkun: TextView = v.findViewById(R.id.nama_akun)
        val nominal: TextView = v.findViewById(R.id.nominal)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_kewajiban, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(v: ViewHolder, pos: Int) {
        val dataSet = dataSet[pos]
        v.namaAkun.text = dataSet.namaAkun
        v.nominal.text = Utils().formatToRupiah(dataSet.nominal)
        total += dataSet.nominal
    }

    fun getTotal(): Int {
        return total
    }
}
