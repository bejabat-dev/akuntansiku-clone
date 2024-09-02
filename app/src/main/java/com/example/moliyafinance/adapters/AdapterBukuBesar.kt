package com.example.moliyafinance.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moliyafinance.R
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.models.formatToRupiah

class AdapterBukuBesar(
    private val dataSet: List<String>,
    private val innerDataSet: List<List<Transaksi>>
) :
    RecyclerView.Adapter<AdapterBukuBesar.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val r: RecyclerView = v.findViewById(R.id.recycler)
        val judul: TextView = v.findViewById(R.id.judul)
        val saldoAkhir: TextView = v.findViewById(R.id.saldo_akhir)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_buku_besar, viewGroup, false)
        return ViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(v: ViewHolder, pos: Int) {
        v.judul.text = dataSet[pos]
        val adapter = InnerAdapter(innerDataSet[pos], dataSet[pos])
        v.r.adapter = adapter
        v.r.layoutManager = LinearLayoutManager(v.itemView.context)
        v.r.setHasFixedSize(false)
        var total = 0
        for (data in innerDataSet[pos]) {
            if (data.debit == dataSet[pos]) {
                total += data.nominal
            } else {
                total -= data.nominal
            }
        }
        if (total < 0) {
            val newTotal = formatToRupiah(total).replace("-","")
            v.saldoAkhir.text = "(${newTotal})"
        } else {
            v.saldoAkhir.text = formatToRupiah(total)
        }
    }

    override fun getItemCount() = dataSet.size
}

class InnerAdapter(private val dataSet: List<Transaksi>, private val checker: String) :
    RecyclerView.Adapter<InnerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tanggal: TextView = view.findViewById(R.id.tanggal)
        val debit: TextView = view.findViewById(R.id.debit)
        val kredit: TextView = view.findViewById(R.id.kredit)
        val catatan: TextView = view.findViewById(R.id.catatan)
        val saldo: TextView = view.findViewById(R.id.saldo)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_buku_besar, viewGroup, false)
        return ViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(view: ViewHolder, pos: Int) {
        val transaksi = dataSet[pos]
        if (transaksi.debit == checker) {
            view.debit.text = transaksi.nominal.toString()
            view.saldo.text = transaksi.nominal.toString()
        }

        if (transaksi.kredit == checker) {
            view.kredit.text = "(${transaksi.nominal})"
            view.saldo.text = "(${transaksi.nominal})"
        }
        view.catatan.text = transaksi.catatan
        view.tanggal.text = transaksi.tanggal
    }

    override fun getItemCount() = dataSet.size
}