package com.example.moliyafinance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moliyafinance.R
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.models.formatToRupiah

class AdapterBebanOperasional(private val dataSet: List<Transaksi>) :
    RecyclerView.Adapter<AdapterBebanOperasional.ViewHolder>() {

    private var total = 0

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val akun: TextView = v.findViewById(R.id.akun)
        val nominal: TextView = v.findViewById(R.id.nominal)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_beban_operasional, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(v: ViewHolder, pos: Int) {
        val data = dataSet[pos]
        v.akun.text = data.debit
        v.nominal.text = formatToRupiah(data.nominal)
        total += data.nominal
    }

    fun getTotal(): Int {
        return total
    }
}
