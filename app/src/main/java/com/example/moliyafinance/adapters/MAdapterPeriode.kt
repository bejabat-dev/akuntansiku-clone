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
import com.example.moliyafinance.Utils
import com.example.moliyafinance.models.InnerPeriode
import com.example.moliyafinance.models.Periode

class MAdapterPeriode {

    class AdapterPeriode(
        private val context: Context,
        private val dataSet: List<Periode>,
    ) : RecyclerView.Adapter<AdapterPeriode.ViewHolder>() {

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val innerRecyclerView: RecyclerView = v.findViewById(R.id.recycler)
            val namaAkun: TextView = v.findViewById(R.id.kategori)
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val view =
                LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.recycler_periode,
                        viewGroup,
                        false
                    )
            return ViewHolder(view)
        }

        override fun onBindViewHolder(v: ViewHolder, pos: Int) {
            val dataSet = dataSet[pos]
            v.namaAkun.text = dataSet.namaAkun
            val listPeriode = ArrayList<String>()
            for (data in dataSet.innerList) {
                if(data.kategoriDebit == dataSet.namaAkun) {
                    listPeriode.add(data.debit)
                }
                if(data.kategoriKredit == dataSet.namaAkun) {
                    listPeriode.add(data.kredit)
                }
            }
            val innerArrayPeriode = ArrayList<InnerPeriode>()
            for (data in listPeriode.distinct()) {
                var total = 0
                var nomor = ""
                for (newData in dataSet.innerList) {
                    if(newData.debit == data) {
                        total += newData.nominal
                        nomor = newData.nomorDebit
                    }
                    if(newData.kredit == data) {
                        total -= newData.nominal
                        nomor = newData.nomorKredit
                    }
                }
                val innerPeriode=InnerPeriode(nomor,data,total)
                innerArrayPeriode.add(innerPeriode)
            }

            val innerAdapter = InnerAdapterPeriode(innerArrayPeriode)
            v.innerRecyclerView.adapter = innerAdapter
            v.innerRecyclerView.layoutManager = LinearLayoutManager(context)
        }

        override fun getItemCount() = dataSet.size
    }

    class InnerAdapterPeriode(
        private val dataSet: List<InnerPeriode>,
    ) : RecyclerView.Adapter<InnerAdapterPeriode.ViewHolder>() {
        private var total = 0

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val namaAkun: TextView = view.findViewById(R.id.nama_akun)
            val nominal: TextView = view.findViewById(R.id.nominal)
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val view =
                LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.item_periode,
                        viewGroup,
                        false
                    )
            return ViewHolder(view)
        }

        fun getTotal(): Int {
            return total
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(view: ViewHolder, pos: Int) {
            val data = dataSet[pos]
            view.namaAkun.text = "${data.nomor} | ${data.namaAkun}"
            view.nominal.text = Utils().formatToRupiah(data.nominal)
        }

        override fun getItemCount() = dataSet.size
    }
}