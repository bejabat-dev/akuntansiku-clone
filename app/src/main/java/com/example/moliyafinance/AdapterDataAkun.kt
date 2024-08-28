package com.example.moliyafinance

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList


class AdapterDataAkun(private val dataSet: ArrayList<Variables.DataAkun>) :
    RecyclerView.Adapter<AdapterDataAkun.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val jenis: TextView = view.findViewById(R.id.jenis)
        val kategori: TextView = view.findViewById(R.id.kategori)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_data_akun, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(v: ViewHolder, position: Int) {
        val jenisText = dataSet[position].nomor + " | "+dataSet[position].jenis
        v.jenis.text = jenisText
        v.kategori.text = dataSet[position].kategori
    }

    override fun getItemCount() = dataSet.size

}
