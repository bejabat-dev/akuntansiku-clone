package com.example.moliyafinance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moliyafinance.R
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.models.formatToRupiah


class AdapterTransaksi(private val dataSet: List<Transaksi>) :
    RecyclerView.Adapter<AdapterTransaksi.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val catatan: TextView = view.findViewById(R.id.catatan)
        val nominal: TextView = view.findViewById(R.id.nominal)
        val tanggal: TextView = view.findViewById(R.id.tanggal)
        val jenisTransaksi: TextView = view.findViewById(R.id.jenisTransaksi)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_home, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(v: ViewHolder, pos: Int) {
        val nominal = formatToRupiah(dataSet[pos].nominal)
        v.catatan.text = dataSet[pos].catatan
        v.nominal.text = nominal
        v.tanggal.text = dataSet[pos].tanggal
        v.jenisTransaksi.text = dataSet[pos].jenisTransaksi
    }

    override fun getItemCount() = dataSet.size

}
