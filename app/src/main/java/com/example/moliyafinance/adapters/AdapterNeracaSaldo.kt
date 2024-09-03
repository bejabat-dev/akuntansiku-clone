package com.example.moliyafinance.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moliyafinance.R
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.models.formatNominal

class AdapterNeracaSaldo(
    private val dataSet: List<String>,
    private val innerDataSet: List<List<Transaksi>>
) :
    RecyclerView.Adapter<AdapterNeracaSaldo.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val saldoDebit: TextView = v.findViewById(R.id.saldo_debit)
        val saldoKredit: TextView = v.findViewById(R.id.saldo_kredit)
        val judul: TextView = v.findViewById(R.id.judul)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_neraca_saldo, viewGroup, false)
        return ViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(v: ViewHolder, pos: Int) {
        val data = innerDataSet[pos]
        val s = dataSet[pos]
        v.judul.text = (s)
        var total = 0
        var sTotal = ""
        for (d in data) {
            if (d.debit == s) {
                total += d.nominal
            }
            if (d.kredit == s) {
                total -= d.nominal
            }
        }
        if (total < 0) {
            sTotal = formatNominal(total).replace("-", "")
            v.saldoKredit.text = sTotal
        } else {
            v.saldoDebit.text = formatNominal(total)
        }
    }

    override fun getItemCount() = dataSet.size
}