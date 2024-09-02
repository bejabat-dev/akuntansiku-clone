package com.example.moliyafinance.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moliyafinance.R
import com.example.moliyafinance.models.Transaksi

class AdapterBukuBesar(
    private val context: Context,
    private val dataSet: ArrayList<HashMap<String, List<Transaksi>>>,
    private val keys: List<String>
) :
    RecyclerView.Adapter<AdapterBukuBesar.ViewHolder>() {
    private var totalDebit = 0
    private var totalKredit = 0

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val r: RecyclerView = v.findViewById(R.id.recycler)
        val judul: TextView = v.findViewById(R.id.judul)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_buku_besar, viewGroup, false)
        return ViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(v: ViewHolder, pos: Int) {
        val adapter = InnerAdapter(dataSet[pos][keys[pos]]!!)
        v.r.adapter = adapter
        v.r.layoutManager = LinearLayoutManager(context)
        v.judul.text = keys[pos]
    }

    override fun getItemCount() = dataSet.size
    fun getTotalDebit(): Int {
        return totalDebit
    }

    fun getTotalKredit(): Int {
        return totalKredit
    }

}

class InnerAdapter(private val dataSet: List<Transaksi>) :
    RecyclerView.Adapter<InnerAdapter.ViewHolder>() {
    private var totalDebit = 0
    private var totalKredit = 0

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tanggal: TextView = v.findViewById(R.id.tanggal)
        val debit: TextView = v.findViewById(R.id.debit)
        val kredit: TextView = v.findViewById(R.id.kredit)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_buku_besar, viewGroup, false)
        return ViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(v: ViewHolder, pos: Int) {
        v.tanggal.text = dataSet[pos].tanggal
        v.debit.text = dataSet[pos].catatan
    }

    override fun getItemCount() = dataSet.size
    fun getTotalDebit(): Int {
        return totalDebit
    }

    fun getTotalKredit(): Int {
        return totalKredit
    }

}