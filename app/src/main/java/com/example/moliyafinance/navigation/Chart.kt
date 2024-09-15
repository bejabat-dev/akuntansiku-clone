package com.example.moliyafinance.navigation

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moliyafinance.Colors
import com.example.moliyafinance.databinding.FragmentChartBinding
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.models.getMonth
import com.github.mikephil.charting.charts.ScatterChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.ScatterData
import com.github.mikephil.charting.data.ScatterDataSet
import com.github.mikephil.charting.formatter.ValueFormatter

class Chart : Fragment() {
    private lateinit var bind: FragmentChartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bind = FragmentChartBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initKasDanBank()
        initPiutang()
        initHutang()
    }

    private val bulan = listOf(
        "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"
    )

    private fun extractString(transaksiList: List<Transaksi>): List<String> {
        val debitValues = transaksiList.map { it.debit }
        val kreditValues = transaksiList.map { it.kredit }
        val combinedValues = debitValues + kreditValues
        return combinedValues.distinct()
    }

    private fun initKasDanBank() {
        val transaksiList = Dashboard.listTransaksi
        val entries = mutableListOf<Entry>()
        for (data in transaksiList){
            entries.add(Entry(getMonth(data.tanggal).toFloat(),data.nominal.toFloat()))
        }

        val dataset = ScatterDataSet(entries, "Bulan")
        dataset.scatterShapeSize = 10f
        dataset.color = Colors.blue
        dataset.setDrawValues(false)
        dataset.setScatterShape(ScatterChart.ScatterShape.CIRCLE)

        val data = ScatterData(dataset)

        bind.kasDanBank.data = data
        bind.kasDanBank.xAxis.setDrawGridLines(false)
        bind.kasDanBank.axisLeft.setDrawGridLines(false)
        bind.kasDanBank.axisRight.setDrawGridLines(false)
        bind.kasDanBank.axisRight.isEnabled = false
        bind.kasDanBank.xAxis.apply {
            setDrawGridLines(false)
            position = XAxis.XAxisPosition.BOTTOM
            granularity = 1f // Ensure x-axis values align with whole numbers
            axisMinimum = 1f // Start x-axis at 1 (January)
            axisMaximum = 12f // End x-axis at 12 (December)
            labelCount = 12 // Show all 12 months
            valueFormatter = object : ValueFormatter() {
                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                    return when (value.toInt()) {
                        1 -> "Jan"
                        2 -> "Feb"
                        3 -> "Mar"
                        4 -> "Apr"
                        5 -> "May"
                        6 -> "Jun"
                        7 -> "Jul"
                        8 -> "Agu"
                        9 -> "Sep"
                        10 -> "Okt"
                        11 -> "Nov"
                        12 -> "Des"
                        else -> ""
                    }
                }
            }
        }

        val description = Description().apply {
            text = ""
            textSize = 8f
            textColor = Color.BLACK
        }
        bind.kasDanBank.description = description


        bind.kasDanBank.axisLeft.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return when {
                    value >= 1_000_000 -> "${(value / 1_000_000).toInt()}jt"
                    value >= 1_000 -> "${(value / 1_000).toInt()}rb"
                    else -> value.toInt().toString()
                }
            }
        }

        bind.kasDanBank.invalidate()
    }

    private fun initPiutang() {
        val entries = mutableListOf<Entry>()
        entries.add(Entry(1f, 1500000f))
        entries.add(Entry(2f, 2500000f))
        entries.add(Entry(3f, 4000000f))
        entries.add(Entry(4f, 6700000f))
        entries.add(Entry(5f, 1250000f))
        entries.add(Entry(6f, 3400000f))

        val dataset = ScatterDataSet(entries, "Kas dan Bank")
        dataset.scatterShapeSize = 10f
        dataset.color = Colors.blue
        dataset.setDrawValues(false)
        dataset.setScatterShape(ScatterChart.ScatterShape.CIRCLE)

        val data = ScatterData(dataset)
        bind.piutang.data = data
        bind.piutang.xAxis.setDrawGridLines(false)
        bind.piutang.axisLeft.setDrawGridLines(false)
        bind.piutang.axisRight.setDrawGridLines(false)
        bind.piutang.axisRight.isEnabled = false
        bind.piutang.xAxis.position = XAxis.XAxisPosition.BOTTOM
        bind.piutang.xAxis.labelRotationAngle = 0f
        bind.piutang.setNoDataText("Tidak ada data")
        bind.piutang.setNoDataTextColor(Colors.blue)

        val description = Description().apply {
            text = ""
            textSize = 12f
            textColor = Color.BLACK
        }
        bind.piutang.description = description

        bind.piutang.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return when (value.toInt()) {
                    1 -> "Jan"
                    2 -> "Feb"
                    3 -> "Mar"
                    4 -> "Apr"
                    5 -> "May"
                    6 -> "Jun"
                    7 -> "Jul"
                    8 -> "Agu"
                    9 -> "Sep"
                    10 -> "Okt"
                    11 -> "Nov"
                    12 -> "Des"
                    else -> ""
                }
            }
        }
        bind.piutang.axisLeft.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return "${value.toInt()}"
            }
        }
        bind.piutang.invalidate()
    }

    private fun initHutang() {
        val entries = mutableListOf<Entry>()
        entries.add(Entry(1f, 1500000f))
        entries.add(Entry(2f, 2500000f))
        entries.add(Entry(3f, 4000000f))
        entries.add(Entry(4f, 6700000f))
        entries.add(Entry(5f, 1250000f))
        entries.add(Entry(6f, 3400000f))

        val dataset = ScatterDataSet(entries, "Kas dan Bank")
        dataset.scatterShapeSize = 10f
        dataset.color = Colors.blue
        dataset.setDrawValues(false)
        dataset.setScatterShape(ScatterChart.ScatterShape.CIRCLE)

        val data = ScatterData(dataset)
        bind.hutang.data = data
        bind.hutang.xAxis.setDrawGridLines(false)
        bind.hutang.axisLeft.setDrawGridLines(false)
        bind.hutang.axisRight.setDrawGridLines(false)
        bind.hutang.axisRight.isEnabled = false
        bind.hutang.xAxis.position = XAxis.XAxisPosition.BOTTOM
        bind.hutang.xAxis.labelRotationAngle = 0f
        bind.hutang.setNoDataText("Tidak ada data")
        bind.hutang.setNoDataTextColor(Colors.blue)

        val description = Description().apply {
            text = ""
            textSize = 12f
            textColor = Color.BLACK
        }
        bind.hutang.description = description

        bind.hutang.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return when (value.toInt()) {
                    1 -> "Jan"
                    2 -> "Feb"
                    3 -> "Mar"
                    4 -> "Apr"
                    5 -> "May"
                    6 -> "Jun"
                    7 -> "Jul"
                    8 -> "Agu"
                    9 -> "Sep"
                    10 -> "Okt"
                    11 -> "Nov"
                    12 -> "Des"
                    else -> ""
                }
            }
        }
        bind.hutang.axisLeft.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return "${value.toInt()}"
            }
        }
        bind.hutang.invalidate()
    }
}