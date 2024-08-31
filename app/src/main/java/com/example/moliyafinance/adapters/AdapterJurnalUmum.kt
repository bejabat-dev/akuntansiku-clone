package com.example.moliyafinance.adapters

import android.annotation.SuppressLint
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
    private var totalDebit = 0
    private var totalKredit = 0

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val jenisTransaksi: TextView = v.findViewById(R.id.jenisTransaksi)
        val catatan: TextView = v.findViewById(R.id.catatan)
        val debit: TextView = v.findViewById(R.id.debit)
        val nominalDebit: TextView = v.findViewById(R.id.nominalDebit)
        val nomorDebit: TextView = v.findViewById(R.id.nomorDebit)
        val nominalKredit: TextView = v.findViewById(R.id.nominalKredit)
        val kredit: TextView = v.findViewById(R.id.kredit)
        val nomorKredit: TextView = v.findViewById(R.id.nomorKredit)
        val tanggal: TextView = v.findViewById(R.id.tanggal)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_jurnal_umum, viewGroup, false)
        return ViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(v: ViewHolder, pos: Int) {
        val data = dataSet[pos]
        totalDebit += data.nominal
        totalKredit += data.nominal
        val nominal = formatToRupiah(data.nominal)
        v.jenisTransaksi.text = data.jenisTransaksi
        v.catatan.text = data.catatan
        v.debit.text = "${data.debit} (D)"
        v.kredit.text = "${data.kredit} (C)"
        v.nomorKredit.text = data.nomorKredit
        v.nomorDebit.text = data.nomorDebit
        v.nominalDebit.text = nominal
        v.nominalKredit.text = nominal
        v.tanggal.text = data.tanggal
    }

    override fun getItemCount() = dataSet.size
    fun getTotalDebit(): Int {
        return totalDebit
    }

    fun getTotalKredit(): Int {
        return totalKredit
    }

}
