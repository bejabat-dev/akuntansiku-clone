package com.example.moliyafinance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moliyafinance.R
import com.example.moliyafinance.objects.Variables


class AdapterDataAkun(
    private val dataSet: List<Variables.DataAkun>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<AdapterDataAkun.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: Variables.DataAkun)
    }

    inner class ViewHolder(view: View, private val itemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(view) {
        val namaAkun: TextView = view.findViewById(R.id.nama_akun)
        val kategori: TextView = view.findViewById(R.id.kategori)

        init {
            view.setOnClickListener {
                itemClickListener.onItemClick(dataSet[adapterPosition]) //dataSet undefined
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_data_akun, viewGroup, false)
        return ViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(v: ViewHolder, position: Int) {
        val namaText = dataSet[position].nomor + " | " + dataSet[position].nama
        v.namaAkun.text = namaText
        v.kategori.text = dataSet[position].kategori
    }

    override fun getItemCount() = dataSet.size
}
