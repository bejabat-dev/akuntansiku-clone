package com.example.moliyafinance.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moliyafinance.R
import com.example.moliyafinance.laporan.Periode
import com.example.moliyafinance.models.Transaksi

class AdapterPeriode(
    private val context: Context,
    private val dataSet: List<Periode.Periode>
) :
    RecyclerView.Adapter<AdapterPeriode.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val innerRecyclerView: RecyclerView = v.findViewById(R.id.recycler)
        val kategori: TextView = v.findViewById(R.id.kategori)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_periode, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(v: ViewHolder, pos: Int) {
        val adapter = InnerAdapterPeriode(dataSet[pos].kategori,dataSet[pos].innerAdapter)
        v.kategori.text = dataSet[pos].kategori
        v.innerRecyclerView.adapter = adapter
        v.innerRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun getItemCount() = dataSet.size
}

class InnerAdapterPeriode(
    private val checker : String,
    private val dataSet: List<Transaksi>
) :
    RecyclerView.Adapter<InnerAdapterPeriode.ViewHolder>() {
        private var total = 0

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tanggal: TextView = view.findViewById(R.id.tanggal)
        val saldo: TextView = view.findViewById(R.id.saldo)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_periode, viewGroup, false)
        return ViewHolder(view)
    }

    fun getTotal():Int{
        return total
    }

    override fun onBindViewHolder(view: ViewHolder, pos: Int) {
        val data = dataSet[pos]
        if(data.debit==checker){
            total+=data.nominal
        }
        if(data.kredit==checker){
            total-=data.nominal
        }

    }

    override fun getItemCount() = dataSet.size
}