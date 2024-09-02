package com.example.moliyafinance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moliyafinance.R
import com.example.moliyafinance.models.Transaksi

fun getDistinctValues(transaksiList: List<Transaksi>): List<String> {
    val debitValues = transaksiList.map { it.debit }
    val kreditValues = transaksiList.map { it.kredit }
    val allValues = debitValues + kreditValues
    return allValues.distinct()
}

fun groupTransaksiByValue(transaksiList: List<Transaksi>, distinctValues: List<String>): Map<String, List<Transaksi>> {
    val groupedData = mutableMapOf<String, MutableList<Transaksi>>()

    distinctValues.forEach { value ->
        groupedData[value] = transaksiList.filter {
            it.debit == value || it.kredit == value
        }.toMutableList()
    }

    return groupedData
}

class TransaksiAdapter(private val groupedData: Map<String, List<Transaksi>>) : RecyclerView.Adapter<TransaksiAdapter.TransaksiViewHolder>() {

    inner class TransaksiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(transaksiList: List<Transaksi>, groupName: String) {
            val groupNameTextView = itemView.findViewById<TextView>(R.id.textViewGroupName)
            val transaksiRecyclerView = itemView.findViewById<RecyclerView>(R.id.recyclerViewTransaksi)

            groupNameTextView.text = groupName
            transaksiRecyclerView.adapter = TransaksiListAdapter(transaksiList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransaksiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaksi_group, parent, false)
        return TransaksiViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransaksiViewHolder, position: Int) {
        val groupName = groupedData.keys.elementAt(position)
        val transaksiList = groupedData[groupName] ?: emptyList()
        holder.bind(transaksiList, groupName)
    }

    override fun getItemCount(): Int {
        return groupedData.size
    }
}

class TransaksiListAdapter(private val transaksiList: List<Transaksi>) : RecyclerView.Adapter<TransaksiListAdapter.TransaksiListViewHolder>() {

    inner class TransaksiListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(transaksi: Transaksi) {
            val debitTextView = itemView.findViewById<TextView>(R.id.textViewDebit)
            val kreditTextView = itemView.findViewById<TextView>(R.id.textViewKredit)
            val nominalTextView = itemView.findViewById<TextView>(R.id.textViewNominal)

            debitTextView.text = transaksi.debit
            kreditTextView.text = transaksi.kredit
            nominalTextView.text = transaksi.nominal.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransaksiListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaksi_detail, parent, false)
        return TransaksiListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransaksiListViewHolder, position: Int) {
        val transaksi = transaksiList[position]
        holder.bind(transaksi)
    }

    override fun getItemCount(): Int {
        return transaksiList.size
    }
}