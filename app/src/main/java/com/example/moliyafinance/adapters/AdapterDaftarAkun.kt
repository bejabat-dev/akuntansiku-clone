package com.example.moliyafinance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moliyafinance.R
import com.example.moliyafinance.Variables

class AdapterDaftarAkun(private val dataSet: List<Variables.DataAkun>) :
    RecyclerView.Adapter<AdapterDaftarAkun.ViewHolder>() {
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val namaAkun: TextView = v.findViewById(R.id.nama_akun)
        val nomorAkun: TextView = v.findViewById(R.id.nomor_akun)
        val kategori: TextView = v.findViewById(R.id.kategori)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_daftar_akun, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(v: ViewHolder, pos: Int) {
        val data = dataSet[pos]
        v.namaAkun.text = data.jenis
        v.nomorAkun.text = data.nomor
        v.kategori.text = data.kategori
    }
}
