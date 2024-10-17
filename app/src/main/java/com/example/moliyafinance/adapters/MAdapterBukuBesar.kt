package com.example.moliyafinance.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moliyafinance.R
import com.example.moliyafinance.Utils
import com.example.moliyafinance.models.Transaksi

class MAdapterBukuBesar{
    class AdapterBukuBesar(
        private val dataSet: List<String>,
        private val innerDataSet: List<List<Transaksi>>
    ) :
        RecyclerView.Adapter<AdapterBukuBesar.ViewHolder>() {

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val r: RecyclerView = v.findViewById(R.id.recycler)
            val judul: TextView = v.findViewById(R.id.judul)
            val saldoAkhir: TextView = v.findViewById(R.id.saldo_akhir)
            val nomorAkun: TextView = v.findViewById(R.id.nomor_akun)
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
                    v.nomorAkun.text = data.nomorDebit
                } else {
                    total -= data.nominal
                    v.nomorAkun.text = data.nomorKredit
                }
            }
            if (total < 0) {
                val newTotal = Utils().formatToRupiah(total).replace("-", "")
                v.saldoAkhir.text = newTotal
            } else {
                v.saldoAkhir.text = Utils().formatToRupiah(total)
            }
        }

        override fun getItemCount() = dataSet.size
    }

    class InnerAdapter(private val dataSet: List<Transaksi>, private val checker: String) :
        RecyclerView.Adapter<InnerAdapter.ViewHolder>() {

        private var saldo = 0

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
                saldo += transaksi.nominal
                view.debit.text = transaksi.nominal.toString().replace("-", "")
            }
            if (transaksi.kredit == checker) {
                saldo -= transaksi.nominal
                view.kredit.text = transaksi.nominal.toString().replace("-", "")
            }
            if (saldo < 0) {
                val totalSaldo = saldo.toString().replace("-", "")
                view.saldo.text = totalSaldo
            } else {
                view.saldo.text = saldo.toString().replace("-", "")
            }
            view.catatan.text = transaksi.catatan
            view.tanggal.text = Utils().formatTanggalBukuBesar(transaksi.tanggal)
        }

        override fun getItemCount() = dataSet.size
    }
}