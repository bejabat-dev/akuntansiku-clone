package com.example.moliyafinance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moliyafinance.R
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.models.formatToRupiah

class AdapterJurnalUmum(private val dataSet: List<Transaksi>) :
    RecyclerView.Adapter<AdapterJurnalUmum.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val jenisTransaksi : TextView = v.findViewById(R.id.jenisTransaksi)
        val catatan : TextView = v.findViewById(R.id.catatan)
        val debit : TextView = v.findViewById(R.id.debit)
        val nomorDebit : TextView = v.findViewById(R.id.nomorDebit)
        val kredit : TextView = v.findViewById(R.id.kredit)
        val nomorKredit : TextView = v.findViewById(R.id.nomorKredit)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_jurnal_umum, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(v: ViewHolder, pos: Int) {
        val nominal = formatToRupiah(dataSet[pos].nominal)
        v.jenisTransaksi.text = dataSet[pos].jenisTransaksi
        v.catatan.text = dataSet[pos].catatan
        v.debit.text = dataSet[pos].debit
        v.nomorKredit.text = dataSet[pos].kredit
        v.nomorDebit.text = dataSet[pos].debit
        v.kredit.text = dataSet[pos].kredit
    }

    override fun getItemCount() = dataSet.size

}
