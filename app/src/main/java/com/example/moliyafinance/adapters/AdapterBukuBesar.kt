package com.example.moliyafinance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moliyafinance.R
import com.example.moliyafinance.models.Transaksi

data class TransactionGroup(
    val header: String,
    val transactions: List<Transaksi>
)


class DebitAdapter(private val groups: List<TransactionGroup>) :
    RecyclerView.Adapter<DebitAdapter.DebitViewHolder>() {

    class DebitViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val headerTextView: TextView = view.findViewById(R.id.debitHeaderTextView)
        val nominalTextView: TextView = view.findViewById(R.id.debitNominalTextView)
        val jenisTransaksiTextView: TextView = view.findViewById(R.id.debitJenisTransaksiTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_debit, parent, false)
        return DebitViewHolder(view)
    }

    override fun onBindViewHolder(holder: DebitViewHolder, position: Int) {
        val group = groups[position]
        holder.headerTextView.text = group.header
        if (group.transactions.isNotEmpty()) {
            val transaction = group.transactions.first()
            holder.nominalTextView.text = transaction.nominal.toString()
            holder.jenisTransaksiTextView.text = transaction.jenisTransaksi
        }
    }

    override fun getItemCount() = groups.size
}

class CreditAdapter(private val groups: List<TransactionGroup>) :
    RecyclerView.Adapter<CreditAdapter.CreditViewHolder>() {

    class CreditViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val headerTextView: TextView = view.findViewById(R.id.creditHeaderTextView)
        val nominalTextView: TextView = view.findViewById(R.id.creditNominalTextView)
        val jenisTransaksiTextView: TextView = view.findViewById(R.id.creditJenisTransaksiTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_credit, parent, false)
        return CreditViewHolder(view)
    }

    override fun onBindViewHolder(holder: CreditViewHolder, position: Int) {
        val group = groups[position]
        holder.headerTextView.text = group.header
        if (group.transactions.isNotEmpty()) {
            val transaction = group.transactions.first()
            holder.nominalTextView.text = transaction.nominal.toString()
            holder.jenisTransaksiTextView.text = transaction.jenisTransaksi
        }
    }

    override fun getItemCount() = groups.size
}


class CombinedAdapter(
    private val debitGroups: List<TransactionGroup>,
    private val creditGroups: List<TransactionGroup>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_DEBIT_HEADER = 0
        private const val VIEW_TYPE_CREDIT_HEADER = 1
        private const val VIEW_TYPE_TRANSACTION = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position < debitGroups.size -> VIEW_TYPE_DEBIT_HEADER
            position < debitGroups.size + creditGroups.size -> VIEW_TYPE_CREDIT_HEADER
            else -> VIEW_TYPE_TRANSACTION
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_DEBIT_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_debit, parent, false)
                DebitAdapter.DebitViewHolder(view)
            }
            VIEW_TYPE_CREDIT_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_credit, parent, false)
                CreditAdapter.CreditViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_DEBIT_HEADER -> {
                val group = debitGroups[position]
                (holder as DebitAdapter.DebitViewHolder).apply {
                    headerTextView.text = group.header
                    if (group.transactions.isNotEmpty()) {
                        val transaction = group.transactions.first()
                        nominalTextView.text = transaction.nominal.toString()
                        jenisTransaksiTextView.text = transaction.jenisTransaksi
                    }
                }
            }
            VIEW_TYPE_CREDIT_HEADER -> {
                val group = creditGroups[position - debitGroups.size]
                (holder as CreditAdapter.CreditViewHolder).apply {
                    headerTextView.text = group.header
                    if (group.transactions.isNotEmpty()) {
                        val transaction = group.transactions.first()
                        nominalTextView.text = transaction.nominal.toString()
                        jenisTransaksiTextView.text = transaction.jenisTransaksi
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return debitGroups.size + creditGroups.size
    }
}
